package tn.teamwill.tnrfx.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.apache.commons.io.IOUtils;

import tn.teamwill.tnrfx.model.Senario;

public class TestUiAspect {
	public static void main(String[] args) throws IOException {
		List<Senario> list = Utilities.fromJSONtoSenario(Utilities.findIp());
		for (Senario s : list) {
			createClassTest(s);
		}
	}

	public static void createClassTest(Senario senario) throws IOException {
		byte[] bytesFromFile;
		Path file = Paths.get("/tmp/TestClass");
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream is = loader.getResourceAsStream("TestUi.java");
		bytesFromFile = IOUtils.toByteArray(is);
		String textFromFile = new String(bytesFromFile, StandardCharsets.UTF_8);
		textFromFile = textFromFile.replaceAll("#######", senario.getContent());
		Files.write(file, textFromFile.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
	}

}
