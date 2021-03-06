package io.github.rxcats.springbootthymeleafdemo.security.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

import io.github.rxcats.springbootthymeleafdemo.security.entity.AdminUserPrincipal;
import io.github.rxcats.springbootthymeleafdemo.security.entity.User;
import io.github.rxcats.springbootthymeleafdemo.security.service.UserService;

@Slf4j
@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping(value = "/registration")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String createNewUser(@Valid User user, BindingResult bindingResult, Model model) {
        User userExists = userService.getUser(user.getEmail());
        log.info("userExists:{}", userExists);
        log.info("user:{}", user);
        if (userExists != null) {
            bindingResult
                .rejectValue("email", "error.user",
                    "There is already a user registered with the email provided");
        }

        if (bindingResult.hasErrors()) {
            log.warn("errors:{}", bindingResult.getFieldErrors());
            List<String> errorMessages = bindingResult.getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
            model.addAttribute("success", false);
            model.addAttribute("errorMessages", errorMessages);
        } else {
            userService.saveUser(user);
            model.addAttribute("success", true);
            model.addAttribute("successMessage", "User has been registered successfully");
            model.addAttribute("user", new User());
        }
        return "registration";
    }

    @GetMapping(value="/admin/home")
    public String home(Model model, @AuthenticationPrincipal AdminUserPrincipal user){
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("userName", user.getName());
        model.addAttribute("userEmail", user.getUsername());
        model.addAttribute("adminMessage","Content Available Only for Users with Admin Role");
        return "admin/home";
    }

}
