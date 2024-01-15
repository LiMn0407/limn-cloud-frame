package com.limn.log.aspect.config;

import com.limn.log.aspect.interceptor.LogAspectInterceptor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@ConditionalOnProperty(name = "limn.log.aspect.enable",havingValue = "true")
public class LogAspectConfig {

    @Bean
    @Order(Integer.MIN_VALUE)
    public DefaultPointcutAdvisor logAspectAdvisor(){
//        切面方法
        LogAspectInterceptor interceptor = new LogAspectInterceptor();

//        切入点
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("@annotation(com.limn.log.aspect.annotation.LogAspect");

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(interceptor);
        return advisor;

    }

}
