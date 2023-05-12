package excelReadingUsingJXLLibrary;

import java.io.FileInputStream;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;

public class ExcelReadingUsingJXL {

	// Read Notes before reading data from excel using JXL
	
	// IMPORTANT : Make sure you have imported the proper library from JXL
	
	WebDriver driver;
	
	public String[][] getExcelData()
	{
		try 
		{
			
			// 1. Read excel file
			FileInputStream fis = new FileInputStream("../DataDrivenFrameworkDesign\\JXL Resources\\loginCredentials.xls");
			
			// 2. Get WorkBook
			Workbook workbook = Workbook.getWorkbook(fis);
			
			// 3. Get Sheet
			Sheet sheet = workbook.getSheet(0);
			
			// 4. Get Rows
			int rowCount = sheet.getRows();
			
			// 5. Get Columns
			int columnCount = sheet.getColumns();
		
			String[][] testData = new String[rowCount-1][columnCount];
			
			// 6. Iterate and get values
			for(int i=1;i<rowCount;i++)
			{
				for(int j=0;j<columnCount;j++)
				{
					testData[i-1][j] = sheet.getCell(j, i).getContents();
					
					System.out.println(testData[i-1][j]);
				}
				
				System.out.println();
			}
			
			return testData;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	String data[][] = null;
	
	@DataProvider(name = "testData")
	public String[][] dataProvider()
	{
		data = getExcelData();
		
		return data;
	}
	
	@BeforeTest
	public  void beforeTest()
	{
		driver = new ChromeDriver();
		
		String path = System.getProperty("user.home").replace("\\", "\\\\")+"\\\\Downloads\\\\chromedriver_win32 (3)\\\\chromedriver.exe";
		
		System.setProperty("webdriver.chrome.driver",path);
		
	}
	
	@AfterTest
	public void afterTest()
	{
		driver.quit();
	}
	
	
	@Test(dataProvider = "testData")
	public void login(String username,String password) throws InterruptedException
	{	
		
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(username);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(password);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//button[text()[normalize-space()='Login']]")).click();
		Thread.sleep(3000);
		
//		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
//		
//		((JavascriptExecutor) driver).executeScript("window.open()");
//		Thread.sleep(3000);
//		driver.switchTo().window(tabs.get(tabs.size()+1));
//		Thread.sleep(3000);
//		
//		driver.switchTo().window(tabs.get(0)).close();

	}
	
	
	
	
	
	
}
