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
		// 新增订单
		lJ.add(SeqWrite.keyPress("new"));
		/**
		 * 申报海关
		 */
		// 一个操作步骤-申报地海关
		lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("tcs:EportLocationCode")));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.mouseLeftClick());
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:EportLocationCode", "tcs:EportLocationTypeCode", "001")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		/**
		 * 开始上半部分
		 */
		// 进口口岸
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:EportLocationCode", "tcs:EportLocationTypeCode", "003")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.keyPress("enter"));// 跳过备案号
		// 合同协议号
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:ContractNo")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		// 进口日期
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:ImportExportDate").replaceAll("-", "")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		// 运输方式
		lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("tcs:MeansOfTransportMode")));
		lJ.add(SeqWrite.mouseLeftClick());
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:MeansOfTransportMode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.keyPress("enter"));// 跳过运输攻击名称
		// 航次号
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:MeansOfTransportId")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		// 提运单号
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:BillOfLadingNo")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		// 监督方式
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:TradeMode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		// 征免性质
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:CutMode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.keyPress("enter"));// 跳过纳税单位
		lJ.add(SeqWrite.keyPress("enter"));// 跳过许可证号
		// 起运国地区--------------------需要修改
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:TradeAreaCode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		// 装货港--------------------需要修改
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:TradeAreaCode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		// 境内目的地
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:DestinationCode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		// 成交方式
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:TransactionMode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.keyPress("enter"));// 跳过杂费
		lJ.add(SeqWrite.keyPress("enter"));// 跳过杂费
		lJ.add(SeqWrite.keyPress("enter"));// 跳过杂费

		// 件数
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:No")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		// 包装种类
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:PackingType")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		// 毛重
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:GrossWeight")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		// 净重
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:NetWeight")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));
		// 贸易国地区
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:TradeAreaCode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("enter"));

		/**
		 * 其他事项确认
		 */
		// 进入其他事项确认
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
		 * 填入下半部分商品信息--------------------------------------
		 */
		// 商品名称多重菜单、
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
		// 获取商品附加信息
		String[] strGoodsInfo = XMLUtils.readGoodInfo("tcs:Model");
		for (String string : strGoodsInfo) {
			lJ.add(SeqWrite.copy(string));
			lJ.add(SeqWrite.keyPress("paste"));
			lJ.add(SeqWrite.keyPress("enter"));
		}
		lJ.add(SeqWrite.keyPress("enter"));
		// 后续商品信息
		lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("tcs:Quantity")));
		lJ.add(SeqWrite.mouseLeftClick());
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:Quantity")));
		lJ.add(SeqWrite.sleep(100));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.keyPress("enter"));

		// 成交单位
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:QuantityUnit")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.keyPress("enter"));
		// 成交单价
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:UnitPrice")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.keyPress("enter"));
		// 成交总价-需要双击，退格，去掉总价，重新输入
		lJ.add(SeqWrite.keyPress("backspace"));
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:TotalPrice")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.keyPress("enter"));
		// 币制
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:Currency")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.keyPress("enter"));
		// 法定数量
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:FirstQuantity")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.keyPress("enter"));
		// 法定单位--无法输入
		// lJ.add(SeqWrite.mouseMoveInRadio(PropertiesUtils.get("tcs:FirstUnit")));
		// lJ.add(SeqWrite.mouseLeftClick());
		// lJ.add(SeqWrite.copy(XMLUtils.read("tcs:FirstUnit")));
		// lJ.add(SeqWrite.keyPress("paste"));
		// lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.keyPress("enter"));// 跳过版本号
		lJ.add(SeqWrite.keyPress("enter"));// 跳过货号
		// 最终目的国
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:DestinationCountry")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.keyPress("enter"));
		// 原产国
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:OriginCode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.keyPress("enter"));
		lJ.add(SeqWrite.keyPress("enter"));// 跳过对话框
		// 征免方式
		lJ.add(SeqWrite.copy(XMLUtils.read("tcs:DutyMode")));
		lJ.add(SeqWrite.keyPress("paste"));
		lJ.add(SeqWrite.keyPress("enter"));

		/**
		 * 商品信息结束-----------------------------------------------
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
