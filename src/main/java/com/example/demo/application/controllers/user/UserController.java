package com.example.demo.application.controllers.user;

import com.example.demo.application.dtos.in.oauth.github.CodeInput;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @GetMapping
    public List<User> fetchUsers() {
        return service.getAll();
    }

    @PostMapping("/auth")
    public User login(@RequestBody CodeInput input) {
        return service.auth(input.code());
    }

}