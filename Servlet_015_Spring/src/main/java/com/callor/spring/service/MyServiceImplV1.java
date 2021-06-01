package com.callor.spring.service;

import org.springframework.stereotype.Service;

import com.callor.spring.model.MyVO;

@Service
public class MyServiceImplV1 implements HomeService{

	@Override
	public MyVO findById() {
		// TODO Auto-generated method stub
		MyVO vo = new MyVO();
		vo.setT_name("냠냠");
		vo.setT_tel("123112");
		vo.setT_age("20");
		
		return vo;
	}

}
