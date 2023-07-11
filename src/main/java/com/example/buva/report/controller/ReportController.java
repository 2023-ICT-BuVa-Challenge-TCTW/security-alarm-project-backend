package com.example.buva.report.controller;

import com.example.buva.global.security.MyUserDetails;
import com.example.buva.report.dto.ReportInsertReq;
import com.example.buva.report.service.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping("/reports")
    public ResponseEntity<?> insertReport(@AuthenticationPrincipal MyUserDetails myUserDetails,
                                          @RequestBody @Valid ReportInsertReq reportInsertReq) {
        return reportService.insertReport(myUserDetails, reportInsertReq);
    }
}
