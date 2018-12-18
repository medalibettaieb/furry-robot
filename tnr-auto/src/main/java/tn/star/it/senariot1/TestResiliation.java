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

import tn.star.it.utilities.ExcelConfig;

public class TestResiliation {
	private final static Logger LOGGER = Logger.getLogger(TestResiliation.class.getName());
	private static ExcelConfig excel = new ExcelConfig("./Testdata.xlsx");

	public static void main(String[] args) throws InterruptedException, IOException {
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String appConfigPath = rootPath + "data.properties";
		Properties appProps = new Properties();
		appProps.load(new FileInputStream(appConfigPath));
		LOGGER.info("step1");

		Thread.sleep(2000);
		System.setProperty("webdriver.chrome.driver", "D:\\drivers\\chromedriver.exe");
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

		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Tiers'])[1]/following::div[1]"))
				.click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Autorisations'])[1]/following::div[1]"))
				.click();
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Gestion proposition'])[1]/following::div[1]"))
				.click();
		driver.findElement(By.id("PcpolizzaCnumpolizza")).click();
		driver.findElement(By.id("PcpolizzaCnumpolizza")).clear();
		driver.findElement(By.id("PcpolizzaCnumpolizza")).sendKeys("CI0555N00066820*");
		driver.findElement(By.id("BTNtrouver0")).click();
		
		Thread.sleep(3000);
		
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Statut'])[1]/following::img[1]"))
				.click();
		driver.findElement(By.id("BTNsélectionner0")).click();
		driver.findElement(By.xpath("//*[@id=\"menuvoices\"]/ul/li[5]/a")).click();
		driver.findElement(By.id("inputSel_CBANN")).click();
		driver.findElement(By.linkText("Résiliation échéance compagnie")).click();
		driver.findElement(By.id("BTNsuivant0")).click();
		driver.findElement(By.id("PcmovimentoCnote")).click();
		driver.findElement(By.id("PcmovimentoCnote")).clear();
		driver.findElement(By.id("PcmovimentoCnote")).sendKeys("test bettaieb");
		driver.findElement(By.id("BTNsuivant1")).click();
		driver.findElement(By.id("BTNfin1")).click();

	}

}
