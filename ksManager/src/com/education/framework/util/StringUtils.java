package com.education.framework.util;

import java.lang.reflect.Method;
import java.util.List;

public class StringUtils {
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

}
