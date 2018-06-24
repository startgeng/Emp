package com.cui.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.cui.db.OracleConst;

public class DBTools {

	public static Connection getConn() {

		try {
			Class.forName(OracleConst.DRIVERNAME);
			Connection conn = DriverManager.getConnection(OracleConst.URL, OracleConst.NAME, OracleConst.PASS);
			return conn;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
