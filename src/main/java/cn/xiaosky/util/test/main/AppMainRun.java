/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package cn.xiaosky.util.test.main;

import cn.xiaosky.util.test.config.RootConfig;
import cn.xiaosky.util.test.dao.PersonDao;
import cn.xiaosky.util.test.entity.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

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