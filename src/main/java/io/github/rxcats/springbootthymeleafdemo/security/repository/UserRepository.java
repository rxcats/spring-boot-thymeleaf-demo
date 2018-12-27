package io.github.rxcats.springbootthymeleafdemo.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.rxcats.springbootthymeleafdemo.security.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
