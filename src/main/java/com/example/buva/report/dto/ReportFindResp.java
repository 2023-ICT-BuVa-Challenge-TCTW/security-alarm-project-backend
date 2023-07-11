package com.example.buva.report.dto;

import com.example.buva.report.entity.Report;

public record ReportFindResp(Long id, String username, String content) {

    public ReportFindResp(Report entity) {
        this(entity.getId(), entity.getUsername(), entity.getContent());
    }
}
