package tn.star.it;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Teqt {
	private static  WebDriver driver;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String appConfigPath = rootPath + "data.properties";
		Properties appProps = new Properties();
		appProps.load(new FileInputStream(appConfigPath));
		System.setProperty("webdriver.chrome.driver", "D:\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void testChromeSelenium() {
		driver.get(
				"https://www.google.com/");
		driver.findElement(By.id("RGI_username")).clear();
		driver.findElement(By.id("RGI_username")).sendKeys("ST1406");
		driver.findElement(By.id("RGI_password")).clear();
		driver.findElement(By.id("RGI_password")).sendKeys("star123");
	}

	@AfterClass
	public static void cleanUp() {
		if (driver != null) {
			driver.close();
			driver.quit();
		}
}
}
