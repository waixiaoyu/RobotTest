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
			robot.mousePress(InputEvent.BUTTON1_MASK);// �������
			robot.mouseRelease(InputEvent.BUTTON1_MASK);// �ͷ����
			robot.delay(100);// ͣ��100����,��0.1��
			robot.mousePress(InputEvent.BUTTON1_MASK);// �������
			robot.mouseRelease(InputEvent.BUTTON1_MASK);// �ͷ����
			break;
		case BehaviorType.INPUT:
			char[] cArr = strValue.toUpperCase().toCharArray();
			for (char c : cArr) {
				robot.keyPress(c);
				robot.keyRelease(c);
			}
			break;
		case BehaviorType.SLEEP:
			robot.delay(Integer.valueOf(strValue.split(",")[0]));
			break;
		default:
			break;
		}

	}
}
