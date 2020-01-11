package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleToMySQLTest {
	private static final String  ORACLE_SELECT_QUERY="SELECT SNO,SNAME,SADD,AVG FROM STUDENT"; 
     private static final String  MYSQL_INSERT_QUERY="INSERT INTO STUDENT VALUES(?,?,?,?)";
	public static void main(String[] args) {
		Connection oraCon=null,mysqlCon=null;
		Statement st=null;
		PreparedStatement ps=null; 
		ResultSet rs=null;
		int no=0;
		String name=null,addrs=null;
		float avg=0.0f;
		try {
			//register JDBC drivers
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			//establish the connections
			oraCon=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			mysqlCon=DriverManager.getConnection("jdbc:mysql:///ntaj413db", "root", "root");
			//create JDBC Statement objects
			if(oraCon!=null)
				st=oraCon.createStatement();
			if(mysqlCon!=null)
				ps=mysqlCon.prepareStatement(MYSQL_INSERT_QUERY);
			
			//execute Select SQL Query in Oracle DB s/w
			   if(st!=null) 
				   rs=st.executeQuery(ORACLE_SELECT_QUERY);
			   //copy records to mysql Student table from oracle student table
			   if(ps!=null && rs!=null) {
				   while(rs.next()) {
					   //get each record from ReusltSet (oracle student table)
					   no=rs.getInt(1);
					   name=rs.getString(2);
					   addrs=rs.getString(3);
					   avg=rs.getFloat(4);
					   //set each above  record values to insert query params of PreparedStatement obj
					   ps.setInt(1,no);
					   ps.setString(2,name);
					   ps.setString(3,addrs);
					   ps.setFloat(4,avg);
					   //execute insert Query (mysql)
					   ps.executeUpdate();
				   }//while
				   System.out.println("Records are copied from oracle student db table to mysql student db table");
			   }//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close jdbc objs
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(ps!=null)
					ps.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(st!=null)
					st.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(oraCon!=null)
					oraCon.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(mysqlCon!=null)
					mysqlCon.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}//finally
		
	}//main
}//class
