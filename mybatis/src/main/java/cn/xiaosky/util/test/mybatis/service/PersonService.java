/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package cn.xiaosky.util.test.mybatis.service;

import cn.xiaosky.util.test.mybatis.dao.PersonDao;
import cn.xiaosky.util.test.mybatis.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wb-tby290972
 * @version $Id: PersonService.java, v 0.1 2017年10月24日 14:46 wb-tby290972 Exp $
 */
@Service
public class PersonService {
    @Autowired private PersonDao personDao;

    @Transactional(readOnly = false,isolation = Isolation.READ_COMMITTED)
    public void testUpdatePerson(){
        Person person = personDao.getById(1);
        if(person.getMoney()<1) {
            System.out.println("金额不足,不能取款");
            return;
        }
        System.out.println(Thread.currentThread().getName()+":开始取款");
        try {
            Thread.sleep(1000);
            person.setMoney(person.getMoney()-1);
            personDao.updateById(person);
            System.out.println(Thread.currentThread().getName()+":取款成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}