package com.education.framework.util.security;

import java.net.URLDecoder;
import java.net.URLEncoder;

import com.education.framework.util.Escape;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class RSACodeFactory {

	public static RSACodeFactory factory;
	
	private String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCeFzv7jtqhrLXpMjx6+BIxomtin3zj9p/e0/yyPKe1xqR9d+7FdaS4fM1q8kDlNxW63b5adSIw+ijfCOUBFEz+Wv4S4FP2PraAOs9DRYUDqC2+Ofr+SkS1HW2CdzAm9PHq4g0WIRVv/M2Pn3ohNVXuh1Op2QnIMhqpuM+UclINRQIDAQAB";
	private String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJ4XO/uO2qGstekyPHr4EjGia2KffOP2n97T/LI8p7XGpH137sV1pLh8zWryQOU3Fbrdvlp1IjD6KN8I5QEUTP5a/hLgU/Y+toA6z0NFhQOoLb45+v5KRLUdbYJ3MCb08eriDRYhFW/8zY+feiE1Ve6HU6nZCcgyGqm4z5RyUg1FAgMBAAECgYBUrAMPZ+LHZvaDcMmd3xH3jZNQXjWI70WPFiEo4X29ySe+n6a+EZVozkdDOX7yhX9Y930Fe+kVLFcJpfzpiBL37Gaf8LpPq1MjFsmcVR4rk6dXjYgIyRcDbVJu9sEBXP66pNw+H6uQNgPehq89k7o77/AGhy346MmK1JTgcoRwoQJBANlPwJeSHMDLvfRj3Vbqr9C+EqJV1z/R9Y3zd4eo9s/bmxs02Kt2mpISuo8ip5HwzLbiDjpL4jVmY8z3sW4KJBkCQQC6PGugKjSlOp/HUoq8V2oM/bY/q3dUyEtRf/K6YAtddLfLwP1FtMXIhTGgJsiYWN/ljPncoOoWci9Awri6RvgNAkAGOijUrxUv2SbKWApBsVv0aV3zVO5kH8oflTh5pLfd4Pci4nLLBw0K3oqGC4Itxm63Vub9eKs0yIhXmUl2U8gRAkAybEl3g/XdBpJcFS0a8U/1VHwToQkeG1zJJCtD6t0o4IEi2Y25YchrEiTth5KoLOfx7sl3wtmeWiwRDKeXR7/BAkB4JoKUPYyQpRf8lLpLEJoBs86yQ1uLqvep54a11JmXJ0Yh1B2pHUgBuFeJsXgE1aIaeIKvdv9el6xEXS3XU44l";
	private int[] defDecryptNum = {8,2,4,9,3,7,5,3,1,0,8,3,2,9,9,7,6,0,2,4,3,2,6,7,4,6,2,3,8};
	
	public static RSACodeFactory getInstance(){
		if(null == factory){
			factory = new RSACodeFactory();
			try {
				//if("".equals(factory.publicKey) && "".equals(factory.privateKey)){
					//Map<String, Object> keyMap = RSACoder.initKey();
					//factory.publicKey = RSACoder.getPublicKey(keyMap);
					//factory.privateKey = RSACoder.getPrivateKey(keyMap);
					//System.out.println("公钥: \n\r" + factory.publicKey);
					//System.out.println("私钥： \n\r" + factory.privateKey);
				//}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return factory;
	}
	
	public String encrypt(String inputStr) throws Exception{
		byte[] data = inputStr.getBytes();
		String enStr = Base64.encode(RSACoder.encryptByPrivateKey(data, privateKey));
		return URLEncoder.encode(enStr);
	}
	
	public String decrypt(String encryptStr) throws Exception{
		String urlDecodeStr = URLDecoder.decode(encryptStr);
		byte[] dataBytes = Base64.decode(urlDecodeStr);
		
		byte[] decodedData = RSACoder.decryptByPublicKey(dataBytes, publicKey);
		String str = new String(decodedData);
		return str;
	}
	

	/**
	 * 加密
	 * @param inputStr
	 * @return
	 * @throws Exception
	 */
	public String encryptDef(String inputStr) throws Exception{
		String s = encrypt(inputStr);
		//System.out.println("res 加密：" + s);
		//字符串反向处理
		String reverseStr = new StringBuffer(s).reverse().toString();
		//System.out.println("res 反向：" + reverseStr);
		//移位处理
		StringBuffer rBuf = new StringBuffer();
		int temp = 0;
		for(int i = 0 ; i < reverseStr.length() ; i++){
			temp = (int)reverseStr.charAt(i);
			char c = (char)(temp+getDDN(i));
			rBuf.append(c);
		}
		
		return Escape.escape(rBuf.toString());
	}
	
	/**
	 * 解密
	 * @param encryptStr
	 * @return
	 * @throws Exception
	 */
	public String decryptDef(String encryptStr) throws Exception{
		encryptStr = Escape.unescape(encryptStr);   
		//移位处理
		StringBuffer rBuf = new StringBuffer();
		int temp = 0;
		for(int i = 0 ; i < encryptStr.length() ; i++){
			temp = (int)encryptStr.charAt(i);
			char c = (char)(temp - getDDN(i));
			rBuf.append(c);
		}
		//字符串反向处理
		String reverseStr = rBuf.reverse().toString();
		return decrypt(reverseStr);
	}
	
	private int getDDN(int n){
		if(null != defDecryptNum && defDecryptNum.length > n) {
			return defDecryptNum[n];
		}else{
			return 0;
		}
	}
	public static void main(String[] args) {
		String inputStr = "简要总结一下，使用公钥加密、私钥解密，完成了乙方到甲方的一次数据传递";
		String ret = "";
		try {
			ret = RSACodeFactory.getInstance().encryptDef(inputStr);
			System.out.println("def 加密：" + ret);
			System.out.println("def 解密：" + RSACodeFactory.getInstance().decryptDef(ret));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
