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
	public static long sleep_time = 30;// ����ͨ����������ʱ��������ٶ�

	public SeqMain(String strJsonPath) {
		super();
		SeqMain.strJsonPath = strJsonPath.substring(0, strJsonPath.length() - 3) + "txt";
	}

	public static void main(String[] args) throws DocumentException {
		SeqMain cs = new SeqMain(XMLUtils.getFileName());
		cs.create();
	}

	/**
	 * ���еĲ������У��������º����и�����ͨ���ú�����������json�ַ����������ȡ�ַ���������Ӧ�Ķ���
	 * 
	 * @throws DocumentException
	 */
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
		lJ.add(SeqWrite.keyInputFromPaste());
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));

		// �ݴ�
		// tmpSave(lJ);
		// �������ص����Ĳ���
		createOrderContent(lJ);
		// ������װ����Ϣ
		createBoxes(lJ);
		// ������Ʒ��Ϣ-----------------------------------------
		createGoods(lJ);
		// ������Ʒ��Ϣ����----------------------------------------
		// ������֤��Ϣ
		createAttachDocuments(lJ);
		// �ݴ�
		tmpSave(lJ);

		/**
		 * ��ȡͳһ����
		 */
		createUniCode(lJ);

		// ����json
		JSONUtils.write(lJ, strJsonPath);

	}

	private void tmpSave(List<JSONObject> lJ) throws DocumentException {
		/**
		 * �ݴ�
		 */
		lJ.add(SeqWrite.keyPress("save"));
		// ����Ƿ��ݴ�ɹ�
		lJ.add(SeqWrite.checkColor(PropertiesUtils.get("TempSaveColorLocation")));
		lJ.add(SeqWrite.keyPress("enter"));
	}

	private void createOrderContent(List<JSONObject> lJ) throws DocumentException {
		/**
		 * ��ʼ�ϰ벿��
		 */
		// ���ڿڰ�
		lJ.add(SeqWrite.copy(XMLUtils.read("ImportPort")));
		lJ.add(SeqWrite.keyInputFromPaste());
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));// ����������
		lJ.add(SeqWrite.sleep(sleep_time));
		// ��ͬЭ���
		lJ.add(SeqWrite.copy(XMLUtils.read("ContractNo")));
		lJ.add(SeqWrite.keyInputFromPaste());
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.sleep(sleep_time));
		// ��������
		lJ.add(SeqWrite.keyPress("enter"));// ������������
		lJ.add(SeqWrite.sleep(sleep_time));
		// �շ�����
		lJ.add(SeqWrite.keyInputFromPaste());
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.sleep(sleep_time));
		// ����շ������Ƿ����
		lJ.add(SeqWrite.checkColor(PropertiesUtils.get("ConsigneeColorLocation")));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.sleep(sleep_time));
		// ����ʹ�õ�λ
		lJ.add(SeqWrite.copy(XMLUtils.read("ConsumerUnit")));
		lJ.add(SeqWrite.keyInputFromPaste());
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.sleep(sleep_time));
		// �������ʹ�õ�λ�Ƿ����
		lJ.add(SeqWrite.checkColor(PropertiesUtils.get("ConsumerUnitColorLocation")));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.sleep(sleep_time));
		// �걨��λ
		lJ.add(SeqWrite.copy(XMLUtils.read("DeclarationUnit")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.sleep(sleep_time));
		// �������ʹ�õ�λ�Ƿ����
		lJ.add(SeqWrite.checkColor(PropertiesUtils.get("DeclarationUnitColorLocation")));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.sleep(sleep_time));
		// ���䷽ʽ
		lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("MeansOfTransportMode")));
		lJ.add(SeqWrite.mouseLeftClick());
		lJ.add(SeqWrite.copy(XMLUtils.read("MeansOfTransportMode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));// �������乥������
		lJ.add(SeqWrite.sleep(sleep_time));
		// ���κ�
		lJ.add(SeqWrite.copy(XMLUtils.read("MeansOfTransportId")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.sleep(sleep_time));
		// ���˵���
		lJ.add(SeqWrite.copy(XMLUtils.read("BillOfLadingNo")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.sleep(sleep_time));
		// �ල��ʽ
		lJ.add(SeqWrite.copy(XMLUtils.read("TradeMode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.sleep(sleep_time));
		// ��������
		lJ.add(SeqWrite.copy(XMLUtils.read("CutMode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));// ������˰��λ
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));// �������֤��
		lJ.add(SeqWrite.sleep(sleep_time));
		// ���˹�
		lJ.add(SeqWrite.copy(XMLUtils.read("PurchaseCountry")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.sleep(sleep_time));
		// װ����
		lJ.add(SeqWrite.copy(XMLUtils.read("LoadingPort")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.sleep(sleep_time));
		// ����Ŀ�ĵ�
		lJ.add(SeqWrite.copy(XMLUtils.read("DestinationCode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.sleep(sleep_time));
		// �ɽ���ʽ
		String sTM = XMLUtils.read("TransactionMode");
		lJ.add(SeqWrite.copy(XMLUtils.read("TransactionMode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.sleep(sleep_time));
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
			lJ.add(SeqWrite.sleep(sleep_time));
			// �˷���
			lJ.add(SeqWrite.copy(XMLUtils.read("FreightRate")));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			lJ.add(SeqWrite.sleep(sleep_time));
			// �˷ѱ���
			lJ.add(SeqWrite.copy(XMLUtils.read("FreightCurrency")));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			lJ.add(SeqWrite.sleep(sleep_time));
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
			lJ.add(SeqWrite.sleep(sleep_time));
			// ����3
			lJ.add(SeqWrite.copy(XMLUtils.read("InsuranceCurrency")));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			lJ.add(SeqWrite.sleep(sleep_time));
			// �ӷ�1
			lJ.add(SeqWrite.copy(XMLUtils.read("ExtrasMark")));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			lJ.add(SeqWrite.sleep(sleep_time));
			// �ӷ�2
			lJ.add(SeqWrite.copy(XMLUtils.read("ExtrasRate")));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			lJ.add(SeqWrite.sleep(sleep_time));
			// �ӷ�3
			lJ.add(SeqWrite.copy(XMLUtils.read("ExtrasCurrency")));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			lJ.add(SeqWrite.sleep(sleep_time));
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
	}

	private void createBoxes(List<JSONObject> lJ) throws DocumentException {
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
		if (XMLUtils.readAll("Container").size() > 0
				&& XMLUtils.readAll("ContainerNo").get(0).getStringValue().equals("")) {
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
	}

	private void createGoods(List<JSONObject> lJ) throws DocumentException {
		Log.info("��Ʒ����: " + XMLUtils.readAll("GoodsInformation").size());
		for (int i = 0; i < XMLUtils.readAll("GoodsInformation").size(); i++) {
			// ��Ʒ���ƶ��ز˵���
			lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("GoodsName")));
			lJ.add(SeqWrite.mouseLeftClick());
			lJ.add(SeqWrite.copy(XMLUtils.readAll("GoodsName").get(i).getStringValue()));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.keyPress("enter"));

			// ����Ƿ�itemlist��ֱ�ӵ���
			lJ.add(SeqWrite.protect());
			lJ.add(SeqWrite.checkColor(PropertiesUtils.get("ItemList")));
			lJ.add(SeqWrite.jump(2));
			lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("ItemListCancel")));
			lJ.add(SeqWrite.mouseLeftClick());
			lJ.add(SeqWrite.unprotect());

			lJ.add(SeqWrite.copy(XMLUtils.readAll("HsCode").get(i).getStringValue()));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.keyPress("enter"));
			lJ.add(SeqWrite.sleep(sleep_time + 500));
			lJ.add(SeqWrite.keyPress("enter"));
			lJ.add(SeqWrite.sleep(sleep_time + 500));

			// ����Ƿ��õ�������Ʒ��Ϣ
			lJ.add(SeqWrite.protect());
			lJ.add(SeqWrite.checkColor(PropertiesUtils.get("GoodDetailColorLocation")));
			lJ.add(SeqWrite.jump(1));
			lJ.add(SeqWrite.keyPress("enter"));
			lJ.add(SeqWrite.unprotect());

			// ��ȡ��Ʒ������Ϣ
			String[] strGoodsInfo = XMLUtils.readGoodInfoAll("Model").get(i).getStringValue().split("\\|");
			for (String string : strGoodsInfo) {
				lJ.add(SeqWrite.copy(string));
				lJ.add(SeqWrite.keyPress("paste"));
				lJ.add(SeqWrite.keyPress("enter"));
			}
			// ���ȷ��
			lJ.add(SeqWrite.keyPress("alt"));
			lJ.add(SeqWrite.keyPress("enter"));
			lJ.add(SeqWrite.keyPress("enter"));
			lJ.add(SeqWrite.keyPress("enter"));
			lJ.add(SeqWrite.keyPress("enter"));

			// ������Ʒ��Ϣ
			// �ɽ�����
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

			// ��ⷨ�������Ƿ��ֱ�ӵ�����ʾ��Ϣ
			lJ.add(SeqWrite.protect());
			lJ.add(SeqWrite.checkColor(PropertiesUtils.get("QuantityInfoLocation")));
			lJ.add(SeqWrite.jump(1));
			lJ.add(SeqWrite.keyPress("enter"));
			lJ.add(SeqWrite.unprotect());

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

			// �ڶ�����
			if (!XMLUtils.readAll("SecondQuantity").isEmpty()
					&& XMLUtils.readAll("SecondQuantity").get(i).getStringValue() != "") {
				lJ.add(SeqWrite.copy(XMLUtils.readAll("SecondQuantity").get(i).getStringValue()));
				lJ.add(SeqWrite.keyPress("paste"));
				lJ.add(SeqWrite.keyPress("enter"));
			}

			// ԭ����
			// lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("OriginCode")));
			// lJ.add(SeqWrite.mouseLeftClick());
			lJ.add(SeqWrite.copy(XMLUtils.readAll("OriginCode").get(i).getStringValue()));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.keyPress("enter"));

			// ��ⷨ�������Ƿ��ֱ�ӵ�����ʾ��Ϣ
			// ����Ƿ�itemlist��ֱ�ӵ���
			lJ.add(SeqWrite.protect());
			lJ.add(SeqWrite.checkColor(PropertiesUtils.get("OriInfoLocation")));
			lJ.add(SeqWrite.jump(2));
			lJ.add(SeqWrite.keyPress("enter"));
			lJ.add(SeqWrite.unprotect());

			// ���ⷽʽ
			lJ.add(SeqWrite.copy(XMLUtils.readAll("DutyMode").get(i).getStringValue()));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.keyPress("enter"));

		}
	}

	private void createAttachDocuments(List<JSONObject> lJ) throws DocumentException {
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
		// ����Ƿ�itemlist��ֱ�ӵ���
		lJ.add(SeqWrite.protect());
		lJ.add(SeqWrite.checkColor(PropertiesUtils.get("OriInfoLocation")));
		lJ.add(SeqWrite.jump(1));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.unprotect());
	}

	private void createUniCode(List<JSONObject> lJ) throws DocumentException {
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
	}

	public void read() {
		List<String> strBehSeq;
		strBehSeq = SeqRead.read(strJsonPath);
		for (String string : strBehSeq) {
			Log.info(string);
		}
	}
}
