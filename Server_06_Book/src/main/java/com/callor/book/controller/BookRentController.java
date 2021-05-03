package com.callor.book.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.callor.book.model.BookRentDTO;
import com.callor.book.model.BookRentVO;
import com.callor.book.service.BookRentService;
import com.callor.book.service.impl.BookRentServiceImplV1;

/*
 * Web Browser의 Request를 처리할 클래스
 */
@WebServlet("/rent/*")
public class BookRentController extends HttpServlet {

	private static final long serialVersionUID = 921652892464670154L;
	protected BookRentService brService;
	public BookRentController() {
		brService = new BookRentServiceImplV1(); // new... 추가하기
	}
	
	/*
	 * exception 처리 방법 2가지
	 * 1. try-catch 를 사용하기
	 * 	exception 이 발생할 코드를 감싸서 "직접" exception을 처리하기
	 * 
	 * 2. 떠넘기기(던지기)
	 * 	method throws 를 추가하여 호출한 method에게 exception을 되돌려 보내기
	 * 	현재 method 에서 직접 exception 을 처리하는 코드가 없어서 코드가 다소 간소화 된다
	 * 	호출한 곳에서 exception 처리
	 * 
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String subPath = req.getPathInfo();
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		if(subPath.equals("/list")) {
			// 도서대여 전체 목록
			out.println("도서대여 전체목록 보기");
			brService.selectAll();
		} else if(subPath.equals("/seq")) {
			// 주문번호로 찾기
			
			String strSeq = req.getParameter("id");
			Long nSeq = Long.valueOf(strSeq);
			
			BookRentDTO brDTO = brService.findById(nSeq);
			
			if(nSeq != null) {
			// view에서 보여줄 데이터 생성
			ServletContext app = this.getServletContext();
			
			// bService가 return한 brDTO를 app객체에 BOOK이라는 속성변수로 세팅하기
			app.setAttribute("BOOK", brDTO);
			
			// book.jsp 파일을 읽어서 app에 setting한 BOOK 변수와 함께 Rendering을 하라
			RequestDispatcher disp = app.getRequestDispatcher("/WEB-INF/views/book.jsp");
			
			// Rendering 된 view 데이터를 Web browser로 response 하라
			disp.forward(req, resp);
			}
			else {
				out.println("찾으시는 정보가 없습니다.");
				out.println("<a href = 'index.html'> 돌아가기 </a>");
			}
		} else if (subPath.equals("/isbn")) {
			// 도서코드로 찾기
			brService.findByBISBN("isbn");
		} else if (subPath.equals("/buyer")) {
			// 회원코드로 찾기
			brService.findByBuyerCode("buyercode");
		} else if(subPath.equals("/rent")) {
			// 대여정보 추가, 대여하기
			BookRentVO bookRentVO = new BookRentVO();
			brService.insert(bookRentVO);
		} else if(subPath.equals("/return")) {
			// 반납하기
			BookRentVO bookRentVO = new BookRentVO();
			brService.update(bookRentVO);
		} else {
			// 더이상 그만하기
			return;
		}
			
			
	}
	
}