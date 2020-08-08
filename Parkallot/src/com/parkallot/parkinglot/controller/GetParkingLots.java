package com.parkallot.parkinglot.controller;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkallot.parkinglot.dao.ParkingLotDao;
import com.parkallot.parkinglot.model.ParkingLot;

public class GetParkingLots {
	static ParkingLotDao parkingLotDao = new ParkingLotDao();
	public static List <String> getAllLots() throws ClassNotFoundException {
		List <String> p_lot = new ArrayList<>();
		try {
			List <ParkingLot> parking_lots = parkingLotDao.getAll();
			ObjectMapper obj = new ObjectMapper();
			for (ParkingLot p : parking_lots) {
	            // get Organization object as a json string 
	            String jsonStr = obj.writeValueAsString(p); 
	  
	            // Displaying JSON String 
	            p_lot.add(jsonStr);
			}
		}
		catch (Exception e) { 
            e.printStackTrace(); 
        }
		//System.out.println(p_lot.size());
		return p_lot;
	}
}
