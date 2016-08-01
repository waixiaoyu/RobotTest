package com.plt.qprobot.behavior;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class BehaviorLogic {
	private Robot robot;

	public BehaviorLogic(Robot robot) {
		super();
		this.robot = robot;
	}

	public void doBehavior(String instruction) {
		String strKey = instruction.split("-")[0];
		String strValue = instruction.split("-")[1];
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
			robot.mousePress(InputEvent.BUTTON1_MASK);// °´ÏÂ×ó¼ü
			robot.mouseRelease(InputEvent.BUTTON1_MASK);// ÊÍ·Å×ó¼ü
			robot.delay(100);// Í£¶Ù100ºÁÃë,¼´0.1Ãë
			robot.mousePress(InputEvent.BUTTON1_MASK);// °´ÏÂ×ó¼ü
			robot.mouseRelease(InputEvent.BUTTON1_MASK);// ÊÍ·Å×ó¼ü
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
		default:
			break;
		}

	}
}
