/**
 * GUID.java
 *
 * 类 名 称：GUID
 * 功    能：生成guid码类
 * 
 * 版 本		   创 建 日 期		  作 者		   部 门		   创 建 类 容    
 * Ver1.0      July 7, 2009       lidy        Edufe:TD       新 建
 * 
 * Copyright(c) 2009---2014 DongCaiKeJi All Rights Reserved.
 * LICENSE INFORMATION
 */
package com.edufe.framework.common;

import java.util.UUID;
/**
 * 生成GUID码 
 * @author   lidy
 * @Version  Ver1.0
 */
public class GUID {
   public static String getGUID()
   {
	   UUID uuid = UUID.randomUUID();
	   return uuid.toString();
   }
}
