package com.yuryanat.restclient.main;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yuryanat.restclient.models.Role;
import com.yuryanat.restclient.models.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties
public class Main {
    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();

        /*Get single user by Id*/
        int id = 1;
        User singleUser = restTemplate.getForObject("http://localhost:8181/rest/admin/"+ id,User.class);
        System.err.println(singleUser);

        /*Get all users*/
        ResponseEntity<List<User>> response = restTemplate.exchange(
                "http://localhost:8181/rest/admin/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {});
        List<User> allUsers = response.getBody();
        allUsers.forEach(System.err::println);

        /*Create new user*/
//        User newUser = new User("RestClient", "", "RestClient", "RestClient@domain.com");
//        Set<Role> role = new HashSet<>(Arrays.asList(new Role("USER"),new Role("ADMIN")));
//        newUser.setRoles(role);
//        restTemplate.postForObject("http://localhost:8181/rest/admin/add/",newUser,User.class);
    }
}
