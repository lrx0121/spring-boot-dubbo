package net.sinyoo.center.core.annotation;

import java.lang.annotation.*;

/**
 * 正则匹配
 * 
 * File: Pattern.java<br/>
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
public @interface Pattern {

	/**
	 * 返回错误信息
	 * 
	 * @return
	 */
	String message() default "不符合正则匹配规则";
	
	/**
	 * 正则表达式
	 * 
	 * @return
	 */
	String regex();
}
