package SeleniumTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Util {
	public static String DriverPath="C:\\\\Users\\\\india\\\\chromedriver.exe";
	public static String Uid="mngr332776";
	public static String Passwrd="vAvyjem";
	public static String Url="http://www.demo.guru99.com/V4/";
	public static String[][] datas=null;
	static WebDriver d;
	
	public static void LoadDriver() {
		System.setProperty("webdriver.chrome.driver", DriverPath);
		d=new ChromeDriver();
	}
	public static void LoadDriver(String path) {
		System.setProperty("webdriver.chrome.driver", DriverPath);
		d=new ChromeDriver();
		d.get(path);
		d.manage().window().maximize();
	}
	public static String GetTitle() {
		return d.getTitle();
	}
	public static void CloseDriver(long delay) throws InterruptedException {
		Thread.sleep(delay);
		d.close();
	}
	public static void ReadExcelFile() throws IOException{
		File file=new File("C:\\\\Users\\\\india\\\\data.xlsx")	;
		FileInputStream str=new FileInputStream(file);
		XSSFWorkbook book=null;
		book = new XSSFWorkbook(str);
		XSSFSheet sheet = book.getSheet("sheet1");
	    int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
	    datas=new String[rowCount+1][sheet.getRow(0).getLastCellNum()];
	    for (int i = 0; i < rowCount+1; i++) {
	        XSSFRow row = sheet.getRow(i);
		  for (int j = 0; j < row.getLastCellNum(); j++) {
            datas[i][j]=row.getCell(j).getStringCellValue();
	        }
		}
	    book.close();
	}
}
