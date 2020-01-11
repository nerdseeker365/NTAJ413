package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;



/*CREATE OR REPLACE PROCEDURE P_ADD1 
( X IN NUMBER,Y IN NUMBER,Z OUT NUMBER) AS 
BEGIN
  Z:=X+Y;
 END;
*/
public class CsProcedureTest1 {
  private  static final String CALL_PROCEDURE="{CALL P_ADD(?,?,?) }";
	public static void main(String[] args) {
		Scanner sc=null;
		int first=0,second=0;
		Connection con=null;
		CallableStatement cs=null;
		int result=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter first number::");
				first=sc.nextInt();
				System.out.println("Enter second number::");
				second=sc.nextInt();
			}
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
			//create CallableStatment object
			if(con!=null)
				cs=con.prepareCall(CALL_PROCEDURE);
			 //register OUt params with JDBC types
			if(cs!=null)
				cs.registerOutParameter(3,Types.INTEGER);
			//set values to IN prams
			 if(cs!=null) {
				 cs.setInt(1,first);
				 cs.setInt(2,second);
			 }
			 //execute PL/SQL procedure
			 if(cs!=null)
				 cs.execute();
			 //gather results from Out Params
			 if(cs!=null)
				 result=cs.getInt(3);
			 //display the result
			 System.out.println("Sum is::"+result);
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
		}
	}
}
