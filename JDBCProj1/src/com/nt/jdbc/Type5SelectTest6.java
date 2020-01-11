package com.nt.jdbc;
//App  to get count of emp records
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Type5SelectTest6 {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		String query=null;
		ResultSet rs=null;
		int count=0;
		try {
			//register JDBC driver
			Class.forName("com.ddtek.jdbc.oracle.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:datadirect:oracle://localhost:1521;serviceName=xe","nit", "tiger");
			//create Statement object
			if(con!=null)
				st=con.createStatement();
			//prepare SQL Query
			          //select count(*) from emp
				query="SELECT COUNT(*) FROM EMP";
				//send execute SQL Query to DB s/w
				if(st!=null)
					rs=st.executeQuery(query);
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
		}//finally
	
	}//main
}//class
