//SelectTest3.java
import java.sql.*; //jdbc api
import java.util.*;  //utility api

public class SelectTest3
{
	public static void main(String args[])throws Exception{
		//read inputs
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Start range of salary ::");
		int start=sc.nextInt(); //gives 2000
		System.out.println("Enter end range of salary::");
		int end=sc.nextInt(); //gives 3000

		//register JDBC driver s/w
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//establish the connection
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		//create JDBC STatement object
		Statement st=con.createStatement();
		//prepare SQL Query
		     //select empno,ename,job,sal from emp where sal>=2000 and sal<=5000
		String query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL>="+start+" AND SAL<="+end;
		System.out.println(query);
		//send and execute SQL Query in DB s/w
		ResultSet rs=st.executeQuery(query);
		//process the ResultSet obj
		boolean flag=false;
		while(rs.next()){
			flag=true;
            System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
		}
		if(flag==true)
			 System.out.println("Records found and displayed");
		else
			System.out.println("Records not found");

		//close jdbc objs
		rs.close();
		st.close();
		con.close();
	}//main
}//class