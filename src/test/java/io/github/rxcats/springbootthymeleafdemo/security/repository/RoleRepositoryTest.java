package io.github.rxcats.springbootthymeleafdemo.security.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

import io.github.rxcats.springbootthymeleafdemo.security.entity.Role;

@Slf4j
@SpringBootTest
class RoleRepositoryTest {

    @Autowired
    RoleRepository roleRepository;

    @Test
    void findByRole() {
        Role role = roleRepository.findByRole("ADMIN");
        log.info("role:{}", role);
    }

}