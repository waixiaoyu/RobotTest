package com.plt.qprobot.seq;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.plt.qprobot.robot.RobotMain;
import com.plt.qprobot.utils.JSONUtils;

public class SeqTest {

	private static final String PATH = RobotMain.PATH;

	public static void main(String[] args) {
		SeqTest cs = new SeqTest();
		cs.create();
	}

	public void create() {
		List<JSONObject> lJ = new ArrayList<>();
		// ��������
		lJ.add(SeqWrite.keyPress("new"));

		// һ����������
		lJ.add(SeqWrite.mouseMoveInRadio(0.2451171875, 0.114709851551957));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.mouseLeftClick());
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.copy("5301"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		// ��Ʒ���ƶ��ز˵���
		lJ.add(SeqWrite.mouseMoveInRadio(0.232, 0.789));
		lJ.add(SeqWrite.mouseLeftClick());
		lJ.add(SeqWrite.copy("˫����"));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.keyPress("enter"));

		lJ.add(SeqWrite.copy("8517703000"));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.sleep(100));

		lJ.add(SeqWrite.copy("Ҫ��1"));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.keyPress("enter"));

		lJ.add(SeqWrite.copy("Ҫ��2"));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.keyPress("enter"));

		lJ.add(SeqWrite.copy("Ҫ��3"));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.keyPress("enter"));

		lJ.add(SeqWrite.copy("Ҫ��4"));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.keyPress("enter"));
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
