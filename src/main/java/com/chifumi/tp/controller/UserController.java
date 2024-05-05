package com.chifumi.tp.controller;

import com.chifumi.tp.service.entity.User;
import com.chifumi.tp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
            return "redirect:/play"; // Rediriger vers la page de jeu après la connexion réussie
        } else {
            return "redirect:/login?error"; // Rediriger vers la page de connexion avec un message d'erreur en cas d'échec de connexion
        }
    }

    @GetMapping("/register")
    public String showRegistrationForm(@SessionAttribute(name = "loggedInUserId", required = false) Long loggedInUserId, Model model) {
        if (loggedInUserId != null) {
            return "redirect:/play"; // Rediriger vers une autre page si l'utilisateur est déjà connecté
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
}