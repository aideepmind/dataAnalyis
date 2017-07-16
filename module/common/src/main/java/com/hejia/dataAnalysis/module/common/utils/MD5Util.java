package com.hejia.dataAnalysis.module.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @Description: MD5工具
 * @author: chenyongqiang
 * @Date: 2015年4月9日
 * @version: 1.0
 */
public class MD5Util {
	//编码方式
	public final static String CHARSET_UTF8 = "UTF-8";
	//加密方式
	public final static String ENCRYPT_TYPE_MD5 = "MD5";
	//加密迭代次数
	public final static int HASH_ITERATIONS = 2;
	/**
	 * @Definition: 获取字符串的MD5散列算法后的字符数
	 * @author: chenyongqiang
	 * @Date: 2015年4月9日
	 * @param orl
	 * @param saltByte 盐
	 * @return
	 */
	public static String getEncryptString(String orl, byte[] saltByte) {
		try {
			//md5散列算法
			byte[] orlByte = orl.getBytes(CHARSET_UTF8);
			MessageDigest md = MessageDigest.getInstance(ENCRYPT_TYPE_MD5);
			md.update(saltByte);
			md.update(orlByte);
			byte[] digestByte = md.digest();
			//把随机盐和md5散列算法后得到的byte整合，防止数据库中的密码泄露，就是被一一比对。
			byte[] dsByte = new byte[digestByte.length + saltByte.length];
			System.arraycopy(saltByte, 0, dsByte, 0, saltByte.length);
			System.arraycopy(digestByte, 0, dsByte, saltByte.length, digestByte.length);
			//base64编码，原因是byte数组中有一些byte变成字符串之后无法显示，有两种做法，一种是base64编码，另外一种是把byte转换成16位进制
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encodeBuffer(dsByte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @Definition: 获取字符串的MD5散列算法后的字符数(随机盐)
	 * @author: chenyongqiang
	 * @Date: 2015年4月9日
	 * @param orl
	 * @return
	 */
	public static String getEncryptString(String orl) {
		//获取随机盐
		Random random = new Random();
		byte[] saltByte = new byte[8];
		random.nextBytes(saltByte);
		return getEncryptString(orl, saltByte);
	}
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2015年4月9日
	 * @param orl 没进行过MD5加密的字符串
	 * @param dest 已进行过MD5加密的字符串
	 * @param saltByte 盐
	 * @return
	 */
	public static boolean compare(String orl, String dest, byte[] saltByte) {
//		return true;
		try {
			//base64解码
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] destByte = decoder.decodeBuffer(dest);
			//获取随机盐
			System.arraycopy(destByte, 0, saltByte, 0, saltByte.length);
			//md5散列算法
			byte[] orlByte = orl.getBytes(CHARSET_UTF8);
			MessageDigest md = MessageDigest.getInstance(ENCRYPT_TYPE_MD5);
			md.update(saltByte);
			md.update(orlByte);
			byte[] digestByte = md.digest();
			//获取dest中的md5散列byte
			byte[] destDigestByte = new byte[destByte.length - saltByte.length];
			System.arraycopy(destByte, saltByte.length, destDigestByte, 0, destDigestByte.length);
			return Arrays.equals(digestByte, destDigestByte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * @Definition: 
	 * @author: Henry
	 * @Date: 2015年4月9日
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 */
	public final static String str2MD5(String inStr) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = inStr.getBytes("UTF-8");
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * @Definition: 比较两个经过MD5加密的字符数(随机盐)
	 * @author: chenyongqiang
	 * @param orl 没进行过MD5加密的字符串
	 * @param dest 已进行过MD5加密的字符串
	 * @return
	 */
	public static boolean compare(String orl, String dest) {
		try {
			//base64解码
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] destByte = decoder.decodeBuffer(dest);
			//获取随机盐
			byte[] saltByte = new byte[8];
			return compare(orl, dest, saltByte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * @Definition: 使用shiro自带的加密方式，不要传user进来。
	 * @author: chenyongqiang
	 * @Date: 2015年4月9日
	 * @param pwd
	 * @param salt
	 * @return 长度为2    1为加密后的密码;2为随机盐
	 */
	public static String[] useShiroMD5Encrypt(String pwd, String salt) {
		String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
		SimpleHash hash = new SimpleHash(ENCRYPT_TYPE_MD5, pwd, salt + salt2, HASH_ITERATIONS);
		return new String[]{hash.toHex(), salt2};
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		String pwd = "jd2015";
		String newpwd = MD5Util.getEncryptString("123456");
		System.out.println(newpwd+"--");
//		
//		System.out.println(MD5Util.compare("abc123./\\`~_-+@#$%^&*()", newpwd));
//		String username = "admin";
//		String pwd = "Admin_162";
//		String[] en = useShiroMD5Encrypt(pwd, username);
//		System.out.println(en[0]);
//		System.out.println(en[1]);
//		String str = new Md5Hash(pwd, username).toBase64();
//		System.out.println(str);
	}

	public static byte[] encode2bytes(String source) {
		byte[] result = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.reset();
			md.update(source.getBytes("UTF-8"));
			result = md.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @author yangxiaolong 将源字符串使用MD5加密为32位16进制数
	 * @param source
	 * @return
	 */
	public static String encode2hex(String source) {
		byte[] data = encode2bytes(source);
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			String hex = Integer.toHexString(0xff & data[i]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();

	}

	/**
	 * @author yangxiaolong 验证字符串是否匹配
	 * @param unknown 待验证的字符串
	 * @param okHex 使用MD5加密过的16进制字符串
	 * @return 匹配返回true，不匹配返回false
	 */
	public static boolean validate(String unknown, String okHex) {
		return okHex.equals(encode2hex(unknown));
	}
	
}
