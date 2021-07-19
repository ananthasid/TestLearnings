package SeleniumTest;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;

public class Day5 {
	public String baseUrl = "http://demo.guru99.com/v4";
	public static String EXPECT_TITLE = "Guru99 Bank Manager HomePage";
	public String managerId="Manger Id : "+Util.Uid;
	public WebDriver driver; 
	
  @Test(dataProvider = "dp")
  	public void TestLogin(String uName, String pwd) throws InterruptedException {
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
			Thread.sleep(600);
		}	
		catch(Exception e) {
			System.out.println("catch");
			Assert.assertNotEquals(EXPECT_TITLE, Util.GetTitle());
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
