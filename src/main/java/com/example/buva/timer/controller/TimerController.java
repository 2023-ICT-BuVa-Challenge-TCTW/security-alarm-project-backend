package com.example.buva.timer.controller;

import java.time.Instant;
import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.buva.timer.dto.TimerCancelReq;
import com.example.buva.timer.dto.TimerSetReq;
import com.example.buva.timer.service.TimerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RestController
public class TimerController {
    
    private TimerService timerservice;
    
    // @PostMapping("/timer/setAlarm")
    // public ResponseEntity<?> setAlarm(@Valid @RequestBody TimerSetReq timerSetReq) throws InterruptedException {
    //     // return timerservice.setAlarm(timerSetReq);
    // }

    @PostMapping("/timer/cancelAlarm")
    public ResponseEntity<?> cancelAlarm(@RequestBody TimerCancelReq timerCancelReq) throws InterruptedException {
        return timerservice.cancelAlarm(timerCancelReq);
    }
}
