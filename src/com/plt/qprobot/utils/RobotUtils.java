package com.plt.qprobot.utils;

import java.awt.Dimension;
import java.awt.Toolkit;

public class RobotUtils {

	
	public static Dimension getScreenSize() {
		// ��ȡ��Ļ�ֱ���
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(d);
		return d;
	}

	public static int getScreenSizeX() {
		// ��ȡ��Ļ�ֱ���
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(d);
		return d.width;
	}

	public static int getScreenSizeY() {
		// ��ȡ��Ļ�ֱ���
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(d);
		return d.height;
	}
}
