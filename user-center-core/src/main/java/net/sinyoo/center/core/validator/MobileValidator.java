package net.sinyoo.center.core.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 手机号码验证
 * 
 * File: MobileValidater.java<br/>
 * Description: <br/>
 * 
 * Copyright: Copyright (c) 2012 ecbox.com<br/>
 * Company: ECBOX,Inc.<br/>
 * 
 * @author WangHui
 * @date 2013-4-22
 * @version 1.0
 */
public class MobileValidator {
	
	//简单的判断就是开头两位 13 14 15 18

	/**
	 * 移动号段16个（2011年版）
	 * 　134、135、136、137、138、139、147、150、151、152、157、158、159、182、187、188
	 * 规则：可以以0开头+三位固定号段+8为数字
	 */
	static final String cmcc = "(^(13[4-9]|147|15[0-2]|15[7-9]18[278])[0-9]{8}$)";

	/**
	 * 联通号段7个 130、131、132、155、156、185、186
	 */
	static final String cucc = "(^(13[0-2]|15[56]|18[56])[0-9]{8}$)";

	/**
	 * 电信号段4个 133、153、180、189
	 */
	static final String cnc = "(^(133|153|180|189)[0-9]{8}$)";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "15921103757";
		System.out.println(isCMCC(str));
	}

	/**
	 * 是中国移动
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean isCMCC(String mobile) {
		Pattern pattern = Pattern.compile(cmcc);
		Matcher matcher = pattern.matcher(mobile);
		return matcher.find();
	}

	/**
	 * 是中国联通的号码
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean isCUCC(String mobile) {
		Pattern pattern = Pattern.compile(cucc);
		Matcher matcher = pattern.matcher(mobile);
		return matcher.find();
	}

	/**
	 * 是中国电信的号码
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean isCNC(String mobile) {
		Pattern pattern = Pattern.compile(cnc);
		Matcher matcher = pattern.matcher(mobile);
		return matcher.find();
	}

}
