package com.luo.context;

/**
 * @author dianmu
 * @date 2023/6/24 17:28
 */
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);
}
