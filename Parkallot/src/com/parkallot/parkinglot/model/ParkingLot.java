package com.parkallot.parkinglot.model;

public class ParkingLot {
	private int id;
	private String name;
	private String address;
	private float lat;
	private float lng;
	private int slot_count;
	private float cost_per_hour;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLng() {
		return lng;
	}
	public void setLng(float lng) {
		this.lng = lng;
	}
	public int getSlot_count() {
		return slot_count;
	}
	public void setSlot_count(int slot_count) {
		this.slot_count = slot_count;
	}
	public float getCost_per_hour() {
		return cost_per_hour;
	}
	public void setCost_per_hour(float cost_per_hour) {
		this.cost_per_hour = cost_per_hour;
	}
	
}
