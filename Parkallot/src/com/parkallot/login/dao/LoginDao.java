package com.parkallot.login.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import com.mysql.cj.protocol.Resultset;

public class LoginDao {
	String sql = "select * from users where mobile = ? and password = ?";
	public boolean isRegisteredUser (String mobile, String password) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/parkallotdb","root","n");
			PreparedStatement st = connection.prepareStatement(sql)){
			st.setString(1, mobile);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			System.out.print(st.toString());
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
