package com.plt.qprobot.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesUtils {
	private static Logger Log=Logger.getLogger(Properties.class);
	
	private static Properties prop;
	static {
		prop = new Properties();// 属性集合对象
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "\\location.properties");
			prop.load(fis);
		} catch (IOException e) {
			Log.error(e);
		}
	}

	public static void main(String[] args) {
		System.out.println(PropertiesUtils.get("QuantityUnit"));
	}

	public static String get(String strPropName) {
		if (strPropName.contains(":")) {
			return prop.getProperty(strPropName.split(":")[1]);
		} else {
			return prop.getProperty(strPropName);
		}

	}
}
