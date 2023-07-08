package com.example.buva.user.dto;

import com.example.buva.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class UserLoginReq {
    @NotBlank(message = "username을 입력해주세요")
    private String username;
    @NotBlank(message = "password를 입력해주세요")
    private String password;

    @Builder
    public UserLoginReq(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }
}
