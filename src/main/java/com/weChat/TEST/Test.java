package com.weChat.TEST;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Pattern pp = Pattern.compile("[\u4e00-\u9fa5]");//中文正则
		String str1 = "fx09090909090909";
//		Matcher mm = pp.matcher(str1);
//		System.out.println(mm.matches());
//		String str2 = "我大大万维网";
//		Matcher m2 = pp.matcher(str2);
//		System.out.println(m2.matches());

		System.out.println("刘".getBytes().length);
	}

}
