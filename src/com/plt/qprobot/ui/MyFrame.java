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

import com.plt.qprobot.robot.RobotMain;

public class MyFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame fr;
	JButton jbStart = new JButton("开始录入");
	JButton jbStop = new JButton("停止录入");

	MyFrame() {
		fr = new JFrame();
		fr.setBounds(100, 100, 250, 150);
		// 设置窗体为空布局
		fr.setLayout(null);
		fr.getContentPane().add(jbStart);
		jbStart.setBounds(10, 80, 100, 25);
		jbStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Thread thread=new Thread(new RobotMain());
				thread.start();
			}
			
		});
		fr.getContentPane().add(jbStop);
		jbStop.setBounds(125, 80, 100, 25);

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
