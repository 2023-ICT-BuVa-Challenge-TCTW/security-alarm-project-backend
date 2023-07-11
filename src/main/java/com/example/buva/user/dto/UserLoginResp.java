package com.example.buva.user.dto;

import com.example.buva.user.entity.User;
import lombok.Getter;

@Getter
public class UserLoginResp {

    private final Long id;
    private final String username;

    public UserLoginResp(User entity) {
        this.id = entity.getId();
        this.username = entity.getUsername();
    }
}
