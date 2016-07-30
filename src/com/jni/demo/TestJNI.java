package com.jni.demo;

public class TestJNI {
	public static void main(String[] args) throws InterruptedException {
		System.out.println(System.getProperty("java.library.path")); 
		JNIDemo jni=new JNIDemo();
		System.out.println(jni.getWindowLocation());
	}			
}
