package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/*CREATE SEQUENCE  "SYSTEM"."TKTID_SEQ"  MINVALUE 1 MAXVALUE 1000000 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;

CREATE TABLE "SYSTEM"."INDIAN_RAILWAY_RESERVATION" 
   (	"TKTID" NUMBER(10,0) NOT NULL ENABLE, 
	"PSNGR_NAME" VARCHAR2(20 BYTE), 
	"SOURCE" VARCHAR2(20 BYTE), 
	"DESTINATION" VARCHAR2(20 BYTE), 
	"COLUMN1" FLOAT(126), 
	 CONSTRAINT "INDIAN_RAILWAY_RESERVATION_PK" PRIMARY KEY ("TKTID"))
 
 */

public class PsBatchGroupReservationTest {
   private static final String  INSERT_QUERY="INSERT INTO INDIAN_RAILWAY_RESERVATION VALUES(TKTID_SEQ.NEXTVAL,?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		int count=0;
		Connection con=null;
		PreparedStatement ps=null;
		String sourcePlace=null,destPlace=null;
		float fare=0.0f;
		String psgnrName=null;
		int result[]=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Passengers Count::");
				count=sc.nextInt();
				System.out.println("Source Place:: ");
				sourcePlace=sc.next();
				System.out.println("Dest Place:: ");
				destPlace=sc.next();
				System.out.println("Ticket Fare:: ");
				fare=sc.nextFloat();
			}
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create PreparedStatement object
			if(con!=null)
				ps=con.prepareStatement(INSERT_QUERY);
			//read passegener names of group and add multiple sets of query param values to the batch
			if(ps!=null && sc!=null) {
				for(int i=1;i<=count;++i) {
					System.out.println("enter "+i+" passenger name::");
					psgnrName=sc.next();
					//add each set of query param values to the batch
					ps.setString(1,psgnrName);
					ps.setString(2,sourcePlace);
					ps.setString(3,destPlace);
					ps.setFloat(4, fare);
					ps.addBatch();
				}//for
			}//if
			
			//execute the batch
			if(ps!=null)
				result=ps.executeBatch();
			
			//process the 
			 if(result!=null)
				   System.out.println("Group reservation successful");
			 else
				 System.out.println("Group Reservation failed");
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
