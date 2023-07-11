package com.example.buva.user.dto;

import com.example.buva.user.entity.User;

public record UserUpdateResp(Long id, String username) {

    public UserUpdateResp(User entity) {
        this(entity.getId(), entity.getUsername());
    }
}
