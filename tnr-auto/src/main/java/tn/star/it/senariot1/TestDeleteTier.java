package tn.star.it.senariot1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import tn.star.it.domain.Tier;
import tn.star.it.utilities.ExcelConfig;

public class TestDeleteTier {
	private final static Logger LOGGER = Logger.getLogger(TestDeleteTier.class.getName());
	private static ExcelConfig excel = new ExcelConfig("./DocTest1.xlsx");

	public void deleteTier(Tier tier) throws InterruptedException, IOException {

		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String appConfigPath = rootPath + "data.properties";
		Properties appProps = new Properties();
		appProps.load(new FileInputStream(appConfigPath));
		Thread.sleep(2000);
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(appProps.getProperty("url"));
		driver.findElement(By.id("RGI_username")).clear();
		driver.findElement(By.id("RGI_username")).sendKeys(appProps.getProperty("username"));
		driver.findElement(By.id("RGI_password")).clear();
		driver.findElement(By.id("RGI_password")).sendKeys(appProps.getProperty("password"));
		driver.findElement(By.id("BTNsuivant0")).click();
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Gestion des agences'])[1]/following::div[1]"))
				.click();
		driver.findElement(By.id("PasubjectkeyCkey3")).clear();
		driver.findElement(By.id("PasubjectkeyCkey3")).sendKeys(tier.getNumDocument());
		driver.findElement(By.id("BTNtrouver0")).click();

//		driver.findElement(By.xpath(
//				"(.//*[normalize-space(text()) and normalize-space(.)='Gestion des agences'])[1]/following::div[1]"))
//				.click();
//		driver.findElement(By
//				.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Numéro Document:'])[1]/following::td[1]"))
//				.click();
//		driver.findElement(By.id("PasubjectkeyCkey3")).click();
//		driver.findElement(By.id("PasubjectkeyCkey3")).clear();
//		driver.findElement(By.id("PasubjectkeyCkey3")).sendKeys(tier.getNumDocument());
//		driver.findElement(By.id("BTNtrouver0")).click();
		driver.findElement(By.id("BTNsupprimer0")).click();
		driver.findElement(By.id("BTNconfirmer0")).click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='close'])[3]/following::button[1]"))
				.click();

	}
}
