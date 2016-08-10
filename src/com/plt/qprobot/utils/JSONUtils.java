package com.plt.qprobot.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class JSONUtils {
	private static Logger Log = Logger.getLogger(JSONUtils.class);

	public static void main(String[] args) {
		JSONObject obj6 = new JSONObject();
		obj6.put("title", "book1").put("price", "$11");
		JSONObject obj3 = new JSONObject();

		obj3.put("author", new JSONObject().put("name", "author-1"));

		JSONArray a1 = new JSONArray();
		JSONObject a2 = new JSONObject();
		a2.put("name", "����");
		a2.put("children", "");
		JSONObject a3 = new JSONObject();
		a3.put("name", "����");
		a3.put("children", "");
		a1.put(a2);
		a1.put(a3);
		Log.info(a1.toString());
	}

	/**
	 * ����json�ַ���д��txt�ļ���ע������������ݵı����ʽ
	 * 
	 * @param jsonString
	 * @param filePath
	 */
	public static void write(String jsonString, String filePath) {
		try {
			FileOutputStream fos = null;
			PrintWriter writer = null;
			fos = new FileOutputStream(filePath);
			writer = new PrintWriter(new OutputStreamWriter(fos));
			writer.write(jsonString);
			writer.close();
			fos.close();
		} catch (IOException e) {
			Log.error(e);
		}
	}

	public static void write(List<JSONObject> lJ, String filePath) {
		JSONArray ja = new JSONArray();
		for (Object obj : lJ) {
			ja.put(obj);
		}
		Log.info(ja.toString());
		write(ja.toString(), filePath);
	}

}
