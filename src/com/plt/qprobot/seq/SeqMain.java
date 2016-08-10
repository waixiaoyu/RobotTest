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
	public static long sleep_time = 50;// 可以通过缩短休眠时间来提高速度

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

		// 新增订单
		lJ.add(SeqWrite.keyPress("new"));
		/**
		 * 申报海关
		 */
		// 一个操作步骤-申报地海关
		lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("CustomsDeclaration")));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.mouseLeftClick());
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.copy(XMLUtils.read("CustomsDeclaration")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		/**
		 * 暂存
		 */
		lJ.add(SeqWrite.keyPress("save"));
		// 检测是否暂存成功
		lJ.add(SeqWrite.checkColor(PropertiesUtils.get("TempSaveColorLocation")));
		lJ.add(SeqWrite.keyPress("enter"));

		/**
		 * 开始上半部分
		 */
		// 进口口岸
		lJ.add(SeqWrite.copy(XMLUtils.read("ImportPort")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.keyPress("enter"));// 跳过备案号
		// 合同协议号
		lJ.add(SeqWrite.copy(XMLUtils.read("ContractNo")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		// 进口日期
		// lJ.add(SeqWrite.copy(XMLUtils.read("ImportExportDate").replaceAll("-",
		// "")));
		// lJ.add(SeqWrite.keyPress("paste"));
		// lJ.add(SeqWrite.sleep(sleep_time));
		// lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.keyPress("enter"));// 跳过进口日期
		// 收发货人
		lJ.add(SeqWrite.copy(XMLUtils.read("Consignee")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		// 检测收发货人是否出现
		lJ.add(SeqWrite.checkColor(PropertiesUtils.get("ConsigneeColorLocation")));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.keyPress("enter"));
		// 消费使用单位
		lJ.add(SeqWrite.copy(XMLUtils.read("ConsumerUnit")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		// 检测消费使用单位是否出现
		lJ.add(SeqWrite.checkColor(PropertiesUtils.get("ConsumerUnitColorLocation")));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.keyPress("enter"));
		// 申报单位
		lJ.add(SeqWrite.copy(XMLUtils.read("DeclarationUnit")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		// 检测消费使用单位是否出现
		lJ.add(SeqWrite.checkColor(PropertiesUtils.get("DeclarationUnitColorLocation")));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.keyPress("enter"));
		// 运输方式
		lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("MeansOfTransportMode")));
		lJ.add(SeqWrite.mouseLeftClick());
		lJ.add(SeqWrite.copy(XMLUtils.read("MeansOfTransportMode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.keyPress("enter"));// 跳过运输攻击名称
		// 航次号
		lJ.add(SeqWrite.copy(XMLUtils.read("MeansOfTransportId")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		// 提运单号
		lJ.add(SeqWrite.copy(XMLUtils.read("BillOfLadingNo")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		// 监督方式
		lJ.add(SeqWrite.copy(XMLUtils.read("TradeMode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		// 征免性质
		lJ.add(SeqWrite.copy(XMLUtils.read("CutMode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.keyPress("enter"));// 跳过纳税单位
		lJ.add(SeqWrite.keyPress("enter"));// 跳过许可证号
		// 起运国
		lJ.add(SeqWrite.copy(XMLUtils.read("PurchaseCountry")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		// 装货港
		lJ.add(SeqWrite.copy(XMLUtils.read("LoadingPort")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		// 境内目的地
		lJ.add(SeqWrite.copy(XMLUtils.read("DestinationCode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		// 成交方式
		String sTM = XMLUtils.read("TransactionMode");
		lJ.add(SeqWrite.copy(XMLUtils.read("TransactionMode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		if (sTM.equals("1")) {
			// 杂费1
			lJ.add(SeqWrite.copy(XMLUtils.read("ExtrasMark")));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			// 杂费2
			lJ.add(SeqWrite.copy(XMLUtils.read("ExtrasRate")));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			// 杂费3
			lJ.add(SeqWrite.copy(XMLUtils.read("ExtrasCurrency")));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
		} else {
			// 运费标记
			lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("FreightMark")));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.mouseLeftClick());
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.copy(XMLUtils.read("FreightMark")));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			// 运费率
			lJ.add(SeqWrite.copy(XMLUtils.read("FreightRate")));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			// 运费币制
			lJ.add(SeqWrite.copy(XMLUtils.read("FreightCurrency")));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			// 保费1
			lJ.add(SeqWrite.copy(XMLUtils.read("InsuranceMark")));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			// 保费2
			lJ.add(SeqWrite.copy(XMLUtils.read("InsuranceRate")));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			// 保费3
			lJ.add(SeqWrite.copy(XMLUtils.read("InsuranceCurrency")));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			// 杂费1
			lJ.add(SeqWrite.copy(XMLUtils.read("ExtrasMark")));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			// 杂费2
			lJ.add(SeqWrite.copy(XMLUtils.read("ExtrasRate")));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			// 杂费3
			lJ.add(SeqWrite.copy(XMLUtils.read("ExtrasCurrency")));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
		}
		// 件数
		lJ.add(SeqWrite.copy(XMLUtils.read("Packages")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		// 包装种类
		lJ.add(SeqWrite.copy(XMLUtils.read("PackingType")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		// 毛重
		lJ.add(SeqWrite.copy(XMLUtils.read("GrossWeight")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		// 净重
		lJ.add(SeqWrite.copy(XMLUtils.read("NetWeight")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		// 贸易国地区
		lJ.add(SeqWrite.copy(XMLUtils.read("TradeAreaCode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));
		/**
		 * 承诺事项
		 */
		// 进入其他事项确认
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
		 * 可能有多个集装箱
		 */
		for (int i = 0; i < XMLUtils.readAll("Container").size(); i++) {
			// 集装箱号
			lJ.add(SeqWrite.copy(XMLUtils.readAll("ContainerNo").get(i).getStringValue()));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			// 集装箱规格
			lJ.add(SeqWrite.copy(XMLUtils.readAll("ContainerSize").get(i).getStringValue()));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			// 自重
			lJ.add(SeqWrite.copy(XMLUtils.readAll("DeadWeight").get(i).getStringValue()));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
		}

		// 报关单类型
		lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("DeclarationType")));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.mouseLeftClick());
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.copy(XMLUtils.read("DeclarationType")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(sleep_time));
		lJ.add(SeqWrite.keyPress("enter"));

		/**
		 * 填入下半部分商品信息-------------------------------------- 可能有多个商品
		 */
		for (int i = 0; i < XMLUtils.readAll("GoodsInformation").size(); i++) {
			// 商品名称多重菜单、
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
			// 获取商品附加信息
			String[] strGoodsInfo = XMLUtils.readGoodInfoAll("Model").get(i).getStringValue().split("\\|");
			for (String string : strGoodsInfo) {
				lJ.add(SeqWrite.copy(string));
				lJ.add(SeqWrite.keyPress("paste"));
				lJ.add(SeqWrite.keyPress("enter"));
			}
			lJ.add(SeqWrite.keyPress("enter"));
			// 后续商品信息
			lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("Quantity")));
			lJ.add(SeqWrite.mouseLeftClick());
			lJ.add(SeqWrite.copy(XMLUtils.readAll("Quantity").get(i).getStringValue()));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.keyPress("enter"));
			// 成交单位
			lJ.add(SeqWrite.copy(XMLUtils.readAll("QuantityUnit").get(i).getStringValue()));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.keyPress("enter"));
			// 成交单价
			lJ.add(SeqWrite.copy(XMLUtils.readAll("UnitPrice").get(i).getStringValue()));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.keyPress("enter"));
			// 成交总价-需要双击，退格，去掉总价，重新输入
			lJ.add(SeqWrite.keyPress("backspace"));
			lJ.add(SeqWrite.copy(XMLUtils.readAll("TotalPrice").get(i).getStringValue()));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.keyPress("enter"));
			// 币制
			lJ.add(SeqWrite.copy(XMLUtils.readAll("Currency").get(i).getStringValue()));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.keyPress("enter"));
			// 法定数量
			lJ.add(SeqWrite.copy(XMLUtils.readAll("FirstQuantity").get(i).getStringValue()));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.keyPress("enter"));
			// 法定单位--无法输入
			// lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("FirstUnit")));
			// lJ.add(SeqWrite.mouseLeftClick());
			// lJ.add(SeqWrite.copy(XMLUtils.read("FirstUnit")));
			// lJ.add(SeqWrite.keyPress("paste"));
			// lJ.add(SeqWrite.keyPress("enter"));
			lJ.add(SeqWrite.keyPress("enter"));// 跳过版本号
			lJ.add(SeqWrite.keyPress("enter"));// 跳过货号
			// 最终目的国
			lJ.add(SeqWrite.copy(XMLUtils.readAll("DestinationCountry").get(i).getStringValue()));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.keyPress("enter"));
			// 原产国
			lJ.add(SeqWrite.copy(XMLUtils.readAll("OriginCode").get(i).getStringValue()));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.keyPress("enter"));
			lJ.add(SeqWrite.keyPress("enter"));// 跳过对话框
			// 征免方式
			lJ.add(SeqWrite.copy(XMLUtils.readAll("DutyMode").get(i).getStringValue()));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.keyPress("enter"));

		}

		/**
		 * 单证
		 */
		lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("AttachedDocumentCode")));
		lJ.add(SeqWrite.mouseLeftClick());
		for (int i = 0; i < XMLUtils.readAll("AttachDocument").size(); i++) {
			// 随附单证代码
			lJ.add(SeqWrite.copy(XMLUtils.readAll("AttachedDocumentCode").get(i).getStringValue()));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
			// 随附单证编号
			lJ.add(SeqWrite.copy(XMLUtils.readAll("AttachedDocumentNo").get(i).getStringValue()));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.sleep(sleep_time));
			lJ.add(SeqWrite.keyPress("enter"));
		}

		/**
		 * 商品信息结束-----------------------------------------------
		 */

		/**
		 * 暂存
		 */
		lJ.add(SeqWrite.keyPress("save"));
		// 检测是否暂存成功
		lJ.add(SeqWrite.checkColor(PropertiesUtils.get("TempSaveColorLocation")));
		lJ.add(SeqWrite.keyPress("enter"));
		/**
		 * 获取统一编码
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

		// 生成json
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
