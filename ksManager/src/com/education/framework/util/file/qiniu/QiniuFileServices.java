package com.education.framework.util.file.qiniu;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.education.framework.base.BaseServices;
import com.education.framework.util.GUID;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

@Service
public class QiniuFileServices extends BaseServices{

	public String uploadLogo(String bucket , String avatar, String fileName) {
		//构造一个带指定Zone对象的配置类
		Configuration cfg = new Configuration(Zone.zone0());
		//...其他参数参考类注释
		UploadManager uploadManager = new UploadManager(cfg);
		//...生成上传凭证，然后准备上传
		String accessKey = "0v909hThbHMLYAFltPP4NkyDFKvpXaxPn2eLHWm2";
		String secretKey = "5MTh9zulgyVjkEfN8IbG-S5SKwCcvP6JJ7eLiSxv";
//		String bucket = "ls-files-logo";
		//默认不指定key的情况下，以文件内容的hash值作为文件名
		String key = GUID.getGUID() + ".jpg";
		try {
		    byte[] uploadBytes = base64Decoding(avatar);//avatar.getBytes("utf-8");
		    Auth auth = Auth.create(accessKey, secretKey);
		    String upToken = auth.uploadToken(bucket);
		    try {
		        Response response = uploadManager.put(uploadBytes, key, upToken);
		        //解析上传成功的结果
		        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
		        System.out.println(putRet.key);
		        System.out.println(putRet.hash);
		        return "http://p0fpvhmsd.bkt.clouddn.com/" +  putRet.key;
		    } catch (QiniuException ex) {
		    	ex.printStackTrace();
		        Response r = ex.response;
		        System.err.println(r.toString());
		        try {
		            System.err.println(r.bodyString());
		        } catch (QiniuException ex2) {
		            //ignore
		        }
		    }
		} catch (Exception ex) {
		    //ignore
			ex.printStackTrace();
		}

		return "";
	}
	
	public String uploadLogoByte(String bucket , byte[] uploadBytes, String fileName) {
		//构造一个带指定Zone对象的配置类
		Configuration cfg = new Configuration(Zone.zone0());
		//...其他参数参考类注释
		UploadManager uploadManager = new UploadManager(cfg);
		//...生成上传凭证，然后准备上传
		String accessKey = "0v909hThbHMLYAFltPP4NkyDFKvpXaxPn2eLHWm2";
		String secretKey = "5MTh9zulgyVjkEfN8IbG-S5SKwCcvP6JJ7eLiSxv";
//		String bucket = "ls-files-logo";
		//默认不指定key的情况下，以文件内容的hash值作为文件名
		String key = GUID.getGUID() + ".jpg";
		try {
		    Auth auth = Auth.create(accessKey, secretKey);
		    String upToken = auth.uploadToken(bucket);
		    try {
		        Response response = uploadManager.put(uploadBytes, key, upToken);
		        //解析上传成功的结果
		        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
 
		        return "http://p9fu6a8oc.bkt.clouddn.com/" +  putRet.key;
		    } catch (QiniuException ex) {
		    	ex.printStackTrace();
		        Response r = ex.response;
		        System.err.println(r.toString());
		        try {
		            System.err.println(r.bodyString());
		        } catch (QiniuException ex2) {
		            //ignore
		        }
		    }
		} catch (Exception ex) {
		    //ignore
			ex.printStackTrace();
		}

		return "";
	}
	
	/**
	  * 解码图片文件。
	  * @param imageContents 待解码的图片文件的字符串格式。
	  * @return 解码后图片文件的二进制内容。
	  */
	 private byte[] base64Decoding(String imageContents) {
	  if(imageContents != null)
	   return Base64.decodeBase64(imageContents);
	  else return null;
	 }
}
