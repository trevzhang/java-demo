package com.trevzhang.demo.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * ��ӡSpring�������е�Bean����
 *
 * @author syj
 */
@Component
public class ApplicationContextBean implements ApplicationContextAware, InitializingBean {

    public static ApplicationContext applicationContext;

    /**
     * ��ȡ ApplicationContext
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextBean.applicationContext = applicationContext;
    }

    /**
     * ��ӡIOC���������е�Bean����
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        String[] names = applicationContext.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(">>>>>>" + name);
        }
        System.out.println("------\nBean �ܼ�:" + applicationContext.getBeanDefinitionCount());
    }
}