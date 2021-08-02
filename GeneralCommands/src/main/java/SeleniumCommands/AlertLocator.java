package SeleniumCommands;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AlertLocator {
	WebDriver drive;
	ExtentTest t;
    @FindBy(id="alertButton") WebElement alert;
    @FindBy(id="timerAlertButton") WebElement timedalert;
    @FindBy(id="confirmButton") WebElement confirm;
    @FindBy(id="promtButton") WebElement prompt;
   
    
	public AlertLocator(WebDriver d, ExtentTest test) {
		PageFactory.initElements(d, this);
		drive=d;
		t=test;
	}
	
	public void AlertPageTesting() throws Throwable
	{
		try {
		alert.click();
		Thread.sleep(1000);
		Alert a=drive.switchTo().alert();
		t.log(LogStatus.PASS, "Read the alert message: "+a.getText());
		a.accept();
		Thread.sleep(600);
		
		timedalert.click();
		a=new WebDriverWait(drive,5).until(ExpectedConditions.alertIsPresent());
		if(a!=null) {
		a=drive.switchTo().alert();
		t.log(LogStatus.PASS, "Read the Timed alert message: "+a.getText());
		a.accept();
		Thread.sleep(600);
		
	/*	confirm.click();
		//Thread.sleep(1000);
		a=drive.switchTo().alert();
		Thread.sleep(600);
		a.accept();
		Thread.sleep(200);
		String msg=drive.findElement(By.id("confirmResult")).getText();
		t.log(LogStatus.PASS, "Action performed in Confirm box :"+ msg);
		Thread.sleep(600);*/
		
		confirm.click();
		a=drive.switchTo().alert();
		Thread.sleep(600);
		a.dismiss();
		Thread.sleep(300);
		String msg=drive.findElement(By.id("confirmResult")).getText();
		t.log(LogStatus.PASS, "Action performed in Confirm box :"+ msg);
		Thread.sleep(600);
		
		prompt.click();
		a=drive.switchTo().alert();
		a.sendKeys("Ananthi Suresh");
		Thread.sleep(600);
		a.accept();
		WebElement  promptresult=drive.findElement(By.id("promptResult"));
		t.log(LogStatus.PASS, "Prompt Result After Alert :"+ promptresult.getText());
		Thread.sleep(200);
		}
		else {
			throw new Throwable();
		}
		}
		catch(Exception ex) {
			t.log(LogStatus.FAIL, ex.getMessage());
		}
	}
}
