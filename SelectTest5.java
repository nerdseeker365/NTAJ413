//SelectTest5.java
package com.nt.jdbc;
/*  App to  get Dept Details  based on the given department number */


import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest5
{
	public static void main(String args[]){
          Scanner sc=null;
		  int dno=0;
		  Connection con=null;
		  Statement st=null;
		  String query=null;
		  ResultSet rs=null;
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
              System.out.println("Enter dept number:");
               dno=sc.nextInt();
			}
			//register  JDBC dirver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create STatement object
			if(con!=null)
                st=con.createStatement();
			//prepare SQL Query 
			          //select * from dept where deptno=10
				query="SELECT * FROM DEPT WHERE DEPTNO="+dno;
				System.out.println(query);
			//send and execute SQL Query in Db s/w
			if(st!=null)
                   rs=st.executeQuery(query);
			  //process the ReusltSet obj
			  if(rs!=null){
                   if(rs.next()){
                      System.out.println(rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getString(3));
				   }//if
				   else{
					   System.out.println("records not found");
				   }
			  }//if
		}//try
		catch(SQLException se){
			System.out.println(se.toString());
		}
		catch(ClassNotFoundException cnf){
			 cnf.printStackTrace();
		}
		catch(Exception  e){
             System.out.println(e.toString());
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
//>javac  -d   .   SelectTest5.java
//>java  com.nt.jdbc.SelectTest5