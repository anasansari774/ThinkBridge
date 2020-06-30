package com.phptravel.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.phptravel.qa.baseclass.BaseClass;
import com.phptravel.qa.pages.Homepage;
import com.phptravel.qa.util.TestUtil;

import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;

public class HomepageTestcase extends BaseClass{
	Homepage home;
	
	public HomepageTestcase() {
		super();
		}	
	
	@BeforeMethod
	public void setup(){
		initializer();
		home= new Homepage();
	}
	
	@Test
	public void title(){
		String title=home.getTitle();
		Assert.assertEquals(title, "PHPTRAVELS | Travel Technology Partner");
        home.selectFlightButtonAndRoundTrip();
		home.selectBusinessClass();
		home.selectFromAndToValue();
		home.setDepatureAndReutrnDate();
		home.numberOfPassengerAndClickOnSave();
		boolean flag=home.list();
		Assert.assertTrue(flag);	
    }

    @AfterMethod
    public void tearDown(ITestResult result){
    	if(ITestResult.FAILURE==result.getStatus()){
    		TestUtil.screenshot();
    	}
	   driver.quit();
    }
}




