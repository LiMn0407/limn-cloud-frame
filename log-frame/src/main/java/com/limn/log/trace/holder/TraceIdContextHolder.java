package com.limn.log.trace.holder;

import com.limn.log.trace.constant.TraceIdConstant;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * 线程Id上下文持有者
 */
public class TraceIdContextHolder {

    public static String createTraceId(){
        return UUID.randomUUID().toString();
    }

    public static String getTraceId(){
        return MDC.get(TraceIdConstant.TRACE_ID);
    }

    public static void setTraceId(String traceId){
        MDC.put(TraceIdConstant.TRACE_ID,traceId);
    }

    public static void removeTraceId(){
        MDC.remove(TraceIdConstant.TRACE_ID);
    }

}
