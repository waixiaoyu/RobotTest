package com.plt.qprobot.seq;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.plt.qprobot.robot.RobotMain;
import com.plt.qprobot.utils.JSONUtils;
import com.plt.qprobot.utils.RobotUtils;

public class SeqTest {

	private static final String PATH = RobotMain.PATH;

	public static void main(String[] args) {
		SeqTest cs = new SeqTest();
		cs.create();
	}

	public void create() {
		List<JSONObject> lJ = new ArrayList<>();
		lJ.add(SeqWrite.mouseMoveInRadio(0.2451171875, 0.114709851551957));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.mouseLeftClick());
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.copy("0000"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("pasts"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));

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
