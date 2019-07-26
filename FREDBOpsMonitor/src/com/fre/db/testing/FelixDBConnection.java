package com.fre.db.testing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fre.db.helper.ReadingInputData;

public class FelixDBConnection {

	public static Connection connection;
	public static Statement stmt;
	public static ResultSet rs;
	public int count = 0;

	public static ReadingInputData configReadData = new ReadingInputData();

	FelixDBConnection() {
		if (connection == null)
			openDBConnection();
	}

	public void openDBConnection() {
		try {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			connection = DriverManager.getConnection(configReadData.readPropertyValue("URL"),
					configReadData.readPropertyValue("USER"), configReadData.readPropertyValue("PASSWORD"));
			stmt = connection.createStatement();
			System.out.println("Connected to DB");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeConnection() {
		try {
			rs.close();
			stmt.close();
			connection.close();
			System.out.println("DB Connection closed");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCountdbQueryExecution(String qury) {
		try {
			rs = stmt.executeQuery(qury);
			System.out.println("Executed Query");
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public void retrieveFirstQueryResults(String query) {
		try {
			rs = stmt.executeQuery(query);
			System.out.println("Executed the Query & below are the results \n");
			System.out.println(
					"PROCESS_CONTEXT \t\t\t\t\t PROCESS_NAME \t\t\t PROCESS_SUBCONTEXT \t\t\t PROCESS_END_DATE \t\t\t PROCESS_EXITSTATUS");
			System.out.println(
					"=================================================================================================================================================================================================================================");
			while (rs.next()) {
				System.out.println(rs.getObject(1) + " \t\t\t" + rs.getString(2) + " \t\t\t\t\t" + rs.getString(3)
						+ " \t\t\t\t" + rs.getDate(4) + " \t\t\t" + rs.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void retrieveSecondQueryResults(String query) {
		try {
			rs = stmt.executeQuery(query);
			System.out.println("Executed the Query & below are the results \n");
			System.out.println("PROCESS_CONTEXT ");
			System.out.println(
					"=================================================================================================================================================================================================================================");
			while (rs.next()) {
				System.out.println(rs.getObject(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		FelixDBConnection obj = new FelixDBConnection();
		System.out.println("\n Executing First Query");
		obj.retrieveFirstQueryResults(configReadData.readPropertyValue("FirstQuery"));
		System.out.println("\n Executing second Query");
		obj.retrieveSecondQueryResults(configReadData.readPropertyValue("SecondQuery"));
		closeConnection();
	}
}
