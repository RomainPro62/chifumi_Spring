package com.chifumi.tp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("loggedInUserId")
public class LogoutController {

    @GetMapping("/logout")
    public String logoutUser(SessionStatus sessionStatus) {
        sessionStatus.setComplete(); // Effacer tous les attributs de session
        return "redirect:/login"; // Rediriger vers la page de connexion après la déconnexion
    }
}
