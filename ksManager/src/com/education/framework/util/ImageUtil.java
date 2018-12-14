package com.education.framework.util;

import java.util.ArrayList;
import java.util.List;

public class ImageUtil {
	/**
	 * @Title: findStringImgList 查询content中图片路径
	 * @Description:
	 * @param
	 * @author yangchao
	 * @date Jun 22, 2011
	 * @return List<String> 返回类型
	 * @throws
	 */
	public static List<String> findStringImgList(String content) {
		List<String> list = new ArrayList<String>();
		String temp = content;//.toLowerCase();
		temp = temp.replaceAll("'", "\"");
		while (temp.toLowerCase().indexOf("<img") != -1) {
			int beginIndex = temp.toLowerCase().indexOf("<img");
			int endIndex = temp.toLowerCase().indexOf("/>", beginIndex);
			String imgTemp = temp.substring(beginIndex, endIndex + 2);
			String imgTempLower = imgTemp.toLowerCase();
			if (imgTempLower.indexOf("src") != -1) {
				int beginSrcIndex = imgTempLower.indexOf("src");
				int beginSrcPointIndex = imgTempLower.indexOf("\"", beginSrcIndex);
				int endSrcPointIndex = imgTempLower.indexOf("\"",
						beginSrcPointIndex + 1);
				String srcPath = imgTemp.substring(beginSrcPointIndex + 1,
						endSrcPointIndex);
				list.add(srcPath);
			}
			temp = temp.substring(endIndex + 2, temp.length());
		}
		return list;
	}
}
