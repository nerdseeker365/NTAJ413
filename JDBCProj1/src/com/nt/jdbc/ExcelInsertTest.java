package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ExcelInsertTest {
  private static final String EXCEL_INSERT_QUERY="INSERT INTO College.Sheet1 VALUES(?,?,?,?)";
	public static void main(String[] args) {
		 Scanner sc=null;
		 int no=0;
		 String name=null,addrs=null;
		 float avg=0.0f;
		 Connection con=null;
		 PreparedStatement ps=null;
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
			
		
					
			//register jdbc Driver s/w
			   Class.forName("com.hxtt.sql.excel.ExcelDriver");
			   //establish the connection
			   con=DriverManager.getConnection("jdbc:Excel:///F:\\Worskpaces\\advjava\\NTAJ413");
			   //create Statement object
			   if(con!=null)
			      ps=con.prepareStatement(EXCEL_INSERT_QUERY);
			   //set values to query params
			   if(ps!=null) {
				   ps.setInt(1,no);
				   ps.setString(2,name);
				   ps.setString(3,addrs);
				   ps.setFloat(4,avg);
			   }
			 
			 // execute SQL Query in DB s/w
			 if(ps!=null)
				 count=ps.executeUpdate();
			 //process the result
			 if(count!=0)
				 System.out.println("record inserted");
			 
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()==1)
				   System.out.println("can not insert duplicates in unique,pk cols");
			else if(se.getErrorCode()==12899)
				System.out.println("value is too large than col size");
			else if(se.getErrorCode()>=900 && se.getErrorCode()<1000)
				System.out.println("Query syntax problem");
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
