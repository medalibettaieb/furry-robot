package tn.star.it.senariot1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import tn.star.it.utilities.ExcelConfig;

public class TestCloture {
	private final static Logger LOGGER = Logger.getLogger(TestCloture.class.getName());
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

		driver.get(
				"http://192.168.210.112:8680/star/ServletLogin?RGICommand=logout&RGIResponseOk=/star/res2/login/Start.html");
		driver.findElement(By.id("RGI_username")).click();
		driver.findElement(By.id("RGI_username")).clear();
		driver.findElement(By.id("RGI_username")).sendKeys("ST1012");
		driver.findElement(By.id("RGI_password")).click();
		driver.findElement(By.id("RGI_password")).clear();
		driver.findElement(By.id("RGI_password")).sendKeys("star123");
		driver.findElement(By.id("BTNsuivant0")).click();
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Centre de liquidation:'])[1]/following::span[1]"))
				.click();
		driver.findElement(By.linkText("AUTO CORPOREL N")).click();
		driver.findElement(By.id("BTNsuivant0")).click();
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Gestion contrats'])[1]/following::div[1]"))
				.click();
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Déclarer sinistre'])[1]/following::div[1]"))
				.click();
		driver.findElement(By.id("PssinsinistroCnumerosinistro")).click();
		driver.findElement(By.id("PssinsinistroCnumerosinistro")).clear();
		driver.findElement(By.id("PssinsinistroCnumerosinistro")).sendKeys("000019960503465");
		driver.findElement(By.id("BTNtrova0")).click();
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Gestionnaire de sinistre'])[1]/following::img[1]"))
				.click();
		driver.findElement(By.id("BTNseleziona0")).click();
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Gestion paiement'])[1]/following::div[1]"))
				.click();

		WebElement element = driver.findElement(By.xpath("//*[@id=\"divFormContainer\"]/table/tbody/tr[2]/td[3]/b"));
		System.out.println(element.getText());

		if (element.getText().equalsIgnoreCase("Fermé sans suite")) {
			LOGGER.info("sinistre cloturé");
			driver.close();

		} else {
			driver.findElement(By.xpath(
					"(.//*[normalize-space(text()) and normalize-space(.)='Clôturer / Rouvrir'])[1]/following::div[1]"))
					.click();
			driver.findElement(By.id("idCausale_33048356")).click();
			driver.findElement(By.xpath(
					"(.//*[normalize-space(text()) and normalize-space(.)='Motif de la mise à jour'])[1]/following::span[1]"))
					.click();
			driver.findElement(By.xpath(
					"(.//*[normalize-space(text()) and normalize-space(.)='Motif de la mise à jour'])[1]/following::span[1]"))
					.click();
			driver.findElement(By.id("noteMotivazioni")).click();
			driver.findElement(By.id("noteMotivazioni")).clear();
			driver.findElement(By.id("noteMotivazioni")).sendKeys("test");
			driver.findElement(By.id("BTNchiudi0")).click();
			driver.close();
		}

	}

}
