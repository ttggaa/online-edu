package com.education.framework.util.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * 
 * @author 梁栋
 * @version 1.0
 * @since 1.0
 */
public class RSACoderTest {
	private String publicKey;
	private String privateKey;

	@Before
	public void setUp() throws Exception {
		Map<String, Object> keyMap = RSACoder.initKey();

		publicKey = RSACoder.getPublicKey(keyMap);
		privateKey = RSACoder.getPrivateKey(keyMap);
		System.err.println("公钥: \n\r" + publicKey);
		System.err.println("私钥： \n\r" + privateKey);
	}

	@Test
	public void test() throws Exception {
		System.err.println("公钥加密——私钥解密");
		String inputStr = "abc";
		byte[] data = inputStr.getBytes();

		byte[] encodedData = RSACoder.encryptByPublicKey(data, publicKey);

		byte[] decodedData = RSACoder.decryptByPrivateKey(encodedData,
				privateKey);

		String outputStr = new String(decodedData);
		System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
		assertEquals(inputStr, outputStr);

	}

	public static void main(String[] args) throws Exception {
		RSACoderTest test = new RSACoderTest();
		test.setUp();
		System.err.println("私钥加密——公钥解密");
		//String inputStr = "sign";
		String inputStr = "sud1HfuSXSvOhMoP3iYr26jg,2015-02-06 08:07:54";
		
		
		byte[] data = inputStr.getBytes();

		byte[] encodedData = RSACoder.encryptByPrivateKey(data, test.privateKey);
		
		String accessToken = "KwQp36GqbWXd6i771UH/+TKoEUKPnh+WeQwEzcxQleAz6bFR4Rth3FS5P9hYHQpVs/V6SdND3r1h1sK8UtHKfZAsiH03LILpq/BMCWqilGKXJ37EhTox9L8dy9uc4TBiSfGRInNfD1K1M9ky6lOtY8f+mQfr16+0pqyVdQvkEVo="; //Base64.encode(encodedData);
		System.out.println("accessToken=" + accessToken);
		
//		String accessToken = "cpHy0MwOPke5JF76EQPJubeIqAUTbOTi+DE5SJQkTtyyff0pEq7f71+c0lUACW/Jei38TSN7YqXr\r\nb6wlblMAkOvAawXDfuUikFjCXinzj9wD3B9H7K/sqNh6mdg0oX3qjY68Z3HnKpyHKe7bIr8u0m4/\r\n/WKnmITenDPvYwOtflE=\r\n";
		byte[] dataBytes = Base64.decode(accessToken);
		byte[] decodedData = RSACoder
				.decryptByPublicKey(dataBytes, test.publicKey);

		String outputStr = new String(decodedData);
		
		System.err.println("加密前: " + inputStr + "\n\r" + "\n\r解密后: " + outputStr);

		System.err.println("私钥签名——公钥验证签名");
		// 产生签名
		String sign = RSACoder.sign(encodedData, test.privateKey);
		System.err.println("签名:\r" + sign);

		// 验证签名
		boolean status = RSACoder.verify(encodedData, test.publicKey, sign);
		System.err.println("状态:\r" + status);
	}
	
	@Test
	public void testSign() throws Exception {
		System.err.println("私钥加密——公钥解密");
		String inputStr = "sign";
		byte[] data = inputStr.getBytes();

		byte[] encodedData = RSACoder.encryptByPrivateKey(data, privateKey);

		byte[] decodedData = RSACoder
				.decryptByPublicKey(encodedData, publicKey);

		String outputStr = new String(decodedData);
		System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
		assertEquals(inputStr, outputStr);

		System.err.println("私钥签名——公钥验证签名");
		// 产生签名
		String sign = RSACoder.sign(encodedData, privateKey);
		System.err.println("签名:\r" + sign);

		// 验证签名
		boolean status = RSACoder.verify(encodedData, publicKey, sign);
		System.err.println("状态:\r" + status);
		assertTrue(status);

	}

}


