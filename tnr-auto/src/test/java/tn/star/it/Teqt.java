package tn.star.it;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import tn.star.it.senariot1.TestAddTier;
import tn.star.it.utilities.ExcelConfig;

public class Teqt {
	private final static Logger LOGGER = Logger.getLogger(TestAddTier.class.getName());
	private static ExcelConfig excel = new ExcelConfig("./Testdata.xlsx");
	static WebDriver driver;
	static Properties appProps;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String appConfigPath = rootPath + "data.properties";
		appProps = new Properties();
		appProps.load(new FileInputStream(appConfigPath));
		LOGGER.info("step1");
		LOGGER.info(excel.readData(1, 1));
		Thread.sleep(2000);
		System.setProperty("webdriver.chrome.driver", "D:\\drivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void testChromeSelenium() {
		driver.get(appProps.getProperty("url"));
		driver.findElement(By.id("RGI_username")).clear();
		driver.findElement(By.id("RGI_username")).sendKeys(appProps.getProperty("username"));
		driver.findElement(By.id("RGI_password")).clear();
		driver.findElement(By.id("RGI_password")).sendKeys(appProps.getProperty("password"));
		driver.findElement(By.id("BTNsuivant0")).click();
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Gestion des agences'])[1]/following::div[1]"))
				.click();
	}

	@AfterClass
	public static void cleanUp() {
		if (driver != null) {
			driver.close();
			driver.quit();
		}
}
}
