package com.parkallot.login.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.parkallot.login.dao.LoginDao;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private LoginDao loginDao = new LoginDao();
     
	/*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}*/
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.print("doPost method");
		String mobile = request.getParameter("mobile");
		String password = request.getParameter("password");
		
		try {
			if (loginDao.isRegisteredUser(mobile, password)) {
				System.out.println("Registered user");
				HttpSession session = request.getSession();
				session.setAttribute("mobile", mobile);
				response.sendRedirect("welcome.jsp");
			} else {
				System.out.println("Else");
				response.sendRedirect("index.html");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}


