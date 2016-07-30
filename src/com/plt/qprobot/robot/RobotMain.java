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
		// ����Robot����һ�������������ʱ��,����ִ�й���
		robot.setAutoDelay(1000);

		// ��ȡ��Ļ�ֱ���
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(d);
		Rectangle screenRect = new Rectangle(d);
		// ��ͼ
		BufferedImage bufferedImage = robot.createScreenCapture(screenRect);
		// �����ͼ
		File file = new File("screenRect.png");
		ImageIO.write(bufferedImage, "png", file);

		// �ƶ����
		System.out.println("�ƶ�");
		robot.mouseMove(500, 300);

		// ������
		// ������
		System.out.println("����");
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);

		// ����Ҽ�
		System.out.println("�һ�");
		robot.mousePress(InputEvent.BUTTON3_MASK);
		robot.mouseRelease(InputEvent.BUTTON3_MASK);

		// ����ESC���˳��Ҽ�״̬
		System.out.println("����ESC");
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
		// ����������
		System.out.println("����");
		robot.mouseWheel(5);

		// ����Alt+TAB��
		robot.keyPress(KeyEvent.VK_ALT);
		for (int i = 1; i <= 2; i++) {
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
		}
		robot.keyRelease(KeyEvent.VK_ALT);
	}
}