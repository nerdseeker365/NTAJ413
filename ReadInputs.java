//ReadInputs.java

import java.util.*;
import java.io.*;

public class ReadInputs
{
	public static void main(String args[])throws Exception{
        //cmd line arg
		String name1=args[0];
		//user-defined System property
		String name2=System.getProperty("my.name");
		//Buffered Reader
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("etner name3:");
		String name3=br.readLine();
		//Using Scanner
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter name4:");
		String name4=sc.next();
		//Using Console
		Console cons=System.console();
		System.out.println("enter name5:");
		String  name5=cons.readLine();

		System.out.println(name1+"  "+name2+"  "+name3+"  "+name4+" "+name5);
	}
}
//>javac ReadInputs.java
//>java  -Dmy.name=raja2          ReadInputs  raja1
