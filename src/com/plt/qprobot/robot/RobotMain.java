package com.plt.qprobot.robot;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import com.jni.demo.JNIDemo;
import com.plt.qprobot.behavior.BehaviorLogic;
import com.plt.qprobot.seq.SeqRead;

public class RobotMain {
	public static final String PATH = "js.txt";

	public static void main(String[] args) throws AWTException, InterruptedException, IOException {
		RobotMain rm = new RobotMain();
		Thread.sleep(3000);
		rm.start();
		//JNIDemo gwl = new JNIDemo();
		//System.out.println(gwl.getWindowLocation());
	}

	public void start() throws AWTException {
		List<String> strBehSeq;
		strBehSeq = SeqRead.read(PATH);

		Robot robot = new Robot();
		robot.setAutoDelay(300);
		BehaviorLogic bl = new BehaviorLogic(robot);
		for (String seq : strBehSeq) {
			bl.doBehavior(seq);
		}
	}

	public void test() throws IOException, AWTException {
		Robot robot = new Robot();
		// 设置Robot产生一个动作后的休眠时间,否则执行过快
		robot.setAutoDelay(1000);

		// 获取屏幕分辨率
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(d);
		Rectangle screenRect = new Rectangle(d);
		// 截图
		BufferedImage bufferedImage = robot.createScreenCapture(screenRect);
		// 保存截图
		File file = new File("screenRect.png");
		ImageIO.write(bufferedImage, "png", file);

		// 移动鼠标
		System.out.println("移动");
		robot.mouseMove(500, 300);

		// 点击鼠标
		// 鼠标左键
		System.out.println("单击");
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);

		// 鼠标右键
		System.out.println("右击");
		robot.mousePress(InputEvent.BUTTON3_MASK);
		robot.mouseRelease(InputEvent.BUTTON3_MASK);

		// 按下ESC，退出右键状态
		System.out.println("按下ESC");
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
		// 滚动鼠标滚轴
		System.out.println("滚轴");
		robot.mouseWheel(5);

		// 按下Alt+TAB键
		robot.keyPress(KeyEvent.VK_ALT);
		for (int i = 1; i <= 2; i++) {
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
		}
		robot.keyRelease(KeyEvent.VK_ALT);
	}
}