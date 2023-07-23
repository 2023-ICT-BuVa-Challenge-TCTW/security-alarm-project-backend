package com.example.buva.sms.controller;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

import com.example.buva.sms.dto.SmsInsertReq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.buva.sms.dto.SmsInsertResp;
import com.example.buva.sms.service.SmsService;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SmsController {
    private final SmsService smsService;
        
    @PostMapping("/sms/send")
    public ResponseEntity<SmsInsertResp> sendSMS(@RequestBody SmsInsertReq smsInsertReq) throws JsonProcessingException, InvalidKeyException, IllegalStateException, UnsupportedEncodingException, NoSuchAlgorithmException, ExecutionException, InterruptedException {
        SmsInsertResp data = smsService.sendSMS(smsInsertReq.getTo(),
                smsInsertReq.getContent(), smsInsertReq.getReserveTime());
        return ResponseEntity.ok().body(data);
    }

//    @DeleteMapping ("/sms/delete")
//    public ResponseEntity<SmsInsertResp> deleteSMS(@RequestBody MessageDto messageDto) throws JsonProcessingException, InvalidKeyException, IllegalStateException, UnsupportedEncodingException, NoSuchAlgorithmException{
//        SmsInsertResp data = smsService.deleteSMS(messageDto.getTo(), messageDto.getContent());
//        return ResponseEntity.ok().body(data);
//    }
}
