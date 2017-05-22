package net.sinyoo.center.core.annotation;

import java.lang.annotation.*;

/**
 * 数据结构列表属性注解。
 * 
 * File: ApiListField.java<br/>
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
public @interface ApiListField {

	/**
	 * JSON列表属性映射名称
	 * 
	 * @return
	 */
	public String value() default "";

}
