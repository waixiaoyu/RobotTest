package com.plt.qprobot.utils;

import java.io.File;

import org.apache.log4j.Logger;

public class IOUtils {
	private static Logger Log=Logger.getLogger(IOUtils.class);
	
	public static void main(String[] args) {
		makeDirs(System.getProperty("user.dir") + "\\receive");
	}

	public static boolean makeDirs(String path) {
		File folder = new File(path);
		if (isExist(path)) {
			Log.info(path + " ÒÑ´æÔÚ");
			return true;
		} else {
			return folder.mkdirs();
		}
	}

	public static boolean isExist(String path) {
		File folder = new File(path);
		return folder.exists() && folder.isDirectory();
	}
}
