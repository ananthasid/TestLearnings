package SeleniumCommands;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BrowserWindow {
	WebDriver driver;
	ExtentTest test;
	String parentId;
	
	public BrowserWindow(WebDriver d,ExtentTest t ) {
		driver=d;
		test=t;
		parentId=d.getWindowHandle();
	}
	
	private void TestnewTab() throws InterruptedException {
		Thread.sleep(600);
		driver.findElement(By.id("tabButton")).click();
	    Thread.sleep(600);
		test.log(LogStatus.PASS, "Clicked New Tab Button");
		Set<String> childs=driver.getWindowHandles();
		Iterator<String> childIds=childs.iterator();
		while(childIds.hasNext()) {
			String cId=childIds.next();
			if(!cId.equalsIgnoreCase(parentId)) {
				driver.switchTo().window(cId);				
				test.log(LogStatus.PASS, "New Tab page is selected successfully -"+driver.getCurrentUrl());	
				Thread.sleep(600);
				driver.close();
			}
		}
		
	}
	private void TestnewWindow() throws InterruptedException {
		
		driver.findElement(By.id("windowButton")).click();
	    Thread.sleep(600);
		test.log(LogStatus.PASS, "Clicked New Window Button");
		Set<String> childs=driver.getWindowHandles();
		Iterator<String> childIds=childs.iterator();
		while(childIds.hasNext()) {
			String cId=childIds.next();
			if(!cId.equalsIgnoreCase(parentId)) {
				driver.switchTo().window(cId);				
				test.log(LogStatus.PASS, "New Window page is selected successfully -"+driver.getCurrentUrl());	
				Thread.sleep(600);	
				driver.close();
			}
		}
	}
	public void TestBrowserWindow() throws InterruptedException {
		TestnewTab();
		driver.switchTo().window(parentId);
		Thread.sleep(600);
		TestnewWindow();
		driver.close();
	}
	
	public void TestFrames() {
		driver.switchTo().frame("frame1");
		test.log(LogStatus.PASS, "Switched to first frame -"+driver.findElement(By.tagName("h1")).getText());
		driver.switchTo().defaultContent();
		driver.switchTo().frame("frame2");
		test.log(LogStatus.PASS, "Switched to second frame -"+driver.findElement(By.tagName("h1")).getText());
	}
}
