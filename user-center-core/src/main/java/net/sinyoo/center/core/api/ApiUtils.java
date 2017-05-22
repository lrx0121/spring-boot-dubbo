package net.sinyoo.center.core.api;

import net.sinyoo.center.core.annotation.*;
import net.sinyoo.center.core.exception.ApiException;
import net.sinyoo.center.core.util.SqlInj;
import net.sinyoo.center.core.validator.MyValidator;

import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URLDecoder;

/**
 * 公用方法
 * 
 * File: ApiUtils.java<br/>
 * Description: <br/>
 * 
 * Copyright: Copyright (c) 2014 ecbox.com<br/>
 * Company: ECBOX,Inc.<br/>
 * 
 * @author WangHui
 * @date 2014年11月27日
 * @version 1.0
 */
public class ApiUtils {

  /**
   * 检测更新数据的值是否全为空
   *
   * @param object
   */
  public static boolean checkUpdateParams(Object object) throws ApiException {
    Class<?> clazz = object.getClass();
    Field[] fields = clazz.getDeclaredFields();// 不包括父类
    for (int i = 0; i < fields.length; i++) {
      fields[i].setAccessible(true); // 使属性可以只接访问

      // 获取到字段对应的注解
      ApiField jsonField = fields[i].getAnnotation(ApiField.class);

      // 不是合法的apiField
      if (jsonField == null) {
        continue;
      }

      Class<?> typeClass = fields[i].getType();

      Object value = null;
      try {
        value = fields[i].get(object);
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
      // 没有传输当前值过来
      if (value == null) {
        continue;
      }

      //防止sql注入
      if (value instanceof  String){
        value = ((String) value).replace(";","；").replace("+","").replace("\'","“").replace(",","，");
        if (!SqlInj.sql_inj((String)value)) {
          throw new ApiException(310,value+" 中包含不合法字符");
        }
        try {
          fields[i].set(object, value);
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }

      return true;
    }
    return false;
  }

  /**
   * 简单对象绑定值
   * 
   * @param object
   */
  public static void checkParams(Object object) throws ApiException {
    Class<?> clazz = object.getClass();
    Field[] fields = clazz.getDeclaredFields();// 不包括父类
    for (int i = 0; i < fields.length; i++) {
      fields[i].setAccessible(true); // 使属性可以只接访问

      // 获取到字段对应的注解
      ApiField jsonField = fields[i].getAnnotation(ApiField.class);

      // 不是合法的apiField
      if (jsonField == null) {
        continue;
      }

      Class<?> typeClass = fields[i].getType();

      String itemName = fields[i].getName();
      // 如果是文件上传值
      Object value = null;
      try {
        value = fields[i].get(object);
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
//      // 没有传输当前值过来
//      if (value == null) {
//        continue;
//      }
      // 数据验证
      value = fieldValueValidate(fields[i], itemName, value);

      //防止sql注入
      if (value != null) {
        if (value instanceof String) {
          value = ((String) value).replace(";","；").replace("+","").replace("\'","‘").replace(",","，");
          if (!SqlInj.sql_inj((String) value)) {
            throw new ApiException(310, value + " 中包含不合法字符");
          }
        }
      }

      try {
        fields[i].set(object, value);
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 字段值验证
   * 
   * @param field
   * @param itemName
   * @param value
   * @throws ApiException
   */
  public static Object fieldValueValidate(Field field, String itemName, Object value) throws ApiException {
    for (Annotation annotation : field.getAnnotations()) {
      Class<?> typeClass = annotation.getClass();
//      String _item = itemName + ":" + value + ",";
      String _item = "";
      if (NotNull.class.isAssignableFrom(typeClass)) {
        NotNull notNull = (NotNull) annotation;
        if (!MyValidator.isNotNull(value)) {
          throw new ApiException(310, _item + notNull.message());
        }
      } else if (NotEmpty.class.isAssignableFrom(typeClass)) {
        NotEmpty notEmpty = (NotEmpty) annotation;
        if (!MyValidator.isNotEmpty((String) value)) {
          throw new ApiException(311, _item + notEmpty.message());
        }
      } else if (Digits.class.isAssignableFrom(typeClass)) {
        if (value == null) {
          continue;
        }
        Digits digits = (Digits) annotation;
        if (!MyValidator.isDigits(digits.integer(), digits.fraction(), (String) value)) {
          throw new ApiException(312, _item + digits.message());
        }
      } else if (Email.class.isAssignableFrom(typeClass)) {
        if (value == null) {
          continue;
        }
        Email email = (Email) annotation;
        if (!MyValidator.isEmail((String) value)) {
          throw new ApiException(313, _item + email.message());
        }
      } else if (Future.class.isAssignableFrom(typeClass)) {
        if (value == null) {
          continue;
        }
        Future future = (Future) annotation;
        try {
          if (!MyValidator.isFuture(Long.valueOf((String) value))) {
            throw new ApiException(314, _item + future.message());
          }
        } catch (Exception e) {
          throw new ApiException(302, _item + "无法转换成long");
        }
      } else if (IdCard.class.isAssignableFrom(typeClass)) {
        if (value == null) {
          continue;
        }
        IdCard idCard = (IdCard) annotation;
        if (!MyValidator.isIdCard((String) value)) {
          throw new ApiException(315, _item + idCard.message());
        }
      } else if (Length.class.isAssignableFrom(typeClass)) {
        if (value == null) {
          continue;
        }
        Length length = (Length) annotation;
        if (!MyValidator.isLength(length.min(), length.max(), (String) value)) {
          throw new ApiException(316, _item + length.message());
        }
      } else if (Max.class.isAssignableFrom(typeClass)) {
        if (value == null) {
          continue;
        }
        Max max = (Max) annotation;
        try {
          if (!MyValidator.isMax(max.value(), Long.valueOf((String) value))) {
            throw new ApiException(317, _item + max.message());
          }
        } catch (Exception e) {
          throw new ApiException(302, _item + "无法转换成long");
        }
      } else if (Min.class.isAssignableFrom(typeClass)) {
        if (value == null) {
          continue;
        }
        Min min = (Min) annotation;
        try {
          if (!MyValidator.isMin(min.value(), Long.valueOf((String) value))) {
            throw new ApiException(318, _item + min.message());
          }
        } catch (Exception e) {
          throw new ApiException(302, _item + "无法转换成long");
        }
      } else if (Mobile.class.isAssignableFrom(typeClass)) {
        if (value == null) {
          continue;
        }
        Mobile mobile = (Mobile) annotation;
        if (!MyValidator.isMobile((String) value)) {
          throw new ApiException(319, _item + mobile.message());
        }
      } else if (Password.class.isAssignableFrom(typeClass)) {
        if (value == null) {
          continue;
        }
        Password password = (Password) annotation;
        if (!MyValidator.isPassword((String) value)) {
          throw new ApiException(320, _item + password.message());
        }
      } else if (Past.class.isAssignableFrom(typeClass)) {
        if (value == null) {
          continue;
        }
        Past past = (Past) annotation;
        try {
          if (!MyValidator.isPast(Long.valueOf((String) value))) {
            throw new ApiException(321, _item + past.message());
          }
        } catch (Exception e) {
          throw new ApiException(302, _item + "无法转换成long");
        }
      } else if (Pattern.class.isAssignableFrom(typeClass)) {
        if (value == null) {
          continue;
        }
        Pattern pattern = (Pattern) annotation;
        if (!MyValidator.isPattern(pattern.regex(), (String) value)) {
          throw new ApiException(322, _item + pattern.message());
        }
      } else if (QQ.class.isAssignableFrom(typeClass)) {
        if (value == null) {
          continue;
        }
        QQ qq = (QQ) annotation;
        if (!MyValidator.isQQ((String) value)) {
          throw new ApiException(323, _item + qq.message());
        }
      } else if (Range.class.isAssignableFrom(typeClass)) {
        if (value == null) {
          continue;
        }
        Range range = (Range) annotation;
        try {
          if (!MyValidator.isRange(range.min(), range.max(), Long.valueOf((String) value))) {
            throw new ApiException(324, _item + range.message());
          }
        } catch (Exception e) {
          throw new ApiException(302, _item + "无法转换成long");
        }
      } else if (Telephone.class.isAssignableFrom(typeClass)) {
        if (value == null) {
          continue;
        }
        Telephone telephone = (Telephone) annotation;
        if (!MyValidator.isTelephone((String) value)) {
          throw new ApiException(325, _item + telephone.message());
        }
      } else if (Url.class.isAssignableFrom(typeClass)) {
        if (value == null) {
          continue;
        }
        Url url = (Url) annotation;
        if (!MyValidator.isUrl((String) value)) {
          throw new ApiException(326, _item + url.message());
        }
      } else if (Numeric.class.isAssignableFrom(typeClass)) {
        if (value == null) {
          continue;
        }
        Numeric numeric = (Numeric) annotation;
        if (!MyValidator.isNumeric((String) value)) {
          throw new ApiException(327, _item + numeric.message());
        }
      } else if (MAC.class.isAssignableFrom(typeClass)) {
        if (value == null) {
          continue;
        }
        MAC mac = (MAC) annotation;
        if (!MyValidator.isMac((String) value)) {
          throw new ApiException(328, _item + mac.message());
        }
      }else if (DecodeUtf8.class.isAssignableFrom(typeClass)){
        if (value == null) {
          continue;
        }
        DecodeUtf8 decodeUtf8 = (DecodeUtf8) annotation;
        try {
          value = URLDecoder.decode((String) value,"UTF-8");
        } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
          throw new ApiException(329, _item + decodeUtf8.message());
        }
      }else if (IntegerInit0.class.isAssignableFrom(typeClass)){
        if (value == null){
          value = "0";
        }
      }
    }
    return value;
  }
}
