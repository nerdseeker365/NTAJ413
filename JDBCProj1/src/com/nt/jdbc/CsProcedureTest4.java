package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;



/*CREATE OR REPLACE PROCEDURE P_GET_EMPDETAILS_BY_NAMECHARS 
(
  INITIALCHARS IN VARCHAR2 
, DETAILS OUT SYS_REFCURSOR 
) AS 
BEGIN
  
  OPEN DETAILS FOR
    SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE ENAME LIKE INITIALCHARS;
  
END P_GET_EMPDETAILS_BY_NAMECHARS;
*/
public class CsProcedureTest4 {
  private static final String  CALL_PROCEDURE="{CALL P_GET_EMPDETAILS_BY_NAMECHARS(?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		String initChars=null;
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		boolean flag=false;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter intial chars of Employee name::");
				initChars=sc.next();
			}
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
			//create CallableStatement object
			if(con!=null)
				cs=con.prepareCall(CALL_PROCEDURE);
			//register Out Params with JDBC data types
			if(cs!=null)
				cs.registerOutParameter(2,OracleTypes.CURSOR);
			//set values to IN params
			if(cs!=null)
				cs.setString(1,initChars+"%");
			//execute PL/SQL procedure
			if(cs!=null)
				cs.execute();
			//gather results from Out params
			if(cs!=null)
			   rs=(ResultSet)cs.getObject(2);
			//process the ResultSet 
			if(rs!=null) {
				while(rs.next()) {
					flag=true;
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getString(3)+"  "+rs.getInt(4));
				}//while
				if(flag==false)
					System.out.println("No records found");
				else
					System.out.println("records found and displayed");
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
				if(cs!=null)
					cs.close();
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