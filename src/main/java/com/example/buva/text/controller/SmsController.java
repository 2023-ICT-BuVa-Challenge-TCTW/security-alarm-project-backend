package com.example.buva.text.controller;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.buva.text.dto.Request;
import com.example.buva.text.dto.TextResp;
import com.example.buva.text.service.SmsService;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SmsController {
    private final SmsService smsService;
        
    @PostMapping("/sms/send")
    public ResponseEntity<TextResp> sendSMS(@RequestBody Request request) throws JsonProcessingException, InvalidKeyException, IllegalStateException, UnsupportedEncodingException, NoSuchAlgorithmException{
        TextResp data = smsService.sendSMS(request.getRecipientPhoneNumber(), request.getContent());
        return ResponseEntity.ok().body(data);
    }
}
