package com.limn.dao.db.aspect;


import com.limn.dao.db.annotation.DynamicDb;
import com.limn.dao.db.constant.DynamicDataSourceConstant;
import com.limn.dao.db.holder.DynamicDataSourceHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
@Slf4j
@Order(1)
public class DynamicDataSourceAspect {

    public DynamicDataSourceAspect() {
    }

    @Pointcut(value = "@annotation(com.limn.dao.db.annotation.DynamicDb)")
    public void dynamicDataSourcePointcut(){

    }

    @Around("dynamicDataSourcePointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method targetMethod = methodSignature.getMethod();
        String dataSource = DynamicDataSourceConstant.PRIMARY;
        if (targetMethod.isAnnotationPresent(DynamicDb.class)){
            DynamicDb dbName = targetMethod.getAnnotation(DynamicDb.class);
            dataSource= dbName.value();
        }
        DynamicDataSourceHolder.setDbName(dataSource);
        log.info("Generate data source : {}",dataSource);
        return joinPoint.proceed();
    }

    @After("dynamicDataSourcePointcut()")
    public void clear(){
        DynamicDataSourceHolder.removeDbName();
    }

}
