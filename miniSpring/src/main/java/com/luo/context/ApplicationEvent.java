package com.luo.context;

import java.util.EventObject;

/**
 * @author dianmu
 * @date 2023/6/24 17:29
 */
public class ApplicationEvent extends EventObject {

    public static final long serialVersionUID = 1L;

    public ApplicationEvent(Object source) {
        super(source);
    }
}
