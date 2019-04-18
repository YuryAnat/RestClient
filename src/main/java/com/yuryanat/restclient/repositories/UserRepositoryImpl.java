package com.yuryanat.restclient.repositories;

import com.yuryanat.restclient.models.User;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final static String USER_NAME = "admin";
    private final static String PASSWORD = "admin";
    private final EntityManager entityManager;
    private final RestTemplate restTemplate;
    private HttpHeaders headers;

    @Autowired
    public UserRepositoryImpl(EntityManager entityManagerFactory) {
        this.entityManager = entityManagerFactory;
        this.restTemplate = new RestTemplate();
        this.headers = new HttpHeaders() {{
            String auth = USER_NAME + ":" + PASSWORD;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
        }};
    }

    @Override
    public void addUser(User user) {
        HttpEntity<User> entity = new HttpEntity<>(user,headers);
        restTemplate.postForObject("http://localhost:8181/rest/admin/add/", entity, User.class);
    }

    @Override
    public void updateUser(User user) {
        HttpEntity<User> entity = new HttpEntity<>(user,headers);
        restTemplate.postForObject("http://localhost:8181/rest/admin/edit/", entity, User.class);

    }

    @Override
    public void deleteUser(int id) {
        String json = "{\"id\":" + id + "}";
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(json,headers);
        restTemplate.postForEntity("http://localhost:8181/rest/admin/delete/", entity, String.class);
    }

    @Override
    public User getUserById(int id) {
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<User> responseEntity = restTemplate.exchange("http://localhost:8181/rest/admin/" + id,
                HttpMethod.GET,
                entity,
                User.class);
        return responseEntity.getBody();
    }

    @Override
    public User getUserByLogin(String login) {
        User user = null;
        try {
            user = entityManager.createQuery("from User where login = :login", User.class).setParameter("login", login).getSingleResult();
        }catch (NoResultException e){
            /*NOOPE*/
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<List<User>> response = restTemplate.exchange(
                "http://localhost:8181/rest/admin/",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<User>>() {});
        return response.getBody();
    }
}
