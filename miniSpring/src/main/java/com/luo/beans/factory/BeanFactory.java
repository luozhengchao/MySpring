package com.luo.beans.factory;

import com.luo.BeanDefinition;
import com.luo.beans.BeansException;

/**
 * @author LZC
 * @date 2023/6/24 10:32
 */
public interface BeanFactory {

    Object getBean(String beanName) throws BeansException;

    void registerBeanDefinition(BeanDefinition beanDefinition);

}
