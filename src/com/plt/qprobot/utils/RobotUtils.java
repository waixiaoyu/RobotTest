package com.plt.qprobot.utils;

import java.awt.Dimension;
import java.awt.Toolkit;

public class RobotUtils {

	
	public static Dimension getScreenSize() {
		// 获取屏幕分辨率
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(d);
		return d;
	}

	public static int getScreenSizeX() {
		// 获取屏幕分辨率
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(d);
		return d.width;
	}

	public static int getScreenSizeY() {
		// 获取屏幕分辨率
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(d);
		return d.height;
	}
}
