package io.github.cezcz.rest.custom;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class AuthRest {

    @GetMapping("/user/current")
    public UserDetails getLoggedUser(){
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @GetMapping("/user/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpSession session){
        session.invalidate();
    }
}
