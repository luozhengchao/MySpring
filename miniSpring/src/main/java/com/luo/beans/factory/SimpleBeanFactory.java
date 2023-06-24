package com.luo.beans.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.luo.BeanDefinition;
import com.luo.beans.BeansException;

/**
 * @author LZC
 * @date 2023/6/24 10:44
 */
public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    private final Map<String, BeanDefinition> beanDefinitions = new ConcurrentHashMap<>();

    public SimpleBeanFactory() {
    }

    @Override
    public Object getBean(String beanName) throws BeansException {
        Object singleton = this.getSingleton(beanName);
        if (singleton == null) {
            BeanDefinition beanDefinition = beanDefinitions.get(beanName);
            try {
                singleton = Class.forName(beanDefinition.getClassName()).newInstance();

                this.registerSingleton(beanName, singleton);
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            return singleton;
        }

        return null;
    }


    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanDefinitions.put(beanDefinition.getId(), beanDefinition);
    }

    @Override
    public boolean containsBean(String beanName) {
        return this.containSingleton(beanName);
    }

    @Override
    public void registerBean(String beanName, Object bean) {
        this.registerSingleton(beanName, bean);
    }
}
