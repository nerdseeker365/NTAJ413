package com.nt.jdbc;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PersonAgeCalculatorinAllDBs {
  private static final String AGE_CALC_QUERY="SELECT DOB FROM PERSON_DATE_TAB WHERE PID=?";
	public static void main(String[] args) {
		Scanner sc=null;
		int pid=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		float age=0.0f;
		java.util.Date udob=null,sysDate=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("enter person Id::");
				pid=sc.nextInt();
			}

			//register JDBC driver s/w
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			  //establish the connection
			  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			
			  /*//register JDBC driver s/ws
			Class.forName("com.mysql.cj.jdbc.Driver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///ntaj413db","root","root");*/
			//create PreparedStatement object
			if(con!=null)
				ps=con.prepareStatement(AGE_CALC_QUERY);
			//set value to query param
			if(ps!=null)
				ps.setInt(1,pid);
			//execute the SQL Query
			if(ps!=null)
				rs=ps.executeQuery();
			
			//process the ResultSet object
			if(rs.next()) {
				udob=rs.getDate(1);  //DOB
				//system date
				sysDate=new Date();
				age=(sysDate.getTime()-udob.getTime())/(1000.0f*60.0f*60.0f*24.0f*365.0f);
				
				System.out.println("Person age is::"+age);
			}
			else
				System.out.println("record not found");
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
