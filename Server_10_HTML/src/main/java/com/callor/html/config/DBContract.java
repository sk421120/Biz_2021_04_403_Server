package com.callor.html.config;

import java.sql.Connection;

public class DBContract {

	public Connection dbConn;
	
	static {
		String jdbcDriver = "oracle.jdbc.OracleDriver";
		String user = "myfood";
		String password = "myfood";
		
		try {
			Class.forName(jdbcDriver);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
