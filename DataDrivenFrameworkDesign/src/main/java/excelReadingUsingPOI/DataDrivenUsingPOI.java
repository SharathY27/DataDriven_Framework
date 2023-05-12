package excelReadingUsingPOI;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DataDrivenUsingPOI {

	WebDriver driver;
	
	List<Object> usernames =new ArrayList<Object>();
	List<Object> passwords =new ArrayList<Object>();
	
	public void readExcelDataUsingPOI(boolean containsHeading) throws IOException
	{
		//1. Read excel file
		FileInputStream fis = new FileInputStream("../DataDrivenFrameworkDesign\\POI Resources\\LoginCredentialsUsingPOI.xlsx");
		
		//2. Get Excel Workbook
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		//3. Get ExcelSheet
		XSSFSheet sheet = wb.getSheetAt(0);
		
		//4. Get No of rows/columns for equal no of rows and columns
		
		int noOfRows = sheet.getLastRowNum();
		
		int noOfColumns = sheet.getRow(0).getPhysicalNumberOfCells();
		
		System.out.println("No of Rows : "+ noOfRows);
		
		System.out.println("No of Rows : "+ noOfColumns);
		
		//5. Iterating each column with respect to each rows using for each loop (horizontal manner)
		int count=0;
		int i = 2;
		for(Row row : sheet)
		{
			if(containsHeading && count==0)
			{
				count++;				
				continue;
			}
			for(Cell cell:row)
			{
				if(i%2==0)
				{
					usernames.add(cell.getStringCellValue());
				}
				else
				{
					passwords.add(cell.getStringCellValue());
				}
				i++;
			}
		}
		
		System.out.println(usernames);
		
		System.out.println(passwords);
	}
	
	
	public void login(String username,String password) throws InterruptedException
	{
		driver = new ChromeDriver();
		
		String path = System.getProperty("user.home").replace("\\", "\\\\")+"\\\\Downloads\\\\chromedriver_win32 (3)\\\\chromedriver.exe";
		
		System.setProperty("webdriver.chrome.driver",path);
		
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(username);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(password);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//button[text()[normalize-space()='Login']]")).click();
		Thread.sleep(3000);
	}
	
	public void executeTest() throws InterruptedException
	{
		for(int i=0;i<usernames.size();i++)
		{
			login(usernames.get(i).toString(),passwords.get(i).toString());
		}
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		DataDrivenUsingPOI dataDrivenUsingPOI = new DataDrivenUsingPOI();
		
		dataDrivenUsingPOI.readExcelDataUsingPOI(true);
		
		dataDrivenUsingPOI.executeTest();
	}

}
