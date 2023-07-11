package com.example.buva.user.controller;

import com.example.buva.user.dto.UserLoginReq;
import com.example.buva.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody UserLoginReq userLoginReq) {
        userService.login(userLoginReq);
        return "login";
    }

    @PostMapping("/join")
    public String join() {
        userService.join();
        return "login";
    }

    @PostMapping("/update")
    public String update() {
        userService.update();
        return "login";
    }
}
