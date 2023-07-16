package com.example.buva.message.dto;

import com.example.buva.message.entity.Message;
import jakarta.validation.constraints.NotBlank;

public record MessageInsertReq(@NotBlank(message = "username을 입력해주세요") String username,
                               @NotBlank(message = "연락처를 입력해주세요") String phone,
                               @NotBlank(message = "문자 내용을 입력해주세요") String content) {
    public Message toEntity() {
        return Message.builder()
                .username(username)
                .phone(phone)
                .content(content)
                .build();
    }
}
