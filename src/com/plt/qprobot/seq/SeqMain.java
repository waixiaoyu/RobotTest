package com.plt.qprobot.seq;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.json.JSONObject;

import com.plt.qprobot.utils.JSONUtils;
import com.plt.qprobot.utils.PropertiesUtils;
import com.plt.qprobot.utils.XMLUtils;

public class SeqMain {
	private static Logger Log = Logger.getLogger(SeqMain.class);

	public static String strJsonPath;
	public static long sleep_time = 50;// ����ͨ����������ʱ��������ٶ�

	public SeqMain(String strJsonPath) {
		super();
		SeqMain.strJsonPath = strJsonPath.substring(0, strJsonPath.length() - 3) + "txt";
	}

	public static void main(String[] args) throws DocumentException {
		SeqMain cs = new SeqMain(XMLUtils.getFileName());
		cs.create();
	}

	public void create() throws DocumentException {
		List<JSONObject> lJ = new ArrayList<>();

		lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("BaoGuanDan")));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.mouseLeftClick());
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("ImportBaoGuanDan")));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.mouseLeftClick());
		lJ.add(SeqWrite.sleep(sleep_time));

		// ��������
		lJ.add(SeqWrite.keyPress("new"));
		/**
		 * �걨����
		 */
		// һ����������-�걨�غ���
		lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("CustomsDeclaration")));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.mouseLeftClick());
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.copy(XMLUtils.read("CustomsDeclaration")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		/**
		 * �ݴ�
		 */
		lJ.add(SeqWrite.keyPress("save"));
		// ����Ƿ��ݴ�ɹ�
		lJ.add(SeqWrite.checkColor(PropertiesUtils.get("TempSaveColorLocation")));
		lJ.add(SeqWrite.keyPress("enter"));

		/**
		 * ��ʼ�ϰ벿��
		 */
		// ���ڿڰ�
		lJ.add(SeqWrite.copy(XMLUtils.read("ImportPort")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.keyPress("enter"));// ����������
		// ��ͬЭ���
		lJ.add(SeqWrite.copy(XMLUtils.read("ContractNo")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		// ��������
		// lJ.add(SeqWrite.copy(XMLUtils.read("ImportExportDate").replaceAll("-",
		// "")));
		// lJ.add(SeqWrite.keyPress("paste"));
		// lJ.add(SeqWrite.sleep(sleep_time));
		// lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.keyPress("enter"));// ������������
		// �շ�����
		lJ.add(SeqWrite.copy(XMLUtils.read("Consignee")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		// ����շ������Ƿ����
		lJ.add(SeqWrite.checkColor(PropertiesUtils.get("ConsigneeColorLocation")));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.keyPress("enter"));
		// ����ʹ�õ�λ
		lJ.add(SeqWrite.copy(XMLUtils.read("ConsumerUnit")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		// �������ʹ�õ�λ�Ƿ����
		lJ.add(SeqWrite.checkColor(PropertiesUtils.get("ConsumerUnitColorLocation")));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.keyPress("enter"));
		// �걨��λ
		lJ.add(SeqWrite.copy(XMLUtils.read("DeclarationUnit")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		// �������ʹ�õ�λ�Ƿ����
		lJ.add(SeqWrite.checkColor(PropertiesUtils.get("DeclarationUnitColorLocation")));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.keyPress("enter"));
		// ���䷽ʽ
		lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("MeansOfTransportMode")));
		lJ.add(SeqWrite.mouseLeftClick());
		lJ.add(SeqWrite.copy(XMLUtils.read("MeansOfTransportMode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.keyPress("enter"));// �������乥������
		// ���κ�
		lJ.add(SeqWrite.copy(XMLUtils.read("MeansOfTransportId")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		// ���˵���
		lJ.add(SeqWrite.copy(XMLUtils.read("BillOfLadingNo")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		// �ල��ʽ
		lJ.add(SeqWrite.copy(XMLUtils.read("TradeMode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		// ��������
		lJ.add(SeqWrite.copy(XMLUtils.read("CutMode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.keyPress("enter"));// ������˰��λ
		lJ.add(SeqWrite.keyPress("enter"));// �������֤��
		// ���˹�
		lJ.add(SeqWrite.copy(XMLUtils.read("PurchaseCountry")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		// װ����
		lJ.add(SeqWrite.copy(XMLUtils.read("LoadingPort")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		// ����Ŀ�ĵ�
		lJ.add(SeqWrite.copy(XMLUtils.read("DestinationCode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		// �ɽ���ʽ
		String sTM = XMLUtils.read("TransactionMode");
		lJ.add(SeqWrite.copy(XMLUtils.read("TransactionMode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		if (sTM.equals("1")) {
			// �ӷ�1
			lJ.add(SeqWrite.copy(XMLUtils.read("ExtrasMark")));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			// �ӷ�2
			lJ.add(SeqWrite.copy(XMLUtils.read("ExtrasRate")));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			// �ӷ�3
			lJ.add(SeqWrite.copy(XMLUtils.read("ExtrasCurrency")));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
		} else {
			// �˷ѱ��
			lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("FreightMark")));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.mouseLeftClick());
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.copy(XMLUtils.read("FreightMark")));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			// �˷���
			lJ.add(SeqWrite.copy(XMLUtils.read("FreightRate")));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			// �˷ѱ���
			lJ.add(SeqWrite.copy(XMLUtils.read("FreightCurrency")));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			// ����1
			lJ.add(SeqWrite.copy(XMLUtils.read("InsuranceMark")));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			// ����2
			lJ.add(SeqWrite.copy(XMLUtils.read("InsuranceRate")));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			// ����3
			lJ.add(SeqWrite.copy(XMLUtils.read("InsuranceCurrency")));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			// �ӷ�1
			lJ.add(SeqWrite.copy(XMLUtils.read("ExtrasMark")));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			// �ӷ�2
			lJ.add(SeqWrite.copy(XMLUtils.read("ExtrasRate")));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			// �ӷ�3
			lJ.add(SeqWrite.copy(XMLUtils.read("ExtrasCurrency")));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
		}
		// ����
		lJ.add(SeqWrite.copy(XMLUtils.read("Packages")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		// ��װ����
		lJ.add(SeqWrite.copy(XMLUtils.read("PackingType")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		// ë��
		lJ.add(SeqWrite.copy(XMLUtils.read("GrossWeight")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		// ����
		lJ.add(SeqWrite.copy(XMLUtils.read("NetWeight")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		// ó�׹�����
		lJ.add(SeqWrite.copy(XMLUtils.read("TradeAreaCode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		/**
		 * ��ŵ����
		 */
		// ������������ȷ��
		lJ.add(SeqWrite.keyPress("enter"));
		String[] strOtherItems = XMLUtils.readOtherItems("PromiseItmes");
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
		 * �����ж����װ��
		 */
		for (int i = 0; i < XMLUtils.readAll("Container").size(); i++) {
			// ��װ���
			lJ.add(SeqWrite.copy(XMLUtils.readAll("ContainerNo").get(i).getStringValue()));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			// ��װ����
			lJ.add(SeqWrite.copy(XMLUtils.readAll("ContainerSize").get(i).getStringValue()));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			// ����
			lJ.add(SeqWrite.copy(XMLUtils.readAll("DeadWeight").get(i).getStringValue()));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
		}

		// ���ص�����
		lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("DeclarationType")));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.mouseLeftClick());
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.copy(XMLUtils.read("DeclarationType")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));

		/**
		 * �����°벿����Ʒ��Ϣ-------------------------------------- �����ж����Ʒ
		 */
		for (int i = 0; i < XMLUtils.readAll("GoodsInformation").size(); i++) {
			// ��Ʒ���ƶ��ز˵���
			lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("GoodsName")));
			lJ.add(SeqWrite.mouseLeftClick());
			lJ.add(SeqWrite.copy(XMLUtils.readAll("GoodsName").get(i).getStringValue()));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.keyPress("enter"));

			lJ.add(SeqWrite.copy(XMLUtils.readAll("HsCode").get(i).getStringValue()));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.keyPress("enter"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			lJ.add(SeqWrite.sleep(sleep_time));
			// ��ȡ��Ʒ������Ϣ
			String[] strGoodsInfo = XMLUtils.readGoodInfoAll("Model").get(i).getStringValue().split("\\|");
			for (String string : strGoodsInfo) {
				lJ.add(SeqWrite.copy(string));
				lJ.add(SeqWrite.keyPress("paste"));
				lJ.add(SeqWrite.keyPress("enter"));
			}
			lJ.add(SeqWrite.keyPress("enter"));
			// ������Ʒ��Ϣ
			lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("Quantity")));
			lJ.add(SeqWrite.mouseLeftClick());
			lJ.add(SeqWrite.copy(XMLUtils.readAll("Quantity").get(i).getStringValue()));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.keyPress("enter"));
			// �ɽ���λ
			lJ.add(SeqWrite.copy(XMLUtils.readAll("QuantityUnit").get(i).getStringValue()));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.keyPress("enter"));
			// �ɽ�����
			lJ.add(SeqWrite.copy(XMLUtils.readAll("UnitPrice").get(i).getStringValue()));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.keyPress("enter"));
			// �ɽ��ܼ�-��Ҫ˫�����˸�ȥ���ܼۣ���������
			lJ.add(SeqWrite.keyPress("backspace"));
			lJ.add(SeqWrite.copy(XMLUtils.readAll("TotalPrice").get(i).getStringValue()));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.keyPress("enter"));
			// ����
			lJ.add(SeqWrite.copy(XMLUtils.readAll("Currency").get(i).getStringValue()));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.keyPress("enter"));
			// ��������
			lJ.add(SeqWrite.copy(XMLUtils.readAll("FirstQuantity").get(i).getStringValue()));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.keyPress("enter"));
			// ������λ--�޷�����
			// lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("FirstUnit")));
			// lJ.add(SeqWrite.mouseLeftClick());
			// lJ.add(SeqWrite.copy(XMLUtils.read("FirstUnit")));
			// lJ.add(SeqWrite.keyPress("paste"));
			// lJ.add(SeqWrite.keyPress("enter"));
			lJ.add(SeqWrite.keyPress("enter"));// �����汾��
			lJ.add(SeqWrite.keyPress("enter"));// ��������
			// ����Ŀ�Ĺ�
			lJ.add(SeqWrite.copy(XMLUtils.readAll("DestinationCountry").get(i).getStringValue()));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.keyPress("enter"));
			// ԭ����
			lJ.add(SeqWrite.copy(XMLUtils.readAll("OriginCode").get(i).getStringValue()));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.keyPress("enter"));
			lJ.add(SeqWrite.keyPress("enter"));// �����Ի���
			// ���ⷽʽ
			lJ.add(SeqWrite.copy(XMLUtils.readAll("DutyMode").get(i).getStringValue()));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.keyPress("enter"));

		}

		/**
		 * ��֤
		 */
		lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("AttachedDocumentCode")));
		lJ.add(SeqWrite.mouseLeftClick());
		for (int i = 0; i < XMLUtils.readAll("AttachDocument").size(); i++) {
			// �渽��֤����
			lJ.add(SeqWrite.copy(XMLUtils.readAll("AttachedDocumentCode").get(i).getStringValue()));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			// �渽��֤���
			lJ.add(SeqWrite.copy(XMLUtils.readAll("AttachedDocumentNo").get(i).getStringValue()));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
		}

		/**
		 * ��Ʒ��Ϣ����-----------------------------------------------
		 */

		/**
		 * �ݴ�
		 */
		lJ.add(SeqWrite.keyPress("save"));
		// ����Ƿ��ݴ�ɹ�
		lJ.add(SeqWrite.checkColor(PropertiesUtils.get("TempSaveColorLocation")));
		lJ.add(SeqWrite.keyPress("enter"));
		/**
		 * ��ȡͳһ����
		 */
		lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("UniCode")));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.mouseLeftClick());
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.mouseRightClick());
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("A"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("copy"));
		lJ.add(SeqWrite.sleep(sleep_time));

		// ����json
		JSONUtils.write(lJ, strJsonPath);

	}

	public void read() {
		List<String> strBehSeq;
		strBehSeq = SeqRead.read(strJsonPath);
		for (String string : strBehSeq) {
			Log.info(string);
		}
	}
}
