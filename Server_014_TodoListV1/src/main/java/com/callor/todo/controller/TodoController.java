package com.callor.todo.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.callor.todo.config.DBInfo;
import com.callor.todo.service.TodoService;
import com.callor.todo.service.impl.TodoServiceImplV1;

@WebServlet("/sub/*")
public class TodoController extends HttpServlet {

	protected TodoService tdService;

	public TodoController() {
		tdService = new TodoServiceImplV1();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String strSeq = req.getParameter("seq");

		Long td_seq = Long.valueOf(strSeq);

		Map<String, Object> tdMap = tdService.findById(td_seq);
		System.out.println(tdMap.toString());
		Object td_edate = tdMap.get(DBInfo.td_edate);
		if(td_edate == null || td_edate.equals("")) {
			Date date = new Date(System.currentTimeMillis());
			
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat st = new SimpleDateFormat("HH:mm:ss");
			
			String edate = sd.format(date);
			String etime = st.format(date);
		
			tdMap.put(DBInfo.td_edate, edate);
			tdMap.put(DBInfo.td_etime, etime);
		} else {
			tdMap.put(DBInfo.td_edate, null);
			tdMap.put(DBInfo.td_etime, null);
		}
		System.out.println(tdMap.toString());
		
		if( tdService.update(tdMap) > 0 ) {
			System.out.println("수정 성공");
			resp.sendRedirect(req.getContextPath());
		} else {
			System.out.println("수정 실패");
		}
		
		
		
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
