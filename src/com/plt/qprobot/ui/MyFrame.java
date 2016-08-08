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
	JButton jbStart = new JButton("��ʼ¼��");
	JButton jbStop = new JButton("ֹͣ¼��");

	MyFrame() {
		fr = new JFrame();
		fr.setBounds(100, 100, 250, 150);
		// ���ô���Ϊ�ղ���
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

		fr.setTitle("QP¼�������");
		fr.setUndecorated(true);
		fr.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setLocationRelativeTo(null); // �ô��������ʾ
		fr.setVisible(true);
	}

	public static void main(String args[]) {
		new MyFrame();
	}

}
