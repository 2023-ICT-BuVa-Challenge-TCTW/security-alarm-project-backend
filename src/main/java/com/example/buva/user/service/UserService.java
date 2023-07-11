package com.example.buva.user.service;

import com.example.buva.global.jwt.MyJwtProvider;
import com.example.buva.user.dto.UserJoinReq;
import com.example.buva.user.dto.UserLoginReq;
import com.example.buva.user.dto.UserLoginResp;
import com.example.buva.user.entity.User;
import com.example.buva.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


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
    public void join(UserJoinReq userJoinReq) {
        // 회원가입 로직
        String rawPassword = joinDTO.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword); // 60Byte
        joinDTO.setPassword(encPassword);

        Optional<Rate> rateOP = rateRepository.findById(1L);
        if (rateOP.isEmpty()) {
            throw new Exception404("등급이 존재하지 않습니다");
        }

        try {
            Rate ratePS = rateOP.get();
            userRepository.save(joinDTO.toEntity(ratePS));
        } catch (Exception e) {
            throw new Exception500("회원가입 실패 : " + e.getMessage());
        }

        // JWT 인증 로직
        Optional<User> userOP = userRepository.findByUsername(joinDTO.getUsername());
        if (userOP.isPresent()) {
            User userPS = userOP.get(); // 조회하는 객체는 PS
            if (passwordEncoder.matches(rawPassword, userPS.getPassword())) {
                String jwt = MyJwtProvider.create(userPS);

                // Body 만들기
                UserResp.JoinDTO body = new UserResp.JoinDTO(userPS);
                ResponseDTO<?> responseDTO = new ResponseDTO<>(body);

                // ResponseEntity 생성
                return ResponseEntity.ok().header(MyJwtProvider.HEADER, jwt).body(responseDTO);
            }
            throw new RuntimeException("패스워드 유효성 실패");
        } else {
            throw new RuntimeException("이메일 유효성 실패");
        }
    }

    @Transactional
    public void update() {
    }
}
