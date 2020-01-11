package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExcelSelectTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
	   try {
		   //register JDBC driver s/w
		   Class.forName("com.hxtt.sql.excel.ExcelDriver");
		   //establish the connection
		   con=DriverManager.getConnection("jdbc:Excel:///F:\\Worskpaces\\advjava\\NTAJ413");
		   //create Statement object
		   if(con!=null)
			   st=con.createStatement();
		   //send and execute SQL query to Db s/w
		   if(st!=null)
			   rs=st.executeQuery("SELECT * FROM College.Sheet1");
		   //process the ResultSet
		   if(rs!=null) {
			   while(rs.next()) {
				   System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"   "+rs.getString(3)+"   "+rs.getFloat(4));
			   }//while
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
			   if(st!=null)
				   st.close();
		   }
		   catch(SQLException se) {
			   se.printStackTrace();
		   }
		   try {
			   if(con!=null)
				   con.close();
		   }
		   catch(SQLException se) {
			   se.printStackTrace();
		   }
	   }

	}

}
