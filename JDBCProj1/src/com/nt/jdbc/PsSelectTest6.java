package com.nt.jdbc;
//App  to get count of emp records
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PsSelectTest6 {
  private static final String  EMPS_COUNT_QUERY="SELECT COUNT(*) FROM EMP";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		String query=null;
		ResultSet rs=null;
		int count=0;
		try {
			//register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
			//create PreparedStatement object
			if(con!=null)
				ps=con.prepareStatement(EMPS_COUNT_QUERY);
				//send execute SQL Query to DB s/w
				if(ps!=null)
					rs=ps.executeQuery();
				//process the ResultSet object
				if(rs!=null) {
					rs.next();
					  count=rs.getInt(1);
				}
				System.out.println("records count::"+count);
			
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
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}
			
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
