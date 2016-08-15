package com.plt.qprobot.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class JnaTest {

	// 定义接口CLibrary，继承自com.sun.jna.Library
	public interface CLibrary extends Library {
		// 定义并初始化接口的静态变量
		CLibrary Instance = (CLibrary) Native.loadLibrary(System.getProperty("user.dir")+"\\src\\getWindowLocation.dll", CLibrary.class);

		// printf函数声明
		String getWindowLocation();
	}

	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir")+".\\src\\getWindowLocation");
		// 调用printf打印信息
		System.out.println(CLibrary.Instance.getWindowLocation());
	}
}
