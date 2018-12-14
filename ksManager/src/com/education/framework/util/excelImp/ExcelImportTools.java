package com.education.framework.util.excelImp;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelImportTools {
	private Logger log = Logger.getLogger(ExcelImportTools.class);
	private static ExcelImportTools et = null;
	
	public static ExcelImportTools getInstance(){
		if(null == et){
			et = new ExcelImportTools();
		}
		return et;
	}

	public String getValForString(HSSFRow hssfRow, int colIndex) {
		HSSFCell c = hssfRow.getCell(colIndex);
		String ret = "";
		if(c!=null){
			c.setCellType(Cell.CELL_TYPE_STRING); 
			ret = c.getStringCellValue();
			ret = ret.replaceAll(" ", ""); 
		}
		return ret;
	}

	
  
}