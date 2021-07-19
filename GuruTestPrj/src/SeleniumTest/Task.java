package SeleniumTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Task {

	static WebDriver d;
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\india\\\\chromedriver.exe");
		d=new ChromeDriver();
		d.get("http://demo.guru99.com/test/newtours/");
		d.manage().window().maximize();
		
		d.findElement(By.linkText("Selenium")).click();
		List<WebElement> links=d.findElement(By.xpath("//*[@id=\"navbar-brand-centered\"]/ul/li[1]/ul")).findElements(By.tagName("a"));
		for(WebElement l:links){
			Actions act=new Actions(d);
			act.moveToElement(l).build().perform();
			Thread.sleep(1000);
		}
		d.close();
	}

}
