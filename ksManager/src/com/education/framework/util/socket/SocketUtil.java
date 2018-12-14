package com.education.framework.util.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.education.framework.constants.CommonConstants;
import com.education.framework.util.DealProperty;

public class SocketUtil {

	private String ip;
	private int listenPortFileUpload;
	private int listenPortCmd;
	
	public SocketUtil(String ip_p){
		this.ip = ip_p;
		this.listenPortCmd=Integer.parseInt((String)DealProperty.setPro().get("LISTEN_PORT_CMD"));
		this.listenPortFileUpload=Integer.parseInt((String)DealProperty.setPro().get("LISTEN_PORT_FILEUPLOAD"));
	}
	
	public boolean sendCommand(String cmd) throws IOException{
		if("".equals(cmd)) return false;
		boolean ret = false;
		Socket socket = null;
		DataOutputStream dos = null;
		try {
			socket = new Socket();  
			socket.connect(new InetSocketAddress(ip, listenPortCmd));
			dos = new DataOutputStream(socket.getOutputStream());
			
			dos.writeUTF(cmd);
			ret = true;
		}catch (Exception e) {
			System.out.println("客户端命令执行失败");
			e.printStackTrace();  
		} finally{  
			if (dos != null)
				dos.close();  
			if (socket != null)
				socket.close();    
		}
		return ret ;
	}
	public String sendFile(String filePath , String appCode) throws Exception{
		if(null == filePath || "".equals(filePath)) throw new NullPointerException();
		int length = 0;
		double sumL = 0 ;
		byte[] sendBytes = null;
		Socket socket = null;
		DataOutputStream dos = null;
		FileInputStream fis = null;
		String bool = "";
		try {
			File file = new File(filePath); //要传输的文件路径
			long l = file.length(); 
			socket = new Socket();  
			socket.connect(new InetSocketAddress(ip, listenPortFileUpload));
			dos = new DataOutputStream(socket.getOutputStream());
			
			dos.write(appCode.getBytes(), 0, 6);
			dos.flush();
			fis = new FileInputStream(file);      
			sendBytes = new byte[1024];  
			while ((length = fis.read(sendBytes, 0, sendBytes.length)) > 0) {
				sumL += length;  
				System.out.println("已传输："+((sumL/l)*100)+"%");
				dos.write(sendBytes, 0, length);
				dos.flush();
			} 
			//发送结束标记
//			dos.write("".getBytes(),0,0);
//			dos.flush();
//			DataInputStream dis = new DataInputStream(socket.getInputStream()); 
//			String deplyTempDirFileName = dis.readUTF();
//			System.out.println("服务器部署WAR临时文件名称====》》》》" + deplyTempDirFileName);
//			dis.close();
			String deplyTempDirFileName = appCode;
			//虽然数据类型不同，但JAVA会自动转换成相同数据类型后在做比较
			if(sumL==l){
				bool = deplyTempDirFileName;
			}else{
				bool = "";
			}
		}catch (Exception e) {
			System.out.println("客户端文件传输异常");
			e.printStackTrace();  
			throw e;
		} finally{  
			if (dos != null)
				dos.close();
			if (fis != null)
				fis.close();   
			if (socket != null)
				socket.close();    
		}
		System.out.println(bool);
		return bool;
	}
	
	/**
	 * 
	 * @param args
	 * @return
	 */
	public String getParamByDeploy(String[] args) {
		String lp = "";
		StringBuffer param = new StringBuffer();
		for(String p : args){
			param.append(lp);
			param.append(p);
			lp = CommonConstants.CMD_SPLIT;
		}
		return param.toString();
	}
}
