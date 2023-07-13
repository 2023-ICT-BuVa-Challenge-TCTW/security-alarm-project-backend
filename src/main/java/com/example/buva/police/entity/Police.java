package com.example.buva.police.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Police {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String district;
    private String name;
    private String division;
    private String phone;
    private String address;
    @Column(nullable = false, columnDefinition = "GEOMETRY")
    private Point point;
}
