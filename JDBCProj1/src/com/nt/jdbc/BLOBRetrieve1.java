package com.nt.jdbc;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class BLOBRetrieve1 {
  private static final String  BLOB_RETRIEVE_QUERY="SELECT APPID,APPNAME,AGE,GENDER,PHOTO FROM MATRIMONY_PROFILE WHERE APPID=?";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		InputStream is=null;
		OutputStream os=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter PErsn Id::");
				no=sc.nextInt();
			}
			/*	//register JDBC driver s/w
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//establish the connection
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");*/
			
			//register JDBC driver s/w
			Class.forName("com.mysql.cj.jdbc.Driver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///ntaj413db","root","root");

			//create PreparedStatement object
			if(con!=null)
				ps=con.prepareStatement(BLOB_RETRIEVE_QUERY);
			//set query param value
			if(ps!=null)
				ps.setInt(1, no);
			//execute the Query
			if(ps!=null)
				rs=ps.executeQuery();
			//process the ResultSet
			if(rs!=null) {
				if(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getInt(3)+"  "+rs.getString(4));
					is=rs.getBinaryStream(5);
				}
				else {
					System.out.println("Record not found");
					return;
				}
			}//if
			//create OutputStream pointing to dest file
			os=new FileOutputStream("new_img.jpg");
			//write buffer based logic using stream to store retrieved content
			// into dest file
			if(is!=null && os!=null) {
				IOUtils.copy(is, os);
				System.out.println("Photo retrived succesfully");
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
			System.out.println("BLOBRetrieve.finally block");
			//close jdbc objs
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
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
				if(is!=null)
					is.close();
			}
			catch(IOException ioe) {
				ioe.printStackTrace();
			}
			
			try {
				if(os!=null)
					os.close();
			}
			catch(IOException ioe) {
				ioe.printStackTrace();
			}
			
		}//finally
	}//main
}//class
