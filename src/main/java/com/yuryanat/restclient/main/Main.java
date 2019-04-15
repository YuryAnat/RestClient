package com.yuryanat.restclient.main;

import ch.qos.logback.core.net.ObjectWriter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yuryanat.restclient.models.Role;
import com.yuryanat.restclient.models.User;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.Writer;
import java.nio.charset.Charset;
import java.util.*;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Main {
    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();

        /*Get single user by Id*/
//        int id = 1;
//        User singleUser = restTemplate.getForObject("http://localhost:8181/rest/admin/"+ id,User.class);
//        System.err.println(singleUser);

        /*Get all users*/
//        ResponseEntity<List<User>> response = restTemplate.exchange(
//                "http://localhost:8181/rest/admin/",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<User>>() {});
//        List<User> allUsers = response.getBody();
//        allUsers.forEach(System.err::println);

        /*Create new user*/
//        User newUser = new User("RestClient", "", "RestClient", "RestClient@domain.com");
//        Set<Role> role = new HashSet<>(Arrays.asList(new Role("USER"),new Role("ADMIN")));
//        newUser.setRoles(role);
//        ObjectMapper om = new ObjectMapper();
//        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        restTemplate.postForObject("http://localhost:8181/rest/admin/add/",newUser,User.class);

        String plainCreds = "admin:admin";
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8181/rest/user/", HttpMethod.GET, request, String.class);
        System.out.println(response);

//        String  singleUser = restTemplate.getForObject("http://localhost:8181/rest/admin/"+ 1,String.class);
//        System.err.println(singleUser);

    }
}
