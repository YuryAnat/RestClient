package com.yuryanat.restclient.controllers;

import com.yuryanat.restclient.models.User;
import com.yuryanat.restclient.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping(value = {"/admin"})
    public String viewAllUsersPage(Model model) {
        List<User> allUsers = service.getAllUsers();
        model.addAttribute("users", allUsers);
        return "admin";
    }

    @GetMapping(value = "/admin/delete", params = {"id"})
    public String deleteUser(@RequestParam String id) {
        service.deleteUser(Integer.parseInt(id));
        return "redirect:/admin";
    }

    @PostMapping(value = {"/admin/add"})
    public String uddUser(@ModelAttribute("user") User user) {
        service.addNewUser(user);
        return "redirect:/admin";
    }

    @PostMapping(value = {"/admin/edit"}, params = {"id", "login", "password", "confPassword", "name", "email", "roles"})
    public String editUser(@ModelAttribute("user") User editedUser) {
        service.updateUser(editedUser);
        return "redirect:/admin";
    }

    @GetMapping(value = {"/"})
    public String viewAllUsersPage(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ADMIN")))
                return "redirect:/admin";
            if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("USER")))
                return "redirect:/user";
        }
        return "redirect:/login";
    }

    @GetMapping(value = {"/login"})
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/user")
    public String userInfo(Model model, Authentication au){
        model.addAttribute("roles",au.getAuthorities().stream().map(s -> ((GrantedAuthority) s).getAuthority()).collect(Collectors.toList()));
        model.addAttribute("user",au.getPrincipal());
        return "userInfo";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }
}