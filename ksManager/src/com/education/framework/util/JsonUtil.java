package com.education.framework.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * json工具类
 * @author yangc
 *
 */
public class JsonUtil {

	
	public static String parseJson(List<Map<String,Object>> list){
		JSONArray arr = JSONArray.fromObject(list);
		String jstr = arr.toString();
		return jstr.replaceAll("\"", "'");  
	}
	public static String objectParseJson(List list){
		JSONArray arr = JSONArray.fromObject(list);
//		Iterator iteratorArray=arr.iterator();
//		while(iteratorArray.hasNext()){
//			JSONObject json=(JSONObject)iteratorArray.next();
//			System.out.println(json.toString());
//		}
//		JSONObject json = arr.toJSONObject(arr);
//		String jstr = json.toString();
//		jstr = jstr.replaceAll("{\"", "");
//		jstr = jstr.replaceAll(",\"", "");
//		jstr = jstr.replaceAll("\":", "");
		return arr.toString();  
	}
	
	public static String parseJson(Map<String,Object> map){
		JSONObject obj = JSONObject.fromObject(map);
		return obj.toString();  
	}
}
