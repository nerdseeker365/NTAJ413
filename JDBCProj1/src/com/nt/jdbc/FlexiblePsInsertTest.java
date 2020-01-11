package com.nt.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;



public class FlexiblePsInsertTest {
	private static final  String  INSERT_STUDENT_QUERY="INSERT INTO STUDENT VALUES(?,?,?,?)";

	public static void main(String[] args) {
		int count=0;
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null; 
		int no=0;
		String name=null,addrs=null;
		float avg=0.0f;
		int result=0;
		InputStream is=null;
		Properties props=null;
		try {
			//Locate properties file
			 is=new FileInputStream("src/com/nt/commons/jdbc.properties");
			 //Load properties file info java.util.Properties collection
			 props=new Properties();
			 props.load(is);
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter students  count::");
				count=sc.nextInt();
			}
			//register JDBC driver s/w
			Class.forName(props.getProperty("jdbc.driver"));
			//esablish the connection
			con=DriverManager.getConnection(props.getProperty("jdbc.url"),
					                                                           props.getProperty("jdbc.user"),
					                                                           props.getProperty("jdbc.pwd"));
			//create PreparedStatement object 
			if(con!=null)
				ps=con.prepareStatement(INSERT_STUDENT_QUERY);
			
			// read multiple student details and set them as query param values 
			 if(ps!=null && sc!=null) {
				 for(int i=1;i<=count;++i) {
					 //get each student details
					 System.out.println("Enter "+i+" student details ::");
					 System.out.println("Enter student number::");
					 no=sc.nextInt();
					 System.out.println("Enter student name::");
					 name=sc.next();
					 System.out.println("Enter student address::");
					 addrs=sc.next();
					 System.out.println("enter student avg::");
					 avg=sc.nextFloat();
					 //set each student details as the query param values
					 ps.setInt(1,no);
					 ps.setString(2, name);
					 ps.setString(3,addrs);
					 ps.setFloat(4, avg);
					 //execute the query
					 result=ps.executeUpdate();
					 //process the Result
					 if(result==0)
						 System.out.println(i+" student detials are not inserted");
					 else
						 System.out.println(i+" student details are inserted");
				 }//for
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
			try {
				if(is!=null)
					is.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}//finally
	}//main
}//class
