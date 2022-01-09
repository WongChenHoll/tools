package com.jason.sourceCode.use.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * 使用Spring中的org.springframework.beans.factory.BeanFactory 类
 *
 * @author WangChenHol
 * @date 2021-12-31 17:34
 **/
@Component
public class TestBeanFactoryAware implements BeanFactoryAware {
    private static BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        TestBeanFactoryAware.beanFactory = beanFactory;
        System.out.println("获取bean......");
    }

    public static Object getBean(String beanName) {
        return beanFactory.getBean(beanName);
    }
}
