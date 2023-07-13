package com.example.buva.police.service;

import com.example.buva.police.dto.PoliceFindReq;
import com.example.buva.police.dto.PoliceFindResp;
import com.example.buva.police.entity.Police;
import com.example.buva.police.repository.PoliceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PoliceService {

    private final PoliceRepository policeRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<?> findPolice(PoliceFindReq policeFindReq) {

        List<Police> policeList = policeRepository.findPoliceByDistance(
                policeFindReq.latitude(), policeFindReq.longitude(), policeFindReq.radius());

        if(policeList.isEmpty()) {
            return ResponseEntity.badRequest().body("근처에 경찰서가 없습니다");
        }

        return ResponseEntity.ok().body(new PoliceFindResp(policeList));
    }
}
