package com.luo.beans.factory;

/**
 * @author LZC
 * @date 2023/6/24 11:38
 */
public interface SingletonBeanRegistry {

    void registerSingleton(String beanName, Object obj);

    Object getSingleton(String beanName);

    boolean containSingleton(String beanName);

    String[] getSingletonNames();

}
