package tn.teamwill.tnrfx.senariotPlace;

import java.io.IOException;
import java.net.URL;
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
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestClass {
	private final static Logger LOGGER = Logger.getLogger(TestClass.class.getName());

	public static void main(String[] args) throws InterruptedException, IOException {
		// Create a new instance of the Firefox driver   /home/bettaieb/Téléchargements  src/main/resources/drivers/chromedriver

				System.setProperty("webdriver.chrome.driver", "/home/bettaieb/Téléchargements/chromedriver");

				LoggingPreferences logs = new LoggingPreferences();
				logs.enable(LogType.BROWSER, Level.ALL);
				logs.enable(LogType.SERVER, Level.ALL);
				logs.enable(LogType.DRIVER, Level.ALL);
				logs.enable(LogType.PROFILER, Level.ALL);
				logs.enable(LogType.CLIENT, Level.ALL);
				DesiredCapabilities capability = DesiredCapabilities.chrome();
				capability.setCapability(CapabilityType.LOGGING_PREFS, logs);

				// WebDriver driver = new RemoteWebDriver(new
				// URL("http://10.10.216.61:4444/wd/hub"), capability);
				// WebDriver driver = new ChromeDriver(options);

				/*
				 * driver.get(
				 * "http://10.10.216.22:8887/CassiopaeFORMATION/faces/jsp/login/login.jspx" );
				 * 
				 * driver.get(
				 * "http://10.10.216.22:8887/CassiopaeFORMATION/faces/jsp/login/login.jspx" );
				 * driver.findElement(By.id("ksiopuser::content")).clear();
				 * driver.findElement(By.id("ksiopuser::content")).sendKeys("ORFI");
				 * driver.findElement(By.id("ksiopvalue::content")).clear();
				 * driver.findElement(By.id("ksiopvalue::content")).sendKeys("ORFI");
				 * driver.findElement(By.id("btnLogin")).click();
				 * driver.findElement(By.linkText("Acteurs")).click();
				 * driver.findElement(By.xpath("//tr[@id='modulesMenu:s_tu_abd']/td[2]")
				 * ).click(); driver.switchTo().frame("__CASSIOPAE_CLIENT_AREA_FRAME_ID__::f");
				 * boolean b = driver.getPageSource().contains(
				 * "secId:s_0q_aao:tbTableToolbar:new::icon");
				 * driver.findElement(By.id("secId:s_0q_aao:tbTableToolbar:new::icon")).
				 * click(); System.out.println(b);
				 */

				// System.setProperty("webdriver.chrome.driver", "/opt/chromedriver");

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
				// ************************************************************************************************

		#######
		
		StringBuilder builder = new StringBuilder();
		Logs log = driver.manage().logs();
		LogEntries logEntries = log.get(LogType.BROWSER);
		if (!logEntries.getAll().isEmpty()) {
			LOGGER.log(Level.INFO, "Console output from browser:");
			for (LogEntry logEntry : logEntries) {
				builder.append( logEntry.getLevel() + ": " + logEntry.getMessage());
				builder.append(System.lineSeparator());
				LOGGER.log(Level.INFO, "Java: " + logEntry.getLevel() + ": " + logEntry.getMessage());
			}
		}
	
	}}
