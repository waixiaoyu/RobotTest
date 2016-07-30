package com.plt.qprobot.seq;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 该类用于读取json格式的操作序列
 * 
 * @author yyy
 *
 */
public class SeqRead {
	private static final String PATH = "C:\\Users\\Administrator\\Desktop\\js.txt";
	private static final String KEY = SeqWrite.KEY;
	private static final String VALUE = SeqWrite.VALUE;

	public static void main(String[] args) {
		read(PATH);
	}

	/**
	 * 将json数组转换为string数组 string数组的格式为 [key]-[value]
	 * 
	 * @param json字符串输入路径
	 * @return List<String>
	 */
	public static List<String> read(String path) {
		JSONArray ja = new JSONArray(readFile(PATH));
		List<String> lStr = new ArrayList<>();
		for (Object object : ja) {
			JSONObject jobj = new JSONObject(object.toString());
			lStr.add(jobj.getString(KEY) + "-" + (jobj.isNull(VALUE) ? null : jobj.getString(VALUE)));
		}
		return lStr;
	}

	private static String readFile(String path) {
		File file = new File(path);
		BufferedReader reader = null;
		String laststr = "";
		try {
			// System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			// 一次读入一行，直到读入null为文件结束
			String tempString;
			while ((tempString = reader.readLine()) != null) {
				laststr = laststr + tempString;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return laststr;
	}

}
