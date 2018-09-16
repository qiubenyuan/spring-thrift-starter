package ru.trylogic.spring.boot.thrift.aop;

import org.apache.thrift.TApplicationException;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

public class LoggingThriftMethodInterceptor implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        log.info("Thrift method {}.{}() is called with args: {}",
                 target.getClass().getSimpleName(),
                 method.getName(),
                 args);
    }

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        log.info("Thrift method {}.{}() returns this: {}",
                 target.getClass().getSimpleName(),
                 method.getName(),
                 returnValue);
    }

    @SuppressWarnings("unused")
    public void afterThrowing(Method method, Object[] args, Object target, Exception e) throws Throwable {
        if (!(e instanceof TException)) {
            log.warn("Unexpected exception in " + target.getClass().getCanonicalName() + "." + method.getName(), e);
            throw new TApplicationException(TApplicationException.INTERNAL_ERROR, e.toString());
        }
        log.warn("Exception in Thrift method {}.{}() when called with args: {}", target.getClass().getSimpleName(), method.getName(), args, e);
    }
}
