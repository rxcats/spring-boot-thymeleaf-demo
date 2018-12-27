package io.github.rxcats.springbootthymeleafdemo.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.rxcats.springbootthymeleafdemo.security.entity.Role;

public interface UserRoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
