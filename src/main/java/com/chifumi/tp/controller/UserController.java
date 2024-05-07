package com.chifumi.tp.controller;

import com.chifumi.tp.service.entity.User;
import com.chifumi.tp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("loggedInUserId")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginForm(@SessionAttribute(name = "loggedInUserId", required = false) Long loggedInUserId, Model model) {
        if (loggedInUserId != null) {
            return "redirect:/play";
        }
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") User user, Model model) {
        if (userService.authenticateUser(user.getUsername(), user.getPassword())) {
            // Authentification réussie, stocker l'identifiant de l'utilisateur en session
            model.addAttribute("loggedInUserId", userService.getUserIdByUsername(user.getUsername()));
            return "redirect:/play";
        } else {
            return "redirect:/login?error";
        }
    }

    @GetMapping("/register")
    public String showRegistrationForm(@SessionAttribute(name = "loggedInUserId", required = false) Long loggedInUserId, Model model) {
        if (loggedInUserId != null) {
            return "redirect:/play";
        } else {
        model.addAttribute("user", new User());
        return "register";
        }
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        userService.registerUser(user);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String showProfileForm(@SessionAttribute(name = "loggedInUserId", required = false) Long loggedInUserId, Model model) {
        if (loggedInUserId != null) {
            User user = userService.getUserById(loggedInUserId);
            model.addAttribute("user", user);
            return "profile";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@SessionAttribute(name = "loggedInUserId") Long loggedInUserId,
                                @RequestParam("username") String username,
                                @RequestParam("password") String password) {
        User user = userService.getUserById(loggedInUserId);
        user.updateProfile(username, password);
        userService.updateUser(user);
        return "redirect:/play";
    }

    @GetMapping("/updateProfile")
    public String returnProfile() {
        return "redirect:/profile";
    }

    @GetMapping("/logout")
    public String logoutUser(SessionStatus sessionStatus) {
        sessionStatus.setComplete(); // Effacer tous les attributs de session
        return "redirect:/login";
    }
}