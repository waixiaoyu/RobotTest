package com.plt.qprobot.utils;

import java.io.File;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.xpath.DefaultXPath;

public class XMLUtils {
	private static SAXReader reader = new SAXReader();
	private static Document document;
	private static DefaultXPath xpath;
	static {
		File file = new File(System.getProperty("user.dir") + "\\send\\PLT2016-08002_201607271159.xml");
		try {
			document = reader.read(file);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws DocumentException {
		System.out.println(XMLUtils.readAll("Container").size());
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
		return (List<Node>)xpath.selectNodes(document);
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
}
