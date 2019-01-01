/**
 * Md5Util.java
 *
 * 类 名 称：Md5Util
 * 功    能：MD5加密方法类
 * 
 * 版 本		   创 建 日 期		  作 者		   部 门		   创 建 类 容    
 * Ver1.0      July 7, 2009       夏文涛        Edufe:TD       新 建
 * 
 * Copyright(c) 2009---2014 DongCaiKeJi All Rights Reserved.
 * LICENSE INFORMATION
 */
package com.edufe.framework.common;

import java.security.MessageDigest;
/**
 * 对字符串按MD5方式进行加密
 *  
 * @author   xiawt
 * @Version  Ver1.0
 */
public class Md5Util {
	private final static String[] hexDigits = {
	      "0", "1", "2", "3", "4", "5", "6", "7",
	      "8", "9", "a", "b", "c", "d", "e", "f"};

	  /**
	   * 转换字节??组为16进制字串
	   * @param b 字节????
	   * @return 16进制字串
	   */

	  public static String byteArrayToHexString(byte[] b) {
	    StringBuffer resultSb = new StringBuffer();
	    for (int i = 0; i < b.length; i++) {
	      resultSb.append(byteToHexString(b[i]));
	    }
	    return resultSb.toString();
	  }

	  private static String byteToHexString(byte b) {
	    int n = b;
	    if (n < 0)
	      n = 256 + n;
	    int d1 = n / 16;
	    int d2 = n % 16;
	    return hexDigits[d1] + hexDigits[d2];
	  }

	  public static String MD5Encode(String origin) {
	    String resultString = null;
	    try {
	      MessageDigest md = MessageDigest.getInstance("MD5");
	      resultString = byteArrayToHexString(md.digest((origin==null?"":origin).getBytes()));
	    }
	    catch (Exception ex) {
	    	ex.printStackTrace();
	    }
	    return resultString.toUpperCase();
	  }
}
