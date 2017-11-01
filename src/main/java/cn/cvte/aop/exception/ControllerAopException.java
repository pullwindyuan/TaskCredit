package cn.cvte.aop.exception;

import cn.cvte.dto.ResponseDto;
import cn.cvte.enums.ResultCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ControllerAopException {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAopException.class);

    @Pointcut("execution(* cn.cvte.controller..*(..))")
    public void exception(){}

    @Around("exception()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        Object result;
        try {
            result = pjp.proceed();
        } catch (Exception e) {
            result = ResponseDto.erro("服务器异常");
            e.printStackTrace();
            logger.error("");
        }
        return result;
    }

    @Pointcut("execution(* cn.cvte.service..*(..))")
    public void service(){}

    @Around("service()")
    public Object aroundService(ProceedingJoinPoint pjp) throws Throwable{
        Object result;
        try {
            result = pjp.proceed();
        } catch (Exception e) {
            result = ResultCode.ERRO;
            e.printStackTrace();
            logger.error("");
        }
        return result;
    }

}
