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

public class Utilities {
	static UiTestDetails uiTestDetails;
	private static  int i;

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

	public static int testApplicationUi(Senario senario) throws MalformedURLException {
		addTestDetail("10.10.216.157", senario);
		Interpreter interpreter = new Interpreter();
		String code = "tn.teamwill.tnrfx.util.Utilities u=new tn.teamwill.tnrfx.util.Utilities(); u.setI(0); System.setProperty(\"webdriver.chrome.driver\", \"/home/bettaieb/Téléchargements/chromedriver\");"
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

		try {
			String code4 = senario.getContent();
			String res = code4.replaceAll("\\n", "").replaceAll("\\\\", "");
			System.out.println(i);
			System.out.println(res.contains("\\n"));
			
			interpreter.eval(code + res);
			senario.setIdUsed(new SimpleBooleanProperty(true));
			updateTestDetail("10.10.216.157", true);
			return i;
		} catch (EvalError e) {
			e.printStackTrace();
			updateTestDetail("10.10.216.157", false);
			return i;
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

	public static String addTestDetail(String ipAdress, Senario senario) {
		String result = "fail";
		try {
			UiTestDetails uiTestDetails = new UiTestDetails();
			uiTestDetails.setType("LOCAL");
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
			Utilities.uiTestDetails = mapper.readValue(result, UiTestDetails.class);
			in.close();
			conn.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String updateTestDetail(String ipAdress, Boolean testResult) {
		uiTestDetails.setResult(testResult);
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
			String json = mapper.writeValueAsString(uiTestDetails);
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
