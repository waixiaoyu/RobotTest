package com.plt.qprobot.monitor;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.xpath.DefaultXPath;
import org.json.JSONObject;

import com.plt.qprobot.behavior.BehaviorLogic;
import com.plt.qprobot.behavior.SysClipboardUtils;
import com.plt.qprobot.seq.SeqRead;
import com.plt.qprobot.seq.SeqWrite;
import com.plt.qprobot.ui.MyFrame;
import com.plt.qprobot.utils.IOUtils;
import com.plt.qprobot.utils.JSONUtils;
import com.plt.qprobot.utils.PropertiesUtils;
import com.plt.qprobot.utils.XMLUtils;

/**
 * 启动获取申报状态进程
 * 
 * @author yyy
 *
 */
public class MonitorStatus implements Runnable {
	private static Logger Log = Logger.getLogger(MonitorStatus.class);

	public static volatile boolean isContinue = false;

	private static final long sleep_time = 100;
	private static final String QUERY_FILENAME = ".//query.txt";

	private static SAXReader reader = new SAXReader();
	private static Document document;
	private static DefaultXPath xpath;
	private static String dir = System.getProperty("user.dir");
	private static String strStatusFileID = "";// id不包含文件后缀名，like
												// <ID>PLT2016-08002_201607271160</ID>

	private static String[] strStatusFileNames;

	private static int nStatusFileNameIndex = -1;

	public static void main(String[] args) throws DocumentException {
		MonitorStatus ms = new MonitorStatus();
		ms.run();
	}

	public MonitorStatus() {
		super();
		IOUtils.makeDirs(dir + "\\status");
	}

	@Override
	public void run() {
		try {
			MyFrame.preparedLabel();
			if (!MyFrame.isWindowActivated()) {
				Log.error("没有检测到QP窗口");
				isContinue = false;
				return;
			}
		} catch (InterruptedException e1) {
			Log.error(e1);
		}

		isContinue = true;
		nStatusFileNameIndex = -1;
		getFileName();

		while (isContinue && nStatusFileNameIndex + 1 < strStatusFileNames.length) {
			try {
				nStatusFileNameIndex++;
				Thread.sleep(2000);
				strStatusFileID = read("ID");
				Log.info("开始获取status，文件名:" + strStatusFileID);
				createQueryJSON();
				robotStart();
				// 输出查询结果到外部
				String str = SysClipboardUtils.getSysClipboardText();
				String[] strs = str.split("[\t]|[\n]");
				createXML(strs);
				XMLUtils.delete(QUERY_FILENAME);
			} catch (DocumentException | AWTException | InterruptedException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (!(nStatusFileNameIndex + 1 < strStatusFileNames.length)) {
			MyFrame.jlTimer.setText("状态检测完毕，等待命令！");
			Log.info("状态检测完毕，等待命令！");
			nStatusFileNameIndex = -1;
		}
		if (!isContinue) {
			MyFrame.jlTimer.setText("状态检测终止，等待命令！");
			Log.info("状态检测终止，等待命令！");
		}
		isContinue = false;
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
		lJ.add(SeqWrite.sleep(1000));
		// 检测是否查询
		lJ.add(SeqWrite.checkColor(PropertiesUtils.get("QueryBlankColorLocation")));
		lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("Result")));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.mouseLeftClick()); // 生成json
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("copy"));

		JSONUtils.write(lJ, QUERY_FILENAME);
	}

	/**
	 * 获取所有的文件名称
	 */
	private void getFileName() {
		dir = System.getProperty("user.dir");
		File fdir = new File(dir + "\\receive");
		if (fdir.list().length > 0) {
			strStatusFileNames = fdir.list();
		}
	}

	/**
	 * 获取receive中的unicode
	 * 
	 * @param StrNodeName
	 * @return
	 * @throws DocumentException
	 */
	private String read(String StrNodeName) throws DocumentException {
		if (strStatusFileNames.length > 0) {
			document = reader.read(dir + "\\receive\\" + strStatusFileNames[nStatusFileNameIndex]);
			xpath = new DefaultXPath("//" + StrNodeName);
			return xpath.selectSingleNode(document).getStringValue();
		} else {
			Log.info("status文件夹为空");
			MonitorStatus.isContinue = false;
			return "";
		}

	}

	/**
	 * 用于启动状态检测逻辑
	 * 
	 * @throws AWTException
	 * @throws InterruptedException
	 */
	private void robotStart() throws AWTException, InterruptedException {
		List<String> strBehSeq;
		strBehSeq = SeqRead.read(QUERY_FILENAME);

		Robot robot = MyFrame.getRobot();
		robot.setAutoDelay(300);
		BehaviorLogic bl = new BehaviorLogic(robot);
		for (String seq : strBehSeq) {
			bl.doBehavior(seq);
		}
	}

	/**
	 * 用于创建新的xml状态文件
	 * 
	 * @throws IOException
	 */
	public static void createXML(String[] strData) throws IOException {
		Element root = DocumentHelper.createElement("root");
		Document document = DocumentHelper.createDocument(root);

		int nInterval = strData.length / 2;
		for (int i = 0; i < strData.length / 2; i++) {
			Element element1 = root.addElement(strData[i]).addText(strData[i + nInterval]);
		}

		// 把生成的xml文档存放在硬盘上 true代表是否换行
		OutputFormat format = new OutputFormat("", true);
		format.setEncoding("GBK");// 设置编码格式
		XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(dir + "\\status\\" + strStatusFileID + "_st.xml"),
				format);

		xmlWriter.write(document);
		xmlWriter.close();
	}
}
