package net.sinyoo.center.core.validator;

/**
 * 身份证号码处理类
 * 
 * File: IdCard.java<br/>
 * Description: <br/>
 * 
 * Copyright: Copyright (c) 2012 ecbox.com<br/>
 * Company: ECBOX,Inc.<br/>
 * 
 * @author WangHui
 * @date 2013-4-22
 * @version 1.0
 */
public class IdCardValidator {

	// 经过计算得出的指数数组，算法：２的ｎ-1次方求和，除以11取模
	// 如：2的0次方除以11取模=1,2的1次方除以11取模=2,2的2次方除以11取模=4
	// 加权因子
	static int[] wi = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };

	// 校验位数组
	static char[] ai = { '1', '0', 'x', '9', '8', '7', '6', '5', '4', '3', '2' };

	/**
	 * main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(IdCardValidator.validId18("xxx"));
	}

	/**
	 * 根据传入的18位身份证号，计算校验位
	 * 
	 * @param id
	 *            ，18位身份证号
	 * @return mod，返回校验位，用于从ai数组取数作为身份证号的校验位，即ai[mod]
	 */
	public static int checkBit(String id) {
		String id17 = id.substring(0, 17);
		int sum = 0;
		for (int i = 0; i < id17.length(); i++) {
			int ai = Integer.parseInt(id17.substring(i, i + 1));
			sum += wi[i] * ai;
		}
		int mod = sum % 11;
		return mod;
	}

	/**
	 * 
	 * 根据15位身份证号和出生日期，计算校验位
	 * 
	 * @param id15
	 *            ，15位身份证号
	 * @param birthday出生日期
	 *            ，19820329
	 * @return mod，第18位校验位，用于从ai数组取数作为身份证号的最后一位，即ai[mod]
	 */
	public static int checkBit(String id15, String birthday) {
		id15 = id15.substring(0, 6) + birthday.substring(0, 2) + id15.substring(6);
		int sum = 0;
		// 计算校验位，前17位加权求和，然后除以11取模
		// S = Sum(Ai * Wi), i = 0, ... , 16 ，对前17位数字的权求和
		// Ai:表示第i位置上的身份证号码数字值
		// Wi:表示第i位置上的加权因子
		for (int i = 1; i < id15.length() + 1; i++) {
			sum = sum + wi[i - 1] * (Integer.parseInt(id15.substring(i - 1, i)));
		}
		int mod = sum % 11;
		return mod;
	}

	/**
	 * 校验15位身份证号与出生日期
	 * 
	 * @param id
	 *            ,15位身份证号
	 * @param birthday
	 *            ,出生日期8位(19820329)
	 * @return result
	 */
	public static boolean checkId15BirthDay(String id, String birthday) {
		boolean result = false;
		String birth_id = id.substring(6, 12);// 6位日期
		String birth = birthday.substring(2);
		// 检验日期，出生日期与身份证中的出生日期相符，然后检验性别
		if (birth_id.equals(birth)) {
			result = true;
		} else {
			// 出生日期与身份证中的出生日期不相符
			result = false;
		}
		return result;
	}

	/**
	 * 校验15位身份证号与性别
	 * 
	 * @param id
	 *            ,15位身份证号
	 * @param sex
	 *            ，性别, 0-女 1-男
	 * @return result
	 */
	public static boolean checkId15Sex(String id, Integer sex) {
		boolean result = false;

		String temp = id.substring(14);// 最后一位代表性别
		int isex = Integer.parseInt(temp);
		// 男为奇数，女为偶数
		if (isex % 2 == sex) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	/**
	 * 校验18位身份证号
	 * 
	 * @param id
	 *            ,18位身份证号
	 * @param birthday
	 *            ,出生日期8位(19820329)
	 * @param sex
	 *            ，性别, 0-女,1-男
	 * @return result
	 */
	public static boolean checkId18BirthDay(String id, String birthday) {
		boolean result = false;
		String birth_id = id.substring(6, 14);// 8位日期
		// 检验日期，出生日期与身份证中的出生日期相符，然后检验性别
		if (birth_id.equals(birthday)) {
			result = true;
		} else {
			// 出生日期与身份证中的出生日期不相符
			result = false;
		}
		return result;
	}

	/**
	 * 校验18位身份证号
	 * 
	 * @param id
	 *            ,18位身份证号
	 * @param birthday
	 *            ,出生日期8位(19820329)
	 * @param sex
	 *            ,性别,0-女,1-男
	 * @return result
	 */
	public static boolean checkId18Sex(String id, Integer sex) {
		boolean result = false;
		String temp = id.substring(16, 17);// 倒数第二位代表性别
		int isex = Integer.parseInt(temp);
		// 男为奇数，女为偶数
		if (isex % 2 == sex) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	/**
	 * 获取18位身份证号的出生日期
	 * 
	 * @param id
	 * @return
	 */
	public static String getId18Birthday(String id) {
		if (isNullEmpty(id) || id.length() != 18)
			return null;
		String birthday = id.substring(6, 14);// 8位日期;
		return birthday;
	}

	/**
	 * 获取18位身份证号的性别
	 * 
	 * @param id
	 * @return
	 */
	public static Integer getId18Sex(String id) {
		if (isNullEmpty(id) || id.length() != 18)
			return null;
		String temp = id.substring(16, 17);// 倒数第二位代表性别
		int isex = Integer.parseInt(temp);
		// 男为奇数，女为偶数
		// 性别 0-女 1-男
		if (isex % 2 == 0) {
			return 0;
		} else {
			return 1;
		}
	}

	/**
	 * 根据15位身份证号和出生日期计算得出18位身份证号
	 * 
	 * @param id15
	 *            ，15位身份证号
	 * @param birthday出生日期
	 *            ，19820329
	 * @return id18，返回18位身份证号
	 */
	public static String id15To18Number(String id15, String birthday) {
		if (isNullEmpty(id15) || id15.length() != 15 || isNullEmpty(birthday) || birthday.length() != 8) {
			return null;
		} else {
			return id15.substring(0, 6) + birthday.substring(0, 2) + id15.substring(6) + ai[checkBit(id15, birthday)];
		}
	}

	/**
	 * 判断字符串是否为null或者长度是否为0
	 * 
	 * @param text
	 *            字符串
	 * @return boolean
	 */
	public static boolean isNullEmpty(String text) {
		if (null == text) {
			return true;
		} else if (0 == text.length()) {
			return true;
		}
		return false;
	}

	/**
	 * 验证身份证号码是否正确
	 * 
	 * @param id
	 *            ,18位身份证号码
	 * @return true 正常，false 错误
	 */
	public static boolean validId18(String id) {
		if (validId18IsNumber(id) && validId18IsBirthday(id) && validId18IsBit(id))
			return true;
		else
			return false;
	}

	/**
	 * 验证身份证出生日期是否合法
	 * 
	 * @param id
	 *            ,18位身份证号码
	 * @return true 正常，false 错误
	 */
	public static boolean validId18IsBirthday(String id) {
		if (isNullEmpty(id) || id.length() != 18)
			return false;
		// 验证出生日期
		int y = Integer.parseInt(id.substring(6, 10));
		int m = Integer.parseInt(id.substring(10, 12));
		int d = Integer.parseInt(id.substring(12, 14));
		if (y < 1900 || m < 1 || m > 12 || d < 1 || d > 31 || ((m == 4 || m == 6 || m == 9 || m == 11) && d > 30)
				|| (m == 2 && ((y % 4 > 0 && d > 28) || d > 29)))
			return false;
		return true;
	}

	/**
	 * 验证身份证验证数是否正确
	 * 
	 * @param id
	 *            ,18位身份证号码
	 * @return true 正常，false 错误
	 */
	public static boolean validId18IsBit(String id) {
		if (isNullEmpty(id) || id.length() != 18)
			return false;
		// 验证身份证验证位
		String bit = id.substring(17);
		String tbit = String.valueOf(ai[checkBit(id)]);
		if (!bit.equalsIgnoreCase(tbit))
			return false;
		return true;
	}

	/**
	 * 验证身份证字符是否为合法
	 * 
	 * @param id
	 *            ,18位身份证号码
	 * @return true 正常，false 错误
	 */
	public static boolean validId18IsNumber(String id) {
		if (isNullEmpty(id) || id.length() != 18)
			return false;
		// 验证前17位是否为数字
		for (int i = 0; i < 17; i++) {
			if (id.charAt(i) < '0' || id.charAt(i) > '9')
				return false;
		}
		return true;
	}

}
