package com.luo;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author LZC
 * @date 2023/6/23 16:50
 * 1.解析外部xml文件并保存到beanDefinition中
 * 2.创建对象
 */
public class ClassPathXmlApplicationContext {

    private final List<BeanDefinition> beanDefinitions = new ArrayList<>();
    private final Map<String, Object> singletons = new HashMap<>();

    public ClassPathXmlApplicationContext(String fileName) {
        this.readXml(fileName);
        this.instanceBeans();
    }

    /**
     * 对外提供表bean的核心方法
     *
     * @param beanName bean名称
     * @return bean对象
     */
    public Object getBean(String beanName) {
        return singletons.get(beanName);
    }


    private void readXml(String fileName) {
        URL xmlPath = this.getClass().getClassLoader().getResource(fileName);
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(xmlPath);

            //对配置文件中的每个bean进行处理
            Element rootElement = document.getRootElement();
            for (Element element : (List<Element>) rootElement.elements()) {
                //获取bean的基本信息
                String beanId = element.attributeValue("id");
                String beanClassName = element.attributeValue("class");

                //将bean的定义放到beanDefinitions中
                BeanDefinition beanDefinition = new BeanDefinition(beanId, beanClassName);
                beanDefinitions.add(beanDefinition);
            }
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 初始化bean
     * 利用发射创建对象并保持成map类型
     */
    private void instanceBeans() {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            try {
                singletons.put(beanDefinition.getId(), Class.forName(beanDefinition.getClassName()).newInstance());
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
