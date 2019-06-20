package com.spade.readexcel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.format.CellFormatType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ExcelLibrary {
	
static String file=".\\Testcases\\testscenarios.xlsx";	
public int Getrowcount(String sheetname) throws EncryptedDocumentException, InvalidFormatException, IOException {
int ratval=0;
FileInputStream fis=new FileInputStream(file);
Workbook wb=WorkbookFactory.create(fis);
Sheet s=wb.getSheet(sheetname);
ratval=	s.getLastRowNum();
return ratval;

          }
	
	
public static String getexcelStringvalue(String sheetname,int rownum,int cellnum) throws EncryptedDocumentException, InvalidFormatException, IOException {
String ratval=null;
FileInputStream fis=new FileInputStream(file);
Workbook wb=WorkbookFactory.create(fis);
Sheet s=wb.getSheet(sheetname);
Row r=s.getRow(rownum);
Cell c=r.getCell(cellnum);
ratval=c.getStringCellValue();
return ratval;
	
	}	

public static int getexcelintvalue(String sheetname,int rownum,int cellnum) throws EncryptedDocumentException, InvalidFormatException, IOException {
int ratval=0;
FileInputStream fis=new FileInputStream(file);
Workbook wb=WorkbookFactory.create(fis);
Sheet s=wb.getSheet(sheetname);
Row r=s.getRow(rownum);
Cell c=r.getCell(cellnum);
String cellvalue=c.getStringCellValue();
ratval=Integer.parseInt(cellvalue);
return ratval;
}	

	
@SuppressWarnings("deprecation")
public  void Setvalueinxl(String sheetname,int rownum,int cellnum,String value) throws EncryptedDocumentException, InvalidFormatException, IOException {
FileInputStream fis=new FileInputStream(file);
Workbook wb=WorkbookFactory.create(fis);
Sheet s=wb.getSheet(sheetname);
Row r=s.getRow(rownum);
Cell c=r.createCell(cellnum);
c.setCellType(CellType.STRING);
//c.setCellType(Cell.CELL_TYPE_STRING);
c.setCellValue(value);

FileOutputStream fos=new FileOutputStream(file);
wb.write(fos);
fos.close();

	
	}	

public String  storetextvalue(String textvalue) {
	String ratval=null;
	
ArrayList<String> textvalues=new ArrayList<String>();
textvalues.add(textvalue);
	
	return ratval;
	
}

	
	
	
	
	
	
	
	
	
	
	
	

}
