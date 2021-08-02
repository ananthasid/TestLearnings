package SeleniumCommands;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserInit {
public static WebDriver d;
public static String DriverPath="C:\\\\Users\\\\india\\\\chromedriver.exe";

public void BrowserInitialize(String url) {
	System.setProperty("webdriver.chrome.driver", DriverPath);
	d=new ChromeDriver();
	d.navigate().to(url);
	//d.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	d.manage().window().maximize();
}

}
