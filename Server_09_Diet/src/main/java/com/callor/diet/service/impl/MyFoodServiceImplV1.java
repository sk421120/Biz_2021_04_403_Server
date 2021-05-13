package com.callor.diet.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.callor.diet.config.DBContract;
import com.callor.diet.config.DBInfo;
import com.callor.diet.model.FoodDTO;
import com.callor.diet.model.MyFoodCDTO;
import com.callor.diet.model.MyFoodVO;
import com.callor.diet.service.MyFoodService;

public class MyFoodServiceImplV1 implements MyFoodService {

	protected Connection dbConn;

	public MyFoodServiceImplV1() {
		dbConn = DBContract.getDBConnection();
	}

	protected List<MyFoodCDTO> select(PreparedStatement pStr) throws SQLException {

		ResultSet rSet = pStr.executeQuery();

		List<MyFoodCDTO> foodCList = new ArrayList<MyFoodCDTO>();

		// DBMS에서 받은 데이터가 있으면
		while (rSet.next()) {
			FoodDTO dto = new FoodDTO();
			dto.setFd_code(rSet.getString(DBInfo.FOOD.fd_code));
			dto.setFd_name(rSet.getString(DBInfo.FOOD.fd_name));
			dto.setFd_year(rSet.getString(DBInfo.FOOD.fd_year));
			dto.setFd_ccode(rSet.getString(DBInfo.FOOD.fd_ccode));
			dto.setFd_icode(rSet.getString(DBInfo.FOOD.fd_icode));
			dto.setFd_once(rSet.getFloat(DBInfo.FOOD.fd_once));
			dto.setFd_capa(rSet.getFloat(DBInfo.FOOD.fd_capa));
			dto.setFd_cal(rSet.getFloat(DBInfo.FOOD.fd_cal));
			dto.setFd_protein(rSet.getFloat(DBInfo.FOOD.fd_protein));
			dto.setFd_fat(rSet.getFloat(DBInfo.FOOD.fd_fat));
			dto.setFd_carbo(rSet.getFloat(DBInfo.FOOD.fd_carbo));
			dto.setFd_sugar(rSet.getFloat(DBInfo.FOOD.fd_sugar));
			dto.setCp_name(rSet.getString(DBInfo.FOOD.cp_name));
			dto.setCp_ceo(rSet.getString(DBInfo.FOOD.cp_ceo));
			dto.setCp_tel(rSet.getString(DBInfo.FOOD.cp_tel));
			dto.setCp_addr(rSet.getString(DBInfo.FOOD.cp_addr));
			dto.setCp_item(rSet.getString(DBInfo.FOOD.cp_item));
			dto.setIt_name(rSet.getString(DBInfo.FOOD.it_name));
//			foodList.add(dto);
		}
		return null;
	}

	@Override
	public List<MyFoodCDTO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyFoodCDTO findById(Long seq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MyFoodCDTO> findByName(String mf_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MyFoodCDTO> findByDate(String mf_date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(MyFoodVO myFoodVO) {
		// TODO Auto-generated method stub
		String sql = " INSERT INTO tbl_myfoods (";
		sql += " mf_seq, ";
		sql += " mf_fcode,";
		sql += " mf_date,";
		sql += " mf_amt)";
		sql += " VALUES ( ";
		sql += "  seq_myfoods.NEXTVAL,";
		sql += " ?, ?, ? )";
		
		System.out.println(sql);
		
		PreparedStatement pStr = null;
		
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, myFoodVO.getMf_fcode());
			pStr.setString(2, myFoodVO.getMf_date());
			pStr.setFloat(3, myFoodVO.getMf_amt());
			
			int result = pStr.executeUpdate();
			
			pStr.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int update(MyFoodVO myFoodVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Long seq) {
		// TODO Auto-generated method stub
		return 0;
	}

}
