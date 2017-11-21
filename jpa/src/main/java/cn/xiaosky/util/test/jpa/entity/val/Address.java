package cn.xiaosky.util.test.jpa.entity.val;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String name;

    public Address() {
    }

    public Address(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
