package com.GroupProject.SocialMediaApp.controller;

import com.GroupProject.SocialMediaApp.dto.UserRegistrationDto;
import com.GroupProject.SocialMediaApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthAndRegistrationController {

    private final UserService userService;

    public AuthAndRegistrationController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "registration";
    }


    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto registrationDto,
                                      BindingResult result,
                                      Model model) {

        if (result.hasErrors()) {
            return "registration"; // Return to registration form to show errors
        }


        if (userService.findByEmail(registrationDto.getEmail()) != null) {
            model.addAttribute("emailError", "Email is already registered.");
            return "registration";
        }

        try {
            userService.registerNewUser(registrationDto);
        } catch (Exception e) {

            model.addAttribute("registrationError", "An error occurred during registration: " + e.getMessage());
            return "registration";
        }


        return "redirect:/login?success";
    }


    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
