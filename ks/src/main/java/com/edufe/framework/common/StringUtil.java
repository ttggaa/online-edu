package com.edufe.framework.common;

import java.io.*;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 * 通用工具类
 * 
 */
public class StringUtil {
	
	/**
	 * 取得属性字符串集合(包含父类)
	 * 输入：List<User> "id" ","
	 * 返回：user对象id属性的字符串（1,2,3）
	 */
	public static String getPropertysStringAll(List objectList, String propertyName, String sign){
		String returnValue = "";
		String methodName = "get"+toUpperCaseFirstOne(propertyName);
		try {
			if (objectList != null && objectList.size() > 0) {
				for (int i = 0; i < objectList.size(); i++) {
					Object obj = objectList.get(i);
					Class clazz = obj.getClass();
					Method[] methods = clazz.getMethods();
					for (int j = 0; j < methods.length; j++) {
						Method method = methods[j];
						if (methodName.equals(method.getName())) {
				            Object o = method.invoke(obj);
				            if (i == 0) {
				            	returnValue = String.valueOf(o);
							} else {
								returnValue += sign + String.valueOf(o);
							}
				            break;
						}
					}
				}
			}
		} catch (Exception e) {
			returnValue = "";
		}
		return returnValue;
	}
	
	/**
	 * 首字母转大写
	 */
    public static String toUpperCaseFirstOne(String s) {
        if(Character.isUpperCase(s.charAt(0))){
        	return s;
        }
        else {
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }
	
	/**
	 * 判断输入字符串是否为空
	 */
	public static boolean isBlank(String str){
		if (str == null || str.trim().equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * 将输入字符串去空格，如果为null则返回空串
	 */
	public static String trimText(String str){
		if (str == null) {
			return "";
		}
		return str.trim();
	}
	
	/**
	 * 日期格式化转换函数(sql_DATE一>String)
	 * @Description: TODO
	 * @param sql_date 日期
	 * @param formatString 格式化字符串
	 * @return String   
	 * @throws
	 * @author 常东旭 cdx900605@Gmail.com
	 * @date 2014年10月23日
	 */
	public static String dateFormat(java.sql.Date sql_date,String formatString){
		java.util.Date date;
		String result;
		SimpleDateFormat formatter =   new SimpleDateFormat(formatString);
		date = new java.util.Date(sql_date.getTime());
		result = formatter.format(date);
		return result;
	}
	
	/**
	 * 日期格式化转换函数(util_DATE一>String)
	 * @Description: TODO
	 * @param date 日期
	 * @param formatString 格式化字符串
	 */
	public static String dateFormat(java.util.Date date, String formatString){
		SimpleDateFormat formatter = new SimpleDateFormat(formatString);
		return formatter.format(date);
	}
	
	public static String dateFormat(Timestamp timestamp, String formatString){
		SimpleDateFormat formatter = new SimpleDateFormat(formatString);
		return formatter.format(timestamp);
	}
	
	/**
	 * 日期格式化转换函数(String一>sql_DATE)
	 * @Description: TODO
	 * @param dateString
	 * @param formatString
	 * @return Date   
	 * @throws ParseException
	 * @throws
	 * @author 常东旭 cdx900605@Gmail.com
	 * @date 2014年10月23日
	 */
	public static Date dateStringFormat(String dateString,String formatString) throws ParseException {
		Date result;
		SimpleDateFormat formatter =   new SimpleDateFormat(formatString);
		java.util.Date date;
			date = formatter.parse(dateString);
			result = new Date(date.getTime());
		return result;
	}
	
	public static String getRandomPwd(){
		  Random rd = new Random();
		  String pwd="";
		  int getNum;
		  while (pwd.length()<6) {
			   getNum = Math.abs(rd.nextInt())%10;
			   pwd += getNum;
		  } 
		  return pwd;
	}

	/**
	 * 类说明：URL参数解析
	 */
	public static Map<String, String> analysisURL(String url){
		Map<String, String> paramMap  = new HashMap<String, String>();
		if (!isBlank(url)) {
			if (url.indexOf("?") != -1) {
				url = url.substring(url.indexOf('?') + 1);
			}
			String paramaters[] = url.split("&");
            for (String param : paramaters) {
                String values[] = param.split("=");
                paramMap.put(values[0], values[1]);
            }
		}
		return paramMap;
	}
	
	/**
	 * 将数组转化成string，并通过opt连接
	 * @param array
	 * @param opt
	 * @return
	 */
	public static String a2s(String[] array, String opt) {
		String string = array[0];
		for (int i = 1; i < array.length; i++) {
			string += opt + array[i];
		}
		return string;
	}
	
	/**
	 * 将分钟格式化 
	 * @return
	 */
	public static String getStringHours(int minutes) {
		int h = minutes / 60;
		int m = minutes % 60;
		if (h == 0) {
			return m + " 分钟";
		} else {
			return h + "小时 " + m + "分钟";
		}
	}

	/**
	 * 序列化对象
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			if (object != null){
				baos = new ByteArrayOutputStream();
				oos = new ObjectOutputStream(baos);
				oos.writeObject(object);
				return baos.toByteArray();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 反序列化对象
	 * @param bytes
	 * @return
	 */
	public static Object unserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		try {
			if (bytes != null && bytes.length > 0){
				bais = new ByteArrayInputStream(bytes);
				ObjectInputStream ois = new ObjectInputStream(bais);
				return ois.readObject();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 转换为字节数组
	 * @param bytes
	 * @return
	 */
	public static String toString(byte[] bytes){
		try {
			return new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	/**
	 * 转换为字节数组
	 * @param str
	 * @return
	 */
	public static byte[] getBytes(String str){
		if (str != null){
			try {
				return str.getBytes(str);
			} catch (UnsupportedEncodingException e) {
				return null;
			}
		}else{
			return null;
		}
	}
}