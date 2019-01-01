package com.edufe.framework.common;

import javax.servlet.http.HttpServletRequest;

/**
 * 工具类
 * @author yangchao
 *
 */
public class Util {

	/**
	 * 字符串去空格方法
	 * @param s
	 * @return
	 */
	public static String trim(String s){
		if(null == s) return "";
		
		String ret = "";
		try{
			ret = s.trim();
		}catch(Exception ex){
		}
		return ret ;
	}
	
	public static String getClientIP(HttpServletRequest request) { 
	    String ip = request.getHeader("x-forwarded-for"); 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getHeader("Proxy-Client-IP"); 
	    } 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getHeader("WL-Proxy-Client-IP"); 

	    } 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getRemoteAddr(); 
	    } 
	    return ip; 
	} 
	
	/**
	 * 描述: 向左补位算法
	 * 
	 * @param      String sequence 序列值
	 * @param      String fill 补位值
	 * @param	   int digit 要求位数
	 */
	public static String leftFill(String sequence, String fill, int digit) {
		if (sequence == null || fill == null || "".equals(fill)) {
			return "";
		} else if (sequence.length() >= digit) {
			return sequence;
		}
		StringBuffer fillBuffer = new StringBuffer();
		fillBuffer.append(sequence);
		while (fillBuffer.length() < digit) {
			fillBuffer.insert(0, fill);
		}
		return fillBuffer.toString();
	}
	
	public static int parseInt(String s) {
		int r = 0 ;
		try{
			r = Integer.parseInt(s);
		}catch(Exception ex){
			
		}
		return r;
	}
}
