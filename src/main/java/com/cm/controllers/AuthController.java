package com.cm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cm.entities.User;
import com.cm.helper.Message;
import com.cm.helper.MessageType;
import com.cm.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/verify-email")
    public String verifyEmail(@RequestParam("token") String token, HttpSession session) {
        // System.out.println("\n\nVerify Email\n\n");

        User user = userService.getUserByEmailToken(token);

        if (user != null) {
            // means user present
            user.setEmailVerified(true);
            user.setEnabled(true);
            userService.updateUser(user);

            System.out.println("\n\nComing here...\n\n");
            session.setAttribute("message", Message.builder()
                    .type(MessageType.green)
                    .content("Your email is verified. Now you can login.")
                    .build());
            return "success_page";
        } else {
            session.setAttribute("message", Message.builder()
                    .type(MessageType.red)
                    .content("Email not verified !! Token is nto associated with user.")
                    .build());
            return "error_page";
        }
    }

}
