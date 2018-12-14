package com.education.framework.util;

import java.util.Random;

public class RandomNumUtil {    
	private RandomNumUtil(){    
		
	}    
	/*   
	* 取得RandomNumUtil实例   
	*/    
	public static RandomNumUtil Instance(){    
		return new RandomNumUtil();    
	} 
	  
	/**
	 * 
	 * 描述: 随机生成随机数字符串（按位数）
	 * 
	 * @param      N/A
	 * @return     N/A
	 * @throws     N/A
	 * @author     赵恩升
	 * date        Aug 24, 2009
	 * 
	 * ----------------------------------------------
	 * 操作人	      操作日期       操作内容
	 * 赵恩升       Aug 24, 2009         创建
	 * ----------------------------------------------
	 * 
	 * @Version    Ver1.0
	 * @see        N/A
	 * @since      Ver1.0
	 */
	public static String getRandomString(int digit) {
		// 生成随机类    
		Random random = new Random();  
		// 取随机产生的认证码(n位数字)    
		String randomStr = "";    
		for (int i=0;i<digit;i++){    
			String rand=String.valueOf(random.nextInt(10));    
			randomStr += rand; 
		}
		return randomStr;
	}
	
	public static void main(String[] args) {
		System.out.println(RandomNumUtil.Instance().getRandomString(6));
	}
}