package com.chifumi.tp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.chifumi.tp.service.UserService;
import com.chifumi.tp.service.entity.User;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        userService.registerUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") User user, HttpServletRequest request) {
        if (userService.authenticateUser(user.getUsername(), user.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user.getUsername());
            return "redirect:/play"; // Rediriger vers la page de jeu après la connexion réussie
        } else {
            return "redirect:/login?error"; // Rediriger vers la page de connexion avec un message d'erreur en cas d'échec de connexion
        }
    }
}
