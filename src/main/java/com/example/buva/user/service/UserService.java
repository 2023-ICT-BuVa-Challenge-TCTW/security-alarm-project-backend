package com.example.buva.user.service;

import com.example.buva.user.dto.UserLoginReq;
import com.example.buva.user.dto.UserLoginResp;
import com.example.buva.user.entity.User;
import com.example.buva.user.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final HttpSession session;

    private final UserRepository userRepository;

    @Transactional
    public UserLoginResp login(UserLoginReq userLoginReq) {
        User user = userRepository
                .findByUsernameAndPassword(
                        userLoginReq.getUsername(), userLoginReq.getPassword())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다"));
        return new UserLoginResp(user);
    }

    @Transactional
    public void join() {
    }

    @Transactional
    public void update() {
    }
}
