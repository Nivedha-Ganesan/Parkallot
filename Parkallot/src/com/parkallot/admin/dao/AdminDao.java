package com.parkallot.admin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDao {
	String sql = "select * from users where mobile = ? and password = ? and admin = 1";
	public boolean isAdmin (String mobile, String password) throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/parkallotdb","root","n");
				PreparedStatement st = connection.prepareStatement(sql)){
				st.setString(1, mobile);
				st.setString(2, password);
				ResultSet rs = st.executeQuery();
				if (rs.next()) {
					return true;
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
