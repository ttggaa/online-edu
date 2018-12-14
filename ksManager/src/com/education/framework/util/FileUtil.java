package com.education.framework.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

public class FileUtil {

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
	
	public static String getCurrentDate() {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(new Date());
	}

	public static String readFileContent(String filePath) {
		if (null == filePath || "".equals(filePath))
			return "";
		String ret = "";
		File file1 = new File(filePath);
		FileReader fr = null;
		StringBuffer sbuff = new StringBuffer();
		BufferedReader inFile = null;
		try {
			fr = new FileReader(file1);
			inFile = new BufferedReader(fr);
			String str;
			while ((str = inFile.readLine()) != null) {
				sbuff.append(str).append("\\n");
			}
			ret = sbuff.toString();
			inFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inFile.close();
				fr.close();
			} catch (IOException e) {
				inFile = null;
				fr = null;				
			}
		}
		return ret;
	}
	
	/**
	 * 创建目录
	 * 
	 * @param dirPath
	 * @return
	 */
	public static boolean mkdirectory(String dirPath) {
		boolean r = true;
		try {
			File file = new File(dirPath);
			if (!file.getParentFile().exists()) {
				boolean bret = mkdirectory(file.getParentFile().getPath());
				file.getParentFile().mkdir();
			}
		} catch (Exception e) {
			r = false;
		}
		return r;
	}
	
	/**
     * 删除空目录
     * @param dir 将要删除的目录路径
     */
    public static void doDeleteEmptyDir(String dir) {
        boolean success = (new File(dir)).delete();
        if (success) {
            System.out.println("Successfully deleted empty directory: " + dir);
        } else {
            System.out.println("Failed to delete empty directory: " + dir);
        }
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
    
    public static boolean deleteFile(String filePath) {
    	File file = new File(filePath);
    	return file.delete();
    }
    /**
	 * 判断指定文件是否存在
	 * @param filePath
	 * @return false: 不存在 true:存在
	 */
	public static boolean fileExists(String filePath){
		if(null == filePath) return false;
		boolean r = false;
		try{
			File f = new File(filePath);
			if (f.exists()) {
				r = true;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 下载文件
	 * 
	 * @param fileName
	 * @param file
	 * @param response
	 * @throws Exception
	 */
	public static boolean download(String fileName, String filePath,HttpServletResponse response) {
		boolean r = false;
		try {
			File file = new File(filePath);
			String filelength = String.valueOf(file.length());
			response.setContentType("application/x-download");
			response.setHeader("Location", fileName);
			response.setHeader("Content-Disposition", "attachment;filename=\""
					+ CommonTools.gBK2ISO_8859_1(fileName));
			response.setContentLength(Integer.parseInt(filelength));
			OutputStream outputStream = response.getOutputStream();
			InputStream inputStream = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int i = -1;
			while ((i = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, i);
				outputStream.flush();
			}
			outputStream.close();
			inputStream.close();
			outputStream = null;
			r = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 文件复制方法
	 * 
	 * @param sourceFile
	 * @param targetFile
	 * @return
	 * @throws IOException
	 */
	public static boolean copyFile(File sourceFile, File targetFile) throws IOException {
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			// 新建文件输入流并对它进行缓冲
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile));
			// 新建文件输出流并对它进行缓冲
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));
			// 缓冲数组
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// 刷新此缓冲的输出流
			outBuff.flush();
		} finally {
			// 关闭流
			if (inBuff != null)
				inBuff.close();
			if (outBuff != null)
				outBuff.close();
		}
		return true;
	}

	public static InputStream getInputStream(String urlPath)
    {
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try
        {
            URL url = new URL(urlPath);
            if (url != null)
            {
                httpURLConnection = (HttpURLConnection) url.openConnection();// 打开链接
                // 设置连接网络的超时时间
                httpURLConnection.setConnectTimeout(3000);
                httpURLConnection.setDoInput(true);
                //
                httpURLConnection.setRequestMethod("GET");
                int resposeCode = httpURLConnection.getResponseCode();
                if (resposeCode == 200)// 如果请求成功
                {
                    // 从服务器中获得一个输入流
                    inputStream = httpURLConnection.getInputStream();
                }
            }
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return inputStream;
    }
	public static boolean downloadImageByUrl(String fileName, String urlPath,HttpServletResponse response) {
		boolean r = false;
		try {
			InputStream inputStream = getInputStream(urlPath);
			response.setContentType("application/x-download");
			response.setHeader("Location", fileName);
			response.setHeader("Content-Disposition", "attachment;filename=\""
					+ CommonTools.gBK2ISO_8859_1(fileName));
			response.setContentLength(inputStream.available());
			OutputStream outputStream = response.getOutputStream();
			
			
			byte[] buffer = new byte[1024];
			int i = -1;
			while ((i = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, i);
				outputStream.flush();
			}
			outputStream.close();
			inputStream.close();
			outputStream = null;
			r = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
	
	public static boolean downloadByStream(String fileName,InputStream inputStream ,HttpServletResponse response) {
		boolean r = false;
		try {
			response.setContentType("application/x-download");
			response.setHeader("Location", fileName);
			response.setHeader("Content-Disposition", "attachment;filename=\""
					+ CommonTools.gBK2ISO_8859_1(fileName));
			response.setContentLength(inputStream.available());
			OutputStream outputStream = response.getOutputStream();
			
			
			byte[] buffer = new byte[1024];
			int i = -1;
			while ((i = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, i);
				outputStream.flush();
			}
			outputStream.close();
			inputStream.close();
			outputStream = null;
			r = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
}