package tn.star.it.senariot2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import tn.star.it.utilities.ExcelConfig;

public class TestStarCases {
	private final static Logger LOGGER = Logger.getLogger(TestStarCases.class.getName());
	private static ExcelConfig excel = new ExcelConfig("./Testdata.xlsx");

	public static void main(String[] args) throws InterruptedException, IOException {
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String appConfigPath = rootPath + "data.properties";
		Properties appProps = new Properties();
		appProps.load(new FileInputStream(appConfigPath));
		LOGGER.info("step1");
		LOGGER.info(excel.readData(1, 1));
		Thread.sleep(2000);
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get(appProps.getProperty("url"));
		driver.findElement(By.id("RGI_username")).clear();
		driver.findElement(By.id("RGI_username")).sendKeys(appProps.getProperty("username"));
		driver.findElement(By.id("RGI_password")).clear();
		driver.findElement(By.id("RGI_password")).sendKeys(appProps.getProperty("password"));
//		driver.findElement(
//				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='close'])[1]/following::button[1]"))
//				.click();
		driver.findElement(By.id("BTNsuivant0")).click();
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Gestion des agences'])[1]/following::div[1]"))
				.click();
		driver.findElement(By.id("PadatisingoliCcognomeOrPadatisingoliCdenominazione")).clear();
		driver.findElement(By.id("PadatisingoliCcognomeOrPadatisingoliCdenominazione")).sendKeys("oooo");
		driver.findElement(By.id("BTNtrouver0")).click();
		driver.findElement(By.id("BTNnouveau0")).click();
		driver.findElement(By.id("inputSel_AnagCurrentPv")).click();
		driver.findElement(By.id("inputSel_AnagCurrentPv")).clear();
		driver.findElement(By.id("inputSel_AnagCurrentPv")).sendKeys("ABI");
//		driver.findElement(By.id("ui-active-menuitem")).click();
//		driver.findElement(By
//				.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Type Personne:'])[1]/following::span[1]"))
//				.click();

		// driver.findElement(By.id("inputSel_PasoggettoEgiuridicofisico")).click();
		driver.findElement(By.id("inputSel_PasoggettoEgiuridicofisico")).clear();
		driver.findElement(By.id("inputSel_PasoggettoEgiuridicofisico")).sendKeys("Personne physique");

		driver.findElement(By.linkText("Personne physique")).click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Profession:'])[1]/following::span[1]"))
				.click();
		driver.findElement(By.linkText("Autres")).click();
		driver.findElement(By.id("BTNsuivant0")).click();
		driver.findElement(By.id("PadatisingoliCcognome")).click();
		driver.findElement(By.id("PadatisingoliCcognome")).clear();
		driver.findElement(By.id("PadatisingoliCcognome")).sendKeys("mohamed");
		driver.findElement(By.id("PadatisingoliCoriginalsurname")).clear();
		driver.findElement(By.id("PadatisingoliCoriginalsurname")).sendKeys("محمد بن عمار");
		driver.findElement(By.id("PadatisingoliCnome")).clear();
		driver.findElement(By.id("PadatisingoliCnome")).sendKeys("ben am");
		// driver.findElement(By.id("header-logo")).click();
//		driver.findElement(By.id("BTNdiv_operatore")).click();
//		driver.findElement(By.id("PadatisingoliCnome")).click();
//		driver.findElement(
//				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Prénom:'])[1]/following::td[2]"))
//				.click();
		driver.findElement(By.id("PadatisingoliCnome")).click();
		driver.findElement(By.id("PadatisingoliCnome")).clear();
		driver.findElement(By.id("PadatisingoliCnome")).sendKeys("Mohamed");
		driver.findElement(By.id("PadatisingoliCoriginalname")).clear();
		driver.findElement(By.id("PadatisingoliCoriginalname")).sendKeys("محمد بن عمار");
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Sexe:'])[1]/following::span[1]"))
				.click();
		driver.findElement(By.linkText("Homme")).click();
		/// ok

		driver.findElement(By.id("PasoggettoDnascita")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("PasoggettoDnascita")).clear();
		Thread.sleep(1000);
		driver.findElement(By.id("PasoggettoDnascita")).sendKeys("01/01/1980");
//		driver.findElement(
//				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Relations'])[1]/following::table[1]"))
//				.click();
//		driver.findElement(By.id("PasoggettoDnascita")).click();
		///// wait
		Thread.sleep(1000);

//		driver.findElement(
//				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Après >'])[1]/following::select[2]"))
//				.click();
//
//		driver.findElement(By.linkText("6")).click();
//		driver.findElement(By.id("PasubjectkeyCkey3")).click();
//		driver.findElement(By.id("PasubjectkeyCkey3")).clear();

		driver.findElement(By.id("PasubjectkeyCkey3")).click();

		driver.findElement(By.id("PasubjectkeyCkey3")).sendKeys(appProps.getProperty("cin"));
//		driver.findElement(
//				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='État civil:'])[1]/following::span[1]"))
//				.click();
		driver.findElement(By.id("BTNRésidence0")).click();
		Thread.sleep(4000);
//		driver.findElement(By
//				.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Type de voie:'])[1]/following::span[1]"))
//				.click();
		driver.findElement(By.id("inputSel_praddressctoponym")).clear();
		driver.findElement(By.id("inputSel_praddressctoponym")).sendKeys("RUE");
		// driver.findElement(By.id("ui-active-menuitem")).click();
		Thread.sleep(3000);
//		driver.findElement(By.id("praddresscaddress")).click();
//		driver.findElement(By.id("praddresscaddress")).clear();
		driver.findElement(By.id("praddresscaddress")).sendKeys("mlkjkhj");

		driver.findElement(By.id("inputSel_praddresscadminlevel3")).click();
		driver.findElement(By.id("inputSel_praddresscadminlevel3")).clear();
		driver.findElement(By.id("inputSel_praddresscadminlevel3")).sendKeys("ABBES");

		Thread.sleep(2000);
//		driver.findElement(
//				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Localité:'])[1]/following::span[1]"))
//				.click();
		System.out.println("tnr test step2 : verfing exixtance of tier");
		try {
			driver.findElement(By.id("BTNsuivant0")).click();
			Thread.sleep(2000);

			driver.findElement(By.linkText("Contacts")).click();
			driver.findElement(By.id("PatelefonoCnumeroFISSO")).click();
			driver.findElement(By.id("PatelefonoCnumeroFISSO")).clear();
			driver.findElement(By.id("PatelefonoCnumeroFISSO")).sendKeys("214654545");

			driver.findElement(By.id("BTNconfirmer0")).click();
			Thread.sleep(2000);

			Alert alert = driver.switchTo().alert();
			alert.accept();

			Thread.sleep(4000);
			driver.findElement(
					By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='close'])[3]/following::button[1]"))
					.click();
			LOGGER.info("tier succefully added ....");
			driver.close();

		} catch (Exception e) {
			LOGGER.info("tier already  exixtant ....");
			driver.close();
		}

	}

}
