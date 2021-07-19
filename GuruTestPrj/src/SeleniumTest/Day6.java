package SeleniumTest;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;

public class Day6 {
	public String baseUrl = "http://demo.guru99.com/v4";
	public static String EXPECT_TITLE = "Guru99 Bank Manager HomePage";
	public String managerId="Manger Id : "+Util.Uid;
	public WebDriver driver; 
	
  @Test(dataProvider = "dp")
  	public void TestLogin(String uName, String pwd) throws InterruptedException, IOException {
	    Util.d.findElement(By.name("uid")).sendKeys(uName);
		Thread.sleep(600);
		Util.d.findElement(By.name("password")).sendKeys(pwd);
		Thread.sleep(600);
		Util.d.findElement(By.name("btnLogin")).click();			
		try {
			Util.d.switchTo().alert().accept();	
			Assert.assertNotEquals(EXPECT_TITLE, Util.GetTitle());
		}			
		catch(NoAlertPresentException ex) {
			Assert.assertEquals(EXPECT_TITLE, Util.GetTitle());
			String msg=Util.d.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[3]/td")).getText();
			Assert.assertEquals(managerId, msg);
			File src=((TakesScreenshot)Util.d).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src,new File("E://Ananthi/Day6.jpg"));
			Assert.assertEquals(true, new File("E://Ananthi/Day6.jpg").isFile());
			Thread.sleep(600);
		}	
  }

  @DataProvider
  	public String[][] dp() throws IOException {
	  Util.ReadExcelFile();
	  return Util.datas;
  }
  
  @BeforeTest
  	public void beforeTest() {
	  Util.LoadDriver(baseUrl);
  }

  @AfterTest
  	public void afterTest() throws InterruptedException {
	  Util.CloseDriver(0);
  }
  
}
