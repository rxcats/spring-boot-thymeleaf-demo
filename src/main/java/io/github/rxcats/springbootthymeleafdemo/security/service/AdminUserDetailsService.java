package io.github.rxcats.springbootthymeleafdemo.security.service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.rxcats.springbootthymeleafdemo.security.entity.AdminUserPrincipal;
import io.github.rxcats.springbootthymeleafdemo.security.entity.Role;
import io.github.rxcats.springbootthymeleafdemo.security.entity.User;
import io.github.rxcats.springbootthymeleafdemo.security.repository.RoleRepository;
import io.github.rxcats.springbootthymeleafdemo.security.repository.UserRepository;

@Service
public class AdminUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found : " + username);
        }

        Iterable<Role> roles = roleRepository.findAllById(user.getRoleIds());
        user.setRoles(StreamSupport.stream(roles.spliterator(), false).collect(Collectors.toList()));

        return new AdminUserPrincipal(user);
    }
}
