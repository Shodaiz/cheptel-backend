package com.hbtech.cheptel.repository;

import com.hbtech.cheptel.entity.HealthRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface HealthRecordRepository extends JpaRepository<HealthRecord, Long> {

    List<HealthRecord> findByAnimalIdOrderByVisitDateDesc(Long animalId);

    @Query("SELECT hr FROM HealthRecord hr WHERE hr.animal.farm.id = :farmId " +
           "AND hr.nextVisitDate IS NOT NULL " +
           "AND hr.nextVisitDate BETWEEN :start AND :end")
    List<HealthRecord> findUpcomingVisitsForFarm(@Param("farmId") Long farmId,
                                                 @Param("start") LocalDate start,
                                                 @Param("end") LocalDate end);
}