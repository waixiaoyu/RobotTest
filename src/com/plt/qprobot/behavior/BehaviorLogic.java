package com.plt.qprobot.behavior;

import java.awt.Color;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import org.apache.log4j.Logger;

import com.plt.qprobot.seq.SeqRead;
import com.plt.qprobot.seq.SeqWrite;
import com.plt.qprobot.ui.MyFrame;
import com.plt.qprobot.utils.PropertiesUtils;

public class BehaviorLogic {
	private Logger Log = Logger.getLogger(BehaviorLogic.class);

	private static final int NORMAL_WINDOW_ATTEMPTS = 10;// ����������ڴ���

	private Robot robot;

	public BehaviorLogic(Robot robot) {
		super();
		this.robot = robot;
	}

	public void doBehavior(String instruction) throws InterruptedException {

		testNormalWindow();

		String strKey = instruction.split(SeqRead.SPLIT_MARK)[0];
		String strValue = "";
		if (instruction.split(SeqRead.SPLIT_MARK).length > 1) {
			strValue = instruction.split(SeqRead.SPLIT_MARK)[1];
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
			robot.mousePress(InputEvent.BUTTON1_MASK);// �������
			robot.mouseRelease(InputEvent.BUTTON1_MASK);// �ͷ����
			robot.delay(100);// ͣ��100����
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
		case BehaviorType.PRESS:

			switch (strValue) {
			case "A":
				robot.keyPress('A');
				robot.keyRelease('A');
				break;
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
			case "copy":
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress('C');
				robot.keyRelease('C');
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
		case BehaviorType.COLOR:// ����������У�value�����ֵ��xy,rgb
			String[] strs = strValue.split(",");
			Color color = robot.getPixelColor(Integer.valueOf(strs[0]), Integer.valueOf(strs[1]));
			Color colorOld = new Color(Integer.valueOf(strs[2]), Integer.valueOf(strs[3]), Integer.valueOf(strs[4]));
			int nAttemptTime = 10;
			int nCount = 0;
			while (!color.equals(colorOld) && nCount < nAttemptTime) {
				++nCount;
				Thread.sleep(500);
				color = robot.getPixelColor(Integer.valueOf(strs[0]), Integer.valueOf(strs[1]));
			}
			if (nCount < nAttemptTime) {
				Log.info("��ɫ���ɹ�:" + colorOld.toString() + " strs:" + strs);
			} else {
				Log.error("��ɫ���ʧ��" + "pixel:" + strs + color);
				System.exit(1);
			}
			break;
		default:
			break;
		}

	}

	/**
	 * ���ڼ�ⴰ�������ĺ�����ʹ��ѭ����⣡����������Դ�����ϵͳ�˳���
	 * 
	 * @throws InterruptedException
	 */
	private void testNormalWindow() throws InterruptedException {

		int nAttempts = 0;
		while (!isNormalWindow() && nAttempts < NORMAL_WINDOW_ATTEMPTS) {
			Thread.sleep(1000);
			++nAttempts;
			Log.error("QP���ڲ����������ܴ��ڼ�������" + nAttempts + "��");
			if (nAttempts >= NORMAL_WINDOW_ATTEMPTS) {
				Log.error("QP���ڲ�������ϵͳ�˳���");
				System.exit(1);
			}
		}
	}

	/**
	 * ���ݴ�����ɫ������Ƿ�������״̬
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	private boolean isNormalWindow() throws InterruptedException {
		String[] str = PropertiesUtils.get("GreenScreenColorLocation").split(",");
		Thread.sleep(50);
		int x = SeqWrite.radioToCoordinate(Double.valueOf(str[0]), Double.valueOf(str[1]))[0];
		int y = SeqWrite.radioToCoordinate(Double.valueOf(str[0]), Double.valueOf(str[1]))[1];
		Color color = MyFrame.getRobot().getPixelColor(x, y);
		Color oldColor = new Color(Integer.valueOf(str[2]), Integer.valueOf(str[3]), Integer.valueOf(str[4]));
		if (color.equals(oldColor)) {
			return true;
		} else {
			return false;
		}

	}
}
