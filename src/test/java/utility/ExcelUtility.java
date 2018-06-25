package utility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility
{
	private  static XSSFWorkbook wBook;
	private  static XSSFSheet sheet;
	private  static XSSFRow row;
	private  static XSSFCell cell;
	
	public static void setExcelPath(String sheetname,String path)throws IOException
	{
	FileInputStream fis=new FileInputStream(path);
	wBook=new  XSSFWorkbook(fis);
	sheet=wBook.getSheet(sheetname);
	
	}
	public static String getCellData(int rows,int column)
	{
		String celldata;
		row=sheet.getRow(rows);
		cell=row.getCell(column);
		celldata=cell.getStringCellValue();
		
		return celldata;
	}
	
}
