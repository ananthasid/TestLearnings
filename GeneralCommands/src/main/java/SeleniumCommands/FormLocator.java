package SeleniumCommands;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FormLocator {
	
	public static WebDriver drive;
	Actions ac;
	
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
	@FindBy(id="submit") WebElement submit;
	public FormLocator(WebDriver d) {
		PageFactory.initElements(d, this);
		drive=d;
		ac=new Actions(drive);		
	}
	
	private void SelectRadio(int index) {
		for(int i=0;i<gender.size();i++) {
			String id=gender.get(i).getAttribute("id");	
			System.out.println("index="+i+",id="+id);
			if(id.endsWith(Integer.toString(index))) {
				ac.moveToElement(gender.get(i)).click().perform();
				break;
			}
		}
	}
	
	private void SelectCheck(int[] index) {
		for(int j=0;j<index.length;j++) {
			for(int i=0;i<hobbies.size();i++) {
				if(index[j]==i+1) {
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
		rb.keyRelease(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_CONTROL);		
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.delay(300);
		rb.keyRelease(KeyEvent.VK_ENTER);
		}
		catch(Exception e) {
			System.out.println("ERROR="+e.getMessage());
		}
	}
	private void ScrolltoBottomPage() {
		JavascriptExecutor js=(JavascriptExecutor)drive;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	
	public void PracticeFormSubmit() throws InterruptedException, AWTException {
		fname.sendKeys("Ani");
		Thread.sleep(600);
		lname.sendKeys("Suresh");
		Thread.sleep(600);
		email.sendKeys("ani@gmail.com");
		Thread.sleep(600);
		System.out.println("size="+gender.size());
		SelectRadio(2);
		Thread.sleep(600);
		mobile.sendKeys("08870654980");
		Thread.sleep(600);
		birthdate.click();
		SelectOPtions("react-datepicker__month-select", "November");
		SelectOPtions("react-datepicker__year-select","1981");
		SelectElement("react-datepicker__day","8");
		Thread.sleep(600);
		int[] ind= {1,2};
		SelectCheck(ind);
		Thread.sleep(600);
		ScrolltoBottomPage();
		address.sendKeys("Flat 157, Rochester Row, London");
		Thread.sleep(600);
		state.sendKeys("NCR");
	    state.sendKeys(Keys.ENTER);
	    city.sendKeys("Noida");
	    city.sendKeys(Keys.ENTER);
		Thread.sleep(600);
		//WebDriverWait wait1 = new WebDriverWait(drive, 10);
		//WebElement element1 = wait1.until(ExpectedConditions.elementToBeClickable(subject));
		//element1.click();
		UploadFile("E:\\Ananthi\\i.png");
		Thread.sleep(600);
		subject.sendKeys("Welcome Bala Sid");
		submit.click();
		Thread.sleep(3000);			
	}
}
