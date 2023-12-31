package com.example.buva.report.service;

import com.example.buva.global.security.MyUserDetails;
import com.example.buva.report.dto.ReportFindReq;
import com.example.buva.report.dto.ReportFindResp;
import com.example.buva.report.dto.ReportInsertReq;
import com.example.buva.report.dto.ReportInsertResp;
import com.example.buva.report.entity.Report;
import com.example.buva.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

    @Transactional
    public ResponseEntity<?> insertReport(MyUserDetails myUserDetails,
                                          ReportInsertReq reportInsertReq) {
        if(!Objects.equals(reportInsertReq.username(), myUserDetails.getUsername())) {
            return ResponseEntity.badRequest().body("유저 이름이 일치하지 않습니다");
        }

        Report report = reportRepository.save(reportInsertReq.toEntity());

        return ResponseEntity.ok().body(new ReportInsertResp(report));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> getReports(MyUserDetails myUserDetails,
                                        ReportFindReq reportFindReq) {
        if(!Objects.equals(reportFindReq.username(), myUserDetails.getUsername())) {
            return ResponseEntity.badRequest().body("유저 이름이 일치하지 않습니다");
        }

        List<Report> report = reportRepository.findByUsername(reportFindReq.username());

        return ResponseEntity.ok().body(new ReportFindResp(report));
    }
}
