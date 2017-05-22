package net.sinyoo.center.core.annotation;

import java.lang.annotation.*;

/**
 * 需要decode为UTF-8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.FIELD })
@Documented
public @interface DecodeUtf8 {

	/**
	 * 返回错误信息
	 * 
	 * @return
	 */
	String message() default "数据编码不正确";
}
