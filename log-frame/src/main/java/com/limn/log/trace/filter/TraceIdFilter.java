package com.limn.log.trace.filter;

import com.limn.log.trace.constant.TraceIdConstant;
import com.limn.log.trace.holder.TraceIdContextHolder;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
public class TraceIdFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest =  (HttpServletRequest) request;
        String ip = getIpAddr(httpServletRequest);
        String requestUrl  = httpServletRequest.getRequestURI();
//        放行Swagger文档请求
        if (requestUrl.startsWith("/webjars") || requestUrl.startsWith("/v3") || requestUrl.startsWith("/doc")){
            chain.doFilter(request,response);
        }else {
//            请求头获取TraceId，涉及到系统服务调用会传TraceId
            String traceId = httpServletRequest.getHeader(TraceIdConstant.TRACE_ID);
            if (StringUtils.isBlank(traceId)){
                traceId = TraceIdContextHolder.createTraceId();
            }
            log.info("拦截请求ip为 {}, 请求路径为 {}, 链路id为 {}",ip,requestUrl,traceId);
            TraceIdContextHolder.setTraceId(traceId);
            chain.doFilter(request,response);
            TraceIdContextHolder.removeTraceId();
        }
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    ipAddress = getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            // "***.***.***.***".length() == 15
            if (ipAddress != null && ipAddress.length() > 15) {
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }

            // 解决请求和响应的IP一致且通过浏览器请求时，request.getRemoteAddr()为"0:0:0:0:0:0:0:1"
            if("0:0:0:0:0:0:0:1".equals(ipAddress)){
                ipAddress = getHostAddress();
            }
        } catch (Exception e) {
            ipAddress = "";
        }

        return ipAddress;
    }

    private static String getHostAddress(){
        // 根据网卡取本机配置的IP
        InetAddress inet = null;
        try {
            inet = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return inet.getHostAddress();
    }



}
