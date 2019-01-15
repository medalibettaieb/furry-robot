package tn.star.it.senariot1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import tn.star.it.domain.Tier;
import tn.star.it.utilities.ExcelConfig;

public class TestAddTierV2 {
	private final static Logger LOGGER = Logger.getLogger(TestAddTierV2.class.getName());
	private static ExcelConfig excel = new ExcelConfig("./DocTest1.xlsx");

	public void ajouterTier(Tier tier) throws InterruptedException, IOException {

		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String appConfigPath = rootPath + "data.properties";
		Properties appProps = new Properties();
		appProps.load(new FileInputStream(appConfigPath));
		LOGGER.info("step1");
		// LOGGER.info(excel.readData(1, 2));
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
		Thread.sleep(1000);
		driver.findElement(By.id("PasubjectkeyCkey3")).sendKeys(tier.getNumDocument());
		driver.findElement(By.id("BTNtrouver0")).click();

		try {
			WebElement element = driver.findElement(By.id("BTNHistorique0"));
			LOGGER.info("tier already exist");
			driver.close();

		} catch (Exception e) {

			driver.findElement(By.id("BTNnouveau0")).click();
			driver.findElement(By.id("inputSel_AnagCurrentPv")).click();
			driver.findElement(By.id("inputSel_AnagCurrentPv")).clear();

			driver.findElement(By.id("inputSel_AnagCurrentPv")).sendKeys(tier.getNoeud());
			// driver.findElement(By.id("AnagCurrentPv")).click();

			driver.findElement(By.id("inputSel_PasoggettoEgiuridicofisico")).clear();
			driver.findElement(By.id("inputSel_PasoggettoEgiuridicofisico")).sendKeys(tier.getTypePersonne());

			driver.findElement(By.linkText("Personne physique")).click();
			driver.findElement(By.xpath(
					"(.//*[normalize-space(text()) and normalize-space(.)='Profession:'])[1]/following::span[1]"))
					.click();
			driver.findElement(By.linkText(tier.getProfession())).click();
			driver.findElement(By.id("BTNsuivant0")).click();

//			try {
//				WebElement element = driver.findElement(By.id("RGI_Alert"));
//				System.out.println("Tiers déjà présent dans la base de données.");
//				driver.close();
//				return;
//			} catch (Exception e2) {
//				// TODO: handle exception
//			}

			System.out.println(tier.getTypeDocument());
			if (tier.getTypeDocument().equalsIgnoreCase("Passeport")) {
				try {
					driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/button\r\n")).click();
					driver.findElement(By.id("inputSel_PasubjectkeyCkey2")).click();
					driver.findElement(By.id("inputSel_PasubjectkeyCkey2")).clear();
					driver.findElement(By.id("inputSel_PasubjectkeyCkey2")).sendKeys(tier.getTypeDocument());
					WebElement element = driver.findElement(By.id("PasubjectkeyCkey3"));
					driver.findElement(By.id("PasubjectkeyCkey3")).clear();
					driver.findElement(By.id("PasubjectkeyCkey3")).sendKeys(tier.getNumDocument());

				} catch (Exception e2) {
				}

			}

			driver.findElement(By.id("PadatisingoliCcognome")).click();
			driver.findElement(By.id("PadatisingoliCcognome")).clear();
			driver.findElement(By.id("PadatisingoliCcognome")).sendKeys(tier.getNom());
			Thread.sleep(1000);
			driver.findElement(By.id("PadatisingoliCoriginalsurname")).clear();
			driver.findElement(By.id("PadatisingoliCoriginalsurname")).sendKeys(tier.getNomArabe());
			Thread.sleep(1000);
			driver.findElement(By.id("PadatisingoliCnome")).clear();
			driver.findElement(By.id("PadatisingoliCnome")).sendKeys(tier.getPrenom());
			Thread.sleep(1000);
			driver.findElement(By.id("PadatisingoliCnome")).click();
			driver.findElement(By.id("PadatisingoliCnome")).clear();
			driver.findElement(By.id("PadatisingoliCnome")).sendKeys(tier.getPrenom());
			Thread.sleep(1000);
			driver.findElement(By.id("PadatisingoliCoriginalname")).clear();
			driver.findElement(By.id("PadatisingoliCoriginalname")).sendKeys(tier.getPrenomArabe());
			Thread.sleep(1000);
			driver.findElement(
					By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Sexe:'])[1]/following::span[1]"))
					.click();
			driver.findElement(By.linkText(tier.getSexe())).click();
			driver.findElement(By.id("PasoggettoDnascita")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("PasoggettoDnascita")).clear();
			Thread.sleep(1000);
			driver.findElement(By.id("PasoggettoDnascita")).sendKeys(tier.getDateNaissance());
			Thread.sleep(1000);
//			driver.findElement(By.id("PasubjectkeyCkey3")).click();
//			//driver.findElement(By.id("PasubjectkeyCkey3")).clear();
//			driver.findElement(By.id("PasubjectkeyCkey3")).sendKeys(tier.getNumDocument());
			driver.findElement(By.id("BTNRésidence0")).click();
			Thread.sleep(4000);
			driver.findElement(By.id("inputSel_praddressctoponym")).clear();
			driver.findElement(By.id("inputSel_praddressctoponym")).click();
			driver.findElement(By.id("inputSel_praddressctoponym")).sendKeys(tier.getTypeVoie());
			driver.findElement(By.id("inputSel_praddressctoponym")).click();
			driver.findElement(By.id("inputSel_praddressctoponym")).sendKeys("");
			driver.findElement(By.id("praddresschousenumber")).click();
			Thread.sleep(3000);

			driver.findElement(By.id("inputSel_praddressctoponym")).sendKeys("");

			driver.findElement(By.id("praddresscaddress")).sendKeys(tier.getAdresse());
			driver.findElement(By.id("inputSel_praddresscadminlevel3")).click();
			driver.findElement(By.id("inputSel_praddresscadminlevel3")).clear();
			driver.findElement(By.id("inputSel_praddresscadminlevel3")).sendKeys(tier.getLocalite());
			Thread.sleep(2000);
			LOGGER.info("tnr test step2 : verfing exixtance of tier");

			driver.findElement(By.id("BTNsuivant0")).click();
			Thread.sleep(2000);

			driver.findElement(By.linkText("Contacts")).click();
			driver.findElement(By.id("PatelefonoCnumeroFISSO")).click();
			driver.findElement(By.id("PatelefonoCnumeroFISSO")).clear();
			driver.findElement(By.id("PatelefonoCnumeroFISSO")).sendKeys(tier.getNumTel());

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

		}

	}
}
