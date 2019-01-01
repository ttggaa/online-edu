package com.edufe.framework.common;

public class RandomUtil {
	/**
	 * 生成不重复随机字符串包括字母数字
	 *
	 * @param len
	 * @return
	 */
	public static String generateRandomStr(int len) {
	    //字符源，可以根据需要删减
	    String generateSource = "0123456789abcdefghigklmnopqrstuvwxyz";
	    String rtnStr = "";
	    for (int i = 0; i < len; i++) {
	        //循环随机获得当次字符，并移走选出的字符
	        String nowStr = String.valueOf(generateSource.charAt((int) Math.floor(Math.random() * generateSource.length())));
	        rtnStr += nowStr;
	        generateSource = generateSource.replaceAll(nowStr, "");
	    }
	    return rtnStr;
	}
	 
	public static void main(String[] args) {
	    for (int i = 0; i < 10; i++) {
	        System.out.println(generateRandomStr(8));
	    }
	}
}
