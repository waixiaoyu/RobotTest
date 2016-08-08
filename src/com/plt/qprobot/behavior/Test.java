package com.plt.qprobot.behavior;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;

import com.plt.qprobot.seq.SeqWrite;

public class Test {
	public static void main(String[] args) throws AWTException, InterruptedException {
		Robot robot = new Robot();
		Thread.sleep(3000);
		Color color = robot.getPixelColor(710,249);
		System.out.println(color);
	}
}
