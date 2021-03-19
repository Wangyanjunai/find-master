package com.potato369.find.common.utils;

import java.math.BigDecimal;

public class MathUtil {

	  private static final Double MONEY_RANG = 0.01;
	  
	  /**
	   * <pre>
	   * 比较两个金额是否相等
	   * @param double1 金额1
	   * @param double2 金额2
	   * @return Boolean
	   * </pre>
	   */
		public static synchronized Boolean equals(Double double1, Double double2) {
			Double result = Math.abs(double1 - double2);
			if (result < MONEY_RANG) {
				return true;
			} else {
				return false;
			}
		}
	  	/**
	   * <pre>
	   * 比较两个金额大小，如果double1 >= double2，返回true，否则返回false。
	   * @param double1 金额1
	   * @param double2 金额2
	   * @return Boolean
	   * </pre>
	   */
		public static synchronized Boolean compareTo(Double double1, Double double2) {
			Double result = Math.abs(double1 - double2);
			if (result >= 0) {
				return true;
			} else {
				return false;
			}
		}
		
		/**
		 * <pre>
		 * getRandom方法的作用：产生随机红包余额
		 * @param rang
		 * @return
		 * </pre>
		 */
		public static synchronized Double getRandom(int rang) {
			return new BigDecimal((Math.random() * rang + 1) / 10).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		}

		public static String subZeroAndDot(String s){
		if(s.indexOf(".") > 0){
			s = s.replaceAll("0+?$", "");//去掉多余的0
			s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
		}
		return s;
	}
}
