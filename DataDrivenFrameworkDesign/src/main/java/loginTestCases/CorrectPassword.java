package loginTestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CorrectPassword {

	@Test
	@Parameters({"username","password"})
	public void correctPassword(String username,String password) throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Sharath\\Downloads\\chromedriver_win32 (3)\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		String path = System.getProperty("user.home").replace("\\", "\\\\");
		
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(username);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(password);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//button[text()[normalize-space()='Login']]")).click();
		Thread.sleep(3000);
		
		driver.quit();
	}
	
}
