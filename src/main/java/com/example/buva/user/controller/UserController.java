package com.example.buva.user.controller;

import com.example.buva.global.security.MyUserDetails;
import com.example.buva.user.dto.UserJoinReq;
import com.example.buva.user.dto.UserLoginReq;
import com.example.buva.user.dto.UserUpdateReq;
import com.example.buva.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginReq userLoginReq) {
        return userService.login(userLoginReq);
    }

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody @Valid UserJoinReq userJoinReq) {
        return userService.join(userJoinReq);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@AuthenticationPrincipal MyUserDetails myUserDetails,
                                    @RequestBody @Valid UserUpdateReq userUpdateReq) {
        return userService.update(myUserDetails, userUpdateReq);
    }
}
