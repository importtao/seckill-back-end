package me.importtao.seckillbackend.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author importta0
 * @version V1.0
 * @package me.importtao.springbootdemo.aspect
 * @class LogAspect
 * @description: 拦截http请求添加日志切面
 * @date 2017/12/5 16:00
 */
@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(public * me.importtao.seckillbackend.controller.*.*(..))")
    public void log(){}

    @Before("log()")
    public void beginLog(){
        logger.info("----------------------------开始调用controller-----------------------------------");
    }

    @After("log()")
    public void afterLog(){
        logger.info("----------------------------controller调用完成-----------------------------------");
    }

    /**
     * @description
     * @author importtao
     * @date 2017/12/14 10:46
     * @param
     * @return
     */
    @Around("log()")
    public Object aroundLog(ProceedingJoinPoint proceedingJoinPoint){
        Object result = null;
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Object[] param = proceedingJoinPoint.getArgs();
        try{
            result = proceedingJoinPoint.proceed();
        }catch (Throwable e){
            logger.info(e.getMessage());
        }
        logger.info("class:{}",proceedingJoinPoint.getTarget().getClass());
        logger.info("method:{}",method.getName());
        StringBuilder paramStr = new StringBuilder();
        for(Object s:param){
            paramStr.append(s);
            paramStr.append("  ");
        }
        logger.info("param:{}",String.valueOf(paramStr));
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        logger.info("uri:{}",request.getRequestURI());
        logger.info("request method:{}",request.getMethod());
        logger.info("URL:{}",request.getRequestURL());
        logger.info("RemoteUser:{}",request.getRemoteUser());
        logger.info("Remoteaddr:{}",request.getRemoteAddr());
        logger.info("Remotehost:{}",request.getRemoteHost());
        logger.info("return:{}", JSON.toJSON(result));
        return result;
    }

}
