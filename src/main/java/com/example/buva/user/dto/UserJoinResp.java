package com.example.buva.user.dto;

import com.example.buva.user.entity.User;

public record UserJoinResp(Long id, String username) {
    public UserJoinResp(User entity) {
        this(entity.getId(), entity.getUsername());
    }
}
