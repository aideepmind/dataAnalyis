package com.hejia.dataAnalysis.module.common.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.hejia.dataAnalysis.module.common.log.LogOperType;
import com.hejia.dataAnalysis.module.common.log.LogOperValue;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ControllerLog {
	int logOperType() default LogOperType.UNKNOWN_OPER_TYPE;
	int logOperValue() default LogOperValue.UNKNOWN_OPER_VALUE;
}
