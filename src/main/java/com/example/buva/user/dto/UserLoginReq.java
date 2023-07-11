package com.example.buva.user.dto;

import jakarta.validation.constraints.NotBlank;

public record UserLoginReq(@NotBlank(message = "username을 입력해주세요") String username,
                           @NotBlank(message = "password를 입력해주세요") String password) {
}
