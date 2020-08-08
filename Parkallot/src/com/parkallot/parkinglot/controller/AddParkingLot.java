package com.parkallot.parkinglot.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.parkallot.parkinglot.dao.ParkingLotDao;
import com.parkallot.parkinglot.model.ParkingLot;

/**
 * Servlet implementation class AddParkingLot
 */
@WebServlet("/AddParkingLot")
public class AddParkingLot extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ParkingLotDao parkingLotDao = new ParkingLotDao();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("p_lot_name");
		String address = request.getParameter("address");
		String lng = request.getParameter("longitude");
		String lat = request.getParameter("latitude");
		String slot_count = request.getParameter("slot_count");
		String cost_per_hour = request.getParameter("cost_per_hour");
		
		ParkingLot parkingLot = new ParkingLot();
		parkingLot.setName(name);
		parkingLot.setAddress(address);
		parkingLot.setLng(Float.parseFloat(lng));
		parkingLot.setLat(Float.parseFloat(lat));
		parkingLot.setSlot_count(Integer.parseInt(slot_count));
		parkingLot.setCost_per_hour(Float.parseFloat(cost_per_hour));
		
		try {
			parkingLotDao.addParkingLot (parkingLot);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    response.sendRedirect("admin.jsp");
	}

}
