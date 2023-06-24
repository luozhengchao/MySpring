package com.luo.beans.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.luo.BeanDefinition;
import com.luo.beans.BeansException;

/**
 * @author LZC
 * @date 2023/6/24 10:44
 */
public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public SimpleBeanFactory() {
    }

    @Override
    public Object getBean(String beanName) throws BeansException {
        Object singleton = this.getSingleton(beanName);
        if (singleton == null) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
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


    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(beanDefinition.getId(), beanDefinition);
        this.beanNames.add(name);
        if (!beanDefinition.isLazyInit()) {
            try {
                getBean(name);
            } catch (BeansException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean containsBean(String beanName) {
        return this.containSingleton(beanName);
    }

    public void registerBean(String beanName, Object bean) {
        this.registerSingleton(beanName, bean);
    }

    @Override
    public boolean isSingletonBean(String name) {
        return this.beanDefinitionMap.get(name).isSingleton();
    }

    @Override
    public boolean isProperty(String name) {
        return this.beanDefinitionMap.get(name).isProperty();
    }

    @Override
    public Class<?> getType(String beanName) {
        return this.beanDefinitionMap.get(beanName).getClass();
    }
}
