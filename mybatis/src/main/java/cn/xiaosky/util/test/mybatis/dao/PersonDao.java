/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package cn.xiaosky.util.test.mybatis.dao;

import cn.xiaosky.util.test.mybatis.entity.Person;

/**
 * @author wb-tby290972
 * @version $Id: PersonDao.java, v 0.1 2017年10月24日 13:37 wb-tby290972 Exp $
 */
public interface PersonDao {
    Person getById(Integer id);
    void updateById(Person person);
}