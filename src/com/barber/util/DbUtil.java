package com.barber.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * DB Utils Class
 * @author Administrator
 *
 */
public class DbUtil {

	private String dbUrl="jdbc:mysql://localhost:3306/db_barbar"; // DB Connection address
	private String dbUserName="root"; // DB user name
	private String dbPassword="ZisanSQL-1336"; // DB password
	private String jdbcName="com.mysql.jdbc.Driver"; // DB Driver
	
	/**
	 * Get DB Connection
	 * @return
	 * @throws Exception
	 */
	public Connection getCon() throws Exception{
		Class.forName(jdbcName);
		Connection con=DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		return con;
	}
	
	/**
	 * Close DB Connection
	 * @param con
	 * @throws Exception
	 */
	public void closeCon(Connection con) throws Exception{
		if(con!=null){
			con.close();
		}
	}
	
	public static void main(String[] args) {
		DbUtil dbUtil=new DbUtil();
		try {
			dbUtil.getCon();
			System.out.println("DB Connection: Success.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB Connection: Failed.");
		}
	}
}
