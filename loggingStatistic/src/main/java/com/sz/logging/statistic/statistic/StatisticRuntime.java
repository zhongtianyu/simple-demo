package com.sz.logging.statistic.statistic;

import java.lang.annotation.*;

/**
 * @author : Vander
 * @date :   2021-04-08
 * @description : 添加此注解后，会打印出方法相应的运行时间
 * @see StatisticRuntimeAspect
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface StatisticRuntime {
    /**
     * 可选值：min/s/ms
     *
     * @return
     */
    String value() default "s";
    /**
     * 打印日志对应的类
     */
    Class clazz() default StatisticRuntimeAspect.class;
    /**
     * 方法描述前缀，methodDesc可以传入占位符，如%s入库耗时
     */
    String methodDesc() default "";
    /**
     * 填写上述占位符对应的真实内容
     */
    String[] args() default {};
}
