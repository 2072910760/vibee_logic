package org.example.vibee.util;

import cn.hutool.core.util.StrUtil;

import java.net.URLEncoder;

/**
 * 消息体:加密，加密前组成为32位MD5+消息体XML
 * 
 * @author luliujun
 * 
 */
public abstract class CEBInfoSecretKey {
	/**
	 * 消息加密KEY
	 */
	public static String KEY = "olmDquMoUdsIbPF9";

	/**
	 * 消息体加密 规则:URLEncoder(Base64(AES(MD5(消息体) + 消息体)),"utf-8");
	 * 
	 * @param message
	 * @return
	 */
	public final static String getSecretKey(String message) {
		if (StrUtil.isEmpty(message)) {
			throw new IllegalArgumentException("The message body is empty");
		}
		try {
			String secretKey = MD5.getMD5(message);
			secretKey = secretKey + message;
			secretKey = AESUtils.aesEncrypt(secretKey, KEY);
			secretKey = Base64Utils.encode(secretKey);
			return URLEncoder.encode(secretKey, "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
