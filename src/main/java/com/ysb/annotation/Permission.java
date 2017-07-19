package com.ysb.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限控制
 * Created by wushenjun on 2017/7/19.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Permission {
	/**
	 * 是否需要登录，缺省为需要
	 * @return
	 */
	boolean loginReqired() default true;
	
}
