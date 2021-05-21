package com.callor.maven;

import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.callor.config.MySQLConnection;

@WebServlet("/")
public class HomeController extends HttpServlet{

	protected Connection dbConn;
	
	public HomeController() {
		dbConn = MySQLConnection.getDBConnection();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Date date = new Date();
		SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd");
		
		req.setAttribute("TODAY", today.format(date));
		req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	
}
