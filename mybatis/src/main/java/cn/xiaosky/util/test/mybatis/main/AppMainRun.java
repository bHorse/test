/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package cn.xiaosky.util.test.mybatis.main;

import cn.xiaosky.util.test.mybatis.config.RootConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wb-tby290972
 * @version $Id: AppMainRun.java, v 0.1 2017年10月24日 10:31 wb-tby290972 Exp $
 */
public class AppMainRun {
    protected  <T> T  getBean(String beanName, Class<T> t){
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(RootConfig.class);
        return applicationContext.getBean(beanName,t);
    }
}