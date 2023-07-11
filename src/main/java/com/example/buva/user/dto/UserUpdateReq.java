package com.example.buva.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateReq {
    @NotBlank(message = "password를 입력해주세요")
    private String password;

    @Builder
    public UserUpdateReq(String password) {
        this.password = password;
    }
}
