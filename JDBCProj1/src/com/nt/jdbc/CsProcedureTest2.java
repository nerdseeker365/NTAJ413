package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*CREATE OR REPLACE PROCEDURE P_GET_EMPDETAILS_BY_NO (
  NO IN NUMBER 
, NAME OUT VARCHAR2 
, DESG OUT VARCHAR2 
, SALARY OUT NUMBER 
) AS 

BEGIN

  SELECT ENAME,JOB,SAL INTO NAME,DESG,SALARY FROM EMP WHERE EMPNO=NO;
  
  
END P_GET_EMPDETAILS_BY_NO;
*/
public class CsProcedureTest2 {
	 private static final String CALL_PROCEDURE="{CALL P_GET_EMPDETAILS_BY_NO(?,?,?,?)   }";
    public static void main(String[] args) {
    	Scanner sc=null;
    	int no=0;
    	Connection con=null;
    	CallableStatement cs=null;
    	try {
    		//read inputs
    		sc=new Scanner(System.in);
    		if(sc!=null) {
    			System.out.println("Enter Employee number::");
    			no=sc.nextInt();
    		}
    		//registger JDBC driver s/w
    		Class.forName("oracle.jdbc.driver.OracleDriver");
    		//establish the connection
    		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
    		//create JDBC CallableStatement object
    		if(con!=null)
    			cs=con.prepareCall(CALL_PROCEDURE);
    		//register OUT params with JDBC data types
    		if(cs!=null) {
    			cs.registerOutParameter(2,Types.VARCHAR);
    			cs.registerOutParameter(3,Types.VARCHAR);
    			cs.registerOutParameter(4, Types.INTEGER);
    		}
    		// set values to IN params
    		if(cs!=null)
    			cs.setInt(1,no);
    		//execute PL/SQL procedure
    		if(cs!=null)
    			cs.execute();
    		//gather results from Out params
    		if(cs!=null) {
    			System.out.println("Emp name::"+cs.getString(2));
    			System.out.println("Emp Desg::"+cs.getString(3));
    			System.out.println("Emp salary::"+cs.getInt(4));
    		}
    	}//try
    	catch(SQLException se) {
    		if(se.getErrorCode()==1403)
    			System.out.println("record not found");
    		else
    			System.out.println("Internal SQL Problem");
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
