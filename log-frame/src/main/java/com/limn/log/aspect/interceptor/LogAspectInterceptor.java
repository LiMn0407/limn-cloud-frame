package com.limn.log.aspect.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

@Slf4j
public class LogAspectInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
//        1.获取方法参数
        Object[] aspectMethodArgs = invocation.getArguments();

//        2.获取方法名称
        Method aspectMethod = invocation.getMethod();
        String className = aspectMethod.getDeclaringClass().getName();
        String aspectMethodName = className + "." +  aspectMethod.getName();

        log.info("方法名：{}, 方法参数：{} ",aspectMethodName, Arrays.toString(aspectMethodArgs));

//        3.记录开始时间
        long start = System.currentTimeMillis();

//        4.执行方法
        Object result = invocation.proceed();

//        5.统计
        long end = System.currentTimeMillis();
        log.info("方法名：{}, 方法返回值：{}, 一共耗时：{} ms",aspectMethodName, Objects.isNull(result)?"无返回值":result.toString(),end - start);

        return result;
    }
}
