package utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
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
	
	
	
	public static String[][] getExcelTable()
	{
		int noOfRows=sheet.getPhysicalNumberOfRows();
		int noOfColumns=2;
		
		String [][]excelData=new String[noOfRows][noOfColumns];
		
		for(int i=0;i<noOfRows;i++)
		{
			for(int j=0;j<noOfColumns;j++)
			{
				excelData[i][j]=getCellData(i,j);
			}
		}
		return excelData;
	}
	
	
	
	
	public static void setExcelData(int rownum,int colnum,String data)throws IOException
	{
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum, MissingCellPolicy.RETURN_BLANK_AS_NULL);
		
		if(cell==null)
		{
			cell=row.createCell(colnum);
			cell.setCellValue(data);
		}
		else
		{
			cell.setCellValue(data);
		}
		
		FileOutputStream fos=new FileOutputStream("C:\\selenium\\testdata.xlsx");
		wBook.write(fos);
		fos.flush();
		fos.close();
	}
	
}
