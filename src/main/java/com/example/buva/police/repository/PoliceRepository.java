package com.example.buva.police.repository;

import com.example.buva.police.entity.Police;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PoliceRepository extends JpaRepository<Police, Long> {
//    @Query(value = "select t from Police t where dwithin(t.gps, :point, 30000, false) = true")
//    List<Police> findPoliceByDistance(@Param("point") Point point);
}
