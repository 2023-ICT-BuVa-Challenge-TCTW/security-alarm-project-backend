package com.example.buva.report.dto;

import jakarta.validation.constraints.NotBlank;

public record ReportInsertReq(@NotBlank(message = "username을 입력해주세요") String username,
                             @NotBlank(message = "신고 메세지를 입력해주세요") String content) {
}
