package net.sinyoo.center.core.annotation;

import java.lang.annotation.*;

/**
 * 数值最大值
 * 
 * File: Max.java<br/>
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
public @interface Max {

	/**
	 * 错误消息
	 * 
	 * @return
	 */
	String message() default "超出最大值";
	
	/**
	 * 最大值
	 * 
	 * @return
	 */
	long value();
}
