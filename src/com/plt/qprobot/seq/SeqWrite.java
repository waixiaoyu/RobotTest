package com.plt.qprobot.seq;

import org.json.JSONObject;

import com.plt.qprobot.behavior.BehaviorType;

/**
 * 该类用于生成json格式的操作序列
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

	public static JSONObject mouseMove(int x, int y) {
		return genJSONObject(BehaviorType.MOVE, x + "," + y);
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

	public static JSONObject sleep(long sleeptime) {
		return genJSONObject(BehaviorType.SLEEP, String.valueOf(sleeptime));
	}
}
