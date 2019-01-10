package io.github.rxcats.springbootthymeleafdemo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

import io.github.rxcats.springbootthymeleafdemo.security.entity.AdminUserPrincipal;

@Slf4j
@Controller
public class HelloController {

    @GetMapping("/")
    public String welcome(Model model, @AuthenticationPrincipal AdminUserPrincipal user) {

        log.info("getUsername:{}", user.getUsername());
        log.info("getName:{}", user.getName());
        log.info("getAuthorities:{}", user.getAuthorities());

        return "welcome";
    }

}
