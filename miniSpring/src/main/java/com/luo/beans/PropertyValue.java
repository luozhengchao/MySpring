package com.luo.beans;

/**
 * @author dianmu
 * @date 2023/6/24 17:44
 */
public class PropertyValue {

    private final String type;

    private final String name;

    private final Object value;

    public PropertyValue(String type, String name, Object value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
