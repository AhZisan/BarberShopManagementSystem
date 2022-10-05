package com.barber.util;
/**
 * String Utils
 * @author xzl
 *
 */
public class StringUtil {
	/**
	 * Judge a string is empty
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if(str == null || "".equals(str.trim())) {
			return true;
		}else {
			return false;
		}
	}
}