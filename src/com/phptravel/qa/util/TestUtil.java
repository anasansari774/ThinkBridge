package com.phptravel.qa.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.FileHandler;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.phptravel.qa.baseclass.BaseClass;

public class TestUtil extends BaseClass{
public static long pageload=5000;
public static long implictWait=5000;

public static String[] date(){
	String[] date1=new String[2];
	Date d=new Date();
	SimpleDateFormat format=new SimpleDateFormat("dd-MM-YYYY");
	date1[0]=format.format(d);
	//System.out.println(today);
	Calendar cal=Calendar.getInstance();
	cal.add(Calendar.DAY_OF_MONTH,7);
	date1[1]=format.format(cal.getTime());
	return date1;
}

public static void screenshot(){
	try{
    String name=new Date().toString().replaceAll("[ :]", "_")+".jpg";
	File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	org.openqa.selenium.io.FileHandler.copy(src, new File(System.getProperty("user.dir")+"\\IssueScreenshot\\"+name));
	}                                                                         
	catch(Exception e){
		e.getCause();
		e.printStackTrace();
	}
}
}
