package com.luo.beans.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.luo.BeanDefinition;
import com.luo.beans.BeansException;

/**
 * @author LZC
 * @date 2023/6/24 10:44
 */
public class SimpleBeanFactory implements BeanFactory {

    private final List<BeanDefinition> beanDefinitions = new ArrayList<>();

    private final List<String> beanNames = new ArrayList<>();

    private final Map<String, Object> singletons = new HashMap<>();


    @Override
    public Object getBean(String beanName) throws BeansException {
        Object singleton = singletons.get(beanName);
        if (singleton == null) {
            int i = beanNames.indexOf(beanName);
            if (i == -1) {
                throw new BeansException();
            }

            BeanDefinition beanDefinition = beanDefinitions.get(i);
            try {
                singleton = Class.forName(beanDefinition.getClassName()).newInstance();

                singletons.put(beanName, singleton);
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            return singleton;
        }

        return null;
    }

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanDefinitions.add(beanDefinition);
        this.beanNames.add(beanDefinition.getId());
    }
}
