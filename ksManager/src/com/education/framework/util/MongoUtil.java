package com.education.framework.util;

import net.sf.json.JSONObject;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;
/**
 * mongodb工具类
 * @author yangc
 *
 */
public class MongoUtil {

	/**
	 * OBJECT转换DBObject
	 * @param obj
	 * @return
	 */
	public static DBObject objectToDbObject(Object obj){
		String jstr = JSONObject.fromObject(obj).toString();
		DBObject dbObj = (DBObject)JSON.parse(jstr);
		return dbObj;
	}
	
	public static DBObject jsonObjectToDbObject(JSONObject obj){
		String jstr = obj.toString();
		DBObject dbObj = (DBObject)JSON.parse(jstr);
		return dbObj;
	}
	
	public static Object dbObjectToObject(DBObject dbObj){
		String jstr = JSONObject.fromObject(dbObj).toString();
		Object obj = (Object)JSON.parse(jstr);
		return obj;
	}
}
