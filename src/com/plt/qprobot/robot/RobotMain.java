package com.plt.qprobot.robot;

import java.awt.AWTException;
import java.awt.Color;
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
import javax.swing.JList;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;

import com.plt.qprobot.behavior.BehaviorLogic;
import com.plt.qprobot.behavior.SysClipboardUtils;
import com.plt.qprobot.seq.SeqMain;
import com.plt.qprobot.seq.SeqRead;
import com.plt.qprobot.seq.SeqWrite;
import com.plt.qprobot.ui.MyFrame;
import com.plt.qprobot.utils.IOUtils;
import com.plt.qprobot.utils.PropertiesUtils;
import com.plt.qprobot.utils.XMLUtils;

public class RobotMain implements Runnable {

	private static Logger Log = Logger.getLogger(Robot.class);

	public static volatile boolean isContinue = false;

	public static String dir = "";
	public static String strCurrentFileName = "";

	public static String strUniCode = "";

	public RobotMain() {
		super();
		// TODO Auto-generated constructor stub
		dir = System.getProperty("user.dir");
		IOUtils.makeDirs(dir + "\\receive");
		IOUtils.makeDirs(dir + "\\error");
		if (!IOUtils.isExist(dir + "\\send")) {
			Log.error("send�ļ��в����ڣ����飡");
			return;
		}
		isContinue = true;
	}

	public static String strJsonPath;

	@Override
	public void run() {
		while (isContinue) {
			Log.info("׼����ʼ��ȡxml�ļ�");
			strCurrentFileName = XMLUtils.getFileName();
			if (strCurrentFileName.equals("")) {
				Log.info("��¼���ļ���");
				MyFrame.jlTimer.setText("��¼���ļ�,�ȴ����");
				JOptionPane.showConfirmDialog(MyFrame.fr, "û�м�⵽¼���ļ���", "��ʾ", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
				isContinue = false;
			} else {
				SeqMain sm = new SeqMain(strCurrentFileName);
				try {
					sm.create();
					Log.info("������׼��" + MyFrame.WAITING_TIME_BEFORE_START + "s��ʼ");
					MyFrame.preparedLabel();
					if (!MyFrame.isWindowActivated()) {
						Log.error("û�м�⵽QP����");
						isContinue = false;
						return;
					}
					RobotMain rm = new RobotMain();
					Thread.sleep(3000);
					rm.startRobot();

					strUniCode = SysClipboardUtils.getSysClipboardText();
					XMLUtils.createXML(strCurrentFileName);
					XMLUtils.deleteTXT(strCurrentFileName);
					XMLUtils.deleteXML(strCurrentFileName);
				} catch (DocumentException | InterruptedException | IOException | AWTException e) {
					Log.error(e);
				}

			}
		}
		isContinue = false;
	}

	public static void main(String[] args) throws AWTException, InterruptedException, IOException, DocumentException {
		boolean isContinue = true;
		while (isContinue) {
			System.out.println("��ʼ��ȡxml�ļ�");
			strCurrentFileName = XMLUtils.getFileName();
			if (strCurrentFileName.equals("")) {
				System.out.println("�����ļ���ȡ������");
				isContinue = false;
			} else {
				SeqMain sm = new SeqMain(strCurrentFileName);
				sm.create();

				System.out.println("������׼����ʼ3s");
				RobotMain rm = new RobotMain();
				Thread.sleep(3000);
				rm.startRobot();

				strUniCode = SysClipboardUtils.getSysClipboardText();
				XMLUtils.createXML(strCurrentFileName);
				XMLUtils.deleteTXT(strCurrentFileName);
				// XMLUtils.deleteXML(strCurrentFileName);
			}
		}

	}

	public void startRobot() throws AWTException, InterruptedException {
		List<String> strBehSeq;
		strBehSeq = SeqRead.read(SeqMain.strJsonPath);

		Robot robot = MyFrame.getRobot();
		robot.setAutoDelay(300);
		BehaviorLogic bl = new BehaviorLogic(robot);
		for (String seq : strBehSeq) {
			bl.doBehavior(seq);
		}

	}

	/**
	 * robot����sample
	 * 
	 * @throws IOException
	 * @throws AWTException
	 */
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

		// ����Ҽ�6.5100
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