package com.plt.qprobot.ui;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextArea;

import com.plt.qprobot.monitor.MonitorError;
import com.plt.qprobot.monitor.MonitorStatus;
import com.plt.qprobot.robot.RobotMain;

public class MyFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame fr;
	JButton jbReadStatusStart = new JButton("开始检查状态");
	JButton jbReadStatusStop = new JButton("停止检查状态");
	JButton jbInputStart = new JButton("开始录入");
	JButton jbInputStop = new JButton("停止录入");

	MyFrame() {
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

		fr.getContentPane().add(jbReadStatusStop);
		jbReadStatusStop.setBounds(145, 140, 120, 25);

		fr.setTitle("QP录入机器人");
		fr.setUndecorated(true);
		fr.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setLocationRelativeTo(null); // 让窗体居中显示
		fr.setVisible(true);
	}

	public static void main(String args[]) {
		new MyFrame();
	}

}

class InputStartActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			// 启动主进程
			Thread threadRM = new Thread(new RobotMain());
			RobotMain.isContinue = true;
			threadRM.start();
			// 启动错误检查进程
			Thread threadME = new Thread(new MonitorError());
			threadME.start();
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}

class InputStopActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		RobotMain.isContinue = false;

	}
}

class ReadStatusStartActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Thread threadMS = new Thread(new MonitorStatus());
		MonitorStatus.isContinue = true;
		threadMS.start();

	}
}

class ReadStatusStopActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		MonitorStatus.isContinue = false;
	}
}
