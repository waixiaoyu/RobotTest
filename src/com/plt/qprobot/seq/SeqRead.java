package com.plt.qprobot.seq;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * �������ڶ�ȡjson��ʽ�Ĳ�������
 * 
 * @author yyy
 *
 */
public class SeqRead {
	private static Logger Log = Logger.getLogger(SeqRead.class);

	
	private static String PATH = SeqMain.strJsonPath;
	private static final String KEY = SeqWrite.KEY;
	private static final String VALUE = SeqWrite.VALUE;
	public static final String SPLIT_MARK = "--";

	
	public static void main(String[] args) {
		read(PATH);
	}

	/**
	 * ��json����ת��Ϊstring���� string����ĸ�ʽΪ [key]-[value]
	 * 
	 * @param json�ַ�������·��
	 * @return List<String>
	 */
	public static List<String> read(String path) {
		JSONArray ja = new JSONArray(readFile(path));
		List<String> lStr = new ArrayList<>();
		for (Object object : ja) {
			JSONObject jobj = new JSONObject(object.toString());
			lStr.add(jobj.getString(KEY) + SPLIT_MARK + (jobj.isNull(VALUE) ? null : jobj.getString(VALUE)));
		}
		return lStr;
	}

	private static String readFile(String path) {
		File file = new File(path);
		BufferedReader reader = null;
		String laststr = "";
		try {
			// System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
			reader = new BufferedReader(new FileReader(file));
			// һ�ζ���һ�У�ֱ������nullΪ�ļ�����
			String tempString;
			while ((tempString = reader.readLine()) != null) {
				laststr = laststr + tempString;
			}
			reader.close();
		} catch (IOException e) {
			Log.error(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					Log.error(e1);
				}
			}
		}
		return laststr;
	}

}
