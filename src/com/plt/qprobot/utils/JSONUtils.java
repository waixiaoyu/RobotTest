package com.plt.qprobot.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONUtils {
	public static void main(String[] args) {
		JSONObject obj6 = new JSONObject();
		obj6.put("title", "book1").put("price", "$11");
		JSONObject obj3 = new JSONObject();

		obj3.put("author", new JSONObject().put("name", "author-1"));

		JSONArray a1 = new JSONArray();
		JSONObject a2 = new JSONObject();
		a2.put("name", "进口");
		a2.put("children", "");
		JSONObject a3 = new JSONObject();
		a3.put("name", "出口");
		a3.put("children", "");
		a1.put(a2);
		a1.put(a3);
		System.out.println(a1.toString());
	}
/**
 * 根据json字符串写入txt文件，注意输出中文内容的编码格式
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void write(List<JSONObject> lJ, String filePath) {
		JSONArray ja = new JSONArray();
		for (Object obj : lJ) {
			ja.put(obj);
		}
		System.out.println(ja.toString());
		write(ja.toString(), filePath);
	}

}
