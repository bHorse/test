/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package cn.xiaosky.util.test.run;

import cn.xiaosky.util.test.entity.Person;
import cn.xiaosky.util.test.main.AppMainRun;
import cn.xiaosky.util.test.service.PersonService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wb-tby290972
 * @version $Id: PersonServiceRun.java, v 0.1 2017年10月24日 14:57 wb-tby290972 Exp $
 */
public class PersonServiceRun extends AppMainRun{

    PersonService personService;
    public void testTrans(){

        personService.testUpdatePerson();
    }

    public static void main(String[] args) {

        PersonServiceRun personServiceRun = new PersonServiceRun();
        personServiceRun.personService = personServiceRun.getBean("personService", PersonService.class);

        ExecutorService executorService = Executors.newFixedThreadPool(20);
        Runnable runnable=()->personServiceRun.testTrans();
        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);

    }
}