package com.potato369.find.common.utils;

import java.math.BigInteger;
import java.util.Random;
import java.util.UUID;

public class UUIDUtil {

	/**
	 * 创建订单号，订单详情
	 *
	 * @return
	 */
	public static synchronized String genTimstampUUID() {
		return DateUtil.getTimestamp()
				.concat(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 15).toLowerCase());
	}

	/**
	 * 创建订单号，订单详情
	 *
	 * @return
	 */
	public static synchronized String genTimstampTransationId() {
		return DateUtil.getTimestamp().concat(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 15)
				.toLowerCase().concat(String.valueOf(System.currentTimeMillis())));
	}

	/**
	 * 创建产品id，商品id
	 *
	 * @return
	 */
	public static synchronized String gen32UUID() {
		return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
	}

	/**
	 * 创建用户mid，商品id
	 *
	 * @return
	 */
	public static synchronized String gen13MID() {
		return String.valueOf(System.currentTimeMillis());
	}

	/**
	 * 随机生成不重复的18位IMEI手机串号
	 * 
	 * @return
	 */
	public static synchronized String genIMEI() {
		String s = "";
		Random random = new Random();
		s += random.nextInt(9) + 1;
		for (int i = 0; i < 18 - 1; i++) {
			s += random.nextInt(10);
		}
		new BigInteger(s);
		// System.out.println(bigInteger);
		return s;
	}

	/**
	 * 随机生成不重复的18位IMEI手机串号
	 * 
	 * @return
	 */
	public static synchronized String genWeChat() {
		String s = "";
		Random random = new Random();
		s += random.nextInt(11) + 1;
		for (int i = 0; i < 8 - 1; i++) {
			s += random.nextInt(8);
		}
		new BigInteger(s);
		// System.out.println(bigInteger);
		return "wx" + s;
	}

	public static void main(String[] args) {
		System.out.println(genWeChat());
	}
}
