package com.education.framework.util.exportExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.log4j.Logger;

import com.education.framework.constants.CommonConstants;
import com.education.framework.util.CommonTools;
import com.education.framework.util.DictionaryUtil;

public class ExcelTools {
	private Logger log = Logger.getLogger(ExcelTools.class);
	private static ExcelTools et = null;
	
	public static ExcelTools getInstance(){
		if(null == et){
			et = new ExcelTools();
		}
		return et;
	}

	/**
	 * 导出合计
	 * 
	 * @param fileName
	 * @param titleList
	 * @param resultList
	 * @param response
	 * @throws Exception
	 */
	public void exportExcel(String fileName, List<ExcelHeader> ehList,List resultList, HttpServletResponse response) throws Exception {
		List<String> colList = new ArrayList<String>();
		File file = null;
		try {
			file = new File(fileName + ".xls");
			WritableWorkbook wwb = Workbook.createWorkbook(file);
			WritableSheet ws = wwb.createSheet(fileName, 0);
			int topNum = 0;
			if(null != ehList && ehList.size()>0){
				topNum = 1;
				setHeaderData(ws, 0, ehList);//设置导出文件头
			}
			
			List<List<String>> list = convertList(ehList,resultList);
			for (int iRow = 0; iRow < list.size(); iRow++) {
				colList = (List<String>) list.get(iRow);
				setExcelData(ws, iRow+topNum, colList);
			}
			wwb.write();
			wwb.close();
			fileUpload(fileName + ".xls", file, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public WritableCellFormat createCellFormat(Colour colour , Alignment alignment, int fontSize){
		WritableFont wf_title = new WritableFont(WritableFont.ARIAL, fontSize,  
                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
                jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色
		WritableCellFormat wcf_title = new WritableCellFormat(NumberFormats.TEXT); // 单元格定义  
		wcf_title.setFont(wf_title);
        try {
			wcf_title.setBackground(colour);
			// 设置单元格的背景颜色  
	        wcf_title.setAlignment(alignment); // 设置对齐方式  
	        
	        wcf_title.setVerticalAlignment(VerticalAlignment.CENTRE);
	        wcf_title.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框  
        } catch (WriteException e) {
			e.printStackTrace();
		}
        return wcf_title;
	}
	
	/**
	* @Title: convertList 
	* @Description: 转换LIST数据为List<List<String>>列表 
	* @param  
	* @author yangchao 
	* @date Aug 18, 2011
	* @return List<List<String>>    返回类型 
	 * @throws NoSuchFieldException 
	 * @throws SecurityException 
	* @throws
	 */
	private List<List<String>> convertList(List<ExcelHeader> ehList,List resultList) throws Exception{
		List<List<String>> list = new ArrayList<List<String>>();
		for(Object obj : resultList){
			List<String> listTemp = new ArrayList<String>();
			for(ExcelHeader eh : ehList){
				String[] fmnArr = eh.getFieldsCode().split("\\.");
				Object value = null;
				Object objParam = obj;
				for(String fmn : fmnArr){
					value = callMethod(objParam,CommonTools.convertGetMothodName(fmn));
					objParam = value;
				}
				String v = objToStr(value,eh.getFieldsKind());
				if(null != eh.getDictFieldId() && !"".equals(eh.getDictFieldId())){
					v = DictionaryUtil.getLabel(v, eh.getDictFieldId());
				}
				listTemp.add(v);
			}
			list.add(listTemp);
		}
		return list;
	}
	
	private Object callMethod(Object obj,String fieldsMethodName) throws Exception{
		Class objClass = obj.getClass();
		Method method = objClass.getMethod(fieldsMethodName, new Class[]{});    
		return method.invoke(obj, new Class[]{}); 
	}
	private String objToStr(Object value,String fieldsKind){
		if(null == fieldsKind || "".equals(fieldsKind)){
			//字符类型
			return null == value ? "" : String.valueOf(value);
		}else if(CommonConstants.INT_TYPE.equals(fieldsKind)){
			//整型
			return String.valueOf(value);
		}else if(CommonConstants.DATE_TYPE.equals(fieldsKind)){
			//日期类型
			return CommonTools.date2String((Date)value);
		}
		return "";
	}
	/**
	 * 设置导出头信息
	 * 
	 * @param ws
	 * @param iRow
	 * @param titleList
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	private void setHeaderData(WritableSheet ws, int iRow,List<ExcelHeader> ehList) throws IOException {
		try {
			WritableFont s12_b = new WritableFont(WritableFont.createFont("锟斤拷锟斤拷"), 12, WritableFont.BOLD, false);
			WritableCellFormat f_s12_b = new WritableCellFormat(s12_b);
			f_s12_b.setBackground(Colour.AQUA);
			f_s12_b.setBorder(jxl.write.Border.ALL,jxl.write.BorderLineStyle.THIN);
			f_s12_b.setAlignment(Alignment.CENTRE);
			for (int i = 0; i < ehList.size(); i++) {
				ExcelHeader eh = ehList.get(i);
				if(null != eh.getHeadBackground()) f_s12_b.setBackground(Colour.AQUA);
				Label lblZbbm = new Label(i, iRow, eh.getFieldsName(),f_s12_b);
				ws.setColumnView(i, eh.getWidth());
				ws.addCell(lblZbbm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param ws
	 * @param iRow
	 * @param colList
	 */
	@SuppressWarnings("deprecation")
	private void setExcelData(WritableSheet ws, int iRow,
			List<String> colList) {
		try {
			WritableFont s12_b = new WritableFont(WritableFont
					.createFont("锟斤拷锟斤拷"), 12, WritableFont.BOLD, false);
			WritableCellFormat f_s12_b = new WritableCellFormat(s12_b);
			f_s12_b.setBorder(jxl.write.Border.ALL,
					jxl.write.BorderLineStyle.THIN);
			WritableFont s12_nb = new WritableFont(WritableFont
					.createFont("锟斤拷锟斤拷"), 12, WritableFont.NO_BOLD, false);
			WritableCellFormat f_s12_nb = new WritableCellFormat(s12_nb);
			f_s12_nb.setBorder(jxl.write.Border.ALL,
					jxl.write.BorderLineStyle.THIN);

			for (int i = 0; i < colList.size(); i++) {
				String pra = "";
				if(colList.get(i)!=null){
					pra = colList.get(i).toString();
				}
				Label lblSjlx = new Label(i, iRow,pra);

				ws.addCell(lblSjlx);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 导出模板
	 * 
	 * @param fileName
	 * @param file
	 * @param response
	 * @throws Exception
	 */
	public void fileUpload(String fileName, File file,
			HttpServletResponse response) throws Exception {
		String filelength = String.valueOf(file.length());
		try {
			response.setContentType("application/x-download");
			response.setHeader("Location", fileName);
			response.setHeader("Content-Disposition", "attachment;filename=\""
					+ CommonTools.gBK2ISO_8859_1(fileName));
			response.setContentLength(Integer.parseInt(filelength));
			OutputStream outputStream = response.getOutputStream();
			InputStream inputStream = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int i = -1;
			while ((i = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, i);
			}
			outputStream.flush();
			outputStream.close();
			inputStream.close();
			outputStream = null;
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
  
}