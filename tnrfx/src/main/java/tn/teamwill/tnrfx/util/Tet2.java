package tn.teamwill.tnrfx.util;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Tet2 {

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
			Thread.sleep(2000);
			new Select(driver.findElement(By.id("secId:mainBody:s_zn_aal::content"))).selectByVisibleText("Client");
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_zn_aal::content")).click();
			Thread.sleep(2000);
			new Select(driver.findElement(By.id("secId:mainBody:s_zn_aau::content")))
					.selectByVisibleText("Personne physique");
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_zn_aau::content")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_zo_aad::content")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_zo_aad::content")).clear();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_zo_aad::content")).sendKeys("d");
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_zn_aak::content")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//tr[@id='secId:mainBody:s_zn_aag']/td[2]")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_zn_aag::content")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_zn_aag::content")).clear();
			Thread.sleep(2000);
			driver.findElement(By.id("secId:mainBody:s_zn_aag::content")).sendKeys("dd");
			Thread.sleep(2000);
			driver.findElement(By.id("secId:s_zl_aab:s_bey_aab::icon")).click();
			Thread.sleep(2000);
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
