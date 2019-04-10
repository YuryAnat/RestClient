package com.yuryanat.restclient.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.Collectors;

@Controller
public class UserController {
//    private final UserService userService;
//    private final RoleService roleService;
//
//    @Autowired
//    public UserController(UserService userService, RoleService roleService) {
//        this.userService = userService;
//        this.roleService = roleService;
//    }

    @GetMapping(value = {"/admin"})
    public String viewAllUsersPage(Model model) {
//        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

//    @GetMapping(value = "/admin/delete", params = {"id"})
//    public String deleteUser(@RequestParam String id, Model model) {
//        userService.deleteUser(Integer.parseInt(id));
//        model.addAttribute("status", "User: " + id + " deleted");
//        return "redirect:/admin";
//    }
//
//    @PostMapping(value = {"/admin/add"}, params = {"login", "password", "confPassword", "name", "email", "roles"})
//    public String uddUser(@ModelAttribute("user") User user, @RequestParam("roles") String[] role, Model model){
//        Set<Role> roles = Arrays.stream(role).map(roleService::findRoleByName).collect(Collectors.toSet());
//        roles.forEach(r -> r.addUser(user));
//        user.setRoles(roles);
//        if (userService.getUserByLogin(user.getLogin()) == null){
//            userService.addNewUser(user);
//            return "redirect:/admin";
//        }else {
//            model.addAttribute("status", "Login name is use");
//            return "redirect:/admin";
//        }
//    }
//
//    @PostMapping(value = {"/admin/edit"},  params = {"id", "login", "password", "confPassword", "name", "email", "roles"})
//    public String editUser(@ModelAttribute("user") User editedUser, @RequestParam("roles") String[] role, Model model){
//        Set<Role> roles = Arrays.stream(role).map(roleService::findRoleByName).collect(Collectors.toSet());
//        for (Role role1 : roles) {
//            role1.addUser(editedUser);
//        }
//        editedUser.setRoles(roles);
//        if (userService.getUserByID(editedUser.getId()) != null){
//            userService.updateUser(editedUser);
//            model.addAttribute("status", "User edited");
//            return "redirect:/admin";
//        }else {
//            model.addAttribute("status", "No user found by id " + editedUser.getId());
//            return "editUser";
//        }
//    }

    @GetMapping(value = {"/"})
    public String viewAllUsersPage(){
        return "redirect:/login";
    }

    @GetMapping(value = {"/login"})
    public String loginPage(){
        return "login";
    }

    @GetMapping(value = "/user")
    public String userInfo(){
        return "userInfo";
    }

    @GetMapping("/403")
    public String accessDenied(){
        return "403";
    }
}