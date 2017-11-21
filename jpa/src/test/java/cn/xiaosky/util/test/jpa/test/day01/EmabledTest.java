package cn.xiaosky.util.test.jpa.test.day01;

import cn.xiaosky.util.test.jpa.entity.Student;
import cn.xiaosky.util.test.jpa.entity.val.Address;
import cn.xiaosky.util.test.jpa.test.BaseTest;
import org.junit.Test;

/**
 * 测试值对象的增删改查
 */
public class EmabledTest extends BaseTest{

    @Test
    public void testAdd(){
        testRun(()->{
            Student student=new Student("王二小");
            Address address=new Address("马王坡");
            student.getAddresses().add(address);
            entityManager.persist(student);
            return student;
        });
    }

    @Test
    public void add2(){
        testRun(()->{
            Student student = entityManager.getReference(Student.class, 2);
            Address address=new Address("九寨沟");
            student.getAddresses().add(address);
            return  address;
        });
    }
}
