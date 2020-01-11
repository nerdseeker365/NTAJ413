package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsUpdateTest1 {
	private static final String UPDATE_STUDENT_QUERY="UPDATE STUDENT SET SADD='lohore',AVG=55.55 WHERE SNO=1001";

	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		String query=null;
		int count=0;
		try {
			//register JDBC driver s/w  (optional)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create JDBC Statement object
				if(con!=null)
					ps=con.prepareStatement(UPDATE_STUDENT_QUERY);
			  //execute the SQL query
			  if(ps!=null)
			      count=ps.executeUpdate();
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
				if(ps!=null)
					ps.close();
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
		}//finally
	}//main
}//class
