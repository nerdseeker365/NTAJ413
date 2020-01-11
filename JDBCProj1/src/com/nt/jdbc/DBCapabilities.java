package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class DBCapabilities {

	public static void main(String[] args) {
		Connection con=null;
		DatabaseMetaData dbmd=null;
		try {
			//register  jdbc driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create DataBaseMetaData object
			if(con!=null)
				dbmd=con.getMetaData();
			//get More Info About DB s/w
			if(dbmd!=null) {
				System.out.println("Db s/w name::"+dbmd.getDatabaseProductName());
				System.out.println("Db s/w product version::"+dbmd.getDatabaseProductVersion());
				System.out.println("DB s/w Major Version::"+dbmd.getDatabaseMajorVersion());
				System.out.println("DB s/w Minor Version::"+dbmd.getDatabaseMinorVersion());
				System.out.println("All SQL keywords ::"+dbmd.getSQLKeywords());
				System.out.println("All Numberic Functions::"+dbmd.getNumericFunctions());
				System.out.println("All String functions::"+dbmd.getStringFunctions());
				System.out.println("All system function::"+dbmd.getSystemFunctions());
				System.out.println(" Max chars in db table name::"+dbmd.getMaxTableNameLength());
				System.out.println("Max chars in column name ::"+dbmd.getMaxColumnNameLength());
				System.out.println("Max Tables in Select Query::"+dbmd.getMaxTablesInSelect());
				System.out.println("supports PL/SQL procededures?::"+dbmd.supportsStoredProcedures());
				System.out.println("max connections count::"+dbmd.getMaxConnections());
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
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}//finally

	}//main
}//class
