package tn.star.it.senariot2;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import tn.star.it.utilities.ExcelConfig;


public class SeleniumConfig {
	
	public static void main(String [] args) throws InterruptedException{
		
		ExcelConfig excel = new ExcelConfig("./Testdata.xlsx");
		
		//System.out.println(excel.getData(0,1,0));
		
		String exePath = "D:\\drivers\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", exePath);
		WebDriver driver = new ChromeDriver();
		 
		//WebDriver driver = new FirefoxDriver();
		driver.get("http://www.google.com/");
		
		
		
		WebElement hello = driver.findElement(By.className("gsfi"));
//		hello.sendKeys(excel.readData(0,1));
//		hello.submit();
		
		Actions actions = new Actions(driver);
		actions.moveToElement(hello);
		actions.click();
		actions.sendKeys(excel.readData(0,1));
		actions.build().perform();
		
		Thread.sleep(5000);
		
		driver.close();
	
	}
}
 