package cn.xiaosky.util.test.jpa.test;

import cn.xiaosky.util.test.jpa.config.JpaRoot;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.function.Supplier;

public class BaseTest {
    protected ApplicationContext applicationContext;
    protected EntityManagerFactory entityManagerFactory;
    protected EntityManager entityManager;
    private ObjectMapper objectMapper=new ObjectMapper();

    // private EntityTransaction transaction;
    @Before
    public void init(){
        applicationContext=new AnnotationConfigApplicationContext(JpaRoot.class);
        entityManagerFactory=applicationContext.getBean("entityManagerFactory",EntityManagerFactory.class);
        entityManager = entityManagerFactory.createEntityManager();
    }
    protected void printJson(Object object){
        try {
            System.out.println(objectMapper.writeValueAsString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    protected void testRun(Supplier supplier){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        printJson(supplier.get());
        transaction.commit();
    }

}
