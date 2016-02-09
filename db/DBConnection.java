package com.poc.db;

import java.sql.*;

public class DBConnection {
	 public static Connection con;
	
	 public DBConnection(){
		 con=null;
	 }
	
	 public static Connection connect(){
		 try{			 
//			 Class.forName("org.apache.hadoop.hive.jdbc.HiveDriver");
//			 con = DriverManager.getConnection(
//						"jdbc:hive://10.102.103.196:10000/default", "", "");
			 Class.forName("com.mysql.jdbc.Driver");
		 con = DriverManager.getConnection("jdbc:mysql://10.102.103.186:3306/poc", "root", "");
		 }
		 catch(Exception e){
			 System.out.println();			 
		 }
		 return con;
	 }
	 public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		 Class.forName("com.mysql.jdbc.Driver");
//		 con = DriverManager.getConnection(
//					"jdbc:mysql://10.102.103.186:3306/bigdata", "root", "");
		 
		 Class.forName("com.mysql.jdbc.Driver");
		 con = DriverManager.getConnection("jdbc:mysql://10.102.103.193:3306/poc", "root", "admin");

		 System.out.println(con);
	}
	

}
