package com.jni.demo;


import com.sun.jna.Structure;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser.POINT;

public class MouseHookStruct extends Structure {
	public static class ByReference extends MouseHookStruct implements Structure.ByReference {
	};

	public POINT pt;
	public HWND hwnd;
	public int wHitTestCode;
	public ULONG_PTR dwExtraInfo;
}
