package com.plt.qprobot.utils;

import java.io.File;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.xpath.DefaultXPath;

public class XMLUtils {
	private static SAXReader reader = new SAXReader();
	private static Document document;
	private static DefaultXPath xpath;
	static {
		File file = new File(System.getProperty("user.dir") + "\\src\\test.xml");
		try {
			document = reader.read(file);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws DocumentException {
		System.out.println(XMLUtils.read("tcs:QuantityUnit"));
	}

	/**
	 * ֱ��ͨ���ڵ�������ѡȡvalue���磺<tcs:EportLocationCode>5301</tcs:EportLocationCode>
	 * ֻ����һ����ֱ������tcs:EportLocationCode����
	 * 
	 * @param StrNodeName
	 * @return
	 * @throws DocumentException
	 */
	public static String read(String StrNodeName) throws DocumentException {
		xpath = new DefaultXPath("//" + StrNodeName);
		xpath.setNamespaceURIs(Collections.singletonMap("tcs", "http://www.chinaport.gov.cn/tcs/v2"));
		return xpath.selectSingleNode(document).getStringValue();
	}

	/**
	 * ������ڵ������£�����ڵ����ƣ����޶��ڵ����ƣ����޶�ֵ �Ȳ�ѯ���ڵ㣬�ٲ�ѯƥ��ֵ
	 * 
	 * @param StrNodeName,qname,qvalue
	 * @param qualifies
	 * @throws DocumentException
	 */
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
		xpath.setNamespaceURIs(Collections.singletonMap("tcs", "http://www.chinaport.gov.cn/tcs/v2"));
		return xpath.selectSingleNode(document).getStringValue().split("\\|");
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
