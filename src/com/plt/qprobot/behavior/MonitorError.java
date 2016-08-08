package com.plt.qprobot.behavior;

import java.awt.Color;

public class MonitorError implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Color color = robot.getPixelColor(Integer.valueOf(strs[0]), Integer.valueOf(strs[1]));
		Color colorOld = new Color(Integer.valueOf(strs[2]), Integer.valueOf(strs[3]), Integer.valueOf(strs[4]));
		int nAttemptTime = 10;
		int nCount = 0;
		while (!color.equals(colorOld) && nCount < nAttemptTime) {
			++nCount;
			Thread.sleep(500);
			color = robot.getPixelColor(Integer.valueOf(strs[3]), Integer.valueOf(strs[4]));
		}
	}

}
