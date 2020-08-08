package com.parkallot.registration.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import com.parkallot.registration.model.User;

public class UserDao {
	public int registerUser (User user) throws ClassNotFoundException {
		String sql = "INSERT INTO users (name, mobile, password, admin) VALUES (?, ?, ?, ?);";
		
		int result = 0;
		
		Class.forName("com.mysql.jdbc.Driver");
		
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/parkallotdb","root","n");
			PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getMobile());
			preparedStatement.setString(3,user.getPassword());
			preparedStatement.setBoolean(4, user.isAdmin());
			result = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
