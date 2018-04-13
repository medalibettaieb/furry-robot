package tn.teamwill.tnrfx.util;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Tet {

	public static void main(String[] args) throws InterruptedException, IOException {

		// Create a new instance of the Firefox driver
		System.setProperty("webdriver.chrome.driver", "/home/bettaieb/Téléchargements/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("test-type");
		options.addArguments("start-maximized");
		options.addArguments("disable-infobars");
		options.addArguments("--disable-extensions");
		options.addArguments("no-sandbox");
		options.addArguments("--enable-automation");
		options.addArguments("test-type=browser");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 15);
		try {
			driver.get("http://10.10.216.157:8887/CassiopaeFORMATION/faces/jsp/login/login.jspx");
			driver.findElement(By.id("ksiopuser::content")).sendKeys("ORFI");
			driver.findElement(By.id("ksiopvalue::content")).sendKeys("ORFI");
			driver.findElement(By.id("btnLogin")).click();
			driver.findElement(By.xpath("//div[@id='modulesMenu:s_tu_abb']/div/table/tbody/tr/td[2]")).click();
			driver.findElement(By.xpath("//tr[@id='modulesMenu:s_tu_abd']/td[2]")).click();
			driver.switchTo().frame("__CASSIOPAE_CLIENT_AREA_FRAME_ID__::f");
			Thread.sleep(2000);
			driver.findElement(By.id("secId:s_0q_aao:tbTableToolbar:new::icon")).click();
			new Select(driver.findElement(By.id("secId:mainBody:s_zn_aal::content"))).selectByVisibleText("Client");
			WebElement element = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.id("secId:mainBody:s_zn_aal::content")));
			element.click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("secId:mainBody:s_zn_aau::content")));
			new Select(driver.findElement(By.id("secId:mainBody:s_zn_aau::content")))
					.selectByVisibleText("Personne morale");
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_zn_aau::content")).click();
			WebElement e1 = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.id("secId:mainBody:s_zo_aad::content")));
			e1.click();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_zo_aad::content")).sendKeys("hedfi");
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_zn_aak::content")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_zn_aak::content")).sendKeys("12 rue ABCD");
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_zn_aag::content")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_zn_aaj::content")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_zn_aaj::content")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_zn_aaj::content")).sendKeys("123 123 123 12345");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//tr[@id='secId:mainBody:s_zn_aba']/td")).click();
			Thread.sleep(2000);
			new Select(driver.findElement(By.id("secId:mainBody:s_zo_aac::content"))).selectByVisibleText("Monsieur");
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_zo_aac::content")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_zo_aaj::content")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_zo_aaj::content")).sendKeys("HAIFA");
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_zo_aam::content")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_zo_aam::content")).sendKeys("12121980");
			Thread.sleep(2000);
			new Select(driver.findElement(By.id("secId:mainBody:s_zo_aau::content"))).selectByVisibleText("Marié(e)");
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_zo_aau::content")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_zo_aav::content")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_zo_aaw::content")).sendKeys("12/12/2003");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//tr[@id='secId:mainBody:s_zo_abe']/td")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_zo_aap::content")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:tf_s_zo_aaq::content")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:tf_s_zo_aaq::content")).sendKeys("75009");
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:tf_s_zo_aar::content")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:bSr_s_zn_aa5::icon")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@id='srcId:s_be8_aaj:innerTbl::db']/table/tbody/tr[2]/td")).click();
			System.out.println("done 1");
			Thread.sleep(2000);
			driver.findElement(By.id("srcId:s_be8_aal")).click();
			Thread.sleep(2000);
			new Select(driver.findElement(By.id("secId:mainBody:s_zn_ba6::content")))
					.selectByVisibleText("IS : Impots sur les Societes");
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_zn_ba6::content")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_zn_aa8::content")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_zn_aa8::content")).sendKeys("123456");
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_zn_aa8::content")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_zn_abf::content")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_yb_aaj::disAcr")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_yc_aac:tbTableToolbar:new::icon")).click();
			Thread.sleep(2000);
			new Select(driver.findElement(By.id("secId:mainBody:j_id_jsp_295101557_17pc26::content")))
					.selectByVisibleText("Propriétaire");
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:j_id_jsp_295101557_17pc26::content")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:j_id_jsp_295101557_18pc26::content")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:j_id_jsp_295101557_18pc26::content")).sendKeys("2");
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:j_id_jsp_295101557_19pc26::content")).sendKeys("2000");
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_yd_abd::content")).sendKeys("2 RUE DDD");
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_yd_aaz::content")).sendKeys("2");
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_yd_aay::content")).sendKeys("FRANCE");
			Thread.sleep(2000);
			System.out.println("done 2");
			Thread.sleep(2000);
			try {
				driver.findElement(By.id("secId:mainBody:j_id180::content")).sendKeys("75009");
			} catch (Exception e) {
				driver.findElement(By.id("secId:mainBody:j_id175::content")).sendKeys("75009");
			}
			
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_yd_abc::content")).sendKeys("1235");
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_yb_aak::disAcr")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_yh_aac:tbTableToolbar:new::icon")).click();
			Thread.sleep(2000);

			System.out.println("done 3");

			driver.findElement(By.id("secId:mainBody:s_yj_aag::content")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_yj_aag::content")).clear();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_yj_aag::content")).sendKeys("FR7630001007941234567890185");
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_yj_aaj::content")).click();
			Thread.sleep(2000);
			new Select(driver.findElement(By.id("secId:mainBody:s_yj_aar::content"))).selectByVisibleText("Autre");
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_yj_aar::content")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_yj_aax::content")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_yj_aax::content")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_yj_aax::content")).sendKeys("02/02/2020");
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:j_id_jsp_1980494421_24pc30::content")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:j_id_jsp_1980494421_24pc30::content")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:j_id_jsp_1980494421_24pc30::content")).sendKeys("02/02/2020");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@id='secId:mainBody:s_yj_aaz']/table/tbody/tr/td[2]")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:s_zl_aab:s_bey_aab::icon")).click();
			Thread.sleep(2000);
			System.out.println("done 4");
			driver.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		StringBuilder builder = new StringBuilder();
		Logs log = driver.manage().logs();
		LogEntries logEntries = log.get(LogType.BROWSER);
		if (!logEntries.getAll().isEmpty()) {
			for (LogEntry logEntry : logEntries) {
				builder.append(logEntry.getLevel() + ": " + logEntry.getMessage());
				builder.append(System.lineSeparator());
			}
		}
	}
}
