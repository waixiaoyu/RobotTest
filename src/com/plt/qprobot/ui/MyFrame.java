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


	private JButton jbReadStatusStart = new JButton("��ʼ���״̬");
	private JButton jbReadStatusStop = new JButton("ֹͣ���״̬");
	private JButton jbInputStart = new JButton("��ʼ¼��");
	private JButton jbInputStop = new JButton("ֹͣ¼��");
	public static JLabel jlTimer = new JLabel("�ȴ�������...");
	public static JLabel jlStopping = new JLabel();
	private static Robot robot;

	public static final long WAITING_TIME_BEFORE_START = 3000;// ��ʼǰ�ȴ������

	MyFrame() throws AWTException {
		robot = new Robot();
		// �������������
		Thread threadME = new Thread(new MonitorError());
		threadME.start();
		// �����쳣�˳�������
		Runtime.getRuntime().addShutdownHook(new MonitorShutdown());

		fr = new JFrame();
		fr.setBounds(100, 100, 300, 250);
		// ���ô���Ϊ�ղ���
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



		fr.setTitle("QP¼�������");
		fr.setUndecorated(true);
		fr.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setLocationRelativeTo(null); // �ô��������ʾ
		fr.setVisible(true);

	}

	public static void main(String args[]) throws AWTException {
		// ��ӳ����쳣���������߳�

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
	 * ����Ƿ񼤻�ָ������
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
			JOptionPane.showConfirmDialog(fr, "û�м�⵽���ڣ������²�����", "����", JOptionPane.DEFAULT_OPTION,
					JOptionPane.ERROR_MESSAGE);
			jlTimer.setText("û�м�⵽���ڣ������²�����");
			return false;
		} else {
			return true;
		}
	}

	/**
	 * ����label��Ϣ
	 * 
	 * @throws InterruptedException
	 */
	public static void preparedLabel() throws InterruptedException {
		MyFrame.jlTimer.setText("������" + WAITING_TIME_BEFORE_START / 1000 + "���׼����ʼ��");
		Thread.sleep(1000);
		MyFrame.jlTimer.setText("�뾡���ֶ��л���ָ����QPҳ�棡");
		Thread.sleep(1000);
		for (int i = 0; i < WAITING_TIME_BEFORE_START / 1000; i++) {
			MyFrame.jlTimer.setText((WAITING_TIME_BEFORE_START / 1000 - i) + "���׼����ʼ...");
			Thread.sleep(1000);
		}
		MyFrame.jlTimer.setText("��������... ");
	}

	/**
	 * ׼��ֹͣ��
	 * 
	 * @throws InterruptedException
	 */
	class LabelStopping implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			jlStopping.setText("��ȴ�ִ����һ���������̣�");
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
	 * ���ڰ�ť�������ڲ���
	 *
	 */
	class InputStartActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// MonitorStatus.isContinue = true;
			if (!MonitorStatus.isContinue) {
				// ����������
				Thread threadRM = new Thread(new RobotMain());
				RobotMain.isContinue = true;
				threadRM.start();
			} else {
				JOptionPane.showConfirmDialog(fr, "�޷�����¼�룬����ֹͣ״̬��飡", "����", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	class InputStopActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (RobotMain.isContinue) {
				RobotMain.isContinue = false;
				jlTimer.setText("�ȴ�����...");
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
				JOptionPane.showConfirmDialog(fr, "�޷�����״̬��飬����ֹͣ¼�룡", "����", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);

			}

		}
	}

	class ReadStatusStopActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (MonitorStatus.isContinue) {
				MonitorStatus.isContinue = false;
				jlTimer.setText("�ȴ�����...");
				new Thread(new LabelStopping()).start();
			}
		}
	}
}
