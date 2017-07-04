package com.hcb.xigou.util;

import java.security.Key;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

public class RSASign {

	
	  public static final String KEY_ALGORITHM = "RSA";
	//  private static final String PUBLIC_KEY = "PublicKey";
//	  private static final String priKey = "PrivateKey";
	

	  /**
		 * 使用私钥加密
		 * 
		 * @param priKey
		 * @param data
		 * @return
		 * @throws Exception
		 */
		public static byte[] encryptByPrivateKey(RSAPrivateKey priKey, byte[] data)
				throws Exception {
			byte[] key = priKey.getEncoded();
			// 取得私钥
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
			// 对数据加密
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			return cipher.doFinal(data);
		}
	  public static void main(String[] args) {
		  
		  
			Map<String, String> mop = new HashMap<String,String>();
			//String aa = money.toString();
			String nonce_str = RandomStringGenerator.getRandomStringByLength(32);
			mop.put("service", "mobile.securitypay.pay");
			mop.put("partner", "2088911200563043");
			mop.put("seller_id", "huancheclub_zfb@163.com");
			mop.put("_input_charset", "http://notify.msp.hk/notify.htm");
			mop.put("notify_url", "");
			mop.put("payment_type", "1");
			mop.put("out_trade_no",nonce_str );
			mop.put("total_fee","100" );
			mop.put("subject", "测试");
			mop.put("body", "测试测试");
			Map<String, String> mapp = AlipayCore.paraFilter(mop);
			String  service = AlipayCore.createLinkString(mapp);
		  byte [] buffer = service.getBytes();
		// RSAPrivateKey priKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALbMt4irl+VodB4vhDo+ONXNu6NciBJD6KkomCp9KWcXixX+OZFVjfSndsO6KdC4kR4FOPUC1LXAOy/on5BYeTtrj7DXWsDeBwrR2IM15n4g1YFqwaXMOBuWoiRpmKrpt/W9BU/ORXij0j6rtku56AgLtkBB+MoXQ36Paa+UBiTDAgMBAAECgYAyTpyj7DVdWSW7uO/253DUxk3BY5G/FRlOlHFsIA3o5T3Ny60kJhtvHTc+Hlmrq1+kl2NZanIHouOnB6oCG5chH/mqO5KqDoIvTEZHIIMiIJDonPTn+Na8OyRabxYpCQznxiWdFxLmy6e3trWPg4rNT/f9WmyqblC5CRZ5Vpv6wQJBAOPuHtEtP+1GDAN37+5VVKNmjk2hoWCYlv2YCwuj8vRc6FQEzxERiUvpWtc81ab8zecmZXRnKiOyhB0X1KzJ67sCQQDNT8hXLvTqcspBCoMsK1OQlJMxmXJQDHRj47JT5fSMhVabZhW1MppB9XI+rtryscIjH8LsT/r6DPwfxn51J6aZAkBONQs/7M3NhUZj/khGN+M1ud/EBVyQ/2p3ky7fDJ81d5eEFK5UBfddI7G2vrn0dTPVR1hya1+LJhqsvNuNei83AkBl7W3wmodMvaBbmfR1QS1DYf+RaDSwOP6veKNXs5otCSVuEMhGJNEgXdJR/E0Gn+lZtrL2zt4yta+Vtt2hHAZBAkAn3a8WoQi6HnNDRclMDwVL0vnDA/mLwCZN+LKEXYZSB8FAfqYZWkazLlqmTBlquPWWp4lfPRy5hkZavQOveJPk";
		  //new RSASign().encryptByPrivateKey(, buffer);
		  
		  
	}
	  
}
