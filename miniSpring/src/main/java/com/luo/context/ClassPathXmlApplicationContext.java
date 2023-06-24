package com.luo.context;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.luo.BeanDefinition;
import com.luo.beans.BeansException;
import com.luo.beans.factory.BeanFactory;
import com.luo.beans.factory.SimpleBeanFactory;
import com.luo.beans.factory.xml.XmlBeanDefinitionReader;
import com.luo.core.Resource;
import com.luo.core.xml.ClassPathXmlResource;

/**
 * @author LZC
 * @date 2023/6/23 16:50
 * 1.解析外部xml文件并保存到beanDefinition中
 * 2.创建对象
 */
public class ClassPathXmlApplicationContext implements BeanFactory {

    private final List<BeanDefinition> beanDefinitions = new ArrayList<>();
    private final Map<String, Object> singletons = new HashMap<>();

    private final BeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String fileName) {
        Resource resource = new ClassPathXmlResource(fileName);
        BeanFactory beanFactory = new SimpleBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);
        this.beanFactory = beanFactory;
    }

    /**
     * 对外提供表bean的核心方法
     *
     * @param beanName bean名称
     * @return bean对象
     */
    public Object getBean(String beanName) throws BeansException {
        return this.beanFactory.getBean(beanName);
    }

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanFactory.registerBeanDefinition(beanDefinition);
    }


}
