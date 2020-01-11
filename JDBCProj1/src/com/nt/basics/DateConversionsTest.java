package com.nt.basics;

import java.text.SimpleDateFormat;

public class DateConversionsTest {

	public static void main(String[] args)throws Exception {
		String d1="40-30-1990"; //dd-MM-yyyy
		
		//converting String date value to java.util.Date class object
		SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date ud1=sdf1.parse(d1);
		System.out.println("util date::"+ud1);
		
		//converting  java.util.Date class obj to java.sql.Date class object
		long ms=ud1.getTime(); //gives  MS representing date and time
		                                             //w.r.t epoach standard jan 1st 1970 00:00 hrs
		java.sql.Date sqd1=new java.sql.Date(ms);
		System.out.println("sql date::"+sqd1);
		
		//if  the String date value is there in yyyy-MM-dd pattern then
		//it can be converted directly to java.sql.Date class obj with out
		//converting it java.util.Date class object
		String d2="1991-11-30"; //yyyy-MM-dd
		java.sql.Date sqd2=java.sql.Date.valueOf(d2);
		System.out.println("sql date value ::"+sqd2);
		
		//converting java.sql.Date class obj to java.util.Date class object
		java.util.Date ud2=sqd2;
		System.out.println("util date::"+ud2);
		
		//converting java.util.Date class obj to String date value
		SimpleDateFormat sdf2=new SimpleDateFormat("MMM-dd-yyyy");
		String d3=sdf2.format(ud2);
		System.out.println("String date::"+d3);
		
	}
}
