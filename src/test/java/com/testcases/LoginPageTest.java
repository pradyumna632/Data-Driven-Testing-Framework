package com.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.pages.LoginPage;
import com.util.XLUtility;

public class LoginPageTest extends TestBase{
	
	LoginPage loginPg;
	XLUtility xlutil;
	
	public LoginPageTest() {
		//call super class constructor 
		// because we want to load properties file to get URL
		// if we don't do it it will give nullPointerException
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		// Launch the browser
		initialization();
		// Create object of RegisterPage Class to access it's methods
		loginPg = new LoginPage();
		
	}
	
	@Test(dataProvider = "Login")
	public void loginTest(String user, String pass) 
	{
		
		loginPg.login(user, pass);
		String actualUrl= loginPg.getCurrentPageUrl();
		String expectedUrl= "https://www.saucedemo.com/inventory.html";
		Assert.assertEquals(actualUrl,expectedUrl);
		
		
		
//		 if (loginPg.getCurrentPageUrl().equals(expectedUrl)) {
//			 
//			 try {
//				 
//				 	xlutil.setCellData("loginTestData",1 ,2, "Pass");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			 
//         } else {
//        	 
//        	 try {
// 				xlutil.setCellData("loginTestData", 1, 2, "Fail");
// 			} catch (IOException e) {
// 				e.printStackTrace();
// 			}
//        	 
//         }
		
		
	}
	
	@DataProvider(name = "Login")
	public String[][] getData() throws IOException{
		
		xlutil = new XLUtility(props.getProperty("swagLabsTestDataPath"));
		int totalrows=xlutil.getRowCount("loginTestData");
		int totalcols=xlutil.getCellCount("loginTestData",1);	
//		xlutil.addColumn("loginTestData", "Status");		
		String loginData[][]=new String[totalrows][totalcols];
			
		
		for(int i=1;i<=totalrows;i++) //1
		{
			for(int j=0;j<totalcols;j++) //0
			{
				loginData[i-1][j]=xlutil.getCellData("loginTestData", i, j);
			}
				
		}
		
		
		return loginData;
		
		
	}
	
	
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
