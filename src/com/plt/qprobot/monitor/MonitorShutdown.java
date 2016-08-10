package com.plt.qprobot.monitor;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

public class MonitorShutdown extends Thread {
	private static Logger Log = Logger.getLogger(MonitorShutdown.class);

	public MonitorShutdown() {
		super();
		Log.info("MonitorShutdown init");
	}

	/**
	 * ΪӦ�ó�����˳�������һ���¼�������Ӧ�ó����˳�ʱ�򣬽������˳�������д�� E:/temp/test.log�ļ�
	 */
	@Override
	public void run() {
		try {
			FileWriter fw = new FileWriter("./test.log");
			Log.info("Im going to end.");
			fw.write("the application ended! "
					+ (new SimpleDateFormat("YYYY-MM-DD HH:mm:ss").format(System.currentTimeMillis())).toString());
			fw.close();
		} catch (IOException ex) {
			Log.error(ex);
			Log.error("exception at MonitorShutdown#run()...");
		}
	}

	/**
	 * @throws InterruptedException
	 ***************************************************/
	public static void main(String[] args) throws InterruptedException {
		// ��ӳ����������
		Runtime.getRuntime().addShutdownHook(new MonitorShutdown());
		long s = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			Thread.sleep(100);
			System.out.println("sleep");
		}
		long se = System.currentTimeMillis();
		System.out.println(se - s);
	}
}