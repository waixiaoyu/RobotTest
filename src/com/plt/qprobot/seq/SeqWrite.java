package com.plt.qprobot.seq;

import org.json.JSONObject;

import com.plt.qprobot.behavior.BehaviorType;

/**
 * ������������json��ʽ�Ĳ�������
 * 
 * @author yyy
 *
 */
public class SeqWrite {
	public static final String KEY = "key";
	public static final String VALUE = "value";

	private static JSONObject genJSONObject(String key, String value) {
		JSONObject jobj = new JSONObject();
		jobj.put(KEY, key);
		jobj.put(VALUE, value);
		return jobj;
	}

	/**
	 * �ú���Ԥ��������jni��ֱ�ӻ�ȡ����λ�ã���ʱʹ��ֱ�ӵ�ֵ����
	 * 
	 * @return
	 */
	private static String getWindowLocation() {
		// up��bot��left��right
		return "59,800,288,1312";
	}

	private static int[] radioToCoordinate(double dX, double dY) {
		String[] strArr = getWindowLocation().split(",");
		int[] nArr = new int[2];
		nArr[0] = (int) ((Integer.valueOf(strArr[3]) - Integer.valueOf(strArr[2])) * dX + Integer.valueOf(strArr[2]));
		nArr[1] = (int) ((Integer.valueOf(strArr[1]) - Integer.valueOf(strArr[0])) * dY + Integer.valueOf(strArr[0]));
		return nArr;
	}

	public static JSONObject mouseMove(int x, int y) {
		return genJSONObject(BehaviorType.MOVE, x + "," + y);
	}

	public static JSONObject mouseMoveInRadio(double x, double y) {
		return mouseMove(radioToCoordinate(x, y)[0], radioToCoordinate(x, y)[1]);
	}

	public static JSONObject mouseLeftClick() {
		return genJSONObject(BehaviorType.LEFT_CLICK, null);
	}

	public static JSONObject mouseDoubleClick() {
		return genJSONObject(BehaviorType.DOUBLE_CLICK, null);
	}

	public static JSONObject mouseRightClick() {
		return genJSONObject(BehaviorType.RIGHT_CLICK, null);
	}

	public static JSONObject keyInput(String str) {
		return genJSONObject(BehaviorType.INPUT, str);
	}
	
	public static JSONObject keyPress(String str) {
		SeqWrite.sleep(100);
		return genJSONObject(BehaviorType.PRESS, str);
	}
	

	public static JSONObject sleep(long sleeptime) {
		return genJSONObject(BehaviorType.SLEEP, String.valueOf(sleeptime));
	}
	
	public static JSONObject copy(String str) {
		return genJSONObject(BehaviorType.COPY, str);
	}

}
