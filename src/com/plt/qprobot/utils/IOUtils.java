package com.plt.qprobot.utils;

import java.io.File;

public class IOUtils {
	public static void main(String[] args) {
		makeDirs(System.getProperty("user.dir") + "\\receive");
	}

	public static boolean makeDirs(String path) {
		File folder = new File(path);
		if (isExist(path)) {
			System.out.println(path + " ÒÑ´æÔÚ");
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
