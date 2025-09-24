package com.dr.care.hospital.dbconnect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect{

	public static boolean getConnection()  throws SQLException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "Root");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
