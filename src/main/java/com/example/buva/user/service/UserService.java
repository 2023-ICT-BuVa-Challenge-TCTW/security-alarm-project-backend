package com.example.buva.user.service;

import com.example.buva.global.jwt.MyJwtProvider;
import com.example.buva.global.security.MyUserDetails;
import com.example.buva.user.dto.UserJoinReq;
import com.example.buva.user.dto.UserJoinResp;
import com.example.buva.user.dto.UserLoginReq;
import com.example.buva.user.dto.UserLoginResp;
import com.example.buva.user.dto.UserUpdateReq;
import com.example.buva.user.dto.UserUpdateResp;
import com.example.buva.user.entity.User;
import com.example.buva.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public ResponseEntity<?> login(UserLoginReq userLoginReq) {
        User user = userRepository
                .findByUsernameAndPassword(
                        userLoginReq.getUsername(), userLoginReq.getPassword())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다"));
        if (passwordEncoder.matches(userLoginReq.getPassword(), user.getPassword())) {
            String jwt = MyJwtProvider.create(user);
            return ResponseEntity.ok().header(MyJwtProvider.HEADER, jwt).body(new UserLoginResp(user));
        }
        throw new RuntimeException("패스워드 유효성 실패");
    }

    @Transactional
    public ResponseEntity<?> join(UserJoinReq userJoinReq) {
        String rawPassword = userJoinReq.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword); // 60Byte
        userJoinReq.setPassword(encPassword);

        userRepository.save(userJoinReq.toEntity());

        // JWT 인증 로직
        User user = userRepository
                .findByUsername(userJoinReq.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다"));

        if (passwordEncoder.matches(rawPassword, user.getPassword())) {
            String jwt = MyJwtProvider.create(user);
            return ResponseEntity.ok().header(MyJwtProvider.HEADER, jwt).body(new UserJoinResp(user));
        }

        throw new RuntimeException("패스워드 유효성 실패");
    }

    @Transactional
    public ResponseEntity<?> update(MyUserDetails myUserDetails, UserUpdateReq userUpdateReq) {
        User user = userRepository.findById(myUserDetails.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다"));

        user.update(userUpdateReq.getPassword());

        return ResponseEntity.ok().body(new UserUpdateResp(user));
    }
}
