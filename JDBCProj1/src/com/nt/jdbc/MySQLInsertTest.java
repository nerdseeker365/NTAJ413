package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MySQLInsertTest {

	public static void main(String[] args) {
		 Scanner sc=null;
		 int no=0;
		 String name=null,addrs=null;
		 float avg=0.0f;
		 Connection con=null;
		 Statement st=null;
		 String query=null;
		int count=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter student number::");
				no=sc.nextInt();  //gives 1001
				System.out.println("enter student name ::");
				name=sc.next(); //gives  karan
				System.out.println("enter  student address::");
				addrs=sc.next(); //gives  hyd
				System.out.println("enter student marks avg::");
				avg=sc.nextFloat();  //gives  89.55
			}
			
			//convert input values as required for the SQL query
			name='\''+name+'\'';  //gives 'karan'
			addrs='\''+addrs+'\'';  //gives  'hyd'
					
			//register jdbc Driver s/w
			   //Class.forName("com.mysql.cj.jdbc.Driver");
			   //establish the connection
			  // con=DriverManager.getConnection("jdbc:mysql:///ntaj413db","root","root");
			 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ntaj413db1","root","root");
			   //create Statement object
			   if(con!=null)
			       st=con.createStatement();
			   //prepare SQL query
			             // insert into student values(1001,'karan','hyd',78.99)
			 query="INSERT INTO STUDENT VALUES("+no+","+name+","+addrs+","+avg+")";
			 System.out.println(query);
			 
			 //send and execute SQL Query in DB s/w
			 if(st!=null)
				 count=st.executeUpdate(query);
			 //process the result
			 if(count!=0)
				 System.out.println("record inserted");
			 
		}//try
		catch(SQLException se) {
		   System.out.println("Problem in record insertion");
			se.printStackTrace();
		}
		/*
		 * catch(ClassNotFoundException cnf) { cnf.printStackTrace(); }
		 */
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close jdbc objs
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
