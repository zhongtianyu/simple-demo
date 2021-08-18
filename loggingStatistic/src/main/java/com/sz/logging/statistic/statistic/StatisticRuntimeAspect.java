package com.sz.logging.statistic.statistic;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : Vander
 * @date :   2021-04-08
 * @description :
 */
@Slf4j
@Aspect
public class StatisticRuntimeAspect {

    private static Map<Class, Logger> loggerCache = new ConcurrentHashMap<>();

    @Around("@annotation(statisticRuntime)")
    public Object runTimeToLog(ProceedingJoinPoint proceedingJoinPoint, StatisticRuntime statisticRuntime) throws Throwable {
        // 获取对应类的Logger
        Class clazz = statisticRuntime.clazz();
        Logger log = getLogger(clazz);
        // 获取方法耗时的输出时间单位
        String timeUnit = statisticRuntime.value();

        // 获取添加了此注解的方法名
        Signature signature = proceedingJoinPoint.getSignature();
        String methodName = signature.getName();
        // 获取方法的参数名
        String[] paramNames = ((CodeSignature) proceedingJoinPoint.getSignature()).getParameterNames();
        // 注解中的参数列表，将用于拼接methodDesc
        String[] annotationArgs = statisticRuntime.args();
        // 获取方法描述-将用于打印日志语句
        String methodDesc = statisticRuntime.methodDesc();
        // 方法中的参数列表
        Object[] args = proceedingJoinPoint.getArgs();

        String[] results = new String[annotationArgs.length];
        for (int i = 0, resultIndex = 0; i < annotationArgs.length; i++) {
            String[] paramNameAndFieldName = annotationArgs[i].split("\\.");
            String paramName = paramNameAndFieldName[0];
            String fieldName = paramNameAndFieldName[1];
            for (int j = 0; j < paramNames.length; j++) {
                if(paramName.equals(paramNames[j])) {
                    results[resultIndex] = getValueFromObj(args[j], fieldName);
                    resultIndex++;
                }
            }
        }
        methodDesc = String.format(methodDesc, results);

        Instant runBefore = Instant.now();
        Object result = proceedingJoinPoint.proceed(args);
        Instant runAfter = Instant.now();
        String durationStr;
        switch (timeUnit) {
            case "min": {
                durationStr = Duration.between(runBefore, runAfter).toMinutes() + " minutes";
                break;
            }
            case "s": {
                durationStr = Duration.between(runBefore, runAfter).toMillis() / 1000 + " seconds";
                break;
            }
            case "ms": {
                durationStr = Duration.between(runBefore, runAfter).toMillis() + " millSeconds";
                break;
            }
            default:
                durationStr = -1 + "";
        }
        if (!StringUtils.hasText(methodDesc)) {
            log.info("method : {} 's run time duration {}", methodName, durationStr);
        } else {
            log.info("{}{}", methodDesc, durationStr);
        }
        return result;
    }

    /**
     *
     *
     * @param obj
     * @param fieldName
     * @return
     * @throws NoSuchFieldException
     */
    private String getValueFromObj(Object obj, String fieldName) {
        String result = "";

        // 若为字符串类型，则直接返回
        if (obj instanceof String) {
            return obj.toString();
        }

        try {
            Field declaredField = obj.getClass().getDeclaredField(fieldName);
            declaredField.setAccessible(true);
            result = declaredField.get(obj).toString();
            // 还原现场
            declaredField.setAccessible(false);
        } catch (NoSuchFieldException e) {
            log.info("处理StatisticRuntime注解时出现异常，找不到相应对象对应的Field!", e);
        } catch (IllegalAccessException e) {
            log.info("处理StatisticRuntime注解时出现异常，无法访问相应的Field!", e);
        }
        return result;
    }

    /**
     * 从缓存中获取Logger，不存在则创建后放入缓存
     *
     * @param clazz
     * @return
     */
    private Logger getLogger(Class clazz) {
        Logger logger = loggerCache.get(clazz);
        if (null != logger) {
            return logger;
        }
        logger = LoggerFactory.getLogger(clazz);
        loggerCache.put(clazz, logger);
        return logger;
    }

}
