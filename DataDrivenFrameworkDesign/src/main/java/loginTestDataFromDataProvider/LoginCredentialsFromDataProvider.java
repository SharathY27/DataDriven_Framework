package loginTestDataFromDataProvider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginCredentialsFromDataProvider {

	
	String[][] data = 	{
							{"Admin","admin123"},
							{"min","min123"},
							{"min","admin123"},
							{"Admin","min123"}
							
						};
	
	@DataProvider(name = "loginData")
	public String[][] dataProvider()
	{
		return data;
	}
	
	
	@Test(dataProvider = "loginData" )
	public void Login(String username,String password) throws InterruptedException
	{	
		WebDriver driver = new ChromeDriver();
		
		String path = System.getProperty("user.home").replace("\\", "\\\\")+"\\\\Downloads\\\\chromedriver_win32 (3)\\\\chromedriver.exe";
		
		System.setProperty("webdriver.chrome.driver",path);
		
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
