package com.lucky.common.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Lazy(false)
@Component("springUtils")
public class SpringUtils implements ApplicationContextAware {


    /** applicationContext */
    private static ApplicationContext applicationContext;

    /**
     * 不可实例化
     */
    private SpringUtils() {
    }
    public void destroy() throws Exception {
        applicationContext = null;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        if (SpringUtils.applicationContext == null) {
            SpringUtils.applicationContext = applicationContext;
        }
    }

    /**
     * 获取applicationContext
     *
     * @return applicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取实例
     *
     * @param name
     *            Bean名称
     * @return 实例
     */
    public static Object getBean(String name) {
        Assert.hasText(name,"name不能为空");
        return getApplicationContext().getBean(name);
    }

    /**
     * 获取实例
     *
     * @param name
     *            Bean名称
     * @param type
     *            Bean类型
     * @return 实例
     */
    public static <T> T getBean(String name, Class<T> type) {
        Assert.hasText(name,"name不能为空");
        Assert.notNull(type,"type不能为空");
        return getApplicationContext().getBean(name, type);
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }



}
