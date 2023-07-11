package com.example.buva.text.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SMSResponse {
    private String requestId; 
    private String requestTime; 
    private String statusCode; 
    private String statusName; 
}
