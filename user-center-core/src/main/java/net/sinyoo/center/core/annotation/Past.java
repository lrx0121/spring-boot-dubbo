package net.sinyoo.center.core.annotation;

import java.lang.annotation.*;

/**
 * 过去的时间
 * 
 * File: Past.java<br/>
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
public @interface Past {

	/**
	 * 返回错误消息
	 * 
	 * @return
	 */
	String message() default "必须是过去的时间";
}
