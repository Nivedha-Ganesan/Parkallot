package com.parkallot.regisration.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.parkallot.registration.dao.UserDao;
import com.parkallot.registration.model.User;

/**
 * Servlet implementation class Register
 */
@WebServlet(description = "New users are registered", urlPatterns = { "/Register" })
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private UserDao userDao = new UserDao();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String password = request.getParameter("password");
		
		User user = new User();
		user.setName(name);
		user.setMobile(mobile);
		user.setPassword(password);
		user.setAdmin(false);
		
	    try {
			userDao.registerUser(user);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    System.out.print("SUCCESS");
	    response.sendRedirect("index.html");
	}

}
