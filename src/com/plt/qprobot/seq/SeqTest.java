package com.plt.qprobot.seq;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.plt.qprobot.utils.JSONUtils;
import com.plt.qprobot.utils.RobotUtils;

public class SeqTest {

	private static final String PATH = "C:\\Users\\Administrator\\Desktop\\js.txt";

	public static void main(String[] args) {
		SeqTest cs = new SeqTest();
		cs.create();
	}

	public void create() {
		List<JSONObject> lJ = new ArrayList<>();
		lJ.add(SeqWrite.mouseMove(RobotUtils.getScreenSizeX() / 2, RobotUtils.getScreenSizeY() / 2));
		lJ.add(SeqWrite.sleep(1000));
		lJ.add(SeqWrite.mouseLeftClick());
		lJ.add(SeqWrite.sleep(1000));
		lJ.add(SeqWrite.mouseRightClick());
		lJ.add(SeqWrite.sleep(1000));
		lJ.add(SeqWrite.keyInput("ABC"));

		JSONUtils.write(lJ, PATH);

	}

	public void read() {
		List<String> strBehSeq;
		strBehSeq = SeqRead.read(PATH);
		for (String string : strBehSeq) {
			System.out.println(string);
		}
	}
}
