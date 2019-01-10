package io.github.rxcats.springbootthymeleafdemo.controller;

import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/public")
    @ResponseBody
    public Map<String, Object> pub() {
        return Map.of("message", "ok");
    }

    @GetMapping("/api/private")
    @ResponseBody
    public Map<String, Object> pri() {
        return Map.of("message", "ok");
    }

}
