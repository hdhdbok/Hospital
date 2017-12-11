package edu.ncwu.util;

public class HospitalUtil {
	//判断字符串是否全部由数字组成
	public static boolean isNumber(String str) {
		for(int i = str.length();--i>0;) {
			if(!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
