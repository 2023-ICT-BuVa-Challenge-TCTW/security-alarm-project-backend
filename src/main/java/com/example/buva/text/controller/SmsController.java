package com.example.buva.text.controller;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.buva.text.dto.MessageDto;
import com.example.buva.text.dto.SMSResp;
import com.example.buva.text.service.SmsService;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SmsController {
    private final SmsService smsService;
        
    @PostMapping("/sms/send")
    public ResponseEntity<SMSResp> sendSMS(@RequestBody MessageDto messageDto) throws JsonProcessingException, InvalidKeyException, IllegalStateException, UnsupportedEncodingException, NoSuchAlgorithmException{
        SMSResp data = smsService.sendSMS(messageDto.getTo(), messageDto.getContent());
        return ResponseEntity.ok().body(data);
    }
}
