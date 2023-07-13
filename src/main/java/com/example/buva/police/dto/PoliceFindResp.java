package com.example.buva.police.dto;

import com.example.buva.police.entity.Police;

public record PoliceFindResp(String city, String district, String name, String division, String phone, String address) {
    public PoliceFindResp(Police police) {
        this(police.getCity(), police.getDistrict(), police.getName(), police.getDivision(), police.getPhone(), police.getAddress());
    }
}
