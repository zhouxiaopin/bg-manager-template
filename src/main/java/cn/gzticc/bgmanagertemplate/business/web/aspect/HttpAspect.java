package cn.gzticc.bgmanagertemplate.business.web.aspect;

import cn.gzticc.bgmanagertemplate.framework.pojo.SysAdmin;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *  http请求切面
 * Created by Administrator on 2017/11/20.
 */
@Component
@Aspect
public class HttpAspect {
    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);
    @Pointcut("execution(* cn.gzticc..controller.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = request.getSession();

        logger.info("*************请求开始**************");
        //url
        logger.info("url={}", request.getRequestURL());

        //method
        logger.info("method={}", request.getMethod());

        //ip
        logger.info("ip={}", request.getRemoteAddr());

        //类方法
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "_" + joinPoint.getSignature().getName());

        //参数
        logger.info("args={}", joinPoint.getArgs());

        SysAdmin adminInfo = (SysAdmin) session.getAttribute("adminInfo");
        if(null != adminInfo) {
            //操作人
            logger.info("currAdminUserName={}", adminInfo.getUserName());
        }
    }

    @After("log()")
    public void doAfter() {

    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        logger.info("response={}", object.toString());
        logger.info("*************请求结束**************");
    }
}
