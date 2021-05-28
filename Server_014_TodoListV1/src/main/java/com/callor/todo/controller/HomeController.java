package com.callor.todo.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.callor.todo.command.ReqController;
import com.callor.todo.config.DBInfo;
import com.callor.todo.service.TodoService;
import com.callor.todo.service.impl.TodoServiceImplV1;

/*
 * Servlet App 에서는 Servlet(Controller) 클래스를 다수 선언하고,
 * 필요할때마다 URI(URL)을 mapping하여 기능을 수행 할 수 있도록 한다
 * 
 * 하지만 프로젝트가 커지면 다수의 Controller 생성되고
 * 그때마다 URI mapping을 하는데 많은 어려움을 겪을 수 있다.
 * 똑같은 객체를 상속받고, 같은 method(doGet, doPost)를 사용
 * 코드를 작성하는데 관리자가 어려워지기 시작한다
 * 
 * URI mapping을 한곳으로 집중하고
 * POJO(Plan Old Java Object, 어떤 클래스를 상속받지 않은 일반적인 자바 클래스 형식의 코드)
 * 를 사용하여 프로젝트를 진행하도록 하는 것
 * Dispatcher Servlet Controller 라고도 한다
 */
@WebServlet("/")
public class HomeController extends HttpServlet{

	protected TodoService tdService;
	
	public HomeController() {
		tdService = new TodoServiceImplV1();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Map<String,Object>> tdList = tdService.selectAll();
		
		req.setAttribute("TDLIST", tdList);
		
		SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일");
		Date today = new Date(System.currentTimeMillis());
		
		req.setAttribute("DATE", date.format(today));
		
		// 먼저 사용할 코드를 작성하고 나중에 사용될 클래스 등의 코드를 작성
		// 테스트 주도형의 일부
		ReqController.forward(req,resp,"home");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
