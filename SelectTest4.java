package com.nt.jdbc;
/*  App to  emp details based on given  3 desgs */
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class SelectTest4
{
    public static void main(String args[]){
		Scanner sc=null;
		String desg1=null,desg2=null,desg3=null;
		String cond=null;
		Connection con=null;
		Statement st=null;
		String query=null;
		ResultSet rs=null;
		boolean flag=false;
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
               System.out.println("Enter Desg1:");
			   desg1=sc.next();  //gives CLERK
			   System.out.println("Enter Desg2:");
			   desg2=sc.next();  //gives MANAGER
   			   System.out.println("Enter Desg3:");
			   desg3=sc.next();  //gives SALESMAN
			}
			//convert input values as required for the SQL query
			              // ('CLERK','MANAGER','SALESMAN')
                 cond="('"+desg1+"','"+desg2+"','"+desg3+"')";  //gives ('CLERK','MANAER','SALESMAN')
                   System.out.println(cond);
			
			//register  jdbc driver  (optional)
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			  //establish the connection
              con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
               //create JDBC STatement object
			   if(con!=null)
				   st=con.createStatement();
			   //prepare SQL Query
			          // select empno,ename,job,sal from emp where job in('CLERK','MANMAGER','SALESMAN') order by job
					  query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE JOB IN"+cond+" ORDER BY JOB";
					  System.out.println(query);
			   //send and execute SQL query in DB s/w
			      if(st!=null)
                      rs=st.executeQuery(query);
				  //process the Resultset object
				  if(rs!=null){
					  while(rs.next()){
						   flag=true;
                         System.out.println(rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
					  }//while
					  if(flag)
						   System.out.println("Records are found displayed");
					  else
						  System.out.println("Records are not found");
				  }//if
		}//try
		 catch(SQLException se){  //To handle known exception
			 se.printStackTrace();
		 }
		 catch(ClassNotFoundException cnf){  //To handle known exception
			 cnf.printStackTrace();
		 }
		 catch(Exception e){   //To handle unknown exceptions
			 e.printStackTrace(); 
		 }
		 finally{
			 //close jdbc objs
			 try{
				 if(rs!=null)
					 rs.close();
			 }
			 catch(SQLException se){
				 se.printStackTrace();
			 }
			  try{
				 if(st!=null)
					 st.close();
			 }
			 catch(SQLException se){
				 se.printStackTrace();
			 }
			 try{
				 if(con!=null)
					 con.close();
			 }
			 catch(SQLException se){
				 se.printStackTrace();
			 }
			 try{
				 if(sc!=null)
					 sc.close();
			 }
			 catch(Exception e){
				 e.printStackTrace();
			 }
			
		 }//finally
	}//main
}//class
//>javac    -d    .     SelectTest4.java
//>java  com.nt.jdbc.SelectTest4


