package com.plt.qprobot.seq;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentException;
import org.json.JSONObject;

import com.plt.qprobot.robot.RobotMain;
import com.plt.qprobot.utils.JSONUtils;
import com.plt.qprobot.utils.PropertiesUtils;
import com.plt.qprobot.utils.XMLUtils;

public class SeqTest {

	private static final String PATH = RobotMain.PATH;

	public static void main(String[] args) throws DocumentException {
		SeqTest cs = new SeqTest();
		cs.create();
	}

	public void create() throws DocumentException {
		List<JSONObject> lJ = new ArrayList<>();
		// ��������
		lJ.add(SeqWrite.keyPress("new"));
		/**
		 * �걨����
		 */
		// һ����������-�걨�غ���
		lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("tcs:EportLocationCode")));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.mouseLeftClick());
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:EportLocationCode", "tcs:EportLocationTypeCode", "001")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		/**
		 * ��ʼ�ϰ벿��
		 */
		// ���ڿڰ�
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:EportLocationCode", "tcs:EportLocationTypeCode", "003")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.keyPress("enter"));// ����������
		// ��ͬЭ���
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:ContractNo")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		// ��������
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:ImportExportDate").replaceAll("-", "")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		// ���䷽ʽ
		lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("tcs:MeansOfTransportMode")));
		lJ.add(SeqWrite.mouseLeftClick());
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:MeansOfTransportMode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.keyPress("enter"));// �������乥������
		// ���κ�
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:MeansOfTransportId")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		// ���˵���
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:BillOfLadingNo")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		// �ල��ʽ
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:TradeMode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		// ��������
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:CutMode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.keyPress("enter"));// ������˰��λ
		lJ.add(SeqWrite.keyPress("enter"));// �������֤��
		// ���˹�����--------------------��Ҫ�޸�
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:TradeAreaCode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		// װ����--------------------��Ҫ�޸�
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:TradeAreaCode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		// ����Ŀ�ĵ�
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:DestinationCode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		// �ɽ���ʽ
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:TransactionMode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.keyPress("enter"));// �����ӷ�
		lJ.add(SeqWrite.keyPress("enter"));// �����ӷ�
		lJ.add(SeqWrite.keyPress("enter"));// �����ӷ�

		// ����
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:No")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		// ��װ����
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:PackingType")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		// ë��
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:GrossWeight")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		// ����
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:NetWeight")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		// ó�׹�����
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:TradeAreaCode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));

		/**
		 * ��������ȷ��
		 */
		// ������������ȷ��
		lJ.add(SeqWrite.keyPress("enter"));
		String[] strOtherItems = XMLUtils.readOtherItems("tcs:PromiseItmes");
		if (strOtherItems[0].equals("1")) {
			for (String string : strOtherItems) {
				lJ.add(SeqWrite.copy(string));
				lJ.add(SeqWrite.keyPress("paste"));
				lJ.add(SeqWrite.keyPress("enter"));
			}
		} else {
			lJ.add(SeqWrite.copy(strOtherItems[0]));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.keyPress("enter"));
			lJ.add(SeqWrite.copy(strOtherItems[2]));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.keyPress("enter"));
		}
		lJ.add(SeqWrite.keyPress("enter"));

		/**
		 * �����°벿����Ʒ��Ϣ--------------------------------------
		 */
		// ��Ʒ���ƶ��ز˵���
		lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("tcs:GoodsName")));
		lJ.add(SeqWrite.mouseLeftClick());
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:GoodsName")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.keyPress("enter"));

		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:HsCode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.sleep(100));
		// ��ȡ��Ʒ������Ϣ
		String[] strGoodsInfo = XMLUtils.readGoodInfo("tcs:Model");
		for (String string : strGoodsInfo) {
			lJ.add(SeqWrite.copy(string));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.keyPress("enter"));
		}
		lJ.add(SeqWrite.keyPress("enter"));
		// ������Ʒ��Ϣ
		lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("tcs:Quantity")));
		lJ.add(SeqWrite.mouseLeftClick());
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:Quantity")));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.keyPress("enter"));

		// �ɽ���λ
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:QuantityUnit")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.keyPress("enter"));
		// �ɽ�����
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:UnitPrice")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.keyPress("enter"));
		// �ɽ��ܼ�-��Ҫ˫�����˸�ȥ���ܼۣ���������
		lJ.add(SeqWrite.keyPress("backspace"));
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:TotalPrice")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.keyPress("enter"));
		// ����
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:Currency")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.keyPress("enter"));
		// ��������
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:FirstQuantity")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.keyPress("enter"));
		// ������λ--�޷�����
		// lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("tcs:FirstUnit")));
		// lJ.add(SeqWrite.mouseLeftClick());
		// lJ.add(SeqWrite.copy(XMLUtils.read("tcs:FirstUnit")));
		// lJ.add(SeqWrite.keyPress("paste"));
		// lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.keyPress("enter"));// �����汾��
		lJ.add(SeqWrite.keyPress("enter"));// ��������
		// ����Ŀ�Ĺ�
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:DestinationCountry")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.keyPress("enter"));
		// ԭ����
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:OriginCode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.keyPress("enter"));// �����Ի���
		// ���ⷽʽ
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:DutyMode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.keyPress("enter"));

		/**
		 * ��Ʒ��Ϣ����-----------------------------------------------
		 */
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
