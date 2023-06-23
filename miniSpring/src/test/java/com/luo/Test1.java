package com.luo;

import com.luo.AService;
import com.luo.ClassPathXmlApplicationContext;

/**
 * @author LZC
 * @date 2023/6/23 17:25
 */
public class Test1 {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

        AService aService = (AService) ctx.getBean("aService");
        aService.sayHello();
    }

}
