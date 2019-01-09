package io.github.rxcats.springbootthymeleafdemo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

import io.github.rxcats.springbootthymeleafdemo.security.entity.AdminUserPrincipal;

@Slf4j
@Controller
public class HelloController {

    @GetMapping("/")
    public String welcome(Model model, Authentication authentication) {

        AdminUserPrincipal userDetails = (AdminUserPrincipal) authentication.getPrincipal();

        log.info("getUsername:{}", userDetails.getUsername());
        log.info("getName:{}", userDetails.getName());
        log.info("getAuthorities:{}", userDetails.getAuthorities());

        return "welcome";
    }

}
