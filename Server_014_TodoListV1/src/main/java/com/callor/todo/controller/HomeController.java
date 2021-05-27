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
		req.setCharacterEncoding("UTF-8");
		String td_doit = req.getParameter(DBInfo.td_doit);
		
		Map<String, Object> tdMap = new HashMap<String, Object>();
		
		tdMap.put(DBInfo.td_doit, td_doit);
		
		if( tdService.insert(tdMap) > 0 ) {
			System.out.println("추가 성공");
			resp.sendRedirect("/todo/");
		} else {
			System.out.println("추가 실패");
		}
		
		
	}

}
