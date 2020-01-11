package com.nt.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/*CREATE TABLE "MATRIMONY_PROFILE" 
(	"APPID" INTEGER(10) AUTOINCREMENT PRIMARY KEY, 
	"APPNAME" VARCHAR(20), 
	"AGE" INT(3), 
	"GENDER" VARCHAR(10), 
	"PHOTO" BLOB) 	 
*/

public class BLOBInsertTestMySQL {
	 private static final String  INSERT_QUERY="INSERT INTO MATRIMONY_PROFILE(APPNAME,AGE,GENDER,PHOTO) VALUES(?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		String name=null;
		int age=0;
		String gender=null,photoPath=null;
		Connection con=null;
		PreparedStatement ps=null;
		File file=null;
		long length=0;
		InputStream is=null;
		int result=0;
		
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Applicant name::");
				name=sc.next();
				System.out.println("Enter Application age::");
				age=sc.nextInt();
				System.out.println("Enter Applicant Gender::");
				gender=sc.next();
				System.out.println("Etner Applicant's photo Path");
				photoPath=sc.next();
			}
			 //register  JDBC driver s/w
			  Class.forName("com.mysql.cj.jdbc.Driver");
			  //establish the connection
			  con=DriverManager.getConnection("jdbc:mysql:///ntaj413db", "root", "root");
			  //create PreparedStatement object
			  if(con!=null)
				  ps=con.prepareStatement(INSERT_QUERY);
			  //create java.io.File class obj holding file name
			   file=new File(photoPath);
			   //get the lenght of the file
			   length= file.length();
			   //create InputStream locating the file
			   is=new FileInputStream(file);
			  //set values to Query params
			   if(ps!=null) {
				   ps.setString(1,name);
				   ps.setInt(2,age);
				   ps.setString(3,gender);
				   //ps.setBinaryStream(4,is);
				   //ps.setBinaryStream(4,is,length);
				   //ps.setBlob(4, is);
				   ps.setBlob(4,is, length);
			   }
			   //execute the Query
			   if(ps!=null) 
				   result=ps.executeUpdate();
			   //process the result
			    if(result==0)
			    	System.out.println("Record not inserted");
			    else
			    	System.out.println("record inserted");
		}//try
		catch(SQLException se) {
			se.printStackTrace();
			System.out.println("record not inserted");
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
				if(ps!=null)
					ps.close();
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
