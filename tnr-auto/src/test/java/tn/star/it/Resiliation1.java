package tn.star.it;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Resiliation1 {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "https://www.katalon.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testResiliation1() throws Exception {
		driver.get(
				"http://192.168.210.112:8680/star/ServletLogin?RGICommand=sysMessages&RGIResponseOk=/star/res2/login/Home.html&RGIGetJS=");
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
		driver.findElement(By.id("PcpolizzaCnumpolizza")).sendKeys("ci05*");
		driver.findElement(By.id("BTNtrouver0")).click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Statut'])[1]/following::img[1]"))
				.click();
		driver.findElement(By.id("BTNsélectionner0")).click();
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Gestion contrats'])[2]/following::div[1]"))
				.click();
		driver.findElement(By.id("inputSel_CBANN")).click();
		driver.findElement(By.linkText("Résiliation échéance compagnie")).click();
		driver.findElement(By.id("BTNsuivant0")).click();
		driver.findElement(By.id("PcmovimentoCnote")).click();
		driver.findElement(By.id("PcmovimentoCnote")).clear();
		driver.findElement(By.id("PcmovimentoCnote")).sendKeys("test dali");
		driver.findElement(By.id("BTNsuivant1")).click();
		driver.findElement(By.id("BTNfin1")).click();
		driver.findElement(By.id("BTNConsulter1")).click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Logout'])[1]/following::b[1]")).click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Logout'])[1]/following::b[1]")).click();
		// ERROR: Caught exception [ERROR: Unsupported command [doubleClick |
		// xpath=(.//*[normalize-space(text()) and
		// normalize-space(.)='Logout'])[1]/following::b[1] | ]]
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
