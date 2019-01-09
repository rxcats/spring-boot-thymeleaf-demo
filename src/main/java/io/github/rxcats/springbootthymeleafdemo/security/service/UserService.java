package io.github.rxcats.springbootthymeleafdemo.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.rxcats.springbootthymeleafdemo.security.entity.Role;
import io.github.rxcats.springbootthymeleafdemo.security.entity.User;
import io.github.rxcats.springbootthymeleafdemo.security.repository.RoleRepository;
import io.github.rxcats.springbootthymeleafdemo.security.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role role = roleRepository.findByRole("ADMIN");
        List<Role> ids = new ArrayList<>();
        ids.add(role);
        user.setRoles(ids);
        return userRepository.save(user);
    }

}
