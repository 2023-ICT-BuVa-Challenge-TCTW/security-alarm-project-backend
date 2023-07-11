package com.example.buva.user.dto;

import com.example.buva.user.entity.User;
import lombok.Getter;

@Getter
public class UserUpdateResp {

    private final Long id;
    private final String username;

    public UserUpdateResp(User entity) {
        this.id = entity.getId();
        this.username = entity.getUsername();
    }
}
