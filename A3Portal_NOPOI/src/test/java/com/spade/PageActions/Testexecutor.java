package com.spade.PageActions;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.spade.filereader.ReadWER;
import com.spade.readexcel.ExcelLibrary;
import com.spade.startup.StartupTest;

public class Testexecutor {


	public WebElement element=null;
	
public	java.util.List<WebElement> listelement;

public  void executetest(String scenarioname) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		ExcelLibrary lib=new ExcelLibrary();
		ReadWER wer=new ReadWER();
		
int Totalteststeps=	lib.Getrowcount(scenarioname);
System.out.println("listelement" +listelement);
		
for(int teststep=1;teststep<=Totalteststeps;teststep++) {
//System.out.println("teststep"+teststep);
String elementidmethod=	ExcelLibrary.getexcelStringvalue(scenarioname, teststep, 2);
//System.out.println("elementidmethod"+elementidmethod);

String logicalname=		ExcelLibrary.getexcelStringvalue(scenarioname, teststep, 3);
//System.out.println("logicalname"+logicalname);

String elementidvalue=wer.getelemnetfromWER(logicalname, ".\\wer\\wer.properties");
//System.out.println("elementidvalue"+elementidvalue);

String action=	ExcelLibrary.getexcelStringvalue(scenarioname, teststep, 4);
String data=ExcelLibrary.getexcelStringvalue(scenarioname, teststep, 5);

//int intdata=ExcelLibrary.getexcelintvalue(scenarioname, teststep, 5);


//element identification

if(elementidmethod.equals("name")) {
	element=StartupTest.driver.findElement(By.name(elementidvalue));
	if(element==null) {
	lib.Setvalueinxl(scenarioname, teststep, 6, "FAIL");
	lib.Setvalueinxl(scenarioname, teststep, 7, "Element "+logicalname+" not found");
	}
	}

else if(elementidmethod.equals("id")) {
	//System.out.println("under id");
 element=StartupTest.driver.findElement(By.id(elementidvalue));
	lib.Setvalueinxl(scenarioname, teststep, 6, "FAIL");
	lib.Setvalueinxl(scenarioname, teststep, 7, "Element "+logicalname+" not found");
}
if(elementidmethod.equals("cssselector")) {
	element=StartupTest.driver.findElement(By.cssSelector(elementidvalue));
	lib.Setvalueinxl(scenarioname, teststep, 6, "FAIL");
	lib.Setvalueinxl(scenarioname, teststep, 7, "Element "+logicalname+" not found");
}
else if(elementidmethod.equals("xpath")) {
	
	element=StartupTest.driver.findElement(By.xpath(elementidvalue));

	listelement=StartupTest.driver.findElements(By.xpath(elementidvalue));
	lib.Setvalueinxl(scenarioname, teststep, 6, "FAIL");
	lib.Setvalueinxl(scenarioname, teststep, 7, "Element "+logicalname+" not found");
}

/*else if(elementidmethod.equals("xpath")) {
	System.out.println("xpath"+listelemet);
	listelemet=StartupTest.driver.findElements(By.xpath(elementidvalue));
	lib.Setvalueinxl(scenarioname, teststep, 6, "FAIL");
	lib.Setvalueinxl(scenarioname, teststep, 7, "Element "+logicalname+" not found");
}*/



else if(elementidmethod.equals("partiallinkText")) {
	element=StartupTest.driver.findElement(By.partialLinkText(elementidvalue));
	lib.Setvalueinxl(scenarioname, teststep, 6, "FAIL");
	lib.Setvalueinxl(scenarioname, teststep, 7, "Element "+logicalname+" not found");
}
else if(elementidmethod.equals("linkText")) {
	
	element=StartupTest.driver.findElement(By.linkText(elementidvalue));
	lib.Setvalueinxl(scenarioname, teststep, 6, "FAIL");
	lib.Setvalueinxl(scenarioname, teststep, 7, "Element "+logicalname+" not found");
}
else if(elementidmethod.equals("classname")) {
	
	element=StartupTest.driver.findElement(By.className(elementidvalue));
	lib.Setvalueinxl(scenarioname, teststep, 6, "FAIL");
	lib.Setvalueinxl(scenarioname, teststep, 7, "Element "+logicalname+" not found");
}
else if(elementidmethod.equals("tagname")) {
	
	element=StartupTest.driver.findElement(By.tagName(elementidvalue));
	lib.Setvalueinxl(scenarioname, teststep, 6, "FAIL");
	lib.Setvalueinxl(scenarioname, teststep, 7, "Element "+logicalname+" not found");
}


// Action



if(action.equals("sendkeys")) {
	//System.out.println("under sendkeys");
if(element!=null) {
	element.sendKeys(data);
	lib.Setvalueinxl(scenarioname, teststep, 6, "PASS");
	lib.Setvalueinxl(scenarioname, teststep, 7,"Typed "+data+" into "+logicalname+"");
}
}

else if(action.equals("click")) {
	//System.out.println("under click");
	if(element!=null) {
	element.click();
	Thread.sleep(4000);
	lib.Setvalueinxl(scenarioname, teststep, 6, "PASS");
	lib.Setvalueinxl(scenarioname, teststep, 7,"Clicked on "+logicalname+" button");
}
}


else if(action.equals("select")) {
	if(element!=null) {
	Select sel=new Select(element);
	sel.selectByVisibleText(data);
	lib.Setvalueinxl(scenarioname, teststep, 6, "PASS");
	lib.Setvalueinxl(scenarioname, teststep, 7,"Selected "+data+" from "+logicalname+" dropdown");
}
}

else if(action.equals("verifycountindropdown")) {
System.out.println("under dropdown check");	
if(element!=null) {
    Select sel=new Select(element);
    java.util.List<WebElement> dropdownlist= sel.getOptions();
 int totaloptionsindropdown= dropdownlist.size()-1;
 
String svalue= Integer.toString(totaloptionsindropdown);

System.out.println("dropdownlist"+svalue);
System.out.println(data);
if(svalue.equals(data)) {

lib.Setvalueinxl(scenarioname, teststep, 6, "PASS");
lib.Setvalueinxl(scenarioname, teststep, 7,"Actual Dropdown count"+svalue+"is matched with Expected"+data+"");
}


}
}



else if(action.equals("verfiyTextcount")) {
	
		if(element!=null) {
		int actualcount=listelement.size();
		System.out.println("actualcount"+actualcount);
		 String str2 = Integer.toString(actualcount);
			
		if(str2.equals(data)) {
			lib.Setvalueinxl(scenarioname, teststep, 6, "pass");
			lib.Setvalueinxl(scenarioname, teststep, 7, "Actual count which is"+str2+"is matched with Expected count"+data+"");
			//lib.storetextvalue(str2);
		}else  {
			lib.Setvalueinxl(scenarioname, teststep, 6, "fail");
			lib.Setvalueinxl(scenarioname, teststep, 7, "Text count does not  matched");			
		}
		}	
}


else if(action.equals("verifyalert")) {
	
Alert alrt=StartupTest.driver.switchTo().alert();
if(data.equals("ok") ) {
alrt.accept();	
lib.Setvalueinxl(scenarioname, teststep, 6, "pass");
lib.Setvalueinxl(scenarioname, teststep, 7, "Clicked on OK");	
}else if(data.equals("cancel"))
	alrt.dismiss();
lib.Setvalueinxl(scenarioname, teststep, 6, "pass");
lib.Setvalueinxl(scenarioname, teststep, 7, "clikced on cancel");	
	
}


else if(action.equals("verifytitlepresent")) {
	
String Actualtitle=StartupTest.driver.getTitle().trim();
System.out.println(Actualtitle);
	if(Actualtitle.equals(data)) {
  lib.Setvalueinxl(scenarioname, teststep, 6, "pass");
  lib.Setvalueinxl(scenarioname, teststep, 7, "title matched");	
	}else {
		lib.Setvalueinxl(scenarioname, teststep, 6, "fail");
		lib.Setvalueinxl(scenarioname, teststep, 7, "Title not Matched");	
	}
}

else if(action.equals("verifyurlpresent")) {
		
String actualurl=com.spade.startup.StartupTest.driver.getTitle().trim();	
	if(actualurl.equals(data)) {
  lib.Setvalueinxl(scenarioname, teststep, 6, "pass");
  lib.Setvalueinxl(scenarioname, teststep, 7, "url Matched");
			}
	else {
		lib.Setvalueinxl(scenarioname, teststep, 6, "fail");
		lib.Setvalueinxl(scenarioname, teststep, 7, "url not Matched");	
	}
}
	
else if(action.equals("verifyelementpresent")) {
	
if(element!=null) {
	lib.Setvalueinxl(scenarioname, teststep, 6, "pass");
	lib.Setvalueinxl(scenarioname, teststep, 7, "element is present");
}else  {
	lib.Setvalueinxl(scenarioname, teststep, 6, "fail");
	lib.Setvalueinxl(scenarioname, teststep, 7, "element is not present");
	
}
}
else if(action.equals("verifyelementenabled")) {
	
if(element!=null) {
	if(element.isEnabled()==true) {
	lib.Setvalueinxl(scenarioname, teststep, 6, "pass");
	lib.Setvalueinxl(scenarioname, teststep, 7, "element is enabled");
	}else if(element.isEnabled()==false) {
		lib.Setvalueinxl(scenarioname, teststep, 6, "fail");
		lib.Setvalueinxl(scenarioname, teststep, 7, "element is  disabled");
	}
	
  }
	

}
 else if(action.equals("verifyTextpresent")) {
	if(element!=null) {
String actaulText=	element.getText().trim();	
System.out.println("actaulText"+actaulText);
	if(actaulText.equals(data)) {
		lib.Setvalueinxl(scenarioname, teststep, 6, "pass");
		lib.Setvalueinxl(scenarioname, teststep, 7, "Text is present");
		
	}else  {
		lib.Setvalueinxl(scenarioname, teststep, 6, "fail");
		lib.Setvalueinxl(scenarioname, teststep, 7, "Text is not present");
		
	}
	}

}

		}

		}
		
		
			
}
	
	
	

