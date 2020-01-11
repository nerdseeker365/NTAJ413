package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

/*CREATE OR REPLACE FUNCTION FX_VIEW_DELETE_STUDENT_BY_ID 
(
  NO IN NUMBER 
, COUNT OUT NUMBER 
) RETURN SYS_REFCURSOR AS 

   DETAILS SYS_REFCURSOR;
BEGIN
     OPEN DETAILS FOR
      SELECT SNO,SNAME,ADRRS,AVG FROM STUDENT WHERE SNO=NO;
      
      DELETE FROM STUDENT WHERE SNO=NO;
       COUNT:=SQL%ROWCOUNT;
  RETURN DETAILS;
END FX_VIEW_DELETE_STUDENT_BY_ID;
*/

public class CsFunctionTest2 {
   private static final String CALL_FUNCTION="{?=call FX_VIEW_DELETE_STUDENT_BY_ID(?,?)  }";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		int count=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("enter student number::");
			    no=sc.nextInt();
			}
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create CallableStatement object
			if(con!=null)
				cs=con.prepareCall(CALL_FUNCTION);
			//register OUT,return params with JDBC types
			if(cs!=null) {
				cs.registerOutParameter(1,OracleTypes.CURSOR);
				cs.registerOutParameter(3,Types.INTEGER);
			}
			//set values to IN params
			if(cs!=null)
				cs.setInt(2,no);
			//call PL/SQL function
			if(cs!=null)
				cs.execute();
			//gather return,OUT params
			  if(cs!=null)
				  rs=(ResultSet) cs.getObject(1); //return param
			  //process the Result
			  if(rs!=null) {
				  if(rs.next())
					  System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				  else
					  System.out.println("record not found");
			  }
			  
			  if(cs!=null)
				  count=cs.getInt(3);
				 if(count==0)
					  System.out.println("record not found for deletion");
				 else
					 System.out.println("record found and deleted");
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
