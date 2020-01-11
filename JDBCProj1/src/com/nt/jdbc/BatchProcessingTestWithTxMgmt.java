package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchProcessingTestWithTxMgmt {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		int result[]=null;
		boolean flag=false;
		try {
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//disable autoCommit  (Begin Tx)
			   if(con!=null)
				   con.setAutoCommit(false);
			//create Statement object
			if(con!=null)
				st=con.createStatement();
			//add queries to the batch
			if(st!=null) {
				st.addBatch("insert into student values(8874,'karan','hyd',67.88)");
				st.addBatch("update student set sadd='delhi7' where sno<=500");
				st.addBatch("delete from student where sno<=100");
				st.addBatch("update employee set esalary=esalary+1000 where eadd='VIZAG'");
			}
			//execute the batch
			if(st!=null)
			  result=st.executeBatch();
			//process the result
			/*	if(result!=null) {
					for(int i=0;i<result.length;++i) {
						total=total+result[i];
					}//for
					System.out.println("no.of records that are effeced::"+total);
				}//if
			*/	
			//perform TxMgmt
			if(result!=null) {
				for(int i=0;i<result.length;++i) {
					if(result[i]==0) {
						flag=true;
						break;
					}//if
				}//for
				if(flag==true) {
					con.rollback();
					System.out.println("Tx rollbed back");
				}
				else {
					con.commit();
					System.out.println("Tx committed");
				}
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
