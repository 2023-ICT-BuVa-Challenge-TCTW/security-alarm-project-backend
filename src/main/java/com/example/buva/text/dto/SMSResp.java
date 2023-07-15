package com.example.buva.text.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SMSResp {
    private String requestId; 
    private String requestTime;
    private String statusCode; 
    private String statusName;
}
