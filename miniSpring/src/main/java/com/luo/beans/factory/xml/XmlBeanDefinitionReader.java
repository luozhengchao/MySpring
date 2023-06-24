package com.luo.beans.factory.xml;

import org.dom4j.Element;

import com.luo.BeanDefinition;
import com.luo.beans.factory.BeanFactory;
import com.luo.core.Resource;

/**
 * @author LZC
 * @date 2023/6/24 10:34
 */
public class XmlBeanDefinitionReader {

    private final BeanFactory beanFactory;

    public XmlBeanDefinitionReader(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String beanId = element.attributeValue("id");
            String beanName = element.attributeValue("class");

            BeanDefinition beanDefinition = new BeanDefinition(beanId, beanName);
            this.beanFactory.registerBeanDefinition(beanDefinition);
        }
    }

}
