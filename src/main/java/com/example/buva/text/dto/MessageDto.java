package com.example.buva.text.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MessageDto {
    private String to;
    private String content;
}
