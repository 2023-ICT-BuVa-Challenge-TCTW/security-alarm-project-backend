package com.example.buva.report.service;

import com.example.buva.global.security.MyUserDetails;
import com.example.buva.report.dto.ReportInsertReq;
import com.example.buva.report.dto.ReportInsertResp;
import com.example.buva.report.entity.Report;
import com.example.buva.report.repository.ReportRepository;
import com.example.buva.user.entity.User;
import com.example.buva.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

    private final UserRepository userRepository;

    @Transactional
    public ResponseEntity<?> insertReport(MyUserDetails myUserDetails,
                                          ReportInsertReq reportInsertReq) {
        User user = userRepository.findById(myUserDetails.user().getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다"));

        Report report = reportRepository.save(reportInsertReq.toEntity());

        return ResponseEntity.ok().body(new ReportInsertResp(report));
    }
}
