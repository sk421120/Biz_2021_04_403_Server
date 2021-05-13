package com.callor.diet.service;

import java.util.List;

import com.callor.diet.model.MyFoodCDTO;
import com.callor.diet.model.MyFoodVO;

public interface MyFoodService {

	public List<MyFoodCDTO> selectAll();
	public MyFoodCDTO findById(Long seq);
	
	public List<MyFoodCDTO> findByName(String mf_name);
	public List<MyFoodCDTO> findByDate(String mf_date);
	
	public int insert(MyFoodVO myFoodVO);
	public int update(MyFoodVO myFoodVO);
	public int delete(Long seq);
}
