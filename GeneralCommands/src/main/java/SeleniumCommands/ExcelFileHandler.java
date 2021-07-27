package SeleniumCommands;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileHandler {
	
public Object[][] ReadExcelFile(String filePath,String fileName,String sheetName) throws IOException{
		String[][] exceldata = null;
		FileInputStream fi=new FileInputStream(filePath+fileName);
		XSSFWorkbook wb=new XSSFWorkbook(fi);
		XSSFSheet sheet=wb.getSheet(sheetName);
		Iterator<Row>rows=sheet.rowIterator();
		int rowcount=sheet.getLastRowNum()-sheet.getFirstRowNum()-1;
		int cellcount=rows.next().getLastCellNum()-1;
		exceldata=new String[rowcount][cellcount];
		for(int i=1;i<rowcount;i++) {
			Row r=sheet.getRow(i);
			for(int j=0;j<cellcount;j++) {
				Cell c=r.getCell(j);
				exceldata[i-1][j]=c.getStringCellValue();				
			}
		}
		wb.close();
		return exceldata;
		
	}

}
