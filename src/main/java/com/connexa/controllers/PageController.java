package com.connexa.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    @GetMapping("/home")
    public String home() {
        System.out.println("Home page handler");
        // sending data to view
        return "home";
    }

    @GetMapping("/about")
    public String aboutPage(Model model) {
        System.out.println("About page loading");
        model.addAttribute("isLogin", true);
        return "about";
    }

    @GetMapping("/services")
    public String servicePage() {
        System.out.println("Service page loading");
        return "services";
    }

    @GetMapping("/contact")
    public String contactPage() {
        System.out.println("Contact page loading");
        return "contact";
    }

    @GetMapping("/login")
    public String loginPage() {
        return new String("login");
    }

    @GetMapping("/register")
    public String registerPage() {
        return new String("register");
    }

}