package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class RSMDTest1 {
  private static final String GET_STUDENTS_QUERY="SELECT SNO,SNAME,SADD,AVG FROM STUDENT";
	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		ResultSetMetaData rsmd=null;
		int colCount=0;
		try {
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create PreparedStatement object
			if(con!=null) {
				st=con.createStatement();
			}
			//execute the Query
			if(st!=null)
				rs=st.executeQuery(GET_STUDENTS_QUERY);
			//create ResultSetMetaData object
			if(rs!=null)
				rsmd=rs.getMetaData();
			
			//get column count
			if(rsmd!=null)
			  colCount=rsmd.getColumnCount();
			//display colum labels
			if(rsmd!=null) {
				for(int i=1;i<=colCount;++i) {
					System.out.println("col name:: "+rsmd.getColumnLabel(i));
					System.out.println("col type:: "+rsmd.getColumnType(i));
					System.out.println("col type name::"+rsmd.getColumnTypeName(i));
					System.out.println("col display size::"+rsmd.getColumnDisplaySize(i));
					System.out.println("is Nullable ?"+rsmd.isNullable(i));
					System.out.println("is Writable ?"+rsmd.isWritable(i));
					System.out.println("is CaseSensitive?"+rsmd.isCaseSensitive(i));
					System.out.println("is Singned? "+rsmd.isSigned(i));
					System.out.println(".....................................");
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
