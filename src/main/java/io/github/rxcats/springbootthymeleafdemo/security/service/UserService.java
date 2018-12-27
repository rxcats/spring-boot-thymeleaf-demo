package io.github.rxcats.springbootthymeleafdemo.security.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.rxcats.springbootthymeleafdemo.security.entity.Role;
import io.github.rxcats.springbootthymeleafdemo.security.entity.User;
import io.github.rxcats.springbootthymeleafdemo.security.repository.UserRepository;
import io.github.rxcats.springbootthymeleafdemo.security.repository.UserRoleRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role role = userRoleRepository.findByRole("ADMIN");
        user.setRoles(Set.of(role));
        return userRepository.save(user);
    }

}
