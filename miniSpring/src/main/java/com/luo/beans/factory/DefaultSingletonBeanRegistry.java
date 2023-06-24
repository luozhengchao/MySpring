package com.luo.beans.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author LZC
 * @date 2023/6/24 11:42
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    protected final List<String> beanNames = new ArrayList<>();

    protected final Map<String, Object> singletons = new ConcurrentHashMap<>();

    @Override
    public void registerSingleton(String beanName, Object obj) {
        synchronized (this.singletons) {
            this.singletons.put(beanName, obj);
            this.beanNames.add(beanName);
        }
    }

    @Override
    public Object getSingleton(String beanName) {
        return this.singletons.get(beanName);
    }

    @Override
    public boolean containSingleton(String beanName) {
        return this.singletons.containsKey(beanName);
    }

    @Override
    public String[] getSingletonNames() {
        return this.beanNames.toArray(new String[0]);
    }
}
