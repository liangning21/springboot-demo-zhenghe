package cn.lihuan.springboot.core.aop;

import cn.lihuan.springboot.common.annotaion.DataSource;
import cn.lihuan.springboot.core.datasource.DynamicDataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Order(-1) // 保证该AOP在@Transactional之前执行
public class DynamicDataSourceAspect {

    protected Logger logger = LoggerFactory.getLogger(getClass());


    @Pointcut("@annotation(cn.lihuan.springboot.common.annotaion.DataSource)"
            + "|| @within(cn.lihuan.springboot.common.annotaion.DataSource)")
    public void dsPointCut()  {
    }

    @Around("dsPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Method targetMethod = this.getTargetMethod(point);
        DataSource dataSource = targetMethod.getAnnotation(DataSource.class);//获取要切换的数据源
        if (dataSource != null)  {
            DynamicDataSourceContextHolder.setDataSourceType(dataSource.name().name());
        }
        try {
            return point.proceed();
        }
        finally  {
            // 销毁数据源 在执行方法之后
            DynamicDataSourceContextHolder.removeDataSourceType();
        }
    }

    /**
     * 获取目标方法
     */
    private Method getTargetMethod(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method agentMethod = methodSignature.getMethod();
        return agentMethod;
    }
}