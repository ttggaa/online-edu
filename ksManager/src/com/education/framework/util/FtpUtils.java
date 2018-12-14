package com.education.framework.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;

public class FtpUtils {
	
	public static String buildFtpFileName(String fileName){
		if(null == fileName || "".equals(fileName)) return "";
		String extName = getFileExtName(fileName);
		String s = GUID.getGUID() + "." + extName;
		return s;
	}
	
	public static String getFileExtName(String fileName){
		if(null == fileName || "".equals(fileName)) return "";
		String[] arr = fileName.split("\\.");
		String s = arr[arr.length-1];
		return s;
	}
	
	public static String uploadFile(String path, String filename, InputStream input) {
		String ftpFileName = buildFtpFileName(filename);
		String url = DealProperty.setPro().getProperty("ftp.sever.ip");
		String username = DealProperty.setPro().getProperty("ftp.sever.username");
		String password = DealProperty.setPro().getProperty("ftp.sever.passwd");
		int port = 21;
	    // 初始表示上传失败
		boolean success = false;
		// 创建FTPClient对象
		FTPClient ftp = new FTPClient();
		ftp.setControlEncoding("GBK");
		try {
			int reply;
			// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.connect(url, port);
			// 登录ftp
			if (ftp.login(username, password)){
				FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
				conf.setServerLanguageCode("zh");
				ftp.configure(conf);
				// 返回的值是不是230，如果是，表示登陆成功
				reply = ftp.getReplyCode();
				// 以2开头的返回值就会为真
				if (!FTPReply.isPositiveCompletion(reply)) {
					ftp.disconnect();
					return "";
				}
				//设置被动模式
				ftp.enterLocalPassiveMode();
				//设置以二进制方式传输  
				ftp.setFileType(FTPClient.BINARY_FILE_TYPE);  
				// 转到指定上传目录
				success = ftp.changeWorkingDirectory(path);
				if(!success){
					return "";
				}
				// 将上传文件存储到指定目录
				success = ftp.storeFile(ftpFileName, input);
				if(!success){
					return "";
				}
				// 关闭输入流
				input.close();
				// 退出ftp
				ftp.logout();
				// 表示上传成功
				success = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception wa) {
			wa.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return success ? ftpFileName : "";
	}
	
	public static void main(String[] args) {
		try {  
			FileInputStream in=new FileInputStream(new File("D:\\test.jpg"));  
			String flag = FtpUtils.uploadFile("/images", "test.jpg", in);  
			System.out.println(flag);
		} catch (FileNotFoundException e) {  
			e.printStackTrace();  
		}  
	}
}