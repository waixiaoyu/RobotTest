package com.plt.qprobot.seq;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.plt.qprobot.behavior.BehaviorType;
import com.plt.qprobot.utils.PropertiesUtils;

/**
 * 该类用于生成json格式的操作序列
 * 
 * @author yyy
 *
 */
public class SeqWrite {

	private static Logger Log = Logger.getLogger(SeqWrite.class);

	public static final String KEY = "key";
	public static final String VALUE = "value";

	private static JSONObject genJSONObject(String key, String value) {
		JSONObject jobj = new JSONObject();
		jobj.put(KEY, key);
		jobj.put(VALUE, value);
		return jobj;
	}

	/**
	 * 该函数预留给调用jni，直接获取窗口位置，暂时使用直接的值代替
	 * 
	 * @return
	 */
	private static String getWindowLocation() {
		// up，bot，left，right
		return PropertiesUtils.get("WindowLocation");
	}

	public static int[] radioToCoordinate(double dX, double dY) {
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

	public static JSONObject mouseMoveInRadio(String str) {
		double x = Double.valueOf(str.split(",")[0]);
		double y = Double.valueOf(str.split(",")[1]);
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

	public static JSONObject keyInputFromPaste() {
		return genJSONObject(BehaviorType.INPUT, "keyInputFromPaste");
	}

	public static JSONObject keyPress(String str) {
		SeqWrite.sleep(100);
		return genJSONObject(BehaviorType.PRESS, str);
	}

	public static JSONObject sleep(long sleeptime) {
		double d = Math.random();
		sleeptime = sleeptime + (long) (500 * d);
		return genJSONObject(BehaviorType.SLEEP, String.valueOf(sleeptime));
	}

	public static JSONObject copy(String str) {
		return genJSONObject(BehaviorType.COPY, str);
	}

	public static JSONObject checkColor(String str) {
		String[] strs = str.split(",");
		strs[0] = String.valueOf(radioToCoordinate(Double.valueOf(strs[0]), Double.valueOf(strs[1]))[0]);
		strs[1] = String.valueOf(radioToCoordinate(Double.valueOf(strs[0]), Double.valueOf(strs[1]))[1]);

		return genJSONObject(BehaviorType.COLOR,
				strs[0] + "," + strs[1] + "," + strs[2] + "," + strs[3] + "," + strs[4]);
	}

	public static JSONObject jump(int n) {
		return genJSONObject(BehaviorType.JUMP, String.valueOf(n));
	}

	public static JSONObject protect() {
		return genJSONObject(BehaviorType.PROTECT, "");
	}

	public static JSONObject unprotect() {
		return genJSONObject(BehaviorType.UNPROTECT, "");
	}

}
