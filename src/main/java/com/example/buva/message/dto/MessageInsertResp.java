package com.example.buva.message.dto;

import com.example.buva.message.entity.Message;

public record MessageInsertResp(Long id, String username, String phone, String content) {
    public MessageInsertResp(Message entity) {
        this(entity.getId(), entity.getUsername(), entity.getPhone(), entity.getContent());
    }
}

