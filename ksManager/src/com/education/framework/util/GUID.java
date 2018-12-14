/**
 * GUID.java
 *
 * �� �� �ƣ�GUID
 * ��    �ܣ����guid����
 * 
 * �� ��		   �� �� �� ��		  �� ��		   �� ��		   �� �� �� ��    
 * Ver1.0      July 7, 2009       lidy        Edufe:TD       �� ��
 * 
 * Copyright(c) 2009---2014 DongCaiKeJi All Rights Reserved.
 * LICENSE INFORMATION
 */
package com.education.framework.util;

import java.util.UUID;
/**
 * GUID 
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
