package com.example.buva.user.dto;

import com.example.buva.user.entity.User;

public record UserLoginResp(Long id, String username) {
    public UserLoginResp(User entity) {
        this(entity.getId(), entity.getUsername());
    }
}
