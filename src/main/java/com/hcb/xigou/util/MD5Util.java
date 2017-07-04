package com.hcb.xigou.util;


import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * CryptTool 封装了一些加密工具方法, 包括 3DES, MD5 等.
 * 
 * @author hxq
 * @version 1.0 2006-01-10
 */
public class MD5Util {

    public MD5Util() {
    }

    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };

    /**
     * 转换字节数组为16进制字串
     * 
     * @param b
     *            字节数组
     * @return 16进制字串
     */

    public static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString().toLowerCase();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * 生成3DES密钥.
     * 
     * @param key_byte
     *            seed key
     * @throws Exception
     * @return javax.crypto.SecretKey Generated DES key
     */
    public static javax.crypto.SecretKey genDESKey(byte[] key_byte)
            throws Exception {

        SecretKey k = null;
        k = new SecretKeySpec(key_byte, "DESede");
        return k;
    }

    public static javax.crypto.SecretKey genDESKey() throws Exception {
        String keyStr = "$1#2@f3&4~6%7!a+*cd(e-h)";// 使用固定key
        // System.out.println("DES 加密使用的固定key，keyStr ＝＝ " + keyStr);
        byte key_byte[] = keyStr.getBytes();// 3DES 24 bytes key
        SecretKey k = null;
        k = new SecretKeySpec(key_byte, "DESede");
        return k;
    }

    public static javax.crypto.SecretKey genDESKey(String key) throws Exception {
        String keyStr = key;// 使用固定key
        // System.out.println("DES 加密使用的固定key，keyStr ＝＝ " + keyStr);
        byte key_byte[] = keyStr.getBytes();// 3DES 24 bytes key
        SecretKey k = null;
        k = new SecretKeySpec(key_byte, "DESede");
        return k;
    }

    /**
     * 3DES 解密(byte[]).
     * 
     * @param key
     *            SecretKey
     * @param crypt
     *            byte[]
     * @throws Exception
     * @return byte[]
     */
    public static byte[] desDecrypt(javax.crypto.SecretKey key, byte[] crypt)
            throws Exception {
        javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("DESede");
        cipher.init(javax.crypto.Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(crypt);
    }

    /**
     * 3DES 解密(String).
     * 
     * @param key
     *            SecretKey
     * @param crypt
     *            byte[]
     * @throws Exception
     * @return byte[]
     */
    public static String desDecrypt(javax.crypto.SecretKey key, String crypt)
            throws Exception {
        return byteArrayToHexString(desDecrypt(key, crypt.getBytes()));
    }

    /**
     * 3DES加密(byte[]).
     * 
     * @param key
     *            SecretKey
     * @param src
     *            byte[]
     * @throws Exception
     * @return byte[]
     */
    public static byte[] desEncrypt(javax.crypto.SecretKey key, byte[] src)
            throws Exception {
        javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("DESede");
        cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(src);
    }

    /**
     * 3DES加密(String).
     * 
     * @param key
     *            SecretKey
     * @param src
     *            byte[]
     * @throws Exception
     * @return byte[]
     */
    public static String desEncrypt(javax.crypto.SecretKey key, String src)
            throws Exception {
        return byteArrayToHexString(desEncrypt(key, src.getBytes()));
    }

    /**
     * MD5 摘要计算(byte[]).
     * 
     * @param src
     *            byte[]
     * @throws Exception
     * @return byte[] 16 bit digest
     */
    public static byte[] md5Digest(byte[] src) throws Exception {
        java.security.MessageDigest alg = java.security.MessageDigest
                .getInstance("MD5"); // MD5 is 16 bit message digest

        return alg.digest(src);
    }

    /**
     * MD5 摘要计算(String).
     * 
     * @param src
     *            String
     * @throws Exception
     * @return String
     */
    public static String md5Digest(String src) throws Exception {
        return byteArrayToHexString(md5Digest(src.getBytes()));
    }

    /**
     * BASE64 编码.
     * 
     * @param src
     *            String inputed string
     * @return String returned string
     */
    public static String base64Encode(String src) {
        sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();

        return encoder.encode(src.getBytes());
    }

    /**
     * BASE64 编码(byte[]).
     * 
     * @param src
     *            byte[] inputed string
     * @return String returned string
     */
    public static String base64Encode(byte[] src) {
        sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();

        return encoder.encode(src);
    }

    /**
     * BASE64 解码.
     * 
     * @param src
     *            String inputed string
     * @return String returned string
     */
    public static String base64Decode(String src) {
        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();

        try {
            return byteArrayToHexString(decoder.decodeBuffer(src));
        } catch (Exception ex) {
            return null;
        }

    }

    /**
     * BASE64 解码(to byte[]).
     * 
     * @param src
     *            String inputed string
     * @return String returned string
     */
    public static byte[] base64DecodeToBytes(String src) {
        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();

        try {
            return decoder.decodeBuffer(src);
        } catch (Exception ex) {
            return null;
        }

    }

    /**
     * 对给定字符进行 URL 编码.
     * 
     * @param src
     *            String
     * @return String
     */
    public static String urlEncode(String src) {
        try {
            src = java.net.URLEncoder.encode(src, "UTF-8");

            return src;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return src;
    }

    /**
     * 对给定字符进行 URL 解码
     * 
     * @param value
     *            解码前的字符串
     * @return 解码后的字符串
     */
    public String urlDecode(String value) {
        try {
            return java.net.URLDecoder.decode(value, "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return value;
    }

    /** Test crypt */
    public static void main(String[] args) {
        try {
            // 获得的明文数据
            String desStr = "MERCHANTID=0219999999&ORDERSEQ=20080225150029000001&ORDERDATE=20080225&ORDERAMOUNT=200";

            // 使用固定key值
            String keyStr = "123456";// 使用固定key
            desStr = desStr + "&KEY=" + keyStr;// 将key值和明文数据组织成一个待加密的串
            System.out.println("待加密的字符串 desStr ＝＝ " + desStr);
            // 转成字节数组
            byte src_byte[] = desStr.getBytes();

            // MD5摘要
            byte[] md5Str = md5Digest(src_byte);
            // 生成最后的SIGN
            String SING = byteArrayToHexString(md5Str);
            System.out.println("SING == " + SING);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}