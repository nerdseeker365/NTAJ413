package com.nt.jdbc;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.net.aso.p;

/*SQL>CREATE TABLE "SYSTEM"."JOBPORTAL_INFO" 
        (	"JSID" INT(10) PRIMARY KEY AUTOINCREMENT, 
	"JSNAME" VARCHAR(20), 
	"ADDRS" VARCHAR(20), 
	"QLFY" VARCHAR(20), 
	"RESUME" LONGTEXT) 
*/
public class CLOBInsertMySQL {
   private static final String  CLOB_INSERT_QUERY="INSERT INTO JOBPORTAL_INFO(JSNAME,ADDRS,QLFY,RESUME) VALUES(?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		String name=null,addrs=null,qlfy=null,resumePath=null;
		Connection con=null;
		PreparedStatement ps=null;
		File file=null;
		long length=0;
		Reader reader=null;
		int count=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter JobSeeker Name::");
				name=sc.next();
				System.out.println("Enter Addrs::");
				addrs=sc.next();
				System.out.println("Enter Qualification::");
				qlfy=sc.next();
				System.out.println("Enter resume file Path::");
				resumePath=sc.next();
			}
			  //register  JDBC driver s/w
			Class.forName("com.mysql.cj.jdbc.Driver");
			//establish the connection
			 con=DriverManager.getConnection("jdbc:mysql:///ntaj413db","root","root");
			 //create PreparedStatement object
			 if(con!=null)
				 ps=con.prepareStatement(CLOB_INSERT_QUERY);
			 // GEt resume file length
			     file=new File(resumePath);
			     length=file.length();
			    //create Reader obj pointing to the file..
			     reader=new FileReader(file);
			     //set values to query params
			     if(ps!=null) {
			    	 ps.setString(1, name);
			    	 ps.setString(2,addrs);
			    	 ps.setString(3,qlfy);
			    	 ps.setCharacterStream(4, reader,length);
			     }
			     //execute the Query
			     if(ps!=null)
			    	 count=ps.executeUpdate();
			     //process the result
			       if(count==0)
			    	   System.out.println("record insertion failed");
			       else
			    	   System.out.println("record insertion succeded");
		}//try
		catch(SQLException se) {
			System.out.println("record insertion failed");
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
			try {
				if(reader!=null)
					reader.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally
	}//main
}//class
