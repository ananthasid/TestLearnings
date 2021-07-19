package SeleniumTest;

import org.openqa.selenium.By;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Login {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub  
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\india\\chromedriver.exe");
		//ChromeDriver d=new ChromeDriver();
		FirefoxDriver d=new FirefoxDriver();
		d.get("http://www.demo.guru99.com/V4/");
		d.manage().window().maximize();
		d.findElement(By.name("uid")).sendKeys("mngr332776");
		Thread.sleep(600);
		d.findElement(By.name("password")).sendKeys("vAvyjem");
		Thread.sleep(600);
		d.findElement(By.name("btnLogin")).click();
		Thread.sleep(1600);
		d.close();
	}

}
