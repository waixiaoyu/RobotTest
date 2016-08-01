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
		// 新增订单
		lJ.add(SeqWrite.keyPress("new"));

		// 一个操作步骤
		lJ.add(SeqWrite.mouseMoveInRadio(0.2451171875, 0.114709851551957));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.mouseLeftClick());
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.copy("5301"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		// 商品名称多重菜单、
		lJ.add(SeqWrite.mouseMoveInRadio(0.232, 0.789));
		lJ.add(SeqWrite.mouseLeftClick());
		lJ.add(SeqWrite.copy("双信器"));
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

		lJ.add(SeqWrite.copy("要素1"));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.keyPress("enter"));

		lJ.add(SeqWrite.copy("要素2"));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.keyPress("enter"));

		lJ.add(SeqWrite.copy("要素3"));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.keyPress("enter"));

		lJ.add(SeqWrite.copy("要素4"));
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
