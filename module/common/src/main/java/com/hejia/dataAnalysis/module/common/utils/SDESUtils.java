package com.hejia.dataAnalysis.module.common.utils;

import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @Description: DES加密工具类
 * @author: chenyongqiang
 * @Date: 2016年2月25日
 * @version: 1.0
 */
public class SDESUtils {
	
	public final static String CHARSET_ISO = "ISO-8859-1";
	public final static String CHARSET_UTF = "UTF-8";
	public final static String CHARSET_GBK = "gb2312";
	public final static String ENCRYPT_TYPE = "PBEWithMD5AndDES";
	/**
	 * DES编码方式和
	 * ECB密码模式
	 * PKCS5Padding填充模式
	 */
	public final static String ENCRYPT_MODEL = "PBEWithMD5AndDES";
	/**
	 * 加密
	 * @param key
	 * @param data 原始串
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String key, String data) throws Exception{
		//获取随机盐
		Random random = new Random();
		byte[] saltByte = new byte[8];
		random.nextBytes(saltByte);
		//对key进行base64编码
//		BASE64Encoder encoder = new BASE64Encoder();
//		key = encoder.encodeBuffer(key.getBytes(CHARSET_GBK));
		//创建一个密钥
		PBEKeySpec pbks = new PBEKeySpec(key.toCharArray());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ENCRYPT_TYPE); 
		SecretKey securekey = keyFactory.generateSecret(pbks); 
		//创建一个随机数生成器
		//SecureRandom secureRandom = new SecureRandom();
		Cipher cipher = Cipher.getInstance(ENCRYPT_MODEL); 
		//初始化向量
		PBEParameterSpec ps = new PBEParameterSpec(saltByte, 1000);
		//加密
		cipher.init(Cipher.ENCRYPT_MODE, securekey, ps);
		byte encryptData[] = cipher.doFinal(data.getBytes(CHARSET_UTF));
		//把随机盐和md5散列算法后得到的byte整合，防止数据库中的密码泄露，就是被一一比对。
		byte[] dsByte = new byte[encryptData.length + saltByte.length];
		System.arraycopy(saltByte, 0, dsByte, 0, saltByte.length);
		System.arraycopy(encryptData, 0, dsByte, saltByte.length, encryptData.length);
		//base64编码，原因是byte数组中有一些byte变成字符串之后无法显示，有两种做法，一种是base64编码，另外一种是把byte转换成16位进制
//		return encoder.encodeBuffer(dsByte);
		return Hex.encodeToString(dsByte);
	}
	/**
	 * 解密
	 * @param key
	 * @param data 加密串
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String key, String data) throws Exception{
		//对key进行base64编码
//		BASE64Encoder encoder = new BASE64Encoder();
//		key = encoder.encodeBuffer(key.getBytes(CHARSET_GBK));
		//base64解码
		BASE64Decoder decoder = new BASE64Decoder();
//		byte[] destByte = decoder.decodeBuffer(data);
		byte[] destByte = Hex.decode(data.getBytes());
		//获取随机盐
		byte[] saltByte = new byte[8];
		System.arraycopy(destByte, 0, saltByte, 0, saltByte.length);
		byte[] destDigestByte = new byte[destByte.length - saltByte.length];
		System.arraycopy(destByte, saltByte.length, destDigestByte, 0, destDigestByte.length);
		//创建一个密钥
		PBEKeySpec pbks = new PBEKeySpec(key.toCharArray());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ENCRYPT_TYPE); 
		SecretKey securekey = keyFactory.generateSecret(pbks); 
		//创建一个随机数生成器
		//SecureRandom secureRandom = new SecureRandom();
		Cipher cipher = Cipher.getInstance(ENCRYPT_MODEL); 
		//初始化向量
		PBEParameterSpec ps = new PBEParameterSpec(saltByte, 1000);
		//解密
		cipher.init(Cipher.DECRYPT_MODE, securekey, ps);
		byte encryptData[] = cipher.doFinal(destDigestByte);
		return new String(encryptData,CHARSET_UTF);
	}
	public static void main(String[] args) throws Exception {
		try {
			String key = "mdsssasasasjly";
			String en = encrypt(key,"test|2013/10/12 11:08:41中文1ss11");
			System.out.println("---------------------------" + en);
			String de = decrypt(key,en);
			System.out.println("---------------------------" + de);
//			System.out.println(new String("王五".getBytes("UTF-8")));
			en = encrypt("beijinghejia2015","111111");
			System.out.println("beijinghejia2015---------------------------" + en);
			de = decrypt("beijinghejia2015",en);
			System.out.println("beijinghejia2015---------------------------" + de);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
