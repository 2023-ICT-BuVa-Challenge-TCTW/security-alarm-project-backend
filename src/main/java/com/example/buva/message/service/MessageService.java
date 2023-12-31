package com.example.buva.message.service;

import com.example.buva.global.security.MyUserDetails;
import com.example.buva.message.dto.MessageFindReq;
import com.example.buva.message.dto.MessageFindResp;
import com.example.buva.message.dto.MessageInsertReq;
import com.example.buva.message.dto.MessageInsertResp;
import com.example.buva.message.entity.Message;
import com.example.buva.message.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    @Transactional
    public ResponseEntity<?> insertMessage(MyUserDetails myUserDetails,
                                           MessageInsertReq messageInsertReq) {
        if(!Objects.equals(messageInsertReq.username(), myUserDetails.getUsername())) {
            return ResponseEntity.badRequest().body("유저 이름이 일치하지 않습니다");
        }

        Message message = messageRepository.save(messageInsertReq.toEntity());

        return ResponseEntity.ok().body(new MessageInsertResp(message));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> getMessages(MyUserDetails myUserDetails,
                                        MessageFindReq messageFindReq) {
        if(!Objects.equals(messageFindReq.username(), myUserDetails.getUsername())) {
            return ResponseEntity.badRequest().body("유저 이름이 일치하지 않습니다");
        }

        List<Message> messages = messageRepository.findByUsername(messageFindReq.username());

        return ResponseEntity.ok().body(new MessageFindResp(messages));
    }
}
