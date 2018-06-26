package com.accenture.testing.selenium;

import org.testng.annotations.Test;

import pages.LoginPageObject;
import utility.ExcelUtility;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class BookTicketTest 
{
 private WebDriver driver;
 private int colNo=2;
 private static int rowNo=0;
 
  @Test(priority=1,dataProvider="logindata")
  public void login(String txtusername,String txtpwd) throws IOException
  {
	  try
	  {
	  LoginPageObject.uname.sendKeys(txtusername);
	  LoginPageObject.pwd.sendKeys(txtpwd);
	  LoginPageObject.login.click();
	  
	 WebDriverWait wait =new WebDriverWait(driver, 60);
	 wait.until(ExpectedConditions.presenceOfElementLocated(By.name("tripType")));
	  
	  Assert.assertEquals("Find a Flight: Mercury Tours:", driver.getTitle());
	ExcelUtility.setExcelData(rowNo, colNo, "pass");
	  
	  driver.get("http://newtours.demoaut.com");
	  
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.name("userName")));
	  }catch(AssertionError e)
	  {
		  ExcelUtility.setExcelData(rowNo, colNo, "fail");
	  }
	  finally
	  {
		  rowNo++;
	  }
  }
  
  @Test(priority=2)
  public void findFlightDetails()
  {
	  driver.findElement(By.name("tripType")).click();
	  
	  Select passenger=new Select(driver.findElement(By.name("passCount")));
	  passenger.selectByValue("1");
	  
	  Select DepartingFrom=new Select(driver.findElement(By.name("fromPort")));
	  DepartingFrom.selectByValue("London");
	  
	  Select on=new Select(driver.findElement(By.name("fromMonth")));
	  on.selectByValue("9");
	  
	  Select fromdate=new Select(driver.findElement(By.name("fromDay")));
	  fromdate.selectByValue("15");
	  
	  Select toPort=new Select(driver.findElement(By.name("toPort")));
	  toPort.selectByValue("Paris");
	  
	  Select toMonth=new Select(driver.findElement(By.name("toMonth")));
	  toMonth.selectByValue("2");
	  
	  Select toDay=new Select(driver.findElement(By.name("toDay")));
	  toDay.selectByValue("1");
	  
	  driver.findElement(By.name("servClass")).click();
	  
	  Select airline=new Select(driver.findElement(By.name("airline")));
	  airline.selectByVisibleText("Unified Airlines");;
	  
  }
  
  
  
  @BeforeTest
  public void beforeTest() 
  {
	  System.setProperty("webdriver.chrome.driver","C:\\selenium\\Selenium Drivers\\chromedriver_win32(1)\\chromedriver.exe");
      driver=new ChromeDriver();
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
	  /*String username=ExcelUtility.getCellData(1, 1);
	  String password=ExcelUtility.getCellData(1,2);
	 
	 return new String[][] {
		 new String[] {username,password},
		 };*/
	  String excelData[][]=ExcelUtility.getExcelTable();
	  return excelData;
   }
	  
	  
}


