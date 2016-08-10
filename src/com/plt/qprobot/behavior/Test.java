package com.plt.qprobot.behavior;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;

import com.plt.qprobot.seq.SeqWrite;
import com.plt.qprobot.utils.PropertiesUtils;

public class Test {
	public static void main(String[] args) throws AWTException, InterruptedException {
		Robot robot = new Robot();
		String[] str = PropertiesUtils.get("error").split(",");
		Thread.sleep(3000);
		int x = SeqWrite.radioToCoordinate(0.742,0.911)[0];
		int y = SeqWrite.radioToCoordinate(0.742,0.911)[1];
		Color color = robot.getPixelColor(x, y);
		System.out.println(color);
	}
}
