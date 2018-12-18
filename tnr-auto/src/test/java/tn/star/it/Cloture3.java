package tn.star.it;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Cloture3 {
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
  public void testCloture3() throws Exception {
    driver.get("http://192.168.210.112:8680/star/ServletLogin?RGICommand=logout&RGIResponseOk=/star/res2/login/Start.html");
    driver.findElement(By.id("RGI_username")).click();
    driver.findElement(By.id("RGI_username")).clear();
    driver.findElement(By.id("RGI_username")).sendKeys("ST1012");
    driver.findElement(By.id("RGI_password")).click();
    driver.findElement(By.id("RGI_password")).clear();
    driver.findElement(By.id("RGI_password")).sendKeys("star123");
    driver.findElement(By.id("BTNsuivant0")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Centre de liquidation:'])[1]/following::span[1]")).click();
    driver.findElement(By.linkText("AUTO CORPOREL N")).click();
    driver.findElement(By.id("BTNsuivant0")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Gestion contrats'])[1]/following::div[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Déclarer sinistre'])[1]/following::div[1]")).click();
    driver.findElement(By.id("PssinsinistroCnumerosinistro")).click();
    driver.findElement(By.id("PssinsinistroCnumerosinistro")).clear();
    driver.findElement(By.id("PssinsinistroCnumerosinistro")).sendKeys("000019960503465");
    driver.findElement(By.id("BTNtrova0")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Gestionnaire de sinistre'])[1]/following::img[1]")).click();
    driver.findElement(By.id("BTNseleziona0")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Gestion paiement'])[1]/following::div[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Clôturer / Rouvrir'])[1]/following::div[1]")).click();
    driver.findElement(By.id("idCausale_33048356")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Motif de la mise à jour'])[1]/following::span[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Motif de la mise à jour'])[1]/following::span[1]")).click();
    driver.findElement(By.id("noteMotivazioni")).click();
    driver.findElement(By.id("noteMotivazioni")).clear();
    driver.findElement(By.id("noteMotivazioni")).sendKeys("test");
    driver.findElement(By.id("BTNchiudi0")).click();
    driver.close();
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
