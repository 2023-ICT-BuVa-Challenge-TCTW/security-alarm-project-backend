package com.example.buva.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserLoginReq {
    @NotBlank(message = "username을 입력해주세요")
    private final String username;
    @NotBlank(message = "password를 입력해주세요")
    private final String password;
}
