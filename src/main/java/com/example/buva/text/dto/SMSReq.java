package com.example.buva.text.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SMSReq{
    private String type;
    private String contentType;
    private String countryCode;
    private String from;
    private String content;
    // private String reserveTime;
    // private String reserveTimeZone;
    private MessageDto message;
}
