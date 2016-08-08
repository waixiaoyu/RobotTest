package com.plt.qprobot.behavior;

import java.awt.Color;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class BehaviorLogic {
	private Robot robot;

	public BehaviorLogic(Robot robot) {
		super();
		this.robot = robot;
	}

	public void doBehavior(String instruction) throws InterruptedException {
		String strKey = instruction.split("-")[0];
		String strValue = "";
		if (instruction.split("-").length > 1) {
			strValue = instruction.split("-")[1];
		}
		switch (strKey) {
		case BehaviorType.MOVE:
			int x = Integer.valueOf(strValue.split(",")[0]);
			int y = Integer.valueOf(strValue.split(",")[1]);
			robot.mouseMove(x, y);
			break;
		case BehaviorType.LEFT_CLICK:
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			break;
		case BehaviorType.RIGHT_CLICK:
			robot.mousePress(InputEvent.BUTTON3_MASK);
			robot.mouseRelease(InputEvent.BUTTON3_MASK);
			break;
		case BehaviorType.DOUBLE_CLICK:
			robot.mousePress(InputEvent.BUTTON1_MASK);// 按下左键
			robot.mouseRelease(InputEvent.BUTTON1_MASK);// 释放左键
			robot.delay(100);// 停顿100毫秒,即0.1秒
			robot.mousePress(InputEvent.BUTTON1_MASK);// 按下左键
			robot.mouseRelease(InputEvent.BUTTON1_MASK);// 释放左键
			break;
		case BehaviorType.INPUT:
			char[] cArr = strValue.toUpperCase().toCharArray();
			for (char c : cArr) {
				robot.keyPress(c);
				robot.keyRelease(c);
			}
			break;
		case BehaviorType.PRESS:

			switch (strValue) {
			case "enter":
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				break;
			case "paste":
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress('V');
				robot.keyRelease('V');
				robot.keyRelease(KeyEvent.VK_CONTROL);
				break;
			case "new":
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress('N');
				robot.keyRelease('N');
				robot.keyRelease(KeyEvent.VK_CONTROL);
				break;
			case "save":
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress('S');
				robot.keyRelease('S');
				robot.keyRelease(KeyEvent.VK_CONTROL);
				break;
			case "backspace":
				robot.keyPress(KeyEvent.VK_BACK_SPACE);
				robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				break;
			default:
				break;
			}
			break;
		case BehaviorType.SLEEP:
			robot.delay(Integer.valueOf(strValue.split(",")[0]));
			break;
		case BehaviorType.COPY:
			SysClipboardUtils.setSysClipboardText(strValue);
			break;
		case BehaviorType.COLOR:// 在这个类型中，value有五个值，xy,rgb
			String[] strs = strValue.split(",");
			Color color = robot.getPixelColor(Integer.valueOf(strs[0]), Integer.valueOf(strs[1]));
			Color colorOld = new Color(Integer.valueOf(strs[2]), Integer.valueOf(strs[3]), Integer.valueOf(strs[4]));
			int nAttemptTime = 10;
			int nCount = 0;
			while (!color.equals(colorOld) && nCount < nAttemptTime) {
				++nCount;
				Thread.sleep(500);
				color = robot.getPixelColor(Integer.valueOf(strs[3]), Integer.valueOf(strs[4]));
			}
			if (nCount < nAttemptTime) {
				System.out.println("颜色检测成功");
			} else {
				System.err.println("颜色检测失败" + "pixel:" + strs + color);
				System.exit(1);
			}
			break;
		default:
			break;
		}

	}
}
