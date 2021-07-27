package SeleniumCommands;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BrowserTest extends BrowserInit {
	public String _filepath;
	final String _propertFile="data.properties";
	final String _excelFileName="MvnGC.xlsx";
	Properties prop;
	ExtentReports report;
	ExtentTest test;
	
  @BeforeTest
  public void LoadDetails() throws IOException {
	  _filepath=System.getProperty("user.dir")+"\\src\\main\\java\\SeleniumCommands\\";
	  String _propFile=_filepath+_propertFile;
	  ObjectRepository _repos=new ObjectRepository();
	  prop=_repos.ReadProperties(_propFile);
	  BrowserInitialize(prop.getProperty("url"));
	  report=new ExtentReports(_filepath+"report.html");
	  test=report.startTest("PracticeForm", "Test the Practice form which includes all types of elements");
	  test.log(LogStatus.INFO, "Starting the Test...");
  }
  
  @DataProvider(name="Browserkeywords")
  public Object[][] GetBrowserCommands() throws IOException{
	  ExcelFileHandler _excelhandler=new ExcelFileHandler();
	  return _excelhandler.ReadExcelFile(_filepath, _excelFileName, "Sheet1");
  }
	
 /* @Test(dataProvider="Browserkeywords" )
  public void BrowserCommands(String Keyword, String Propname) throws IOException {
	
  }*/
  
  @Test
  public void PracticeForm() throws InterruptedException, AWTException, IOException {
	  FormLocator locator=new FormLocator(d,test);
	  locator.PracticeFormSubmit();
  }
  @AfterTest
  public void CloseBrowser() {
	  test.log(LogStatus.INFO, "Closing the Test...");	 
	  report.endTest(test);
	  report.flush(); 
	  d.close();
  }
}
