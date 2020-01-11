//SelectTest1.java
import java.sql.*;  //jdbc api
import java.util.*;  // utility api

public class SelectTest1
{
	public static void main(String args[])throws Exception{
          //read inputs
		  Scanner sc=new Scanner(System.in);
		  System.out.println("enter start range of student number");
          int start=sc.nextInt();  //gives 500
		  System.out.println("enter end range of student number");
          int end=sc.nextInt(); //gives 600

		  //register JDBC driver s/w
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  //establish the connection
		  Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		  //create JDBC Statement object
		  Statement st=con.createStatement();
		  //prepare SQL Query
		     //select * from student where sno>=500 and sno<=600
		  String query="SELECT * FROM STUDENT WHERE SNO>="+start+" AND SNO<="+end;
            System.out.println(query);
		  //send and execute SQL query in DB s/w
		  ResultSet rs=st.executeQuery(query);
		  //process the ResultSet obj
		 boolean flag=false;

		  while(rs.next()!=false){
			  flag=true;
			    System.out.println(rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
		  }

		  if(flag)
   			  System.out.println("Records found and displayed");			  
		  else
			   System.out.println("Records not found");


		  //close jdbc objs
		  rs.close();
		  st.close();
		  con.close();

	}
}
