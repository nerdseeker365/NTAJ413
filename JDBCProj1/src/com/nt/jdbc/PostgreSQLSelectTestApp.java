package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgreSQLSelectTestApp {
  private static final String  GET_ALL_PRODUCTS="SELECT PID,PNAME,PRICE,QTY FROM PRODUCT";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//register jdbc driver s/w
			//Class.forName("org.postgresql.Driver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:postgresql:ntaj413db", "postgres","root");
			//create PreparedStatement object
			if(con!=null)
				ps=con.prepareStatement(GET_ALL_PRODUCTS);
			//execute the SQL query
			if(ps!=null)
			   rs=ps.executeQuery();
			//process the ResultSet
			  if(rs!=null) {
				  while(rs.next()) {
					  System.out.println(rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getFloat(3)+"  "+rs.getFloat(4));
				  }//while
			  }//if
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		/*catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}*/
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
		}//finally
	}//main
}//class
