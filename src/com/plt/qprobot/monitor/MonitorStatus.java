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
 * ������ȡ�걨״̬����
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
	private static String strStatusFileID = "";// id�������ļ���׺����like
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
				Log.error("û�м�⵽QP����");
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
				Log.info("��ʼ��ȡstatus���ļ���:" + strStatusFileID);
				createQueryJSON();
				robotStart();
				// �����ѯ������ⲿ
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
			MyFrame.jlTimer.setText("״̬�����ϣ��ȴ����");
			Log.info("״̬�����ϣ��ȴ����");
			nStatusFileNameIndex = -1;
		}
		if (!isContinue) {
			MyFrame.jlTimer.setText("״̬�����ֹ���ȴ����");
			Log.info("״̬�����ֹ���ȴ����");
		}
		isContinue = false;
	}

	private void createQueryJSON() throws DocumentException {
		List<JSONObject> lJ = new ArrayList<>();
		// ��������
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
		// ����Ƿ��ѯ
		lJ.add(SeqWrite.checkColor(PropertiesUtils.get("QueryBlankColorLocation")));
		lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("Result")));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.mouseLeftClick()); // ����json
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("copy"));

		JSONUtils.write(lJ, QUERY_FILENAME);
	}

	/**
	 * ��ȡ���е��ļ�����
	 */
	private void getFileName() {
		dir = System.getProperty("user.dir");
		File fdir = new File(dir + "\\receive");
		if (fdir.list().length > 0) {
			strStatusFileNames = fdir.list();
		}
	}

	/**
	 * ��ȡreceive�е�unicode
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
			Log.info("status�ļ���Ϊ��");
			MonitorStatus.isContinue = false;
			return "";
		}

	}

	/**
	 * ��������״̬����߼�
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
	 * ���ڴ����µ�xml״̬�ļ�
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

		// �����ɵ�xml�ĵ������Ӳ���� true�����Ƿ���
		OutputFormat format = new OutputFormat("", true);
		format.setEncoding("GBK");// ���ñ����ʽ
		XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(dir + "\\status\\" + strStatusFileID + "_st.xml"),
				format);

		xmlWriter.write(document);
		xmlWriter.close();
	}
}
