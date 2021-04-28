package com.callor.welcome.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sk421120")
public class AddrController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println("<h1>오늘은 2021년 4월 28일 수요일</h1>");
		out.println("<p>매월 마지막 주 수요일은 문화의 날</p>");
		out.println("<p>고로 오늘은 문화의 날</p>");
		out.println("<p>무슨 영화를 보러갈까</p>");
		
		out.print("<a href='");
		out.print("https://movie.naver.com'>");
		out.println("영화 목록</a>");
		out.close();
	}
}
