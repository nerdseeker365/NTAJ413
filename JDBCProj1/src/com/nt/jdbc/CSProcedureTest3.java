package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*CREATE TABLE "SYSTEM"."USERINFO" 
(	"USERNAME" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"PASSWORD" VARCHAR2(20 BYTE), 
	 CONSTRAINT "USERINFO_PK" PRIMARY KEY ("USERNAME"))
	 
 CREATE OR REPLACE PROCEDURE P_AUTHENTICATION 
(
  UNAME IN VARCHAR2 
, PWD IN VARCHAR2 
, RESULT OUT VARCHAR2 
) AS 
  CNT number(4);
BEGIN
   SELECT COUNT(*) INTO CNT FROM USERINFO WHERE USERNAME=UNAME AND PASSWORD=PWD;
  
  IF(CNT<>0) THEN
      RESULT:='VALID CREDENTIALS';
  ELSE
    RESULT:='INVALID CREDENTIALS';
    END IF;
END P_AUTHENTICATION;

*/
public class CSProcedureTest3 {
   private  static final String CALL_PROCEDURE="{CALL P_AUTHENTICATION(?,?,?)}"; 
	public static void main(String[] args) {
		Scanner sc=null;
		String uname=null,pass=null;
		Connection con=null;
		CallableStatement cs=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter username::");
				uname=sc.nextLine();
				System.out.println("Enter Password::");
				pass=sc.nextLine();
			}
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create CallableStatement object
			if(con!=null)
				cs=con.prepareCall(CALL_PROCEDURE);
			//register OUT params with JDBC data types
			if(cs!=null) 
				cs.registerOutParameter(3,Types.VARCHAR);
			//set input values to Query params
			   if(cs!=null) {
				   cs.setString(1,uname);
				   cs.setString(2,pass);
			   }
             //execute the PL/SQL procedure
			   if(cs!=null)
				   cs.execute();
			   //gather results from OUT params
			   if(cs!=null) 
				   System.out.println("Result:::"+cs.getString(3));
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
		}//finally
	}//main
}//class
