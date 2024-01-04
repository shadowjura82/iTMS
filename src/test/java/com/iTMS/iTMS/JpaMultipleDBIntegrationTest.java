package com.iTMS.iTMS;

import com.iTMS.iTMS.models.OracleTask;
import com.iTMS.iTMS.repositories.OracleTaskRepository;

import myDB.repositories.PostgreSQLTaskRepository;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableTransactionManagement
public class JpaMultipleDBIntegrationTest {

    @Autowired
    private OracleTaskRepository oracleTaskRepository;

    @Autowired
    private PostgreSQLTaskRepository productRepository;

    @Test
    @Transactional("userTransactionManager")
    public void whenCreatingUser_thenCreated() {
        OracleTask oracleTask = new OracleTask();
        oracleTask.setClient("CLIENT");
        oracleTask.setClientId("123456");
        oracleTask = oracleTaskRepository.save(oracleTask);

//        assertNotNull(oracleTaskRepository.findOne(oracleTask.getId()));
    }

//    @Test
//    @Transactional("userTransactionManager")
//    public void whenCreatingUsersWithSameEmail_thenRollback() {
//        User user1 = new User();
//        user1.setName("John");
//        user1.setEmail("john@test.com");
//        user1.setAge(20);
//        user1 = oracleTaskRepository.save(user1);
//        assertNotNull(oracleTaskRepository.findOne(user1.getId()));
//
//        User user2 = new User();
//        user2.setName("Tom");
//        user2.setEmail("john@test.com");
//        user2.setAge(10);
//        try {
//            user2 = oracleTaskRepository.save(user2);
//        } catch (DataIntegrityViolationException e) {
//        }
//
//        assertNull(oracleTaskRepository.findOne(user2.getId()));
//    }
//
//    @Test
//    @Transactional("productTransactionManager")
//    public void whenCreatingProduct_thenCreated() {
//        Product product = new Product();
//        product.setName("Book");
//        product.setId(2);
//        product.setPrice(20);
//        product = productRepository.save(product);
//
//        assertNotNull(productRepository.findOne(product.getId()));
//    }
}
