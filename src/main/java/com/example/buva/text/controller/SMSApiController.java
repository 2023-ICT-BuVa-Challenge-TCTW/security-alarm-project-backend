package com.example.buva.text.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.buva.text.dto.Request;
import com.example.buva.text.dto.SMSResponse;
import com.example.buva.text.service.SmService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SMSApiController {
    private final SmService smService;
        
    @PostMapping("/sms/send")
    public ResponseEntity<SMSResponse> sendSMS(@RequestBody Request request) throws Exception{
        SMSResponse data = smService.sendSMS(request.getRecipientPhoneNumber(), request.getContent());
        return ResponseEntity.ok().body(data);
    }
}
