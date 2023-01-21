package com.orangehrm.hybdridframework.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {

	public static XSSFWorkbook workboook;

	public ExcelDataProvider(String excelname) {
		try {

			File fs = new File("./TestData/" + excelname + ".xlsx");
			FileInputStream fins = new FileInputStream(fs);
			workboook = new XSSFWorkbook(fins);
		} catch (Exception e) {
		}

	}

	public int rowCount(String sheetname) {
		return workboook.getSheet(sheetname).getLastRowNum();
	}

	public int rowCount(int index) {
		return workboook.getSheetAt(index).getLastRowNum();
	}

	public int columnCount(String sheetname) {
		return workboook.getSheet(sheetname).getRow(0).getLastCellNum();
	}

	public int columnCount(int index) {
		return workboook.getSheetAt(index).getRow(0).getLastCellNum();
	}

	public int getNumericCellData(String sheetname, int row, int cols) {
		return (int) workboook.getSheet(sheetname).getRow(row).getCell(cols).getNumericCellValue();
	}

	public int getNumericCellData(int index, int row, int cols) {
		return (int) workboook.getSheetAt(index).getRow(row).getCell(cols).getNumericCellValue();
	}

	public String getStringCellData(String sheetname, int row, int cols) {

		return workboook.getSheet(sheetname).getRow(row).getCell(cols).getStringCellValue();
	}

	public String getStringCellData(int index, int row, int cols) {
		return workboook.getSheetAt(index).getRow(row).getCell(cols).getStringCellValue();
	}
	
	
	
	public Object[][] getCellData(String sheetname)
	{
		XSSFSheet sheet = workboook.getSheet(sheetname);
		int rows = rowCount(sheetname);
		int cols = columnCount(sheetname);
		
		
		Object[][] data = new Object[rows][cols];
		
		for(int i=0; i<rows; i++)
		{
			for(int j=0; j<cols; j++) {
				
				data[i][j] =sheet.getRow(i+1).getCell(j).toString();
			}
		}
			
		return data;
	}

	
	
	//	public static void main(String[] args) {
//
//		ExcelDataProvider excelDataProvider = new ExcelDataProvider("TestData");
//		
//		
//		System.out.println(excelDataProvider.rowCount("Login"));
//		System.out.println(excelDataProvider.columnCount("Login"));
//		
//		System.out.println(excelDataProvider.rowCount(0));
//		System.out.println(excelDataProvider.columnCount(0));
//		
//		
//		System.out.println(excelDataProvider.getStringCellData("Login", 1, 0));
//		System.out.println(excelDataProvider.getStringCellData("Login", 1, 1));
//	}

}
