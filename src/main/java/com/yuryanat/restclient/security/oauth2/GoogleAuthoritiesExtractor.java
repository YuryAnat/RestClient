package com.yuryanat.restclient.security.oauth2;

import com.yuryanat.restclient.models.Role;
import com.yuryanat.restclient.models.User;
import com.yuryanat.restclient.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class GoogleAuthoritiesExtractor implements AuthoritiesExtractor {
    @Autowired
    public UserService userService;

    @Override
    public List<GrantedAuthority> extractAuthorities(Map<String, Object> map) {
        String login = (String) map.get("name");
        login = login.replaceAll("\\s+", "");
        User user = userService.getUserByLogin(login);
        if (user == null) {
            return Collections.<GrantedAuthority>emptyList();
        }
        return AuthorityUtils.createAuthorityList(user.getRoles().stream().map(Role::getRole).toArray(String[]::new));
    }
}