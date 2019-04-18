package com.yuryanat.restclient.security.oauth2;

import com.yuryanat.restclient.models.Role;
import com.yuryanat.restclient.models.User;
import com.yuryanat.restclient.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

@Component
public class GooglePrincipalExtractor implements PrincipalExtractor {
    private final UserService userService;
    private final UserDetailsService userDetailsService;

    @Autowired
    public GooglePrincipalExtractor(UserService userService, @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Object extractPrincipal(Map<String, Object> map) {
        String login = (String) map.get("name");
        login = login.replaceAll("\\s+", "");
        User user = userService.getUserByLogin(login);
        if (user == null) {
            user = new User();
            user.setEmail((String) map.get("email"));
            user.setLogin(login);
            user.setName((String) map.get("name"));
            user.setPassword("");
            user.setRoles(new HashSet<>(Collections.singletonList(new Role("USER"))));
            userService.addNewUser(user);
        }
        return userDetailsService.loadUserByUsername(user.getLogin());
    }
}