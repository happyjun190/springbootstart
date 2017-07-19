package com.ysb.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 判断哪种profile需要启用swagger2
 *
 * Created by wushenjun on 2017-05-23.
 */
public class AutoSwagger2Condition implements Condition {
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String profileStr = context.getEnvironment().getProperty("spring.profiles.active");
		String envStr = context.getEnvironment().getProperty("ysb.boot.swagger2.env");

		if(StringUtils.isAnyBlank(profileStr, envStr)){
			return true;
		}

		String[] envs = StringUtils.split(envStr, ',');
		for(String env: envs){
			if(profileStr.equals(env.trim())){
				return true;
			}
		}

		return false;
	}
}
