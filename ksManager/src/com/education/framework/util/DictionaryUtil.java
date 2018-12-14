package com.education.framework.util;

import java.util.ArrayList;
import java.util.List;

import com.education.framework.application.ApplicationHelper;
import com.education.framework.baseModule.domain.SysDictionary;

/**
 * 描述: 数据字典表工具类
 * 
 * @author   赵恩升
 * date      Jul 16, 2009 
 * 
 * --------------------------------------------------
 * 修 改 人    修改日期          修改描述
 * 赵恩升      Jul 16, 2009     创建
 * --------------------------------------------------
 *
 * @Version  Ver1.0
 * @see      N/A
 * @Since    Ver1.0
 */

public class DictionaryUtil {
	/**
	 * 
	 * 描述: 根据field获取字典容器
	 * 
	 * @param      String field
	 * @return     List<SysDictionary> 所需要的数据字典容器
	 * @throws     N/A
	 * @author     dave
	 * date        Jul 16, 2009
	 * 
	 * ----------------------------------------------
	 * 操作人	      操作日期          操作内容
	 * 赵恩升       Jul 16, 2009     创建
	 * ----------------------------------------------
	 * 
	 * @Version    Ver1.0
	 * @see        N/A
	 * @since      Ver1.0
	 * TODO: N/A
	 */
	public static List<SysDictionary> getSysDictionaryList(String field) {
		List<SysDictionary> dictionaryList = new ArrayList<SysDictionary>();
		List<SysDictionary> allSysDictionaryList = ApplicationHelper.getInstance().getSysDictionaryList();
		for (SysDictionary dictionary : allSysDictionaryList) {
			if (field.equals(dictionary.getFieldId())) {
				dictionaryList.add(dictionary);
			}
		}
		return dictionaryList;
	}
	
	public static String getSysDictionaryLabels(String field) {
		StringBuffer buff = new StringBuffer();
		String lp = "'";
		List<SysDictionary> allSysDictionaryList = ApplicationHelper.getInstance().getSysDictionaryList();
		for (SysDictionary dictionary : allSysDictionaryList) {
			if (field.equals(dictionary.getFieldId())) {
				buff.append(lp).append(dictionary.getLabel());
				buff.append("'");
				lp = ",'";
			}
		}
		return buff.toString();
	}
	
	/**
	 * 
	 * 描述: 根据code取得label
	 * 
	 * @param      String code 索引code，数据字典表某code 
	 * @return     String 数据字典表述值
	 * @author     赵恩升
	 * date        Jul 16, 2009
	 * 
	 * ----------------------------------------------
	 * 操作人	      操作日期          操作内容
	 * 赵恩升       Jul 16, 2009     创建
	 * ----------------------------------------------
	 * 
	 * @Version    Ver1.0
	 * @see        N/A
	 * @since      Ver1.0
	 * TODO: N/A
	 */
	public static String getLabel(String code, String field) {
		String label = "";
		
		if(!CommonTools.isNullString(code)){
			List<SysDictionary> dictionaryList = getSysDictionaryList(field);
			for (SysDictionary dictionary : dictionaryList) {
				if (code.equals(dictionary.getCode())) {
					label = dictionary.getLabel();
					break;
				}
			}
		}
		return label;
	}
	
	public static String getLabels(String codeArr, String field) {
		String label = "";
		if(null == codeArr) return label;
		String[] codes = codeArr.split(",");
		if(null == codes) return label;
		if(codes.length == 0) return label;
		StringBuffer buff = new StringBuffer();
		String lp = "";
		for(String code : codes){
			if(!CommonTools.isNullString(code)){
				List<SysDictionary> dictionaryList = getSysDictionaryList(field);
				for (SysDictionary dictionary : dictionaryList) {
					if (code.equals(dictionary.getCode())) {
						label = dictionary.getLabel();
						buff.append(lp).append(label);
						lp = ",";
					}
				}
			}
		}
		return buff.toString();
	}
	
	public static String getRemark(String field) {
		if(!CommonTools.isNullString(field)){
			List<SysDictionary> dictionaryList = getSysDictionaryList(field);
			if(null != dictionaryList && dictionaryList.size() >0){
				return dictionaryList.get(0).getRemark();
			}
		}
		return "";
	}
	/**
	 * 
	 * 描述: 根据label取得code
	 * 
	 * @param      label 数据字典表述值
	 * @param      field 域
	 * @return     String 索引code
	 * @author     梁贺
	 * date        Aug 3, 2009
	 * 
	 * ----------------------------------------------
	 * 操作人	      操作日期     操作内容
	 * 梁贺     Aug 3, 2009     创建
	 * ----------------------------------------------
	 * 
	 * @Version    Ver1.0
	 * @see        N/A
	 * @since      Ver1.0
	 * TODO: N/A
	 */
	public static String getCode(String label, String field) {
		String code = "";
		if(!CommonTools.isNullString(label)) {
			List<SysDictionary> dictionaryList = getSysDictionaryList(field);
			for(SysDictionary dictionary : dictionaryList) {
				if (label.equals(dictionary.getLabel())) {
					code = dictionary.getCode();
					break;
				}
			}
		}
		return code;
	}
	
	/**
	 * 
	 * 描述: 根据code取得SysDictionary
	 * 
	 * @param      String code 索引code，数据字典表某code 
	 * @return     对应的数据字典对象
	 * @author     杨超
	 * date        2011-2-25
	 */
	public static SysDictionary getSysDictionary(String code, String field) {
		SysDictionary dict = null;
		
		if(!CommonTools.isNullString(code)){
			List<SysDictionary> dictionaryList = getSysDictionaryList(field);
			for (SysDictionary dictionary : dictionaryList) {
				if (code.equals(dictionary.getCode())) {
					dict = dictionary;
					break;
				}
			}
		}
		return dict;
	}
	
}