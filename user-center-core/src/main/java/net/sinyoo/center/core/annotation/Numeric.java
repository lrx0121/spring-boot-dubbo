package net.sinyoo.center.core.annotation;

import java.lang.annotation.*;

/**
 * 验证数字
 * 
 * File: Numeric.java<br/>
 * Description: <br/>
 *
 * Copyright: Copyright (c) 2012 ecbox.com<br/>
 * Company: ECBOX,Inc.<br/>
 *
 * @author WangHui
 * @date 2013-6-4
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.FIELD })
@Documented
public @interface Numeric {

	/**
	 * 返回错误信息
	 * 
	 * @return
	 */
	String message() default "内容必须为全数字";
}
