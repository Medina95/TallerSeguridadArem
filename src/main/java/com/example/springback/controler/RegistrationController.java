package com.example.springback.controler;


import com.example.springback.model.MyUser;
import com.example.springback.repository.MyUserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class RegistrationController {

    @Autowired
    private MyUserRepository myUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register/user")
    public MyUser createUser(@RequestBody MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return myUserRepository.save(user);
    }
    @PostMapping("/admin/addUser")
    public void addUser(@ModelAttribute MyUser user, HttpServletResponse response) throws IOException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        myUserRepository.save(user);
        response.sendRedirect("https://mipaginita.duckdns.org/home_admin.html");
    }

}