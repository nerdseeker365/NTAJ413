package com.nt.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesTest {

	public static void main(String[] args) {
		InputStream is=null;
		Properties props=null;
		try {
			//Locate properties file
			is=new FileInputStream("src/com/nt/commons/info.properties");
			//Load properties info into java.util.Properties collection
			props=new Properties();
			props.load(is);
			System.out.println("props data::"+props);
			System.out.println("name key value::"+props.getProperty("name"));
			System.out.println("age key value ::"+props.getProperty("age"));
		}//try
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(is!=null)
					is.close();
			}//try
			catch(IOException ioe) {
				ioe.printStackTrace();
			}
		}//finally
	}//main
}//class
