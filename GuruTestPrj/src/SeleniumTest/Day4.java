package SeleniumTest;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@Test
public class Day4 {

public String baseUrl = "http://demo.guru99.com/v4";
public static String EXPECT_TITLE = "Guru99 Bank Manager HomePage";
public WebDriver driver; 
    
  @BeforeMethod
public void beforeTest() {
	  Util.LoadDriver(baseUrl);
  }

  @AfterMethod
public void afterTest() throws InterruptedException {
	  Util.CloseDriver(0);
  }
  
  @Test
public void TestLogin() throws IOException, InterruptedException
  {
	    Util.ReadExcelFile();
		Util.d.manage().timeouts().implicitlyWait(1500, TimeUnit.MILLISECONDS);	
		for(int d=0;d<Util.datas.length;d++) {
			Util.d.findElement(By.name("uid")).sendKeys(Util.datas[d][0]);
			Thread.sleep(600);
			Util.d.findElement(By.name("password")).sendKeys(Util.datas[d][1]);
			Thread.sleep(600);
			Util.d.findElement(By.name("btnLogin")).click();			
			try {
				Util.d.switchTo().alert().accept();	
				Assert.assertNotEquals(EXPECT_TITLE, Util.GetTitle());
				}			
			catch(NoAlertPresentException ex) {	
				Assert.assertEquals(EXPECT_TITLE, Util.GetTitle());
				Thread.sleep(600);
			}	
			catch(Exception e) {
				Assert.assertNotEquals(EXPECT_TITLE, Util.GetTitle());
			}
		}
		
  }
}
