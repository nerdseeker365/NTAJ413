package com.nt.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

public class ConnPoolTest {

	public static void main(String[] args) {
		OracleConnectionPoolDataSource ds=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//create Datasoruce obj repreenting jdbc con pool
			ds=new OracleConnectionPoolDataSource();
			ds.setDriverType("thin");
			ds.setURL("jdbc:oracle:thin:@localhost:1521:xe");
			ds.setUser("nit");
			ds.setPassword("tiger");
			//get One Pooled jdbc con object
			if(ds!=null)
			 con=ds.getConnection();
			//create Statement object
			if(con!=null)
				st=con.createStatement();
			 //send and execute SQL Query in Db s/w
			if(st!=null)
				rs=st.executeQuery("SELECT EMPNO,ENAME,JOB,SAL FROM EMP");
			//process the ReusltSet object
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+" "+rs.getFloat(4));
				}//while
			}//if		
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
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

	}

}
