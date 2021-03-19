package com.potato369.find.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

// Java 根据出生日期计算年龄
public class AgeUtil {

	public static Date parse(String strDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(strDate);
	}

	public static int getAge(Date birthDay) throws Exception {
		Calendar cal = Calendar.getInstance();
		if (cal.before(birthDay)) { // 出生日期晚于当前时间，无法计算
			throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
		}
		int yearNow = cal.get(Calendar.YEAR); // 当前年份
		int monthNow = cal.get(Calendar.MONTH); // 当前月份
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); // 当前日期
		cal.setTime(birthDay);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
		int age = yearNow - yearBirth; // 计算整岁数
		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth)
					age--;// 当前日期在生日之前，年龄减一
			} else {
				age--;// 当前月份在生日之前，年龄减一

			}
		}
		return age;
	}

	public static void main(String[] args) {
		try {
			int age = getAge(parse("1988-01-21")); // 由出生日期获得年龄***
			System.out.println("age:" + age);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
