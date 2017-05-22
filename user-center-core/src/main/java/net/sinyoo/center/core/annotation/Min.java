package net.sinyoo.center.core.annotation;

import java.lang.annotation.*;

/**
 * 数值最小值
 * 
 * File: Min.java<br/>
 * Description: <br/>
 *
 * Copyright: Copyright (c) 2012 ecbox.com<br/>
 * Company: ECBOX,Inc.<br/>
 *
 * @author WangHui
 * @date 2013-4-21
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.FIELD })
@Documented
public @interface Min {

	/**
	 * 错误消息
	 * 
	 * @return
	 */
	String message() default "小于最小值";
	
	/**
	 * 最小值
	 * 
	 * @return
	 */
	long value();
}
