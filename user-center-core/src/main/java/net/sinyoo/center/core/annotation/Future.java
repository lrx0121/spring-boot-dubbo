package net.sinyoo.center.core.annotation;

import java.lang.annotation.*;

/**
 * 将来的时间
 * 
 * File: Future.java<br/>
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
public @interface Future {

	/**
	 * 返回错误信息
	 * 
	 * @return
	 */
	String message() default "必须是将来的时间";
}
