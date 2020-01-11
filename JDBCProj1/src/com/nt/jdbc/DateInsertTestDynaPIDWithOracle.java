package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateInsertTestDynaPIDWithOracle {
  private static final String INSERT_DATE_QUERY="INSERT INTO PERSON_DATE_TAB VALUES(PID_SEQ1.NEXTVAL,?,?,?,?)"; 
	public static void main(String[] args) {
		Scanner sc=null;
	
		String name=null,dob=null,doj=null,dom=null;
		java.util.Date udob=null,udoj=null;
		java.sql.Date sqdob=null,sqdoj=null,sqdom=null;
		SimpleDateFormat sdf1=null,sdf2=null;
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Person name::");
				name=sc.next();
				System.out.println("Enter Person DOB (dd-MM-yyyy)::");
				dob=sc.next();
				System.out.println("Enter Person DOJ (MM-dd-yyyy)::");
				doj=sc.next();
				System.out.println("Enter Person DOM (yyyy-MM-dd)::");
				dom=sc.next();
			}
			//convert DOB to java.sql.Date class object
			   //String date to util date
			sdf1=new SimpleDateFormat("dd-MM-yyyy");
			 udob=sdf1.parse(dob);
			  // util date to  sql date
			  sqdob=new java.sql.Date(udob.getTime());  
			  
			//convert DOJ to java.sql.Date class object
			   //String date to util date
			sdf2=new SimpleDateFormat("MM-dd-yyyy");
			 udoj=sdf2.parse(doj);
			  // util date to  sql date
			  sqdoj=new java.sql.Date(udoj.getTime());
			  
			  //convert  DOM to java.sql.Date class object
			  sqdom=java.sql.Date.valueOf(dom);
			  
				  //register JDBC driver s/w
				  Class.forName("oracle.jdbc.driver.OracleDriver");
				  //establish the connection
				  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			
			/*  //register JDBC driver s/w
			    Class.forName("com.mysql.cj.jdbc.Driver");
			  //establish the connection
			    con=DriverManager.getConnection("jdbc:mysql:///ntaj413db","root","root");
			    */
			  
			  //create PreparedStatement object
			  if(con!=null)
				  ps=con.prepareStatement(INSERT_DATE_QUERY);
			  //set values to query params
			  if(ps!=null) {
				  ps.setString(1,name);
				  ps.setDate(2, sqdob);
				  ps.setDate(3,sqdoj);
				  ps.setDate(4,sqdom);
			  }
			  
			  //execute the query
			  if(ps!=null) 
				  result=ps.executeUpdate();
			  //process the result
			  if(result==0)
				  System.out.println("record not inserted");
			  else
				  System.out.println("record inserted");
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
