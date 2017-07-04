package com.hcb.xigou.util;

import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * des加密工具
 */
public class DES {

	private static byte[] desKey = "box.shkf".getBytes();
	private String key = "box.shkf";

	public DES(String desKey) {
		DES.desKey = key.getBytes();
	}

	public static byte[] desEncrypt(byte[] plainText) throws Exception {
		SecureRandom sr = new SecureRandom();
		byte rawKeyData[] = desKey;
		DESKeySpec dks = new DESKeySpec(rawKeyData);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, key,sr);
		byte data[] = plainText;
		byte encryptedData[] = cipher.doFinal(data);
		return encryptedData;
	}

	public static byte[] desDecrypt(byte[] encryptText) throws Exception {
		SecureRandom sr = new SecureRandom();
		byte rawKeyData[] = desKey;
		DESKeySpec dks = new DESKeySpec(rawKeyData);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, key, sr);
		byte encryptedData[] = encryptText;
		byte decryptedData[] = cipher.doFinal(encryptedData);
		return decryptedData;
	}

	/**
	 * 加密
	 * @param input 加密的字符串
	 * @return 加密串
	 * @throws Exception
	 */
	public static String encrypt(String input) throws Exception {
		return base64Encode(desEncrypt(input.getBytes()));
	}

	/**
	 * 解密
	 * @param input 解密串
	 * @return 解密后的字符串
	 * @throws Exception
	 */
	public static String decrypt(String input) throws Exception {
		if(null == input)input="";
		byte[] result = base64Decode(input);
		return new String(desDecrypt(result));
	}

	public static String base64Encode(byte[] s) {
		if (s == null)
			return null;
		BASE64Encoder b = new sun.misc.BASE64Encoder();
		return b.encode(s);
	}

	public static byte[] base64Decode(String s) throws IOException {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] b = decoder.decodeBuffer(s);
		return b;
	}

	public static String des(String input){
		try {
			return base64Encode(encrypt(input).getBytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		//DES crypt = new DES("zsty.login.phoneNumber");
		//String ctel = crypt.decrypt(CookieUtil.cookie("phoneNumber"));
//		System.out.println(DES.decrypt("15355001057"));/**/
		System.out.println(DES.decrypt("4blOKQLJa ZrpJ3PEfbQHQ=="));
		System.out.println(DES.encrypt("13370212825"));
	}
	
}