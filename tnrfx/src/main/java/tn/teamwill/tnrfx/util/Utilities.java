package tn.teamwill.tnrfx.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import bsh.EvalError;
import bsh.Interpreter;
import tn.teamwill.tnrfx.model.Senario;

public class Utilities {
	static String returnValue;

	public static void createClassTest(Senario senario) throws IOException {
		byte[] bytesFromFile;
		Path file = Paths.get("/tmp/TestClass.java");
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream is = loader.getResourceAsStream("TestUi.java");
		bytesFromFile = IOUtils.toByteArray(is);
		String textFromFile = new String(bytesFromFile, StandardCharsets.UTF_8);
		textFromFile = textFromFile.replaceAll("#######", senario.getContent());
		Files.write(file, textFromFile.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
	}

	public static String findIp() throws FileNotFoundException, IOException {
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String appConfigPath = rootPath + "app.properties";
		Properties appProps = new Properties();
		appProps.load(new FileInputStream(appConfigPath));
		return appProps.getProperty("ip1");
	}

	public static String testApplicationUi() throws MalformedURLException {
		
		Interpreter interpreter = new Interpreter();
		String code = "int i=0; \n System.setProperty(\"webdriver.chrome.driver\", \"/home/bettaieb/Téléchargements/chromedriver\");\n"
				+ "		org.openqa.selenium.chrome.ChromeOptions options = new org.openqa.selenium.chrome.ChromeOptions();\n"
				+ "		options.addArguments(new String[] {\"test-type\"});\n"
				+ "		options.addArguments(new String[] {\"start-maximized\"});\n"
				+ "		options.addArguments(new String[] {\"disable-infobars\"});\n"
				+ "		options.addArguments(new String[] {\"--disable-extensions\"});\n"
				+ "		options.addArguments(new String[] {\"no-sandbox\"});\n"
				+ "		options.addArguments(new String[] {\"--enable-automation\"});\n"
				+ "		options.addArguments(new String[] {\"test-type=browser\"});\n"
				+ "		org.openqa.selenium.WebDriver driver = new org.openqa.selenium.chrome.ChromeDriver(options);\n"
				+ "		driver.manage().timeouts().implicitlyWait(10, java.util.concurrent.TimeUnit.SECONDS);\n"
				+ "		org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, 15);";

		String code2 = "try {\n"
				+ "			driver.get(\"http://10.10.216.157:8887/CassiopaeFORMATION/faces/jsp/login/login.jspx\");\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"ksiopuser::content\")).sendKeys(new String[] {\"ORFI\"});\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"ksiopvalue::content\")).sendKeys(new String[] {\"ORFI\"});\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"btnLogin\")).click();\n"
				+ "			driver.findElement(org.openqa.selenium.By.xpath(\"//div[@id='modulesMenu:s_tu_abb']/div/table/tbody/tr/td[2]\")).click();\n"
				+ "			driver.findElement(org.openqa.selenium.By.xpath(\"//tr[@id='modulesMenu:s_tu_abd']/td[2]\")).click();\n"
				+ "			Thread.sleep(3000); \n driver.switchTo().frame(\"__CASSIOPAE_CLIENT_AREA_FRAME_ID__::f\");\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:s_0q_aao:tbTableToolbar:new::icon\")).click();\n"
				+ "			new org.openqa.selenium.support.ui.Select(driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_zn_aal::content\"))).selectByVisibleText(\"Client\");\n"
				+ "			org.openqa.selenium.WebElement element = wait\n"
				+ "					.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(org.openqa.selenium.By.id(\"secId:mainBody:s_zn_aal::content\")));\n "
				+ "			element.click();\n " 
				+ "	Thread.sleep(2000);\n"
				
				+ "			wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(org.openqa.selenium.By.id(\"secId:mainBody:s_zn_aau::content\")));\n"
				+ "			System.out.println(\"ooo\"); \n new Select(driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_zn_aau::content\")))\n"
				+ "					.selectByVisibleText(\"Personne morale\");\n" + "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_zn_aau::content\")).click();\n"
				+ "			WebElement e1 = wait\n"
				+ "					.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(org.openqa.selenium.By.id(\"secId:mainBody:s_zo_aad::content\")));\n"
				+ "			e1.click();\n" + "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_zo_aad::content\")).sendKeys(\"hedfi\");\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_zn_aak::content\")).click();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_zn_aak::content\")).sendKeys(\"12 rue ABCD\");\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_zn_aag::content\")).click();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_zn_aaj::content\")).click();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_zn_aaj::content\")).click();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_zn_aaj::content\")).sendKeys(\"123 123 123 12345\");\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.xpath(\"//tr[@id='secId:mainBody:s_zn_aba']/td\")).click();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			new Select(driver.findElement(By.id(\"secId:mainBody:s_zo_aac::content\"))).selectByVisibleText(\"Monsieur\");\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_zo_aac::content\")).click();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_zo_aaj::content\")).click();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_zo_aaj::content\")).sendKeys(\"HAIFA\");\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_zo_aam::content\")).click();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_zo_aam::content\")).sendKeys(\"12121980\");\n"
				+ "			Thread.sleep(2000);\n"
				+ "			new Select(driver.findElement(By.id(\"secId:mainBody:s_zo_aau::content\"))).selectByVisibleText(\"Marié(e)\");\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_zo_aau::content\")).click();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_zo_aav::content\")).click();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_zo_aaw::content\")).sendKeys(\"12/12/2003\");\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.xpath(\"//tr[@id='secId:mainBody:s_zo_abe']/td\")).click();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_zo_aap::content\")).click();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:tf_s_zo_aaq::content\")).click();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:tf_s_zo_aaq::content\")).sendKeys(\"75009\");\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:tf_s_zo_aar::content\")).click();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:bSr_s_zn_aa5::icon\")).click();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.xpath(\"//div[@id='srcId:s_be8_aaj:innerTbl::db']/table/tbody/tr[2]/td\")).click();\n"
				+ "			System.out.println(\"done 1\");\n" + "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"srcId:s_be8_aal\")).click();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			new org.openqa.selenium.support.ui.Select(driver.findElement(By.id(\"secId:mainBody:s_zn_ba6::content\")))\n"
				+ "					.selectByVisibleText(\"IS : Impots sur les Societes\");\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_zn_ba6::content\")).click();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_zn_aa8::content\")).click();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_zn_aa8::content\")).sendKeys(\"123456\");\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_zn_aa8::content\")).click();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_zn_abf::content\")).click();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_yb_aaj::disAcr\")).click();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_yc_aac:tbTableToolbar:new::icon\")).click();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			new org.openqa.selenium.support.ui.Select(driver.findElement(By.id(\"secId:mainBody:j_id_jsp_295101557_17pc26::content\")))\n"
				+ "					.selectByVisibleText(\"Propriétaire\");\n" + "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:j_id_jsp_295101557_17pc26::content\")).click();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:j_id_jsp_295101557_18pc26::content\")).click();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:j_id_jsp_295101557_18pc26::content\")).sendKeys(\"2\");\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:j_id_jsp_295101557_19pc26::content\")).sendKeys(\"2000\");\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_yd_abd::content\")).sendKeys(\"2 RUE DDD\");\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_yd_aaz::content\")).sendKeys(\"2\");\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_yd_aay::content\")).sendKeys(\"FRANCE\");\n"
				+ "			Thread.sleep(2000);\n" + "			System.out.println(\"done 2\");\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:j_id175::content\")).sendKeys(\"75009\");\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_yd_abc::content\")).sendKeys(\"1235\");\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_yb_aak::disAcr\")).click();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_yh_aac:tbTableToolbar:new::icon\")).click();\n"
				+ "			Thread.sleep(2000);\n" + "\n" + "			System.out.println(\"done 3\");\n" + "\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_yj_aag::content\")).click();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_yj_aag::content\")).clear();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_yj_aag::content\")).sendKeys(\"FR7630001007941234567890185\");\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_yj_aaj::content\")).click();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			new Select(driver.findElement(By.id(\"secId:mainBody:s_yj_aar::content\"))).selectByVisibleText(\"Autre\");\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_yj_aar::content\")).click();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_yj_aax::content\")).click();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_yj_aax::content\")).click();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:s_yj_aax::content\")).sendKeys(\"02/02/2020\");\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:j_id_jsp_1980494421_24pc30::content\")).click();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:j_id_jsp_1980494421_24pc30::content\")).click();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:mainBody:j_id_jsp_1980494421_24pc30::content\")).sendKeys(\"02/02/2020\");\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.xpath(\"//div[@id='secId:mainBody:s_yj_aaz']/table/tbody/tr/td[2]\")).click();\n"
				+ "			Thread.sleep(2000);\n"
				+ "			driver.findElement(org.openqa.selenium.By.id(\"secId:s_zl_aab:s_bey_aab::icon\")).click();\n"
				+ "			Thread.sleep(2000);\n" + "			System.out.println(\"done 4\");\n" + "\n"
				+ "		} catch (Exception e) {\n" + "		e.printStackTrace();\n" + "		}";

		try {
			interpreter.eval(code + code2);
			return returnValue;
		} catch (EvalError e) {
			return returnValue;
		}

	}

	public void findResultTest(int i) {
		System.out.println(i);
	}

	public static List<Senario> fromJSONtoSenario(String ipAdress) {
		List<Senario> senario2s = null;
		try {
			URL url = new URL("http://" + ipAdress + ":8080/tnr/webservice/uitest");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			while ((output = br.readLine()) != null) {
				output.replace("[", "");
				ObjectMapper mapper = new ObjectMapper();
				senario2s = mapper.readValue(output, new TypeReference<List<Senario>>() {
				});
			}
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return senario2s;
	}
}
