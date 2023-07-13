package com.example.buva.police.service;

import com.example.buva.police.dto.PoliceFindReq;
import com.example.buva.police.repository.PoliceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PoliceService {

    private final PoliceRepository policeRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<?> findPolice(PoliceFindReq policeFindReq) {

//        policeRepository.getPoliceByLocation(policeFindReq.getLatitude(), policeFindReq.getLongitude());

        return ResponseEntity.ok().build();
    }
}
