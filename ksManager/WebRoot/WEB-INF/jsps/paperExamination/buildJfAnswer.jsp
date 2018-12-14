<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/includes/common-import.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>${systemName}</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="${resPath}media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
	<script src="${resPath}media/js/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
  </head>
  
  <body>
    计算分析题答案生成器工具. <br /><br />
    <div id="questionContent" class="span10" style="margin-left: 0px;">
		<div style="margin-left: 0px;width:650px;">
			<textarea id="examinationContentView" class="span5 m-wrap" rows="5" readonly="readonly" style="margin-left: 0px;width:650px;"></textarea>
		</div>
		<div id="descDiv" style="margin-left: 0px;width:650px;">
			试题描述：<br />
			<textarea id="examinationDescriptionView" class="span5 m-wrap" rows="5" readonly="readonly" style="margin-left: 0px;width:650px;"></textarea>
		</div>
	</div>
	<br />
    <form id="form1">
    	<table border="1" width="100%">
    		<tr>
    		   <td width="10%">分类</td>
    		   <td>答案</td>
    		</tr>
    		<%for(int i=0;i<5;i++){ %>
    		<tr>
    		   <td>
    		   	  <select id="jfOptionKind<%=i%>" onchange="optionKindChange(<%=i%>);">
    		   	  	<option value=""></option>
    		   	  	<option value="fl">分录</option>
    		   	  	<option value="js">计算</option>
    		   	  </select>
    		   </td>
    		   <td>
    		   	   <table id="jfOptionFL<%=i%>" style="display: none;" > 
    		   	        <tr>
    		   	           <td>方向</td>
			    		   <td>科目</td>
			    		   <td>金额</td>
    		   	        </tr>
    		   	        <%for(int j=0;j<5;j++){ %>
	    		   	    <tr>
			    		   <td>
							  <select id="jfOptionDire<%=i%>_<%=j%>">
			    		   	  	<option value="借" selected="selected">借</option>
			    		   	  	<option value="贷">贷</option>
			    		   	  </select>
						   </td>
			    		   <td>
						   	  <input type="text" id="jfOptionAccount<%=i%>_<%=j%>" />
						   </td>
			    		   <td>
			    		   	  <input type="text" id="jfOptionValue<%=i%>_<%=j%>" />
			    		   </td>
			    		</tr>
			    		<%} %>
	    		   </table>
	    		   <table id="jfOptionJS<%=i%>" style="display: none;"> 
	    		   		<tr>
			    		   <td>金额</td>
			    		   <td><input type="text" id="jfOptionValue<%=i%>" /></td>
    		   	        </tr>
	    		   </table>
    		   </td>
    		</tr>
    		<%} %>
    	</table>
    	
    	<div>
    		<h2>会计科目参照表</h2>
    		1001 库存现金<br />
			1002 银行存款<br />
			1002-01 工行存款<br />
			1002-02 中国银行存款<br />
			1003 存放中央银行款项<br />
			1011 存放同业<br />
			1012 其他货币资金<br />
			1021 结算备付金<br />
			1031 存出保证金<br />
			1101 交易性金融资产<br />
			1111 买入返售金融资产<br />
			1121 应收票据<br />
			1122 应收账款<br />
			1123 预付账款<br />
			1131 应收股利<br />
			1132 应收利息<br />
			1201 应收代位追偿款<br />
			1211 应收分保账款<br />
			1212 应收分保合同准备金<br />
			1221 其他应收款<br />
			1231 坏账准备<br />
			1301 贴现资产<br />
			1302 拆出资金<br />
			1303 贷款<br />
			1304 贷款损失准备<br />
			1311 代理兑付证券<br />
			1321 代理业务资产<br />
			1401 材料采购<br />
			1401-01 A材料<br />
			1401-02 B材料<br />
			1402 在途物资<br />
			1403 原材料<br />
			1404 材料成本差异<br />
			1405 库存商品<br />
			1406 发出商品<br />
			1407 商品进销差价<br />
			1408 委托加工物资<br />
			1411 周转材料<br />
			1421 消耗性生物资产<br />
			1431 贵金属<br />
			1441 抵债资产<br />
			1451 损余物资<br />
			1461 融资租赁资产<br />
			1471 存货跌价准备<br />
			1501 持有至到期投资<br />
			1502 持有至到期投资减值准备<br />
			1503 可供出售金融资产<br />
			1511 长期股权投资<br />
			1512 长期股权投资减值准备<br />
			1521 投资性房地产<br />
			1531 长期应收款<br />
			1532 未实现融资收益<br />
			1541 存出资本保证金<br />
			1601 固定资产<br />
			1602 累计折旧<br />
			1603 固定资产减值准备<br />
			1604 在建工程<br />
			1605 工程物资<br />
			1606 固定资产清理<br />
			1611 未担保余值<br />
			1621 生产性生物资产<br />
			1622 生产性生物资产累计折旧<br />
			1623 公益性生物资产<br />
			1631 油气资产<br />
			1632 累计折耗<br />
			1701 无形资产<br />
			1702 累计摊销<br />
			1703 无形资产减值准备<br />
			1711 商誉<br />
			1801 长期待摊费用<br />
			1811 递延所得税资产<br />
			1821 独立账户资产<br />
			1901 待处理财产损溢<br />
			2001 短期借款<br />
			2002 存入保证金<br />
			2003 拆入资金<br />
			2004 向中央银行借款<br />
			2011 吸收存款<br />
			2012 同业存放<br />
			2021 贴现负债<br />
			2101 交易性金融负债<br />
			2111 卖出回购金融资产款<br />
			2201 应付票据<br />
			2202 应付账款<br />
			2203 预收账款<br />
			2211 应付职工薪酬<br />
			2221 应交税费<br />
			222101 应交税费—应交增值税<br />
			22210101 应交税费—应交增值税—进项税额<br />
			22210102 应交税费—应交增值税—销项税额<br />
			22210103 应交税费—应交增值税—进项税额转出<br />
			22210104 应交税费—应交增值税—已交税金<br />
			222102 应交税费—应交消费税<br />
			222103 应交税费—应交营业税<br />
			222104 应交税费—应交所得税<br />
			222105 应交税费—应交城市维护建设税<br />
			222106 应交税费—应交教育费附加<br />
			2231 应付利息<br />
			2232 应付股利<br />
			2241 其他应付款<br />
			2251 应付保单红利<br />
			2261 应付分保账款<br />
			2311 代理买卖证券款<br />
			2312 代理承销证券款<br />
			2313 代理兑付证券款<br />
			2314 代理业务负债<br />
			2401 递延收益<br />
			2501 长期借款<br />
			2502 应付债券<br />
			2601 未到期责任准备金<br />
			2602 保险责任准备金<br />
			2611 保户储金<br />
			2621 独立账户负债<br />
			2701 长期应付款<br />
			2702 未确认融资费用<br />
			2711 专项应付款<br />
			2801 预计负债<br />
			2901 递延所得税负债<br />
			3001 清算资金往来<br />
			3002 货币兑换<br />
			3101 衍生工具<br />
			3201 套期工具<br />
			3202 被套期项目<br />
			4001 实收资本<br />
			4002 资本公积<br />
			4101 盈余公积<br />
			4102 一般风险准备<br />
			4103 本年利润<br />
			4104 利润分配<br />
			4201 库存股<br />
			5001 生产成本<br />
			5101 制造费用<br />
			5201 劳务成本<br />
			5301 研发支出<br />
			5401 工程施工<br />
			5402 工程结算<br />
			5403 机械作业<br />
			6001 主营业务收入<br />
			6011 利息收入<br />
			6021 手续费及佣金收入<br />
			6031 保费收入<br />
			6041 租赁收入<br />
			6051 其他业务收入<br />
			6061 汇兑损益<br />
			6101 公允价值变动损益<br />
			6111 投资收益<br />
			6201 摊回保险责任准备金<br />
			6202 摊回赔付支出<br />
			6203 摊回分保费用<br />
			6301 营业外收入<br />
			6401 主营业务成本<br />
			6402 其他业务成本<br />
			6403 营业税金及附加<br />
			6411 利息支出<br />
			6421 手续费及佣金支出<br />
			6501 提取未到期责任准备金<br />
			6502 提取保险责任准备金<br />
			6511 赔付支出<br />
			6521 保单红利支出<br />
			6531 退保金<br />
			6541 分出保费<br />
			6542 分保费用<br />
			6601 销售费用<br />
			6602 管理费用<br />
			6603 财务费用<br />
			6604 勘探费用<br />
			6701 资产减值损失<br />
			6711 营业外支出<br />
			6801 所得税费用<br />
			6901 以前年度损益调整<br />
    	</div>
    </form>
  </body>
  <script>
  	var api = frameElement.api, W = api.opener;
  	function ok()
  	{
  		//fl^借^银行存款^1106000;fl^贷^银行存款^25000♂js^1000♂
  		var s = "";
  		var lp = "";
  		for(var i=0;i<5;i++){
  			if($("#jfOptionKind" + i).val() == "fl"){
  				s += lp;
  				s += $("#jfOptionKind" + i).val() + "^";
  				var lp2 = "";
  				for(var j=0;j<5;j++){
  					if($("#jfOptionAccount" + i+"_"+j).val() != "" && $("#jfOptionValue" + i+"_"+j).val() != ""){
	  	  	  			s += lp2 + $("#jfOptionDire" + i+"_"+j).val() + "^";
	  	  	  			s += $("#jfOptionAccount" + i+"_"+j).val() + "^";
	  	  	  			s += $("#jfOptionValue" + i+"_"+j).val();
	  	  	  			lp2 = ";";
  					}
  				}
  			}else if($("#jfOptionKind" + i).val() == "js"){
  				if($("#jfOptionValue" + i).val() != ""){
  					s += lp;
  					s += $("#jfOptionKind" + i).val() + "^";
  					s += $("#jfOptionValue" + i).val();
  				}
  			}
  			lp = "♂";
  		}
  	    return s;
  	}
  	function init(quesContent,answer,quesDesc){
  		if(answer == "") return ;
  		$("#examinationContentView").val(quesContent);
  		$("#examinationDescriptionView").val(quesDesc);
  		if(quesDesc == ""){
  			$("#descDiv").hide();
  		}else{
  			$("#descDiv").show();
  		}
  		var jfArr = answer.split("♂");
  		for(var i=0;i<jfArr.length;i++){
  			var jfType = jfArr[i].substring(0,2);
  			if(jfType == "fl"){
  				$("#jfOptionKind" + i).val("fl");
  				var flArr = jfArr[i].substring(3).split(";");
  				for(var j=0;j<flArr.length;j++){
  					var flOptionArr = flArr[j].split("^");
  					$("#jfOptionDire" + i+"_"+j).val(flOptionArr[0]);
  					$("#jfOptionAccount" + i+"_"+j).val(flOptionArr[1]);
  					$("#jfOptionValue" + i+"_"+j).val(flOptionArr[2]);
  				}
  			}else if(jfType == "js"){
  				$("#jfOptionKind" + i).val("js");
  				$("#jfOptionValue" + i).val(jfArr[i].substring(3));
  			}else{
  				$("#jfOptionKind" + i).val("");
  			}
  			optionKindChange(i);
  		}
  	}
  	
  	function optionKindChange(n){
  		if($("#jfOptionKind" + n).val() == ""){
  			$("#jfOptionFL" + n).hide();
  			$("#jfOptionJS" + n).hide();
  		}else if($("#jfOptionKind" + n).val() == "fl"){
  			$("#jfOptionFL" + n).show();
  			$("#jfOptionJS" + n).hide();
  		}else if($("#jfOptionKind" + n).val() == "js"){
  			$("#jfOptionFL" + n).hide();
  			$("#jfOptionJS" + n).show();
  		}
  	}
  </script>
</html>
