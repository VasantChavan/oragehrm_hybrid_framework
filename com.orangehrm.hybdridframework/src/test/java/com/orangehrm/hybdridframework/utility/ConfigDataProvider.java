package com.orangehrm.hybdridframework.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider {

	public static Properties prop;

	public ConfigDataProvider(String configFile) {

		try {

			File fs = new File("./Config/" + configFile + ".properties");
			FileInputStream fins = new FileInputStream(fs);

			prop = new Properties();
			prop.load(fins);

		} catch (Exception e) {
			System.out.println(" File not found :- " + e);
		}

	}

	public String getUserName() {
		return prop.getProperty("username");
	}

	public String getPassword() {
		return prop.getProperty("password");
	}

	public String getAppUrl() {
		return prop.getProperty("url");
	}

	public String getKeyValue(String keyname) {
		return prop.getProperty(keyname);
	}

//	public static void main(String[] args) {
//
//		ConfigDataProvider configDataProvider = new ConfigDataProvider("config");
//
//		String username = configDataProvider.getUserName();
//		String pwd = configDataProvider.getPassword();
//
//		System.out.println(username + "    " + pwd);
//		
//		
//		System.out.println(configDataProvider.getKeyValue("myname"));
//	}

}
