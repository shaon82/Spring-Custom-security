package com.shaon.springsecurity.controller;

import com.shaon.springsecurity.model.AuthenticatedUser;
import com.shaon.springsecurity.model.UserEntity;
import com.sun.security.auth.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class HomeController {


    @GetMapping("/")
    public String homePage(Model model, Principal principal, HttpServletResponse response){



        if (principal != null){

            UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            return "home";
        }
        model.addAttribute("auth", "not Authenticate");
        return "login";
    }

    @GetMapping("/login")
    public String loginPage(Model model){
        return "login";
    }
}
