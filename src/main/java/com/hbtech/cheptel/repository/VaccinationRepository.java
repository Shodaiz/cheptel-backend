package com.hbtech.cheptel.repository;

import com.hbtech.cheptel.entity.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {

    @Query("SELECT v FROM Vaccination v WHERE v.healthRecord.animal.id = :animalId ORDER BY v.createdAt DESC")
    List<Vaccination> findByAnimalIdOrderByVaccinationDateDesc(@Param("animalId") Long animalId);

    @Query("SELECT v FROM Vaccination v WHERE v.healthRecord.animal.farm.id = :farmId " +
           "AND v.nextVaccinationDate IS NOT NULL " +
           "AND v.nextVaccinationDate BETWEEN :start AND :end")
    List<Vaccination> findUpcomingForFarm(@Param("farmId") Long farmId,
                                         @Param("start") LocalDate start,
                                         @Param("end") LocalDate end);
}
