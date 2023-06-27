package com.luo.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dianmu
 * @date 2023/6/24 18:46
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList;

    public PropertyValues() {
        this.propertyValueList = new ArrayList<>();
    }

    public List<PropertyValue> getPropertyValueList() {
        return this.propertyValueList;
    }

    public int size() {
        return this.propertyValueList.size();
    }

    public void addPropertyValue(PropertyValue value) {
        this.propertyValueList.add(value);
    }

    public void addPropertyValue(String type, String name, Object value) {
        this.propertyValueList.add(new PropertyValue(type, name, value));
    }

    public void removePropertyValue(PropertyValue pv) {
        this.propertyValueList.remove(pv);
    }

    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue propertyValue : propertyValueList) {
            if (propertyValue.getName().equals(propertyName)) {
                return propertyValue;
            }
        }
        return null;
    }

    public Object get(String propertyName) {
        var propertyValue = getPropertyValue(propertyName);
        if (null != propertyValue) {
            return propertyValue.getValue();
        }
        return null;
    }

    public boolean contains(String propertyName) {
        return null != getPropertyValue(propertyName);
    }

    public boolean isEmpty() {
        return this.propertyValueList.isEmpty();
    }

}
