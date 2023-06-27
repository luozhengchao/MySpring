package com.luo.beans.factory.xml;

import java.util.List;
import java.util.Properties;

import org.dom4j.Element;

import com.luo.BeanDefinition;
import com.luo.beans.ArgumentValue;
import com.luo.beans.ArgumentValues;
import com.luo.beans.PropertyValue;
import com.luo.beans.PropertyValues;
import com.luo.beans.factory.BeanFactory;
import com.luo.beans.factory.SimpleBeanFactory;
import com.luo.core.Resource;

/**
 * @author LZC
 * @date 2023/6/24 10:34
 */
public class XmlBeanDefinitionReader {

    private final SimpleBeanFactory simpleBeanFactory;

    public XmlBeanDefinitionReader(SimpleBeanFactory simpleBeanFactory) {
        this.simpleBeanFactory = simpleBeanFactory;
    }

    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String beanId = element.attributeValue("id");
            String beanName = element.attributeValue("class");

            BeanDefinition beanDefinition = new BeanDefinition(beanId, beanName);

            //处理properties 和 args
            var propertyValues = new PropertyValues();
            var propertyElements = (List<Element>) element.elements("properties");
            for (Element property : propertyElements) {
                var pType = property.attributeValue("type");
                var pName = property.attributeValue("name");
                var pValue = property.attributeValue("value");
                propertyValues.addPropertyValue(pType, pName, pValue);
            }

            var argValues = new ArgumentValues();
            var argElements = (List<Element>) element.elements("constructor-args");
            for (Element property : argElements) {
                var cType = property.attributeValue("type");
                var cName = property.attributeValue("name");
                var cValue = property.attributeValue("value");
                argValues.addGenericArgumentValue(cValue, cType, cName);
            }

            beanDefinition.setPropertyValues(propertyValues);
            beanDefinition.setConstructorArgumentValues(argValues);

            this.simpleBeanFactory.registerBeanDefinition(beanId, beanDefinition);
        }
    }

}
