package com.jni.demo;

public class JNIDemo {
	public native String getWindowLocation();
    static /* static块也可以放在最后需要调用的Main类里加载，放在这里表示用到HelloWorld类时加载，需要import */
    {
        System.loadLibrary("getWindowLocation"); 
    }
}
