package com.luo.beans.support;

import com.luo.BeanDefinition;

/**
 * @author dianmu
 * @date 2023/6/24 19:06
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String name, BeanDefinition bd);

    void removeBeanDefinition(String name);

    BeanDefinition getBeanDefinition(String name);

    boolean containsBeanDefinition(String name);

}
