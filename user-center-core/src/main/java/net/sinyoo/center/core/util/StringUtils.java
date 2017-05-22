package net.sinyoo.center.core.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * 字符串工具类。
 * 
 * File: StringUtils.java<br/>
 * Description: <br/>
 * 
 * Copyright: Copyright (c) 2012 ecbox.com<br/>
 * Company: ECBOX,Inc.<br/>
 * 
 * @author WangHui
 * @date 2013-4-22
 * @version 1.0
 */
public abstract class StringUtils {

  private StringUtils() {
  }

//  public static void main(String[] args) {
////    String line = ";;a;b;c;;;;d;;;;;;;;;;f;;;;";
////    String delimiters = ";";
////    String[] ss = StringUtils.lineParse(line, delimiters);
////    for (String s : ss) {
////      System.out.println(s);
////    }
//
//    System.out.println(isNumeric("1"));
//  }

  public static String[] lineParse(String line, String delimiters) {
    StringTokenizer st = new StringTokenizer(line, delimiters, true);

    ArrayList<String> fieldList = new ArrayList<String>();
    String[] ss = new String[st.countTokens()];
    String preField = delimiters;
    for (int i = 0; i < ss.length; i++) {
      String field = st.nextToken();
      if (preField.equals(delimiters) && field.equals(delimiters)) {
        fieldList.add("");
        preField = field;
      } else if (preField.equals(delimiters) && !field.equals(delimiters)) {
        fieldList.add(field);
        preField = field;
      } else if (!preField.equals(delimiters) && field.equals(delimiters)) {
        preField = field;
        continue;
      }
    }
    if (preField.equals(delimiters)) {
      fieldList.add("");
    }

    return fieldList.toArray(new String[fieldList.size()]);
  }

  /**
   * 把latin1转换成指定的编码
   * 
   * @param s
   * @param charsetName
   *          "GBK" "UTF-8"
   * @return
   */
  public static String convertCharset(String s, String charsetName) {
    if (s != null) {
      try {
        int length = s.length();
        byte[] buffer = new byte[length];
        // 0x81 to Unicode 0x0081, 0x8d to 0x008d, 0x8f to 0x008f, 0x90
        // to 0x0090, and 0x9d to 0x009d.
        for (int i = 0; i < length; ++i) {
          char c = s.charAt(i);
          if (c == 0x0081) {
            buffer[i] = (byte) 0x81;
          } else if (c == 0x008d) {
            buffer[i] = (byte) 0x8d;
          } else if (c == 0x008f) {
            buffer[i] = (byte) 0x8f;
          } else if (c == 0x0090) {
            buffer[i] = (byte) 0x90;
          } else if (c == 0x009d) {
            buffer[i] = (byte) 0x9d;
          } else {
            buffer[i] = Character.toString(c).getBytes("CP1252")[0];
          }
        }
        String result = new String(buffer, charsetName);
        return result;
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  /**
   * 检查指定的字符串是否为空。
   * <ul>
   * <li>SysUtils.isEmpty(null) = true</li>
   * <li>SysUtils.isEmpty("") = true</li>
   * <li>SysUtils.isEmpty("   ") = true</li>
   * <li>SysUtils.isEmpty("abc") = false</li>
   * </ul>
   * 
   * @param value
   * @return true/false
   */
  public static boolean isEmpty(String value) {
    int strLen;
    if (value == null || (strLen = value.length()) == 0) {
      return true;
    }
    for (int i = 0; i < strLen; i++) {
      if ((Character.isWhitespace(value.charAt(i)) == false)) {
        return false;
      }
    }
    return true;
  }

  /**
   * 检查对象是否为数字型字符串,包含负数开头的。
   * 
   * @param obj
   * @return
   */
  public static boolean isNumeric(Object obj) {
    if (obj == null) {
      return false;
    }
    char[] chars = obj.toString().toCharArray();
    int length = chars.length;
    if (length < 1)
      return false;

    int i = 0;
    if (length > 1 && chars[0] == '-')
      i = 1;

    for (; i < length; i++) {
      if (!Character.isDigit(chars[i])) {
        return false;
      }
    }
    return true;
  }

  /**
   * 检查字符串是否是数字
   * 
   * @param str
   * @return
   */
  public static boolean isNumeric(String str) {
    Pattern pattern = Pattern.compile("[0-9]*");
    return pattern.matcher(str).matches();
  }

  /**
   * 检查指定的字符串列表是否不为空
   * 
   * @param values
   * @return
   */
  public static boolean areNotEmpty(String... values) {
    boolean result = true;
    if (values == null || values.length == 0) {
      result = false;
    } else {
      for (String value : values) {
        result &= !isEmpty(value);
      }
    }
    return result;
  }

  /**
   * 把通用字符编码的字符串转化为汉字编码
   * 
   * @param unicode
   * @return
   */
  public static String unicodeToChinese(String unicode) {
    StringBuilder out = new StringBuilder();
    if (!isEmpty(unicode)) {
      for (int i = 0; i < unicode.length(); i++) {
        out.append(unicode.charAt(i));
      }
    }
    return out.toString();
  }

  /**
   * 过滤不可见字符
   * 
   * @param input
   * @return
   */
  public static String stripNonValidXMLCharacters(String input) {
    if (input == null || ("".equals(input)))
      return "";
    StringBuilder out = new StringBuilder();
    char current;
    for (int i = 0; i < input.length(); i++) {
      current = input.charAt(i);
      if ((current == 0x9) || (current == 0xA) || (current == 0xD) || ((current >= 0x20) && (current <= 0xD7FF))
          || ((current >= 0xE000) && (current <= 0xFFFD)) || ((current >= 0x10000) && (current <= 0x10FFFF)))
        out.append(current);
    }
    return out.toString();
  }


  /**
   * 获取一定长度的随机字符串
   * @param length 指定字符串长度
   * @return 一定长度的字符串
   */
  public static String getRandomStringByLength(int length) {
    String base = "abcdefghijklmnopqrstuvwxyz0123456789";
    java.util.Random random = new java.util.Random();
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < length; i++) {
      int number = random.nextInt(base.length());
      sb.append(base.charAt(number));
    }
    return sb.toString();
  }

}
