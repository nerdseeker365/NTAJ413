package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest {

	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		String newAddrs=null;
		float newAvg=0.0f;
		Connection con=null;
		Statement st=null;
		String query=null;
		int count=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter existing  student number:: ");
				no=sc.nextInt(); //gives 601
				System.out.println("enter student new address::");
				            sc.nextLine();
				newAddrs=sc.nextLine(); // new hyd
				System.out.println("Enter student new avg");
				newAvg=sc.nextFloat(); // 89.55
			}//if
			//convert inputs as required for SQL Quey
			newAddrs="'"+newAddrs+"'"; //gives 'new hyd'
			//register JDBC driver s/w  (optional)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create JDBC Statement object
				if(con!=null)
					st=con.createStatement();
			//prepare SQL Query
				   //update student set sadd='new hyd' ,avg=90.55 where sno=1001
				query="UPDATE STUDENT SET SADD="+newAddrs+",AVG="+newAvg+" WHERE SNO="+no;
				System.out.println(query);
			 count=st.executeUpdate(query);
			//process the reuslt
			 if(count==0)
				 System.out.println("No records found for updation");
			 else
				 System.out.println(count+" no.of records are updated");
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
				if(st!=null)
					st.close();
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(sc!=null)
					sc.close();
			}//try
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally
	}//main
}//class
