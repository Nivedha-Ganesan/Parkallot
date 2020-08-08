<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.parkallot.parkinglot.controller.GetParkingLots" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Map</title>
<meta name="viewport" content="initial-scale=1,maximum-scale=1,user-scalable=no" />
<script src="https://api.mapbox.com/mapbox-gl-js/v1.11.1/mapbox-gl.js"></script>
<link href="https://api.mapbox.com/mapbox-gl-js/v1.11.1/mapbox-gl.css" rel="stylesheet" />
<style>
	body { margin: 0; padding: 0; }
	#map { position: absolute; top: 0; bottom: 0; width: 100%; }
</style>
</head>
<body>
<script src="https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-geocoder/v4.5.1/mapbox-gl-geocoder.min.js"></script>
<link
	rel="stylesheet"
	href="https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-geocoder/v4.5.1/mapbox-gl-geocoder.css"
	type="text/css"
/>
<!-- Promise polyfill script required to use Mapbox GL Geocoder in IE 11 -->
<script src="https://cdn.jsdelivr.net/npm/es6-promise@4/dist/es6-promise.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/es6-promise@4/dist/es6-promise.auto.min.js"></script>
<div id="map"></div>
<script>
	mapboxgl.accessToken = 'pk.eyJ1Ijoibml2ZWRoYS1nYW5lc2FuIiwiYSI6ImNrZGZxN2picjJ0ZHoycHNjcHFzdTY4bjcifQ.tVnRi4hx0bhYYJDwo9kqFQ';
	var map = new mapboxgl.Map({
		container: 'map',
		style: 'mapbox://styles/mapbox/streets-v11',
		center: [-87.6244, 41.8756],
		zoom: 13
	});
	
	var parkingLots = <%= GetParkingLots.getAllLots() %>
	
	function forwardGeocoder(query) {
		var matchingFeatures = [];
		for (var i = 0; i < parkingLots.length; i++) {
			var feature = parkingLots[i];
			// handle queries with different capitalization than the source data by calling toLowerCase()
			if (
			feature.name
			.toLowerCase()
			.search(query.toLowerCase()) !== -1
		) {
		// add a tree emoji as a prefix for custom data results
		// using carmen geojson format: https://github.com/mapbox/carmen/blob/master/carmen-geojson.md
			f = {};
			f['place_name'] = '🚘 ' + feature.name;
			f['center'] = [feature.lng, feature.lat];
			f['place_type'] = ['parking lot'];
			matchingFeatures.push(f);
		  }
		}
		return matchingFeatures;
	}
	
	//display a popup on hover
	var pLotFeatures = [];
	for (var i = 0; i < parkingLots.length; i++) {
		var feature = {
			'type': 'Feature',
			'properties': {
				'description':
					'<strong>' + parkingLots[i].name + '</strong><p>' + 
					parkingLots[i].address + '</p><br> Cost per hour: ' + 
					parkingLots[i].cost_per_hour + '<br> Slots available: ' +
					parkingLots[i].slot_count
			},
			'geometry': {
				'type': 'Point',
				'coordinates': [parkingLots[i].lng, parkingLots[i].lat]
			}
		}
		pLotFeatures.push(feature);
	}
	
	map.on('load', function() {
		map.loadImage(
			'https://docs.mapbox.com/mapbox-gl-js/assets/custom_marker.png',
			// Add an image to use as a custom marker
			function(error, image) {
				if (error) throw error;
				map.addImage('custom-marker', image);
				 
				map.addSource('places', {
					'type': 'geojson',
					'data': {
						'type': 'FeatureCollection',
						'features': pLotFeatures
					}
				});
				 
				// Add a layer showing the places.
				map.addLayer({
					'id': 'places',
					'type': 'symbol',
					'source': 'places',
					'layout': {
						'icon-image': 'custom-marker',
						'icon-allow-overlap': true
					}
				});
			}
		);
		 
		// Create a popup, but don't add it to the map yet.
		var popup = new mapboxgl.Popup({
			closeButton: false,
			closeOnClick: false
		});
		 
		map.on('mouseenter', 'places', function(e) {
			// Change the cursor style as a UI indicator.
			map.getCanvas().style.cursor = 'pointer';
			 
			var coordinates = e.features[0].geometry.coordinates.slice();
			var description = e.features[0].properties.description;
			 
			// Ensure that if the map is zoomed out such that multiple
			// copies of the feature are visible, the popup appears
			// over the copy being pointed to.
			while (Math.abs(e.lngLat.lng - coordinates[0]) > 180) {
				coordinates[0] += e.lngLat.lng > coordinates[0] ? 360 : -360;
			}
			 
			// Populate the popup and set its coordinates
			// based on the feature found.
			popup
			.setLngLat(coordinates)
			.setHTML(description)
			.addTo(map);
		});
		 
		map.on('mouseleave', 'places', function() {
			map.getCanvas().style.cursor = '';
			popup.remove();
		});
	});
	
	//navigate to a place
	map.addControl(
		new MapboxGeocoder({
			accessToken: mapboxgl.accessToken,
			localGeocoder: forwardGeocoder,
			zoom: 14,
			placeholder: 'Search for a location',
			mapboxgl: mapboxgl
		})
	);
	
	// Add geolocate control to the map.
	map.addControl(
		new mapboxgl.GeolocateControl({
		positionOptions: {
			enableHighAccuracy: true
		},
		trackUserLocation: true
		})
	);
</script>
 
</body>
</html>