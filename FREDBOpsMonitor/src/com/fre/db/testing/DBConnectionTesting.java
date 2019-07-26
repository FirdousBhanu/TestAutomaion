package com.fre.db.testing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionTesting {

	public static final String URL ="jdbc:oracle:thin:@slcao717.us.oracle.com:1603/fretool1";
	public static final String USER =  "fredbqa";
	public static final String PASSWORD = "changeSoonAsPlease";
	Connection connection;
	static Statement stmt;
	ResultSet rs;
	int count=0;

	public DBConnectionTesting(){
		try {	
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} 
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connection = DriverManager.getConnection(URL,USER,PASSWORD);
			stmt= connection.createStatement();
			System.out.println("Connected to DB");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getCountdbQueryExecution(String qury) {
		try {
			rs = stmt.executeQuery(qury);
			System.out.println("Executed Query");
			while(rs.next()) {
				count=rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public void closeConnection() {		
		try {
			rs.close();
			stmt.close();
			connection.close();
			System.out.println("DB Connection closed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String query="select count(*) from FUSION_NEXTGEN_BASICCHECKS";

		DBConnectionTesting obj=new DBConnectionTesting();
		System.out.print(obj.getCountdbQueryExecution(query));
		System.out.println();
		obj.closeConnection();

	}

}
