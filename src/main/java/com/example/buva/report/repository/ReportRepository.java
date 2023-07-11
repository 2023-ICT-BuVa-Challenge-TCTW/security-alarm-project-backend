package com.example.buva.report.repository;

import com.example.buva.report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByUsername(String username);
}
