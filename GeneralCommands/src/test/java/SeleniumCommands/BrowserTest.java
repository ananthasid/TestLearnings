package SeleniumCommands;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BrowserTest extends BrowserInit {
	public String _filepath;
	final String _propertFile="data.properties";
	final String _excelFileName="MvnGC.xlsx";
	Properties prop;
	
  @BeforeTest
  public void LoadDetails() throws IOException {
	  _filepath=System.getProperty("user.dir")+"\\src\\main\\java\\SeleniumCommands\\";
	  String _propFile=_filepath+_propertFile;
	  ObjectRepository _repos=new ObjectRepository();
	  prop=_repos.ReadProperties(_propFile);
	  BrowserInitialize(prop.getProperty("url"));
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
  public void PracticeForm() throws InterruptedException, AWTException {
	  FormLocator locator=new FormLocator(d);
	  locator.PracticeFormSubmit();
  }
  @AfterTest
  public void CloseBrowser() {
	  d.close();
  }
}
