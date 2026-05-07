package com.hbtech.cheptel.repository;

import com.hbtech.cheptel.entity.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {

    @Query("select v from Vaccination v where v.healthRecord.animal.id = :animalId order by v.createdAt desc")
    List<Vaccination> findByAnimalIdOrderByVaccinationDateDesc(@Param("animalId") Long animalId);
}
