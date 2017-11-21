/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package cn.xiaosky.util.test.mybatis.entity;

/**
 * @author wb-tby290972
 * @version $Id: Person.java, v 0.1 2017年10月24日 13:37 wb-tby290972 Exp $
 */
public class Person {
    private Integer id;
    private String name;
    private Integer money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}