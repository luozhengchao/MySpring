package com.luo.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author dianmu
 * @date 2023/6/24 17:49
 */
public class ArgumentValues {

    private final Map<Integer, ArgumentValue> indexedArgumentValues = new HashMap<>();

    private final List<ArgumentValue> genericArgumentValues = new LinkedList<>();

    public ArgumentValues() {
    }

    public boolean hasIndexedArgumentValue(int index) {
        return this.indexedArgumentValues.containsKey(index);
    }

    public void addGenericArgumentValue(Object value, String type, String name) {
        this.genericArgumentValues.add(new ArgumentValue(value, type, name));
    }

    public int getArgumentCount() {
        return this.indexedArgumentValues.size();
    }

    public boolean isEmpty() {
        return this.indexedArgumentValues.isEmpty();
    }

    private void addArgumentValue(Integer key, ArgumentValue newValue) {
        this.indexedArgumentValues.put(key, newValue);
    }

    private void addGenericArgumentValue(ArgumentValue newValue) {
        if (newValue != null) {
            genericArgumentValues.removeIf(curValue -> curValue.getName().equals(newValue.getName()));
            this.genericArgumentValues.add(newValue);
        }
    }
}
