package io.github.rxcats.springbootthymeleafdemo.security.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

import io.github.rxcats.springbootthymeleafdemo.security.entity.User;

@Slf4j
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void findByEmail() {
        User user = userRepository.findByEmail("admin@c.com");
        log.info("user:{}", user);
    }

}