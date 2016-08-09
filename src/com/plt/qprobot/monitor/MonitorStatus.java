package com.plt.qprobot.monitor;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.dom4j.xpath.DefaultXPath;
import org.json.JSONObject;

import com.plt.qprobot.behavior.BehaviorLogic;
import com.plt.qprobot.behavior.SysClipboardUtils;
import com.plt.qprobot.seq.SeqRead;
import com.plt.qprobot.seq.SeqWrite;
import com.plt.qprobot.utils.JSONUtils;
import com.plt.qprobot.utils.PropertiesUtils;

/**
 * 启动获取申报状态进程
 * 
 * @author yyy
 *
 */
public class MonitorStatus implements Runnable {
	public static volatile boolean isContinue=true;
	
	private static final long sleep_time = 100;
	private static final String QUERY_FILENAME = ".//query.txt";

	private static SAXReader reader = new SAXReader();
	private static Document document;
	private static DefaultXPath xpath;
	private static String dir;

	public static void main(String[] args) throws DocumentException {
		MonitorStatus ms = new MonitorStatus();
		ms.run();
	}

	@Override
	public void run() {
		while (isContinue) {
			try {
				Thread.sleep(2000);
				createQueryJSON();
				robotStart();
				//输出查询结果到外部
				System.out.println(SysClipboardUtils.getSysClipboardText());
			} catch (DocumentException | AWTException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void createQueryJSON() throws DocumentException {
		List<JSONObject> lJ = new ArrayList<>();
		// 新增订单
		lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("Query")));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.mouseLeftClick());
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("QueryOrder")));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.mouseLeftClick());
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("QueryUniCode")));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.mouseLeftClick());
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.copy(read("UniCode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.keyPress("A"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("Result")));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.mouseLeftClick()); // 生成json
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("copy"));

		JSONUtils.write(lJ, QUERY_FILENAME);
	}

	/**
	 * 获取receive中的unicode
	 * 
	 * @param StrNodeName
	 * @return
	 * @throws DocumentException
	 */
	private String read(String StrNodeName) throws DocumentException {
		dir = System.getProperty("user.dir");
		String[] str = null;
		File fdir = new File(dir + "\\receive");
		if (fdir.list().length > 0) {
			document = reader.read(fdir.listFiles()[0]);
			xpath = new DefaultXPath("//" + StrNodeName);
			return xpath.selectSingleNode(document).getStringValue();
		} else {
			System.out.println("status文件夹为空");
			System.exit(1);
			return "";
		}

	}

	private void robotStart() throws AWTException, InterruptedException {
		List<String> strBehSeq;
		strBehSeq = SeqRead.read(QUERY_FILENAME);

		Robot robot = new Robot();
		robot.setAutoDelay(300);
		BehaviorLogic bl = new BehaviorLogic(robot);
		for (String seq : strBehSeq) {
			bl.doBehavior(seq);
		}
	}
}
