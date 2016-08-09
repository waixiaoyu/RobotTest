package com.plt.qprobot.monitor;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.plt.qprobot.robot.RobotMain;
import com.plt.qprobot.seq.SeqWrite;
import com.plt.qprobot.utils.PropertiesUtils;

/**
 * �������̣߳�����ͬ������Ƿ񷢳���������������󣬾ͽ�ͼ���棬������������
 * 
 * @author QP011
 *
 */
public class MonitorError implements Runnable {
	private Robot robot;
	private boolean isError = false;

	public MonitorError() throws AWTException {
		super();
		this.robot = new Robot();
	}

	public static void main(String[] args) throws AWTException {
		new Thread(new MonitorError()).start();
	}

	@Override
	public void run() {
		System.out.println("���������������");
		String[] str = PropertiesUtils.get("error").split(",");
		int x = SeqWrite.radioToCoordinate(Double.valueOf(str[0]), Double.valueOf(str[1]))[0];
		int y = SeqWrite.radioToCoordinate(Double.valueOf(str[0]), Double.valueOf(str[1]))[1];

		while (!isError) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Color color = robot.getPixelColor(x, y);
			Color colorOld = new Color(Integer.valueOf(str[2]), Integer.valueOf(str[3]), Integer.valueOf(str[4]));
			if (color.equals(colorOld)) {
				try {
					printScreen();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.err.println("ϵͳ�����˳���");
				System.exit(1);
			}

		}
	}

	private void printScreen() throws IOException {
		// ����Robot����һ�������������ʱ��,����ִ�й���
		robot.setAutoDelay(1000);

		// ��ȡ��Ļ�ֱ���
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(d);
		Rectangle screenRect = new Rectangle(d);
		// ��ͼ
		BufferedImage bufferedImage = robot.createScreenCapture(screenRect);
		// �����ͼ
		File file = new File(RobotMain.dir + "\\error\\"
				+ RobotMain.strCurrentFileName.substring(0, RobotMain.strCurrentFileName.length() - 4) + "_err.png");
		ImageIO.write(bufferedImage, "png", file);

	}

}
