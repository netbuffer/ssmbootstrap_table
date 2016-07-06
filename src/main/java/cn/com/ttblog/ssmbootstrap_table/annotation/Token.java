package cn.com.ttblog.ssmbootstrap_table.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 表单重复提交问题
 * http://www.oschina.net/question/1539369_2154772?fromerr=aQTSxo9I
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {
	boolean save() default false;
	boolean remove() default false;
	/**
	 * 默认值token
	 * @return
	 */
	String tokenname() default "token";
	/**
	 * 校验不通过后跳转url
	 */
	String failuri() default "error";
}