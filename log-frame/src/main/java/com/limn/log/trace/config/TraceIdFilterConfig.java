package com.limn.log.trace.config;

import com.limn.log.trace.filter.TraceIdFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class TraceIdFilterConfig {

    @Bean
    public FilterRegistrationBean<Filter> traceIdFilter(){
        FilterRegistrationBean<Filter> traceIdFilterRegistrationBean = new FilterRegistrationBean<>();
        traceIdFilterRegistrationBean.setFilter(new TraceIdFilter());
        traceIdFilterRegistrationBean.setEnabled(true);
        traceIdFilterRegistrationBean.setOrder(1);
        traceIdFilterRegistrationBean.setUrlPatterns(List.of("/**"));
        return traceIdFilterRegistrationBean;
    }



}
