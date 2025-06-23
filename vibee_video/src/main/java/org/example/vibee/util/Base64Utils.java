package org.example.vibee.util;



import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Utils {


	/**
	 * BASE64加密
	 * 
	 * @param str
	 * @return
	 */
	public static String encode(String str) {
		byte[] b = null;
		String s = null;
		try {
			b = str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (b != null) {
			s =  Base64.getEncoder().encodeToString(b);
		}
		return s;
	}

	/**
	 * BASE64加密
	 *
	 * @param b
	 * @return
	 */
	public static String encode(byte[] b) {
		String s = null;
		if (b != null) {
			s = Base64.getEncoder().encodeToString(b);
		}
		return s;
	}

	/**
	 * BASE64 解密
	 * @param s
	 * @return
	 */
	public static String decode(String s) {
		byte[] b = null;
		String result = null;
		if (s != null) {
			try {
				b =Base64.getDecoder().decode(s);
				result = new String(b, "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public static String decode(byte[] bs) {
		String result = null;
		if (bs != null) {
			try {
				String str=new String(bs,"utf-8");
				bs = Base64.getDecoder().decode(str);
				result = new String(bs, "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
