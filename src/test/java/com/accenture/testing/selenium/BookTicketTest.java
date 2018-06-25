package com.accenture.testing.selenium;

import org.testng.annotations.Test;

import pages.LoginPageObject;
import utility.ExcelUtility;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;

public class BookTicketTest 
{
 private WebDriver driver;
  @Test(priority=1,dataProvider="logindata")
  public void login(String txtusername,String txtpwd)
  {
	  LoginPageObject.uname.sendKeys(txtusername);
	  LoginPageObject.pwd.sendKeys(txtpwd);
	  LoginPageObject.login.click();
	  
  }
  @BeforeTest
  public void beforeTest() 
  {
	  System.setProperty("webdriver.gecko.driver","C:\\selenium\\Selenium Drivers\\geckodriver-v0.20.1-win64\\geckodriver.exe");
      driver=new FirefoxDriver();
      driver.get("http://newtours.demoaut.com");
      PageFactory.initElements(driver, LoginPageObject.class);
  }

  @AfterTest
  public void afterTest()
  {
	  
  }
  
  @DataProvider(name="logindata")
  public String[][] loginData()throws Exception
   {
	  ExcelUtility.setExcelPath("Sheet1","C:\\selenium\\testdata.xlsx");
	  String username=ExcelUtility.getCellData(1, 1);
	  String password=ExcelUtility.getCellData(1,2);
	 
	 return new String[][] {
		 new String[] {username,password},
		 };
   }
	  
	  
}


