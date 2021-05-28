package com.callor.todo.command;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.callor.todo.config.DBInfo;
import com.callor.todo.service.TodoService;
import com.callor.todo.service.impl.TodoServiceImplV1;

public class TodoCommandImplV1 implements TodoCommand {

	// Logger 객체인 log를 선언하고 생성을 할 때 관리이름을 TODO를 부착하라
	// TODO라는 이름으로 Logger를 싱글톤으로 제공하라
	// Factory 패턴
	// 객체를 생성하는 클래스 method를 준비해두고 필요에 따라 생성된 객체를 제공받는 패턴
	
	private static final Logger log = LoggerFactory.getLogger("TODO");
	
	private TodoService tdService;
	public TodoCommandImplV1() {
		tdService = new TodoServiceImplV1();
	}
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String td_doit = req.getParameter("td_doit");
//		System.out.println(td_doit);
		// Server APP에서 System.out.println() 대신 사용할 console 출력 method
		log.debug(td_doit);
		
		/*
		 * Map으로 만든 동적(Dynamic) vo
		 * value를 Object로 만든 이유
		 *  table에서 데이터를 Select하거나
		 */
		Map<String,Object> tdVO = new HashMap<String, Object>();
		
		// 현재 시스템의 날짜 가져오기
		Date date = new Date(System.currentTimeMillis());
		
		// 날짜를 문자열로 변환하기 위하여 format 을 생성하고
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat st = new SimpleDateFormat("HH:mm:ss");
		
		// 생성된 form을 사용하여 날짜, 시각 데이터를
		// 문자열로 변환하여 변수에 저장
		String curDate = sd.format(date);
		String curTime = st.format(date);
		
		// Map type의 VO에 현재 날짜, 시각, 할일 정보를 저장하기
		// VO에 칼럼을 추가하면서 동시에 저장하기
		// Map type의 VO에 데이터를 put() 할 때 만약 key에 해당하는
		// 	데이터가 이미 있으면 기존의 데이터를 수정한다
		// 	없으면 새로운 칼럼을 추가하고 데이터 저장
		tdVO.put(DBInfo.td_sdate, curDate);
		tdVO.put(DBInfo.td_stime, curTime);
		tdVO.put(DBInfo.td_doit, td_doit);
		
		log.debug("VO 데이터 {} ", tdVO.toString());
		
		// insert로 부터 전달받은 숫자
		// 1이상이면 정상 insert이고 그렇지 않으면 추가가 잘못된것
		Integer ret = tdService.insert(tdVO);
		if(ret < 1) {
			req.setAttribute("MSG", "INSERT 실패!!");
		} else {
			req.setAttribute("MSG", "INSERT 성공!!");
		}
		
		// "/todo/"
		res.sendRedirect( req.getContextPath() );
	}
}