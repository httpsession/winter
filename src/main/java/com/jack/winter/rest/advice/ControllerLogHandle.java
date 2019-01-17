package com.jack.winter.rest.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 正常响应（status:200）时打印“响应信息”日志
 * @author lilongjie
 *
 */
@Aspect
@Component
public class ControllerLogHandle {
	private static final Logger logger = LoggerFactory.getLogger(ControllerLogHandle.class);
   
    /**
     * 通过returning属性指定连接点方法返回的结果放置在result变量中，
     * 在返回通知方法中可以从result变量中获取连接点方法的返回结果
     */
    @SuppressWarnings("rawtypes")
	@AfterReturning(value="execution(* com.jack.winter.rest.controller.*.*(..))", returning="result")
    public void afterReturning(JoinPoint point, Object result){
    	///请求处理类
    	Class clz = point.getSignature().getDeclaringType();
    	//请求处理方法
    	String methodName = point.getSignature().getName(); 
    	logger.info("class: {}, method: {}, return: {}",clz,methodName,result);
    }

}
