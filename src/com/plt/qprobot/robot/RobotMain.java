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
			Log.error("send文件夹不存在，请检查！");
			return;
		}
		isContinue = true;
	}

	public static String strJsonPath;

	@Override
	public void run() {
		while (isContinue) {
			Log.info("准备开始获取xml文件");
			strCurrentFileName = XMLUtils.getFileName();
			if (strCurrentFileName.equals("")) {
				Log.info("无录入文件！");
				MyFrame.jlTimer.setText("无录入文件,等待命令！");
				JOptionPane.showConfirmDialog(MyFrame.fr, "没有检测到录入文件！", "提示", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
				isContinue = false;
			} else {
				SeqMain sm = new SeqMain(strCurrentFileName);
				try {
					sm.create();
					Log.info("主程序准备" + MyFrame.WAITING_TIME_BEFORE_START + "s后开始");
					MyFrame.preparedLabel();
					if (!MyFrame.isWindowActivated()) {
						Log.error("没有检测到QP窗口");
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
			System.out.println("开始获取xml文件");
			strCurrentFileName = XMLUtils.getFileName();
			if (strCurrentFileName.equals("")) {
				System.out.println("所有文件读取结束！");
				isContinue = false;
			} else {
				SeqMain sm = new SeqMain(strCurrentFileName);
				sm.create();

				System.out.println("主程序准备开始3s");
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
	 * robot功能sample
	 * 
	 * @throws IOException
	 * @throws AWTException
	 */
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

		// 鼠标右键6.5100
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