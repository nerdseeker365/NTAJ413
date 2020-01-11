package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdatableResultSetTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int count=0;
		try {
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create Statement object
			if(con!=null)
			  st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			//send and execute SQL Query in Db s/w
			if(st!=null)
			  rs=st.executeQuery("SELECT SNO,SNAME,SADD,AVG FROM STUDENT");
			//process the ResultSEt object
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
					count++;
				}//while
			}//if
			//insert operation
			/*System.out.println("insert opertion");
			if(rs!=null) {
				rs.moveToInsertRow();
				rs.updateInt(1,9010);
				rs.updateString(2,"ramesh");
				rs.updateString(3,"vizag");
				rs.updateFloat(4,67.88f);
				rs.insertRow();
				System.out.println("record inserted");
			}*/
			
			/*//update operation
			System.out.println("update operation");
			if(rs!=null) {
				rs.absolute(4);
				rs.updateString(3,"vizag");
				rs.updateRow();
				System.out.println("record updated");
			}*/
			
			//delete operation
				if(rs!=null) {
					rs.absolute(2);
					rs.deleteRow();
					System.out.println("Record deleted");
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
			
		}//finally
	}//main
}//class
