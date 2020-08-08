package com.parkallot.parkinglot.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.parkallot.parkinglot.model.ParkingLot;

public class ParkingLotDao {
	
	public static List <ParkingLot> parking_lots = new ArrayList<>();
	public List <ParkingLot> getAll() throws ClassNotFoundException {
		String sql = "SELECT * FROM parking_lots";
		Class.forName("com.mysql.cj.jdbc.Driver");
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/parkallotdb","root","n");
			PreparedStatement st = connection.prepareStatement(sql)){
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				ParkingLot p_lot = new ParkingLot();
				p_lot.setId(Integer.parseInt(rs.getString(1)));
				p_lot.setName(rs.getString(2));
				p_lot.setAddress(rs.getString(3));
				p_lot.setLat(Float.parseFloat(rs.getString(4)));
				p_lot.setLng(Float.parseFloat(rs.getString(5)));
				p_lot.setSlot_count(Integer.parseInt(rs.getString(6)));
				p_lot.setCost_per_hour(Float.parseFloat(rs.getString(7)));
				parking_lots.add(p_lot);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parking_lots;
	}
	public void addParkingLot (ParkingLot parkingLot) throws ClassNotFoundException {
		int result = 0;
		String checkSql = "SELECT * from parking_lots where name = ? and address = ?;";
		String addPlotSql = "INSERT INTO parking_lots (name, address, lat, lng, slot_count, cost_per_hour) VALUES (?,?,?,?,?,?);";
		String updatePlotSql = "UPDATE parking_lots SET slot_count = ?, cost_per_hour = ? where name = ? and address = ?;";
		Class.forName("com.mysql.cj.jdbc.Driver");
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/parkallotdb","root","n");
			PreparedStatement preparedStatement = connection.prepareStatement(checkSql)){
			preparedStatement.setString(1, parkingLot.getName());
			preparedStatement.setString(2, parkingLot.getAddress());
			ResultSet rs;
			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				PreparedStatement update = connection.prepareStatement(updatePlotSql);
				update.setInt(1, parkingLot.getSlot_count());
				update.setFloat(2, parkingLot.getCost_per_hour());
				update.setString(3,parkingLot.getName());
				update.setString(4, parkingLot.getAddress());
				result = update.executeUpdate();
			} else {
				PreparedStatement add = connection.prepareStatement(addPlotSql);
				add.setString(1, parkingLot.getName());
				add.setString(2, parkingLot.getAddress());
				add.setFloat(3, parkingLot.getLat());
				add.setFloat(4, parkingLot.getLng());
				add.setInt(5, parkingLot.getSlot_count());
				add.setFloat(6, parkingLot.getCost_per_hour());
				result = add.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
