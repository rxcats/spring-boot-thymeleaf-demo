package io.github.rxcats.springbootthymeleafdemo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

import io.github.rxcats.springbootthymeleafdemo.security.entity.Role;
import io.github.rxcats.springbootthymeleafdemo.security.entity.User;
import io.github.rxcats.springbootthymeleafdemo.security.repository.RoleRepository;
import io.github.rxcats.springbootthymeleafdemo.security.repository.UserRepository;

@Slf4j
@EnableMongoRepositories
@SpringBootApplication
public class SpringBootThymeleafDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootThymeleafDemoApplication.class, args);
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    // create default admin account
    @EventListener(ApplicationReadyEvent.class)
    public void checkDefaultAdminAccount() {

        log.info("application ready!");

        Role adminRole = roleRepository.findByRole("ADMIN");
        if (adminRole == null) {
            adminRole = new Role();
            adminRole.setRole("ADMIN");
            adminRole = roleRepository.save(adminRole);

            log.info("created default adminRole.");
        }

        List<Role> roles = new ArrayList<>();
        roles.add(adminRole);

        User user = userRepository.findByEmail("admin@c.com");
        if (user == null) {
            user = new User();
            user.setEmail("admin@c.com");
            user.setName("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setRoles(roles);
            user.setActive(1);
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());
            userRepository.save(user);

            log.info("created default admin account.");
        }

    }
}
