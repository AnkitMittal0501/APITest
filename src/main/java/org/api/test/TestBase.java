package org.api.test;

import java.io.FileInputStream;
import java.util.Properties;

public class TestBase {
	public Properties prp;
	public FileInputStream file;

	public TestBase() {
		prp = new Properties();
		try {
			file = new FileInputStream(
					"C:\\Users\\My Pc\\eclipse-workspace_photon\\org.api.test\\src\\main\\java\\com\\qa\\config\\config.properties");
			prp.load(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
