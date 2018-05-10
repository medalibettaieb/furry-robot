package tn.teamwill.tnrfx.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
import javafx.beans.property.SimpleBooleanProperty;
import tn.teamwill.tnrfx.model.Senario;
import tn.teamwill.tnrfx.model.UITest;
import tn.teamwill.tnrfx.model.UiTestDetails;
import tn.teamwill.tnrfx.model.UiTestDetailsEntity;

public class Utilities {
	static UiTestDetailsEntity uiTestDetailsRef, uiTestDetailsTest;
	private static int i;

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

	public static int testApplicationUi(Senario senario) throws FileNotFoundException, IOException {
		Interpreter interpreter = new Interpreter();
		 String init = "org.openqa.selenium.remote.DesiredCapabilities capability = org.openqa.selenium.remote.DesiredCapabilities.chrome();"
	                + "    org.openqa.selenium.WebDriver driver = new org.openqa.selenium.remote.RemoteWebDriver(new java.net.URL(\"http://"
	                + Utilities.findIp() + ":4444/wd/hub\"), capability);"
	                + "    driver.manage().timeouts().implicitlyWait(10, java.util.concurrent.TimeUnit.SECONDS);"
	                + "    org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, 15);  ";

		String code = "tn.teamwill.tnrfx.util.Utilities u=new tn.teamwill.tnrfx.util.Utilities(); u.setI(0); System.setProperty(\"webdriver.chrome.driver\", \"chromedriver\");"
				+ "		org.openqa.selenium.chrome.ChromeOptions options = new org.openqa.selenium.chrome.ChromeOptions();"
				+ "		options.addArguments(new String[] {\"test-type\"});"
				+ "		options.addArguments(new String[] {\"start-maximized\"});"
				+ "		options.addArguments(new String[] {\"disable-infobars\"});"
				+ "		options.addArguments(new String[] {\"--disable-extensions\"});"
				+ "		options.addArguments(new String[] {\"no-sandbox\"});"
				+ "		options.addArguments(new String[] {\"--enable-automation\"});"
				+ "		options.addArguments(new String[] {\"test-type=browser\"});"
				+ "		org.openqa.selenium.WebDriver driver = new org.openqa.selenium.chrome.ChromeDriver(options);"
				+ "		driver.manage().timeouts().implicitlyWait(10, java.util.concurrent.TimeUnit.SECONDS);"
				+ "		org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, 15);  ";
		String codeIp = "driver.get(\"http://10.10.216.157:8887/CassiopaeFORMATION/faces/jsp/login/login.jspx\");";
		String codeIpTest = "driver.get(\"http://10.10.216.157:8888/CassiopaeFORMATION/faces/jsp/login/login.jspx\");";
		String code4 = senario.getContent();
		String res = code4.replaceAll("\\n", "").replaceAll("\\\\", "");

		Thread thRef = new Thread(() -> {
			try {
				interpreter.eval(code + codeIp + res);
				senario.setIdUsed(new SimpleBooleanProperty(true));
				updateTestDetail("10.10.216.157", true, "REFERENCE");
			} catch (EvalError e) {
				e.printStackTrace();
				updateTestDetail("10.10.216.157", false, "REFERENCE");
			}
		});

		Thread thTest = new Thread(() -> {
			try {
				interpreter.eval(code + codeIpTest + res);
				senario.setIdUsed(new SimpleBooleanProperty(true));
				updateTestDetail("10.10.216.157", true, "TEST");
			} catch (EvalError e) {
				e.printStackTrace();
				updateTestDetail("10.10.216.157", false, "TEST");
			}
		});

		try {
			addTestDetail("10.10.216.157", senario, "REFERENCE");
			interpreter.eval(code + codeIp + res);
			senario.setIdUsed(new SimpleBooleanProperty(true));
			updateTestDetail("10.10.216.157", true, "REFERENCE");
		} catch (EvalError e) {
			e.printStackTrace();
			updateTestDetail("10.10.216.157", false, "REFERENCE");
		}

		try {
			addTestDetail("10.10.216.157", senario, "TEST");
			interpreter.eval(code + codeIpTest + res);
			senario.setIdUsed(new SimpleBooleanProperty(true));
			updateTestDetail("10.10.216.157", true, "TEST");
		} catch (EvalError e) {
			e.printStackTrace();
			updateTestDetail("10.10.216.157", false, "TEST");
		}
		// thRef.start();
		// thTest.start();
		return 1;
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

	public static List<UiTestDetailsEntity> fromJSONtoUiTestDetails(String ipAdress, String uuidUiTest) {
		List<UiTestDetailsEntity> uiTestDetails = null;
		try {
			URL url = new URL("http://" + ipAdress + ":8080/tnr/webservice/uitestdetails/" + uuidUiTest);
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
				uiTestDetails = mapper.readValue(output, new TypeReference<List<UiTestDetailsEntity>>() {
				});
			}
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return uiTestDetails;
	}

	public static String addTestDetail(String ipAdress, Senario senario, String database) {
		String result = "fail";
		try {
			UiTestDetailsEntity uiTestDetails = new UiTestDetailsEntity();
			uiTestDetails.setType("LOCAL");
			uiTestDetails.setDatabase(database);
			UITest uiTest = new UITest();
			uiTest.setId(senario.getId());
			uiTestDetails.setUiTest(uiTest);

			URL url = new URL("http://" + ipAdress + ":8080/tnr/webservice/uitestdetails");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");

			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(uiTestDetails);
			System.out.println(json);
			OutputStreamWriter os = new OutputStreamWriter(conn.getOutputStream());
			os.write(json);
			os.close();

			// read the response
			InputStream in = new BufferedInputStream(conn.getInputStream());
			result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
			// JSONObject jsonObject = new JSONObject(result);
			if (database.equalsIgnoreCase("REFERENCE"))
				Utilities.uiTestDetailsRef = mapper.readValue(result, UiTestDetailsEntity.class);
			else
				Utilities.uiTestDetailsTest = mapper.readValue(result, UiTestDetailsEntity.class);

			in.close();
			conn.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String updateTestDetail(String ipAdress, Boolean testResult, String database) {
		if (database.equalsIgnoreCase("REFERENCE"))
			uiTestDetailsRef.setResult(testResult);
		else
			uiTestDetailsTest.setResult(testResult);

		String result = "fail";
		try {

			URL url = new URL("http://" + ipAdress + ":8080/tnr/webservice/uitestdetails");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("PUT");

			ObjectMapper mapper = new ObjectMapper();
			String json;
			if (database.equalsIgnoreCase("REFERENCE"))
				json = mapper.writeValueAsString(uiTestDetailsRef);
			else
				json = mapper.writeValueAsString(uiTestDetailsTest);
			OutputStreamWriter os = new OutputStreamWriter(conn.getOutputStream());
			os.write(json);
			os.close();

			// read the response
			InputStream in = new BufferedInputStream(conn.getInputStream());
			result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");

			in.close();
			conn.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
}
