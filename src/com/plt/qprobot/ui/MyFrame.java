package com.plt.qprobot.ui;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;

import org.apache.log4j.Logger;

import com.plt.qprobot.monitor.MonitorError;
import com.plt.qprobot.monitor.MonitorShutdown;
import com.plt.qprobot.monitor.MonitorStatus;
import com.plt.qprobot.robot.RobotMain;
import com.plt.qprobot.seq.SeqWrite;
import com.plt.qprobot.utils.PropertiesUtils;

public class MyFrame extends JFrame {
	private static Logger Log = Logger.getLogger(MyFrame.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JFrame fr;


	private JButton jbReadStatusStart = new JButton("开始检查状态");
	private JButton jbReadStatusStop = new JButton("停止检查状态");
	private JButton jbInputStart = new JButton("开始录入");
	private JButton jbInputStop = new JButton("停止录入");
	public static JLabel jlTimer = new JLabel("等待命令中...");
	public static JLabel jlStopping = new JLabel();
	private static Robot robot;

	public static final long WAITING_TIME_BEFORE_START = 3000;// 开始前等待间隔。

	MyFrame() throws AWTException {
		robot = new Robot();
		// 启动错误检查进程
		Thread threadME = new Thread(new MonitorError());
		threadME.start();
		// 启动异常退出检测进程
		Runtime.getRuntime().addShutdownHook(new MonitorShutdown());

		fr = new JFrame();
		fr.setBounds(100, 100, 300, 250);
		// 设置窗体为空布局
		fr.setLayout(null);
		fr.getContentPane().add(jbInputStart);
		jbInputStart.setBounds(20, 180, 100, 25);

		jbInputStart.addActionListener(new InputStartActionListener());

		fr.getContentPane().add(jbReadStatusStart);
		jbReadStatusStart.setBounds(10, 140, 120, 25);

		jbReadStatusStart.addActionListener(new ReadStatusStartActionListener());

		fr.getContentPane().add(jbInputStop);
		jbInputStop.setBounds(155, 180, 100, 25);

		jbInputStop.addActionListener(new InputStopActionListener());

		fr.getContentPane().add(jbReadStatusStop);
		jbReadStatusStop.setBounds(145, 140, 120, 25);

		jbReadStatusStop.addActionListener(new ReadStatusStopActionListener());

		fr.getContentPane().add(jlTimer);
		jlTimer.setBounds(60, 50, 200, 25);

		fr.getContentPane().add(jlStopping);
		jlStopping.setBounds(60, 80, 200, 25);



		fr.setTitle("QP录入机器人");
		fr.setUndecorated(true);
		fr.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setLocationRelativeTo(null); // 让窗体居中显示
		fr.setVisible(true);

	}

	public static void main(String args[]) throws AWTException {
		// 添加程序异常结束监听线程

		new MyFrame();
	}

	public static Robot getRobot() {
		if (robot != null) {
			return robot;
		} else {
			Log.error("robot has not initiazied");
			System.exit(1);
			return null;
		}
	}

	/**
	 * 检测是否激活指定窗口
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public static boolean isWindowActivated() throws InterruptedException {

		String[] str = PropertiesUtils.get("GreenScreenColorLocation").split(",");
		Thread.sleep(500);
		int x = SeqWrite.radioToCoordinate(Double.valueOf(str[0]), Double.valueOf(str[1]))[0];
		int y = SeqWrite.radioToCoordinate(Double.valueOf(str[0]), Double.valueOf(str[1]))[1];
		Color color = robot.getPixelColor(x, y);
		Color oldColor = new Color(Integer.valueOf(str[2]), Integer.valueOf(str[3]), Integer.valueOf(str[4]));
		if (!color.equals(oldColor)) {
			JOptionPane.showConfirmDialog(fr, "没有检测到窗口，请重新操作！", "错误", JOptionPane.DEFAULT_OPTION,
					JOptionPane.ERROR_MESSAGE);
			jlTimer.setText("没有检测到窗口，请重新操作！");
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 控制label信息
	 * 
	 * @throws InterruptedException
	 */
	public static void preparedLabel() throws InterruptedException {
		MyFrame.jlTimer.setText("主程序" + WAITING_TIME_BEFORE_START / 1000 + "秒后准备开始！");
		Thread.sleep(1000);
		MyFrame.jlTimer.setText("请尽快手动切换到指定的QP页面！");
		Thread.sleep(1000);
		for (int i = 0; i < WAITING_TIME_BEFORE_START / 1000; i++) {
			MyFrame.jlTimer.setText((WAITING_TIME_BEFORE_START / 1000 - i) + "秒后准备开始...");
			Thread.sleep(1000);
		}
		MyFrame.jlTimer.setText("正在运行... ");
	}

	/**
	 * 准备停止中
	 * 
	 * @throws InterruptedException
	 */
	class LabelStopping implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			jlStopping.setText("请等待执行完一次完整过程！");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jlStopping.setText("");
		}

	}

	/**
	 * 用于按钮监听的内部类
	 *
	 */
	class InputStartActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// MonitorStatus.isContinue = true;
			if (!MonitorStatus.isContinue) {
				// 启动主进程
				Thread threadRM = new Thread(new RobotMain());
				RobotMain.isContinue = true;
				threadRM.start();
			} else {
				JOptionPane.showConfirmDialog(fr, "无法启动录入，请先停止状态检查！", "错误", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	class InputStopActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (RobotMain.isContinue) {
				RobotMain.isContinue = false;
				jlTimer.setText("等待命令...");
				new Thread(new LabelStopping()).start();
			}
		}
	}

	class ReadStatusStartActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!RobotMain.isContinue) {
				Thread threadMS = new Thread(new MonitorStatus());
				MonitorStatus.isContinue = true;
				threadMS.start();
			} else {
				JOptionPane.showConfirmDialog(fr, "无法启动状态检查，请先停止录入！", "错误", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);

			}

		}
	}

	class ReadStatusStopActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (MonitorStatus.isContinue) {
				MonitorStatus.isContinue = false;
				jlTimer.setText("等待命令...");
				new Thread(new LabelStopping()).start();
			}
		}
	}
}
