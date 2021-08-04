package SeleniumCommands;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;

public class Grid_WidgetTesting extends BrowserInit {
	WebDriver d;
	String baseUrl;
	String nodeUrl;
	

  @BeforeTest
  public void beforeTest() throws MalformedURLException {
	  baseUrl="https://demoqa.com/accordian";
	  nodeUrl="http://192.168.1.41:4444/wd/hub";
	  DesiredCapabilities cap=DesiredCapabilities.chrome();
	  cap.setBrowserName("chrome");
	  cap.setPlatform(Platform.WINDOWS);
	  d=new RemoteWebDriver(new URL(nodeUrl), cap);
  }
 
  @Test
  public void TestAccordion() throws InterruptedException {
	  d.get(baseUrl);
	  d.manage().window().maximize();
	  List<WebElement> cards=d.findElements(By.className("card-header"));
	  for(int c=0;c<cards.size()-1;c++){
		  cards.get(c).click();
		  Thread.sleep(1000);
	  }
  }
  @AfterTest
  public void afterTest() {
	  d.quit();
  }

}
