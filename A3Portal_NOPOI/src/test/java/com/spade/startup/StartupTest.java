package com.spade.startup;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.runner.JUnitCore;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.spade.filereader.ReadWER;
import com.spade.readexcel.ExcelLibrary;
public class StartupTest {
	
	  public static WebDriver driver=null;
	
	  
	  
  @Test
  public void TestStartUp () throws IOException, EncryptedDocumentException, InvalidFormatException, InterruptedException {
	  

	  	
	  ReadWER wer=new ReadWER();

	  String browsername=wer.getelemnetfromWER("BROWSER", ".\\config\\config.properties");
	  String Url=wer.getelemnetfromWER("URL", ".\\config\\config.properties");



	  if(browsername.equals("chrome")) {
	  	System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");
	  	driver=new ChromeDriver();
	  		
	  }else if(browsername.equals("firefox")) {
	  	System.setProperty("webdriver.gecko.driver", ".\\geckodriver.exe");
	  driver=new FirefoxDriver();

	  }


	  driver.manage().window().maximize();
	  driver.get(Url);
	  driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);


	  ExcelLibrary lib=new ExcelLibrary();
	  int totaltests=lib.Getrowcount("scenarios");
	  for(int testscenario=1;testscenario<=totaltests;testscenario++) {
	  	
	  	String executionstatus=ExcelLibrary.getexcelStringvalue("scenarios", testscenario, 1);
	  	System.out.println("test Module execution status"+executionstatus);
//	  	System.out.println(executionstatus);
	  	if(executionstatus.equalsIgnoreCase("yes")) {
	  String scenarioname=ExcelLibrary.getexcelStringvalue("scenarios", testscenario, 0);
	  System.out.println("********************scenario name******************************"+scenarioname);

	  JUnitCore runTest = new JUnitCore();
	  @SuppressWarnings("rawtypes")
	  Class c;
	  try {
	  	
	  	c = Class.forName("com.spade.testcases."+scenarioname);
	  	
	  	System.out.println(c);
	  	runTest.run(c);
	  	} catch (ClassNotFoundException e) {
	  	// TODO Auto-generated catch block
	  	e.printStackTrace();
	       }	

	  }
	  	
	  	}
	 Thread.sleep(2000);
	 driver.quit();

	  
  }
}
