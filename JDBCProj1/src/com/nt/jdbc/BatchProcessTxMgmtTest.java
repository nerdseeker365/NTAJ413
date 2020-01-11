package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*CREATE TABLE "SYSTEM"."JDBC_BANK_ACCOUNT" 
(	"ACNO" NUMBER(10,0) NOT NULL ENABLE, 
	"HOLDERNAME" VARCHAR2(20 BYTE), 
	"BALANCE" FLOAT(126), 
	 CONSTRAINT "JDBC_BANK_ACCOUNT_PK" PRIMARY KEY ("ACNO"))
*/

public class BatchProcessTxMgmtTest {

	public static void main(String[] args) {
		Scanner sc=null;
		int srcAcno=0,destAcno=0;
		float amount=0.0f;
		Connection con=null;
		Statement st=null;
		int result[]=null;
		boolean flag=false;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter source account number::");
				srcAcno=sc.nextInt();
				System.out.println("Enter Dest Account number::");
				destAcno=sc.nextInt();
				System.out.println("Enter Amount to transfer::");
				amount=sc.nextFloat();
			}
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establis the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//begin Tx
			if(con!=null)
				con.setAutoCommit(false);
			
			//create JDBC Statement object
			if(con!=null)
				st=con.createStatement();
			//add queries to batch
			if(st!=null) {
				//for withdraw operation
				st.addBatch("UPDATE JDBC_BANK_ACCOUNT SET BALANCE=BALANCE-"+amount+" WHERE ACNO="+srcAcno);
				//for deposite operation
				st.addBatch("UPDATE JDBC_BANK_ACCOUNT SET BALANCE=BALANCE+"+amount+" WHERE ACNO="+destAcno);
			}
			//execute the batch of queries
			if(st!=null) 
				result=st.executeBatch();
			//process the Results (Perform Tx mgmt)
			if(result!=null) {
				for(int i=0;i<result.length;++i) {
					if(result[i]==0) {
						flag=true;
						break;
					}//if
				}//for
			}//if
			
		}//try
		catch(SQLException se) {
			flag=true;
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			flag=true;
			cnf.printStackTrace();
		}
		catch(Exception e) {
			flag=true;
			e.printStackTrace();
		}
		finally {
		 try {	
			if(flag==true) {
				con.rollback();
				System.out.println("Tx Rolledback-->Money not transfered");
			}
			else {
				con.commit();
				System.out.println("Tx Committed-->Money Transffered");
			}
		 }//try
		 catch(SQLException se) {
			 se.printStackTrace();
		 }
		 //close jdbc objs
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
