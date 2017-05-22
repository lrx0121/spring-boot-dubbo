package net.sinyoo.center.core.validator;

import net.sinyoo.center.core.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 我的常用验证器
 * 
 * File: MyValidater.java<br/>
 * Description: <br/>
 * 
 * Copyright: Copyright (c) 2012 ecbox.com<br/>
 * Company: ECBOX,Inc.<br/>
 * 
 * @author WangHui
 * @date 2013-4-22
 * @version 1.0
 */
public abstract class MyValidator {

  private MyValidator() {
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
//    System.out.println(isMobile("17921103751"));
    System.out.println(isPassword("wabg_a121"));
  }

  /**
   * 是否是email地址
   * 
   * @param input
   * @return
   */
  public static boolean isEmail(String email) {
    Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    email = email.toLowerCase();
    if (email.endsWith(".con"))
      return false;
    if (email.endsWith(".cm"))
      return false;
    if (email.endsWith("@gmial.com"))
      return false;
    if (email.endsWith("@gamil.com"))
      return false;
    if (email.endsWith("@gmai.com"))
      return false;
    return emailer.matcher(email).matches();
  }

  /**
   * 是否是手机号码
   * 
   * @param input
   * @return
   */
  public static boolean isMobile(String input) {
    if (input.length() != 11) {
      return false;
    }
//    return isNumeric(input);
    Pattern p = null;
    Matcher m = null;
    boolean b = false;
    p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
    m = p.matcher(input);
    b = m.matches();
    return b;
  }


  /**
   * 全数字
   * 
   * @param str
   * @return
   */
  public static boolean isNumeric(String input) {
    Pattern pattern = Pattern.compile("[0-9]*");
    Matcher isNum = pattern.matcher(input);
    if (!isNum.matches()) {
      return false;
    }
    return true;
  }

  /**
   * 是否是网络设备物理地址格式
   * 
   * @param input
   * @return
   */
  public static boolean isMac(String input) {
    return isPattern("[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}", input);
  }

  /**
   * 是否是固定电话号码
   * 
   * @param input
   * @return
   */
  public static boolean isTelephone(String input) {
    return isPattern("(\\d{3}-|\\d{4}-)?(\\d{8}|\\d{7})?", input);
  }

  /**
   * 是否是url
   * 
   * @param input
   * @return
   */
  public static boolean isUrl(String input) {
    return isPattern("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", input);
  }

  /**
   * 是否是qq号码
   * 
   * @param input
   * @return
   */
  public static boolean isQQ(String input) {
    return isPattern("(^\\s*[1-9]+[0-9]{4,9}\\s*$)", input);
  }

  /**
   * 是否是身份证号码
   * 
   * @param input
   * @return
   */
  public static boolean isIdCard(String input) {
    return IdCardValidator.validId18(input);
  }

  /**
   * 数值符合范围
   * 
   * @param min
   * @param max
   * @param input
   * 
   * @return
   */
  public static boolean isRange(long min, long max, long input) {
    if (input <= max && input >= min) {
      return true;
    }
    return false;
  }

  /**
   * 正则匹配
   * 
   * @param regex
   * @param input
   * @return
   */
  public static boolean isPattern(String regex, String input) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(input);
    return matcher.find();
  }

  /**
   * 是否是过去的时间
   * 
   * @param input
   * @return
   */
  public static boolean isPast(long input) {
    if (System.currentTimeMillis() / 1000 > input) {
      return true;
    }
    return false;
  }

  /**
   * 不是空值
   * 
   * @param input
   * @return
   */
  public static boolean isNotNull(Object input) {
    if (input == null) {
      return false;
    }
    return true;
  }

  /**
   * 不是空字符串
   * 
   * @param input
   * @return
   */
  public static boolean isNotEmpty(String input) {
    if (StringUtils.isEmpty(input)) {
      return false;
    }
    return true;
  }

  /**
   * 符合最小值
   * 
   * @param min
   * @param input
   * @return
   */
  public static boolean isMin(long min, long input) {
    if (min < input) {
      return true;
    }
    return false;
  }

  /**
   * 符合最大值
   * 
   * @param max
   * @param input
   * @return
   */
  public static boolean isMax(long max, long input) {
    if (max > input) {
      return true;
    }
    return false;
  }

  /**
   * 符合字符串长度范围
   * 
   * @param min
   * @param max
   * @param input
   * @return
   */
  public static boolean isLength(int min, int max, String input) {
    int length = input.length();
    if (length >= min && length <= max) {
      return true;
    }
    return false;
  }

  /**
   * 是一个未来的时间
   * 
   * @param input
   * @return
   */
  public static boolean isFuture(long input) {
    if (System.currentTimeMillis() / 1000 < input) {
      return true;
    }
    return false;
  }

  /**
   * 是否符合数字范围
   * 
   * @param integer
   * @param fraction
   * @param input
   * @return
   */
  public static boolean isDigits(int integer, int fraction, String input) {
    String[] arr = input.split(".");
    if (arr.length == 2) {// 有小数部分
      if (arr[1].length() > fraction) {
        return false;
      }
    }
    if (arr[0].length() > integer) {
      return false;
    }
    return true;
  }

  /**
   * 是否符合密码格式<br>
   * 目前密码规则是6-16位长度字符串
   * 
   * @param input
   * @return
   */
  public static boolean isPassword(String input) {
    if (StringUtils.isEmpty(input)) {
      return false;
    }
    if (input.length() < 6 || input.length() > 16) {
      return false;
    }
    return isPattern("^\\w+$",input);
  }

}
