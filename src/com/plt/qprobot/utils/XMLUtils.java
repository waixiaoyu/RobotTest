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
	 * 先获取文件夹的第一个名字
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
			System.err.println("send目录不存在，请检查");
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
	 * 直接通过节点名称来选取value，如：<tcs:EportLocationCode>5301</tcs:EportLocationCode>
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
	 * 读取多个相同元素的
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
	 * 重名多节点的情况下，输入节点名称，和限定节点名称，和限定值 先查询父节点，再查询匹配值 在后来的xml中，已经没有这么复杂了，此函数将废弃
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
	 * 用于读取完一个xml以后删除
	 */
	public static void delete(String fileName) {
		File file = new File(fileName);
		if (file.exists()) {
			file.delete();
			System.out.println(fileName + "成功删除");
		} else {
			System.err.println("文件夹不存在出错,无法删除");
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
	 * 用于创建新的xml状态文件
	 * 
	 * @throws IOException
	 */
	public static void createXML(String fileName) throws IOException {
		Element root = DocumentHelper.createElement("root");
		Document document = DocumentHelper.createDocument(root);

		// 给根节点添加孩子节点
		Element element1 = root.addElement("UniCode").addText(RobotMain.strUniCode);

		// 把生成的xml文档存放在硬盘上 true代表是否换行
		OutputFormat format = new OutputFormat("", true);
		format.setEncoding("GBK");// 设置编码格式
		XMLWriter xmlWriter = new XMLWriter(
				new FileOutputStream(
						RobotMain.dir + "\\receive\\" + fileName.substring(0, fileName.length() - 4) + "_re.xml"),
				format);

		xmlWriter.write(document);
		xmlWriter.close();
	}
}
