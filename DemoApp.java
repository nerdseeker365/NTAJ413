//DemoApp.java

class Test
{
	static{
		System.out.println("Test:: static block");
	}
	public Test(){
         System.out.println("Test:: 0-param constructor");
	}
}

class Demo
{
	static{
		  System.out.println("Demo:: static block");
	}
	public Demo(){
         System.out.println("Demo:: 0-param constructor");
	}
}

public class DemoApp
{
	static{
		System.out.println("DemoApp:static block");
	}
	public static void main(String args[])throws Exception{
		System.out.println("DemoApp: start of main(-) method");
		 Test t1=new Test();
		 Test t2=new Test();
		 Class.forName("Demo");
		 Class.forName("Demo");
		 Class.forName("Test");
		 	 System.out.println("DemoApp: end of main(-) method");
	}//main
}//class

//cmd> javac  DemoApp.java
//cmd>java  DemoApp