package net.sinyoo.center.core.annotation;

import java.lang.annotation.*;

/**
 * 不允许为null
 * 
 * File: NotNull.java<br/>
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
public @interface NotNull {

	/**
	 * 返回错误消息
	 * 
	 * @return
	 */
	String message() default "值不能为空";

}
