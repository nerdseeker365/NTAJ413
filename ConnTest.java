//ConnTest.java

import java.sql.*;

public  class ConnTest 
{
	public static  void main(String args[])throws Exception{

         

			  //establish the connection with DB s/w..
			    Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
                 System.out.println("jdbc con obj class name::"+con.getClass());

                //verify the connection
				if(con==null)
					  System.out.println("Connection is not established");
				else
					 System.out.println("Connection is  established");


	} //main
} //class
//>javac ConnTest.java
//>java -Djdbc.drivers=oracle.jdbc.driver.OracleDriver   ConnTest