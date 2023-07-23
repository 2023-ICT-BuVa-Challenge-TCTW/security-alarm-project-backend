package com.example.buva.police.controller;

import com.example.buva.police.dto.PoliceFindReq;
import com.example.buva.police.service.PoliceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PoliceController {

    private final PoliceService policeService;

    @GetMapping("/api/police")
    public ResponseEntity<?> findPolice(@RequestBody @Valid PoliceFindReq policeFindReq) {
        return policeService.findPolice(policeFindReq);
    }
}
