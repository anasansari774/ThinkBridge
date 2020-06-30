package com.phptravel.qa.baseclass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.phptravel.qa.util.TestUtil;


public class BaseClass {
	public static WebDriver driver;
	public static Properties prop;
	
	public BaseClass(){
		try{
			
		prop=new Properties();
		
		FileInputStream ip=new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\phptravel\\qa\\testdata\\config.properties");		
		
		prop.load(ip);
	}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static void initializer()
	{
		String browser=prop.getProperty("Browser");
		
		if(browser.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver.exe");
			driver=new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.pageload, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.implictWait, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
	}
	
	

}
