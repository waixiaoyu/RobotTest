package com.jni.demo;

public class TestJNI {
	public static void main(String[] args) throws InterruptedException {
		JNIDemo jni=new JNIDemo();
		System.out.println(jni.getWindowLocation());
	}			
}
