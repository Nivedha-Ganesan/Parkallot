package com.parkallot.registration.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.mysql.cj.xdevapi.PreparableStatement;
import com.parkallot.registration.model.User;

public class UserDao {
	static int id = 1;
	public int registerUser (User user) throws ClassNotFoundException {
		String sql = "INSERT INTO users (id, name, mobile, password, admin) VALUES (?, ?, ?, ?, ?);";
		
		int result = 0;
		
		Class.forName("com.mysql.jdbc.Driver");
		
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/parkallotdb","root","n");
			PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1,id);
			preparedStatement.setString(2, user.getName());
			preparedStatement.setString(3, user.getMobile());
			preparedStatement.setString(4,user.getPassword());
			preparedStatement.setBoolean(5, user.isAdmin());
			
			id++;
			
			System.out.println (preparedStatement);
			result = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
