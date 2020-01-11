package com.nt.jdbc;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CLOBRetrieve {
   private static final String GET_JOBSEEKER_INFO_QUERY="SELECT JSID,JSNAME,ADDRS,QLFY,RESUME FROM JOBPORTAL_INFO WHERE JSID=?";
	public static void main(String[] args) {
		Scanner sc=null;
		int jsId=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Reader reader=null;
		Writer writer=null;
		char[] buffer=new char[1024];
		int charsRead=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter JobSeeker Id::");
				jsId=sc.nextInt();
			}
			/*	//register JDBC driver s/w
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//establish the connection
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");*/
			
			//register JDBC driver s/w
			Class.forName("com.mysql.cj.jdbc.Driver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///ntaj413db", "root", "root");
			
				//create PreparedStatement object
				if(con!=null)
					ps=con.prepareStatement(GET_JOBSEEKER_INFO_QUERY);
				//set values to query params
				if(ps!=null)
					ps.setInt(1,jsId);
				//execute the Query
				if(ps!=null)
					rs=ps.executeQuery();
				  //process ResultSet
				if(rs!=null) {
					if(rs.next()) {
						System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4));
						reader=rs.getCharacterStream(5);
					}
				else {
					System.out.println("record not found");
					return;
				 }
				}//if
					
			//create Writer Steam pointing to dest file
				writer=new FileWriter("new_resume.txt");
				//write buffer based logic using stream to write recieved data
				// to dest file 
				if(reader!=null && writer!=null) {
					while((charsRead=reader.read(buffer))!=-1) {
						writer.write(buffer,0,charsRead);
					}//while
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
				if(reader!=null)
					reader.close();
			}
			catch(IOException ioe) {
				ioe.printStackTrace();
			}
			
			try {
				if(writer!=null)
					writer.close();
			}
			catch(IOException ioe) {
				ioe.printStackTrace();
			}
			
		}//finally
	}//main
}//class
