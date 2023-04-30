package com.pro.utilities;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {

	XSSFWorkbook workbook;
	
	public ExcelDataProvider() {
		File src = new File("./TestData/Data.xlsx");		
		try {
			FileInputStream fis = new FileInputStream(src);
			workbook = new XSSFWorkbook(fis);
		} catch (Exception e) {
			System.out.println("Unable to read excel file >> " + e.getMessage()); 
		}		
	}
	
	public String getStringData(int sheetNum, int row, int column) {
	return	workbook.getSheetAt(sheetNum).getRow(row).getCell(column).getStringCellValue();
	}
	
	public String getStringData(String sheet, int row, int column) {
		return	workbook.getSheet(sheet).getRow(row).getCell(column).getStringCellValue();
	}
	
	public int getNumericData(String sheet, int row, int column) {
		return	(int)(workbook.getSheet(sheet).getRow(row).getCell(column).getNumericCellValue());	
	}
}









