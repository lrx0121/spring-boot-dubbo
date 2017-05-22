package net.sinyoo.center.core.util;

/**
 * 随机数
 * 
 * File: Random.java<br/>
 * Description: <br/>
 *
 * Copyright: Copyright (c) 2012 ecbox.com<br/>
 * Company: ECBOX,Inc.<br/>
 *
 * @author WangHui
 * @date 2013-4-22
 * @version 1.0
 */
public class Random {

//	public static void main(String args[]) {
//		try {
//			String user = randomKey(10); // 传几位生成几位随机数
//			try {
//				System.out.println(user);
//			} catch (Exception ex) {
//				ex.printStackTrace();
//			}
//		} catch (Exception es) {
//			es.printStackTrace();
//		}
//	}

	/**
	 * 字符串随机数-指定长度的
	 * 
	 * @param sLen
	 * @return
	 */
	public static String randomKey(int sLen) {
		final String base = "1234567890";
		StringBuilder temp = new StringBuilder();
		int p;
		for (int i = 0; i < sLen; i++) {
			p = (int) (Math.random() * 10);
			temp.append(base.substring(p, p + 1));
		}
		return temp.toString();
	}

	/**
	 * 生成指定范围的随机数
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int randomRound(long min, long max) {
		return (int) Math.round(Math.random() * (max - min) + min);
	}

	/**
	 * 随机指定范围内N个不重复的数
	 * 最简单最基本的方法
	 * @param min 指定范围最小值
	 * @param max 指定范围最大值
	 * @param n 随机数个数
	 */
	public static int[] randomCommon(int min, int max, int n){
		if (n > (max - min + 1) || max < min) {
			return null;
		}
		int[] result = new int[n];
		int count = 0;
		while(count < n) {
			int num = (int) (Math.random() * (max - min)) + min;
			boolean flag = true;
			for (int j = 0; j < n; j++) {
				if(num == result[j]){
					flag = false;
					break;
				}
			}
			if(flag){
				result[count] = num;
				count++;
			}
		}
		return result;
	}
}
