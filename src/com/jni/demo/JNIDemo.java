package com.jni.demo;

public class JNIDemo {
	public native String getWindowLocation();
    static /* static��Ҳ���Է��������Ҫ���õ�Main������أ����������ʾ�õ�HelloWorld��ʱ���أ���Ҫimport */
    {
        System.loadLibrary("getWindowLocation"); 
    }
}
