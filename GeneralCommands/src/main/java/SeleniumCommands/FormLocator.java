package SeleniumCommands;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class FormLocator {
	
	public static WebDriver drive;
	Actions ac;
	ExtentTest t;
	@FindBy(id="firstName") WebElement fname;
	@FindBy(id="lastName") WebElement lname;
	@FindBy(id="userEmail") WebElement email;
	@FindBy(name="gender") List<WebElement> gender;
	@FindBy(id="userNumber") WebElement mobile;
	@FindBy(id="dateOfBirthInput") WebElement birthdate;
	@FindBy(id="subjectsInput") WebElement subject;
	@FindBy(xpath="//input[@type='checkbox']") List<WebElement> hobbies;
	@FindBy(id="currentAddress") WebElement address;
	@FindBy(id ="react-select-3-input") WebElement state;
	@FindBy(id ="react-select-4-input") WebElement city;
	@FindBy(className ="form-file") WebElement upload;
	@FindBy(id="submiit") WebElement submit;
	
	public FormLocator(WebDriver d, ExtentTest test) {
		PageFactory.initElements(d, this);
		drive=d;
		t=test;
		ac=new Actions(drive);		
	}
	
	private void SelectRadio(int index) {
		for(int i=0;i<gender.size();i++) {
			String id=gender.get(i).getAttribute("id");	
			System.out.println("index="+i+",id="+id);
			if(id.endsWith(Integer.toString(index))) {
				ac.moveToElement(gender.get(i)).click().perform();
				t.log(LogStatus.PASS, "Selected Option:- "+gender.get(i).getAttribute("value"));
				break;
			}
		}
	}
	
	private void SelectCheck(int[] index) {
		for(int j=0;j<index.length;j++) {
			for(int i=0;i<hobbies.size();i++) {
				if(index[j]==i+1) {
					t.log(LogStatus.PASS, "Selected Option:- "+hobbies.get(i).getAttribute("value"));
					ac.moveToElement(hobbies.get(i)).click().perform();
				}
			}
		}
	}
	
	private void SelectOPtions(String cssnam,String value) {
		List<WebElement> options=drive.findElement(By.className(cssnam)).findElements(By.tagName("option"));
		for(int i=0;i<options.size();i++) {
			if(options.get(i).getText().equals(value)) {
				
				options.get(i).click();
			}
		}
	}
	
	private void SelectElement(String css,String value) {
		List<WebElement> elems=drive.findElements(By.className(css));
		for(WebElement ele:elems){
			if(ele.getText().equalsIgnoreCase(value)) {
				ele.click();
				break;
			}
		}
	}
	private void UploadFile(String fileloc) throws InterruptedException, AWTException {
		try{
			
		upload.click();		
		Thread.sleep(600);
		System.out.println("ini"+fileloc);
		StringSelection sc=new StringSelection(fileloc);
		System.out.println(fileloc);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sc, null);
		Robot rb=new Robot();
		rb.delay(259);
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
				
	    rb.keyRelease(KeyEvent.VK_CONTROL);
	    rb.keyRelease(KeyEvent.VK_V);
		
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.delay(300);
		rb.keyRelease(KeyEvent.VK_ENTER);
		}
		catch(Exception e) {
			System.out.println("ERROR="+e.getMessage());
		}
	}
	private void TakeScreenShot() throws IOException {
		File src=((TakesScreenshot)drive).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("E://Ananthi//PF.png"));
	}
	private void ScrolltoBottomPage() {
		JavascriptExecutor js=(JavascriptExecutor)drive;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	
	public void PracticeFormSubmit() throws InterruptedException, AWTException, IOException {
		try 
		{
			fname.sendKeys("Ani");
			Thread.sleep(600);
			t.log(LogStatus.PASS, "Entered First Name:- Ani");
			lname.sendKeys("Suresh");
			Thread.sleep(600);
			t.log(LogStatus.PASS, "Entered Last Name:- Suresh");
			email.sendKeys("ani@gmail.com");
			Thread.sleep(600);
			t.log(LogStatus.PASS, "Entered Email:- ani@gmail.com");
			SelectRadio(2);
			Thread.sleep(600);
			mobile.sendKeys("08870654980");
			Thread.sleep(600);
			t.log(LogStatus.PASS, "Entered Mobile:- 08870654980");
			birthdate.click();
			SelectOPtions("react-datepicker__month-select", "November");
			SelectOPtions("react-datepicker__year-select","1981");
			SelectElement("react-datepicker__day","8");
			t.log(LogStatus.PASS, "Selected Birth Date:- 8 November 1981");
			Thread.sleep(600);
			int[] ind= {1,2};
			SelectCheck(ind);
			Thread.sleep(600);
			ScrolltoBottomPage();
			address.sendKeys("Flat 157, Rochester Row, London");
			t.log(LogStatus.PASS, "Entered Address:- Flat 157, Rochester Row, London");
			Thread.sleep(600);
			state.sendKeys("NCR");
		    state.sendKeys(Keys.ENTER);
		    t.log(LogStatus.PASS, "Entered State:- NCR");
		    city.sendKeys("Noida");
		    city.sendKeys(Keys.ENTER);
		    t.log(LogStatus.PASS, "Entered State:- Noida");
			Thread.sleep(600);
			//WebDriverWait wait1 = new WebDriverWait(drive, 10);
			//WebElement element1 = wait1.until(ExpectedConditions.elementToBeClickable(subject));
			//element1.click();
			UploadFile("E:\\Ananthi\\i.png");
			t.log(LogStatus.PASS, "Uploaded file:- E:\\\\Ananthi\\\\i.png");
			Thread.sleep(600);
			subject.sendKeys("Welcome Bala Sid");
			t.log(LogStatus.PASS, "Entered Subject:- Welcome Bala Sid");
			submit.click();
			t.log(LogStatus.PASS, "Successfully submitted the form");
			TakeScreenShot();
			t.log(LogStatus.PASS, "Taken a screenshot and saved as pf.png");
			Thread.sleep(3000);	
		}
		catch(Exception ex) {
			t.log(LogStatus.FAIL, ex.getMessage());
		}
	}
}
