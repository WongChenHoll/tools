package com.jason.sourceCode.use.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * SpringAOP的使用
 *
 * @author WangChenHol
 * @date 2022-1-5 17:41
 **/
@Aspect
@Component
public class UserAOP {

//    @Pointcut("execution(* com.jason.sourceCode.use.project.controller.UserControlle.add(..))") // 只拦截UserControlle.add()方法
//    @Pointcut("execution(* com.jason.sourceCode.use.project.service.UserService.*(..))") // 拦截UserService接口中的所有的方法
//    @Pointcut("execution(* com.jason.sourceCode.use.project..*.*(..))") // 拦截project包和子包中的任何方法
//    @Pointcut("execution(* com.jason.sourceCode.use.project.controller.*.*(..))") // 只拦截controller该包中的任何方法
//    @Pointcut("within(com.jason.sourceCode.use.project..*)") // 拦截project包和子包中的任何方法
//    @Pointcut("within(com.jason.sourceCode.use.project.controller.*)") // 只拦截controller包下的方法
//    @Pointcut("this(com.jason.sourceCode.use.project.service.UserService)") // 拦截所有实现了UserService接口的类中方法
//    @Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)") // 任何使用了Transactional注解的方法
    @Pointcut("args(com.jason.sourceCode.use.model.User)")
//    @Pointcut("bean(*Impl)") // 任何spring管理的bean的名字中以Impl结尾的bean中的方法
    public void userAop() {
    }

    @Before("userAop()")
    public void beforeUser() {
        System.out.println("before");
    }

    @After("userAop()")
    public void afterUser() {
        System.out.println("after");
    }

    @Around("userAop()")
    public Object aroundUser(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("around before");
        Object proceed = null;
        try {
            proceed = proceedingJoinPoint.proceed();

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("around after");
        return proceed;
    }
}
