package com.luo.core.xml;

import java.net.URL;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.luo.core.Resource;

/**
 * @author LZC
 * @date 2023/6/24 10:22
 */
public class ClassPathXmlResource implements Resource {

    private Document document;
    private Element rootElement;

    private Iterator elementIterator;


    public ClassPathXmlResource(String fileName) {
        SAXReader saxReader = new SAXReader();

        URL xmlPath = this.getClass().getClassLoader().getResource(fileName);
        try {
            this.document = saxReader.read(xmlPath);
            this.rootElement = saxReader.read(xmlPath).getRootElement();
            this.elementIterator = this.rootElement.elementIterator();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean hasNext() {
        return this.elementIterator.hasNext();
    }

    @Override
    public Object next() {
        return this.elementIterator.next();
    }
}
