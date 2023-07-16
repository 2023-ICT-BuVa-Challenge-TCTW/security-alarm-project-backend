package com.example.buva.message.controller;

import com.example.buva.global.security.MyUserDetails;
import com.example.buva.message.dto.MessageInsertReq;
import com.example.buva.message.service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/messages")
    public ResponseEntity<?> insertMessage(@AuthenticationPrincipal MyUserDetails myUserDetails,
                                           @RequestBody @Valid MessageInsertReq messageInsertReq) {
        return messageService.insertMessage(myUserDetails, messageInsertReq);
    }
}
