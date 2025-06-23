package org.example.vibee.util;

import java.util.ArrayList;
import java.util.List;

public final class FilterWordUtils {
	// 禁用语或不文明语言列表
	private static List<String> DISABLE_WORDS = new ArrayList<String>(50);
	// 禁用语或不文明语言数量
	private static int DISABLE_WORDS_COUNT = 0;
	static {
		DISABLE_WORDS.add("奶奶的");
		DISABLE_WORDS.add("你妹啊");
		DISABLE_WORDS.add("fuck you");
		// 禁用语言自己添加
		DISABLE_WORDS_COUNT = DISABLE_WORDS.size();
	}

	/**
	 * 过滤不文明语言
	 * 
	 * @param oldword
	 * @return
	 */
	public static String getFilterWord(String oldword) {
		if (isEmptyStr(oldword)) {
			return "";
		}
		int len = oldword.length();
		String newWord = oldword.toLowerCase();
		for (int index = 0; index < DISABLE_WORDS_COUNT; index++) {
			String disabledWord = DISABLE_WORDS.get(index);
			if (len < disabledWord.length()) {
				continue;
			}
			newWord = newWord.replace(disabledWord.toLowerCase(),
					getRepString(disabledWord));
		}
		StringBuffer strBuf = new StringBuffer();
		for (int index = 0; index < len; index++) {
			if ('*' == newWord.charAt(index)) {
				strBuf.append('*');
			} else {
				strBuf.append(oldword.charAt(index));
			}
		}
		return strBuf.toString();
	}

	/**
	 * 将字符串中全角字符转成半角字符
	 * 
	 * @param str
	 * @return
	 */
	public  static String converSBCtoDBC(String str) {
		if (null == str) {
			return str;
		}
		int length = str.length();
		int[] codePoints = new int[length];
		for (int i = 0; i < length; i++) {
			if (str.codePointAt(i) == 12288) {
				codePoints[i] = str.codePointAt(i) - 12256;
				continue;
			}
			if (str.codePointAt(i) > 65280 && str.codePointAt(i) < 65375) {
				codePoints[i] = str.codePointAt(i) - 65248;
			} else {
				codePoints[i] = str.codePointAt(i);
			}
		}
		return new String(codePoints, 0, length);
	}

	/**
	 * 获取过滤字符
	 * 
	 * @param disabledWord
	 */
	public static String getRepString(String disabledWord) {
		int len = disabledWord.length();
		StringBuffer strBuf = new StringBuffer();
		for (int index = 0; index < len; index++) {
			if (' ' == disabledWord.charAt(index)) {
				strBuf.append(' ');
			} else {
				strBuf.append('*');
			}
		}
		return strBuf.toString();

	}

	/**
	 * 过滤字符串所有空格
	 * 
	 * @param str
	 * @return
	 */
	public  static String filterAllBlank(String str) {
		if (null == str) {
			return str;
		}
		char bc = ' ';
		str = converSBCtoDBC(str);
		String bStr = String.valueOf(bc);
		str = str.replaceAll(bStr, bStr.trim());
		return str;
	}

	/**
	 * 字符串转基本数据类型int
	 * 
	 * @param str
	 * @return
	 */
	public final static int converToInt(String str) {
		char bc = ' ';
		String bStr = String.valueOf(bc);
		str = str.replaceAll(bStr, bStr.trim());
		return Integer.valueOf(str).intValue();
	}

	/**
	 * 字符串转基本数据类型long
	 * 
	 * @param str
	 * @return
	 */
	public  static long convertToLong(String str) {
		char bc = ' ';
		String bStr = String.valueOf(bc);
		str = str.replaceAll(bStr, bStr.trim());
		return Long.valueOf(str);
	}

	/**
	 * 判断是否为空字符串
	 * 
	 * @param str
	 * @return
	 */
	public  static boolean isEmptyStr(String str) {
		boolean isEmpty = null == str;
		if (!isEmpty) {
			str = filterAllBlank(str);
			char bc = ' ';
			String bStr = String.valueOf(bc);
			isEmpty = str.equals(bStr.trim());
		}
		return isEmpty;
	}

}
