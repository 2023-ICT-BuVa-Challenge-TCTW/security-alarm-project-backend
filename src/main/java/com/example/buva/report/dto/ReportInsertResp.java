package com.example.buva.report.dto;

import com.example.buva.report.entity.Report;
import lombok.Getter;

public record ReportInsertResp(Long id, String username, String content) {
    public ReportInsertResp(Report entity) {
        this(entity.getId(), entity.getUsername(), entity.getContent());
    }
}
