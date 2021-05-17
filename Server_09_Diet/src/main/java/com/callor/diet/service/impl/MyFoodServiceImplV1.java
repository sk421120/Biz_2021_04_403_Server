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

		List<MyFoodCDTO> mfCList = new ArrayList<MyFoodCDTO>();

		ResultSet rSet = pStr.executeQuery();

		// DBMS에서 받은 데이터가 있으면
		while (rSet.next()) {
			MyFoodCDTO mfCDTO = new MyFoodCDTO();
			mfCDTO.setMf_seq(rSet.getLong(DBInfo.섭취정보계산.mf_seq));
			mfCDTO.setMf_date(rSet.getString(DBInfo.섭취정보계산.mf_date));
			mfCDTO.setMf_fcode(rSet.getString(DBInfo.섭취정보계산.mf_fcode));
			mfCDTO.setMf_fname(rSet.getString(DBInfo.섭취정보계산.mf_fname));
			mfCDTO.setMf_amt(rSet.getFloat(DBInfo.섭취정보계산.mf_amt));
			mfCDTO.setMf_once(rSet.getFloat(DBInfo.섭취정보계산.mf_once));
			mfCDTO.setMf_capa(rSet.getFloat(DBInfo.섭취정보계산.mf_capa));
			mfCDTO.setMf_cal(rSet.getFloat(DBInfo.섭취정보계산.mf_cal));
			mfCDTO.setMf_protein(rSet.getFloat(DBInfo.섭취정보계산.mf_protein));
			mfCDTO.setMf_fat(rSet.getFloat(DBInfo.섭취정보계산.mf_fat));
			mfCDTO.setMf_carbo(rSet.getFloat(DBInfo.섭취정보계산.mf_carbo));
			mfCDTO.setMf_sugar(rSet.getFloat(DBInfo.섭취정보계산.mf_sugar));
			mfCList.add(mfCDTO);
		}
		return mfCList;
	}

	@Override
	public List<MyFoodCDTO> selectAll() {
		// TODO 전체 식품섭취 리스트
		String sql = " SELECT * FROM view_섭취량계산 ";
		
		PreparedStatement pStr = null;
		
		try {
			pStr = dbConn.prepareStatement(sql);
			List<MyFoodCDTO> myList = this.select(pStr);
			pStr.close();
			return myList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public MyFoodCDTO findById(Long seq) {
		// TODO Auto-generated method stub
		String sql = " SELECT * FROM view_섭취량계산 ";
		sql += " WHERE 일련번호 = ? ";
		
		PreparedStatement pStr = null;
		
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setLong(1, seq);
			
			List<MyFoodCDTO> myList = this.select(pStr);
			
			pStr.close();
			if(myList != null && myList.size() > 0 ) {
				return myList.get(0);	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		String sql = " SELECT * FROM view_섭취량계산 ";
		sql += " WHERE 섭취일자 = ? ";
		
		PreparedStatement pStr = null;
		
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, mf_date);
			
			List<MyFoodCDTO> myList = this.select(pStr);
			
			pStr.close();
			return myList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
