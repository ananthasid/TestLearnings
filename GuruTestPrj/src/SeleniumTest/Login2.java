package SeleniumTest;
import org.openqa.selenium.By;


public class Login2 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub		
	Util.LoadDriver();
	Util.d.get(Util.Url);
	Util.d.manage().window().maximize();
	Util.d.findElement(By.name("uid")).sendKeys(Util.Uid);
	Thread.sleep(600);
	Util.d.findElement(By.name("password")).sendKeys(Util.Passwrd);
	Thread.sleep(600);
	Util.d.findElement(By.name("btnLogin")).click();
	Thread.sleep(1600);
	System.out.println(Util.d.getTitle());
	Util.d.close();
	}

}
