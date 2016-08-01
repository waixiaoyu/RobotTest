package com.plt.qprobot.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {
	private static Properties prop;
	static {
		prop = new Properties();// 属性集合对象
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\location.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 属性文件流
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
