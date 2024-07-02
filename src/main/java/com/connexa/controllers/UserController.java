package com.connexa.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.connexa.entities.User;
import com.connexa.helper.Helper;
import com.connexa.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    // user dsahboard page
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String userDashboard() {
        System.out.println("user dashboard");
        return "user/dashboard";
    }

    // user profile page
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String userProfile(Model model, Authentication authentication) {

        // String name =principal.getName();
        String username = Helper.getEmailOfLoggedInUser(authentication);
        logger.info("user logged in{}", username);
        System.out.println("user profile");

        User user = userService.getUserByEmail(username);
        System.out.println(user.getName());
        System.out.println(user.getEmail());
        model.addAttribute("loggedInUser", user);
        return "user/profile";
    }
    // user add contacts page
    // user view contacts
    // user edit contact
    // user delete contact

}
