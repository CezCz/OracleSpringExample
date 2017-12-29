package io.github.cezcz.rest.custom;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthRest {

    @GetMapping("/user/current")
    public UserDetails getLoggedUser(){
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
