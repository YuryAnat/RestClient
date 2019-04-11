package com.yuryanat.restclient.controllers;

import com.yuryanat.restclient.models.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class UserController {
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping(value = {"/admin"})
    public String viewAllUsersPage(Model model) {
        ResponseEntity<List<User>> response = restTemplate.exchange(
                "http://localhost:8181/rest/admin/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                });
        List<User> allUsers = response.getBody();
        model.addAttribute("users", allUsers);
        return "admin";
    }

    @GetMapping(value = "/admin/delete", params = {"id"})
    public String deleteUser(@RequestParam String id) {
        String json = "{\"id\":" + id + "}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(json,headers);
        restTemplate.postForEntity("http://localhost:8181/rest/admin/delete/", entity, String.class);
        return "redirect:/admin";
    }

    @PostMapping(value = {"/admin/add"}, params = {"login", "password", "confPassword", "name", "email", "roles"})
    public String uddUser(@ModelAttribute("user") User user) {
        restTemplate.postForObject("http://localhost:8181/rest/admin/add/", user, User.class);
        return "redirect:/admin";
    }

    @PostMapping(value = {"/admin/edit"}, params = {"id", "login", "password", "confPassword", "name", "email", "roles"})
    public String editUser(@ModelAttribute("user") User editedUser) {
        restTemplate.postForObject("http://localhost:8181/rest/admin/edit/", editedUser, User.class);
        return "redirect:/admin";
    }

    @GetMapping(value = {"/"})
    public String viewAllUsersPage(){
        return "redirect:/login";
    }

    @GetMapping(value = {"/login"})
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/user")
    public String userInfo() {
        return "userInfo";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }
}