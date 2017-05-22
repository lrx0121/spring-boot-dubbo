package net.sinyoo.center.core.annotation;

import java.lang.annotation.*;

/**
 * 字符串长度范围
 * 
 * File: Length.java<br/>
 * Description: <br/>
 *
 * Copyright: Copyright (c) 2012 ecbox.com<br/>
 * Company: ECBOX,Inc.<br/>
 *
 * @author WangHui
 * @date 2013-4-22
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.FIELD })
@Documented
public @interface Length {

	/**
	 * 返回错误
	 * 
	 * @return
	 */
	String message() default "长度不在范围内";
	
	/**
	 * 最小值
	 * 
	 * @return
	 */
	int min() default 0;
	
	/**
	 * 最大值
	 * 
	 * @return
	 */
	int max();
}
