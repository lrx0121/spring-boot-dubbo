package net.sinyoo.center.core.annotation;

import java.lang.annotation.*;

/**
 * 是qq号码
 * 
 * File: QQ.java<br/>
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
public @interface QQ {
	
	/**
	 * 返回错误信息
	 * 
	 * @return
	 */
	String message() default "qq号码不正确";
}
