package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectNonSelectTest {

	public static void main(String[] args) {
		Scanner sc=null;
		String query=null;
		Connection con=null;
		Statement st=null;
		boolean flag=false,isEmpty=true;
		ResultSet rs=null;
		int count=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter SQL query for Student(select or non-select)");
				query=sc.nextLine();
			}
			//register  JDBC Driver 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create JDBC Statement object
			if(con!=null)
				st=con.createStatement();
			//send and execute SQL Query in DB s/w..
			 if(st!=null)
				 flag=st.execute(query);
			 //get Query execution Result and process if
			 if(flag==true) {
				 System.out.println("select SQL Query is executed");
				 rs=st.getResultSet();
				 while(rs.next()) {
					 isEmpty=false;
					 System.out.println(rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getString(3)+"  "+rs.getFloat(4));
				 }
				 if(isEmpty==true)
					 System.out.println("no records found");
				 else
					 System.out.println("records found and displayed");
			 }//if
			 else {
				 System.out.println("Non-Select SQL Query is executed");
				 count=st.getUpdateCount();
				 System.out.println("no.of records that are effected::"+count);
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
