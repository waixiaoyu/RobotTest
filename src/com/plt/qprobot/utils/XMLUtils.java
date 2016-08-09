package com.plt.qprobot.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.xpath.DefaultXPath;

import com.plt.qprobot.robot.RobotMain;

public class XMLUtils {
	private static SAXReader reader = new SAXReader();
	private static Document document;
	private static DefaultXPath xpath;
	private static String dir = "";

	public static void main(String[] args) throws DocumentException {
		System.out.println(getFileName());
	}

	/**
	 * �Ȼ�ȡ�ļ��еĵ�һ������
	 * 
	 * @return
	 */
	public static String getFileName() {
		dir = System.getProperty("user.dir");
		String[] str = null;
		File fdir = new File(dir + "\\send");
		if (fdir.exists() && fdir.isDirectory()) {
			str = fdir.list();
		} else {
			System.err.println("sendĿ¼�����ڣ�����");
			System.exit(1);
		}

		if (str.length == 0) {
			return "";
		} else {
			File file = new File(dir + "\\send\\" + str[0]);
			try {
				document = reader.read(file);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return str[0];
		}
	}

	/**
	 * ֱ��ͨ���ڵ�������ѡȡvalue���磺<tcs:EportLocationCode>5301</tcs:EportLocationCode>
	 * 
	 * @param StrNodeName
	 * @return
	 * @throws DocumentException
	 */
	public static String read(String StrNodeName) throws DocumentException {
		xpath = new DefaultXPath("//" + StrNodeName);
		return xpath.selectSingleNode(document).getStringValue();
	}

	/**
	 * ��ȡ�����ͬԪ�ص�
	 * 
	 * @param StrNodeName
	 * @return
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public static List<Node> readAll(String StrNodeName) throws DocumentException {
		xpath = new DefaultXPath("//" + StrNodeName);
		return (List<Node>) (xpath.selectNodes(document));
	}

	/**
	 * ������ڵ������£�����ڵ����ƣ����޶��ڵ����ƣ����޶�ֵ �Ȳ�ѯ���ڵ㣬�ٲ�ѯƥ��ֵ �ں�����xml�У��Ѿ�û����ô�����ˣ��˺���������
	 * 
	 * @param StrNodeName,qname,qvalue
	 * @param qualifies
	 * @throws DocumentException
	 */
	@Deprecated
	public static String read(String StrNodeName, String strQName, String strQValue) throws DocumentException {
		xpath = new DefaultXPath("//" + StrNodeName);
		xpath.setNamespaceURIs(Collections.singletonMap("tcs", "http://www.chinaport.gov.cn/tcs/v2"));
		List<?> list = xpath.selectNodes(document);
		Iterator<?> iterator = list.iterator();
		while (iterator.hasNext()) {
			Element node = (Element) iterator.next();
			Element parent = node.getParent();
			if (parent.selectSingleNode(strQName) != null
					&& parent.selectSingleNode(strQName).getStringValue().equals(strQValue)) {
				return node.getStringValue();
			}
		}
		return null;
	}

	public static String[] readGoodInfo(String strNodeName) throws DocumentException {
		xpath = new DefaultXPath("//" + strNodeName);
		return xpath.selectSingleNode(document).getStringValue().split("\\|");
	}

	@SuppressWarnings("unchecked")
	public static List<Node> readGoodInfoAll(String strNodeName) throws DocumentException {
		xpath = new DefaultXPath("//" + strNodeName);
		return (List<Node>) xpath.selectNodes(document);
	}

	public static String[] readOtherItems(String strNodeName) throws DocumentException {
		xpath = new DefaultXPath("//" + strNodeName);
		xpath.setNamespaceURIs(Collections.singletonMap("tcs", "http://www.chinaport.gov.cn/tcs/v2"));
		String it = xpath.selectSingleNode(document).getStringValue();
		String[] items = new String[it.length()];
		for (int i = 0; i < it.length(); i++) {
			items[i] = String.valueOf(it.charAt(i));
		}
		return items;
	}

	/**
	 * ���ڶ�ȡ��һ��xml�Ժ�ɾ��
	 */
	public static void delete(String fileName) {
		File file = new File(fileName);
		if (file.exists()) {
			file.delete();
			System.out.println(fileName + "�ɹ�ɾ��");
		} else {
			System.err.println("�ļ��в����ڳ���,�޷�ɾ��");
			System.exit(1);
		}
	}

	public static void deleteTXT(String fileName) {
		delete(RobotMain.dir + "\\" + fileName.substring(0, fileName.length() - 3) + "txt");
	}

	public static void deleteXML(String fileName) {
		delete(RobotMain.dir + "\\send\\" + fileName);
	}

	/**
	 * ���ڴ����µ�xml״̬�ļ�
	 * 
	 * @throws IOException
	 */
	public static void createXML(String fileName) throws IOException {
		Element root = DocumentHelper.createElement("root");
		Document document = DocumentHelper.createDocument(root);

		// �����ڵ���Ӻ��ӽڵ�
		Element element1 = root.addElement("UniCode").addText(RobotMain.strUniCode);

		// �����ɵ�xml�ĵ������Ӳ���� true�����Ƿ���
		OutputFormat format = new OutputFormat("", true);
		format.setEncoding("GBK");// ���ñ����ʽ
		XMLWriter xmlWriter = new XMLWriter(
				new FileOutputStream(
						RobotMain.dir + "\\receive\\" + fileName.substring(0, fileName.length() - 4) + "_re.xml"),
				format);

		xmlWriter.write(document);
		xmlWriter.close();
	}
}
