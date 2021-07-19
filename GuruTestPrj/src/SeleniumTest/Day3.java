package SeleniumTest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;

public class Day3 {
	
	public static String EXPECT_TITLE = "Guru99 Bank Manager HomePage";
	
	public static void main(String[] args) throws InterruptedException, UnhandledAlertException, IOException {
		// TODO Auto-generated method stub
		Util.ReadExcelFile();
		Util.LoadDriver();
		Util.d.get(Util.Url);
		Util.d.manage().window().maximize();
		Util.d.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);	
		for(int d=0;d<Util.datas.length;d++) {
			Util.d.findElement(By.name("uid")).sendKeys(Util.datas[d][0]);
			Thread.sleep(600);
			Util.d.findElement(By.name("password")).sendKeys(Util.datas[d][1]);
			Thread.sleep(600);
			Util.d.findElement(By.name("btnLogin")).click();			
			try {
				Util.d.switchTo().alert().accept();	
				System.out.println("Testcase-"+(d+1)+" PASSED");	
			}
			catch(NoAlertPresentException ex) {	
				if(Util.d.getTitle().equals(EXPECT_TITLE)) {
					System.out.println("Testcase-"+(d+1)+" PASSED");Thread.sleep(600);}
				else
					System.out.println("Testcase-"+(d+1)+" FAILED");						
			}		
		}
		Util.d.close();	
	}

}
