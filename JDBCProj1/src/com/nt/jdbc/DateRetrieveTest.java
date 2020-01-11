package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateRetrieveTest {
  private static final String  GET_DATES_QUERY="SELECT PID,PNAME,DOB,DOJ,DOM FROM PERSON_DATE_TAB WHERE PID=?";
	public static void main(String[] args) {
		int  pid=0;
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String name=null;
		java.sql.Date sqdob=null,sqdoj=null,sqdom=null;
		java.util.Date udob=null,udoj=null,udom=null;
		SimpleDateFormat sdf=null;
		String dob=null,doj=null,dom=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Person Id::");
				pid=sc.nextInt();
			}
			/*	//register JDBC driver s/ws
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//establish the connection
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				*/
			
			//register JDBC driver s/ws
			Class.forName("com.mysql.cj.jdbc.Driver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///ntaj413db","root","root");
			
			//create PreparedStatement object
			if(con!=null)
				ps=con.prepareStatement(GET_DATES_QUERY);
			//set values to query params
			if(ps!=null)
				ps.setInt(1,pid);
			//execute the Query
			if(ps!=null)
				rs=ps.executeQuery();
			//process the ResultSet obj
			if(rs.next()) {
				pid=rs.getInt(1);
				name=rs.getString(2);
				sqdob=rs.getDate(3);
				sqdoj=rs.getDate(4);
				sqdom=rs.getDate(5);
				//convert java.sql.Date class objs to java.util.Date class objs
				udob=sqdob;
				udoj=sqdoj;
				udom=sqdom;
				//convert  java.util.Date class objs  to String date values
				sdf=new SimpleDateFormat("yyyy-MMM-dd");
				dob=sdf.format(udob);
				doj=sdf.format(udoj);
				dom=sdf.format(udom);
				System.out.println(pid+"  "+name+" "+dob+"  "+doj+"  "+dom);
			}
			else {
				System.out.println("record not found");
			}
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
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally
	}//main
}//class
