package com.plt.qprobot.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class JnaTest {

	// ����ӿ�CLibrary���̳���com.sun.jna.Library
	public interface CLibrary extends Library {
		// ���岢��ʼ���ӿڵľ�̬����
		CLibrary Instance = (CLibrary) Native.loadLibrary(System.getProperty("user.dir")+"\\src\\getWindowLocation.dll", CLibrary.class);

		// printf��������
		String getWindowLocation();
	}

	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir")+".\\src\\getWindowLocation");
		// ����printf��ӡ��Ϣ
		System.out.println(CLibrary.Instance.getWindowLocation());
	}
}
