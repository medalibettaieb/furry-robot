package tn.star.it.senariot1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestCloture {
	private final static Logger LOGGER = Logger.getLogger(TestCloture.class.getName());

	public void clotureSinistre(String numSinistre, String cdl) throws SecurityException, IOException {
		Logger logger = Logger.getLogger("MyLog");
		FileHandler fh;
		fh = new FileHandler("/home/bettaieb/Desktop/MyLogFile.log");
		logger.addHandler(fh);
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String appConfigPath = rootPath + "data.properties";
		Properties appProps = new Properties();
		try {
			appProps.load(new FileInputStream(appConfigPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("step1");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.setProperty("webdriver.chrome.driver", "chromedriver");
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

		System.out.println(cdl);
		driver.findElement(By.id("inputSel_RGI_listaCdl")).sendKeys(cdl);
		driver.findElement(By.id("BTNsuivant0")).click();
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Gestion contrats'])[1]/following::div[1]"))
				.click();
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Déclarer sinistre'])[1]/following::div[1]"))
				.click();
		driver.findElement(By.id("PssinsinistroCnumerosinistro")).click();
		driver.findElement(By.id("PssinsinistroCnumerosinistro")).clear();
		driver.findElement(By.id("PssinsinistroCnumerosinistro")).sendKeys(numSinistre);
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

		if (element.getText().equalsIgnoreCase("Fermé sans suite") || element.getText().equalsIgnoreCase("Fermé")) {
			logger.info("sinistre cloturé");
			driver.close();

		} else {
			driver.findElement(By.xpath(
					"(.//*[normalize-space(text()) and normalize-space(.)='Clôturer / Rouvrir'])[1]/following::div[1]"))
					.click();

			// chella
			WebElement tableParent = driver.findElement(By.className("centerposition"));
			List<WebElement> tablesChild = tableParent.findElements(By.className("tabellaRiepilogo"));

			for (WebElement tc : tablesChild) {
				List<WebElement> cheldrens = tc.findElements(By.className("cellaTitolo"));

				for (WebElement cs : tablesChild) {
					List<WebElement> targets = cs.findElements(By.tagName("input"));

					targets.get(0).click();
					targets.get(0).click();
				}
			}

//			WebElement table = driver.findElement(By.className("tabellaRiepilogo"));
//			List<WebElement> elements = table.findElements(By.className("cellaTitolo"));
//
//			System.out.println(elements);
//
//			List<WebElement> elementsk = elements.get(0).findElements(By.tagName("input"));
//			elementsk.get(0).click();
//			elementsk.get(0).click();

			WebElement element2 = driver.findElement(By.id("inputSel_PssinclaimcausIdclaimcaus"));
			element2.click();
			element2.clear();
			element2.sendKeys("Avis technique de l’expert défavorable");
			driver.findElement(By.id("noteMotivazioni")).click();
			driver.findElement(By.id("noteMotivazioni")).clear();
			driver.findElement(By.id("noteMotivazioni")).sendKeys("test");
			driver.findElement(By.id("BTNchiudi0")).click();
			driver.close();
		}

	}

}
