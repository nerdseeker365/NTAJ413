package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PMDTest {

	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		ParameterMetaData pmd=null;
		int count=0;
		try {
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create PreparedStaement object
			if(con!=null)
				ps=con.prepareStatement("INSERT INTO STUDENT VALUES(?,?,?,?)");
			//create ParameterMetaData
			if(ps!=null) {
				pmd=ps.getParameterMetaData();
			}
			//get Parameters count
			if(pmd!=null) {
				count=pmd.getParameterCount();
			}
			//display parameter details
			for(int i=1;i<=count;++i) {
				System.out.println("parameter index::"+i);
				System.out.println("parmaeter type ::"+pmd.getParameterType(i));
				System.out.println("parameter type name::"+pmd.getParameterTypeName(i));
				System.out.println("parameter mode ::"+pmd.getParameterMode(i));
				System.out.println(" isNullable ::"+pmd.isNullable(i));
			}//for
			
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
		}//finally
	}//main
}//class