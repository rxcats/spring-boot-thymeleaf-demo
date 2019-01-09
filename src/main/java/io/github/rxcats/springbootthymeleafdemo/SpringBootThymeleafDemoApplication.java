package io.github.rxcats.springbootthymeleafdemo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.github.rxcats.springbootthymeleafdemo.security.entity.Role;
import io.github.rxcats.springbootthymeleafdemo.security.entity.User;
import io.github.rxcats.springbootthymeleafdemo.security.repository.RoleRepository;
import io.github.rxcats.springbootthymeleafdemo.security.repository.UserRepository;

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

    @Bean
    CommandLineRunner runner() {
        return args -> {

            // create default admin account

            Role adminRole = roleRepository.findByRole("ADMIN");
            if (adminRole == null) {
                adminRole = new Role();
                adminRole.setRole("ADMIN");
                adminRole = roleRepository.save(adminRole);
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
            }
        };
    }
}
