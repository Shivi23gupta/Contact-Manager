package com.cm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestParam;

import com.cm.entities.User;
import com.cm.forms.UserForm;

import com.cm.helper.Message;
import com.cm.helper.MessageType;
import com.cm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        System.out.println("Home page handler");
        // sending data to view
        model.addAttribute("name", "Gupta jii");
        model.addAttribute("youtubeChannel", "No Youtube Channel");
        model.addAttribute("githubRepo", "https://github.com/rik-m-27");
        // change kr lena.. change krega vo shayad..
        return "home";
    }

    @GetMapping("path")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }

    @GetMapping("/about")
    public String aboutPage() {
        System.out.println("About page loading");
        return "about";
    }

    @GetMapping("/services")
    public String servicePage(Model model) {
        System.out.println("Service page loading");
        model.addAttribute("isLogin", false);
        return "services";
    }

    @GetMapping("/contact")
    public String contactPage() {
        System.out.println("contact page loading");

        return "contact";
    }
    // this is login page

    @GetMapping("/login")
    public String loginPage() {
        System.out.println("login page loading");

        return "login";
    }
    // this is registration page

    @GetMapping("/register")
    public String registerPage(Model model) {
        // userform mai blank data ja rh haii abhi idaar se
        UserForm userForm = new UserForm();
        // userForm.setName("sourav");
        // userForm.setAbout("jiii kaise");

        model.addAttribute("userForm", userForm);
        System.out.println("Register page loading");

        return "register";
    }

    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult,
            HttpSession session) {
        System.out.println("processing");
        // fetch from data
        // UserForm
        System.out.println(userForm);
        // validate from data
        if (rBindingResult.hasErrors()) {
            return "register";
        }
        // sava to database
        // userservice
        // userform-> se data nikal kr user bnya haii
        // User user=User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber()).
        // profilePic("@{'/images/contact.jpeg'}").
        // build();
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setAbout(userForm.getAbout());
        user.setPassword(userForm.getPassword());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setEnabled(false); // by default it will be false
        user.setProfilePic("@{'/images/contact.jpeg'}");
        User savedUser = userService.saveUser(user);
        System.out.println("user saved:");
        // message==registration sucessful
        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();
        session.setAttribute("message", message);
        // redirect login page
        return "redirect:/register";
    }
}