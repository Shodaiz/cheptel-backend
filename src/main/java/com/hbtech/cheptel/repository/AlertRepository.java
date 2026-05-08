package com.hbtech.cheptel.repository;

import com.hbtech.cheptel.entity.Alert;
import com.hbtech.cheptel.entity.AlertType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    List<Alert> findByFarmIdOrderByDueDateAsc(Long farmId);

    @Query("SELECT a FROM Alert a WHERE a.farm.id = :farmId AND a.alertType = :alertType " +
           "AND a.animal.id = :animalId AND a.dueDate = :dueDate AND a.isResolved = false")
    List<Alert> findDuplicate(@Param("farmId") Long farmId,
                              @Param("alertType") AlertType alertType,
                              @Param("animalId") Long animalId,
                              @Param("dueDate") LocalDate dueDate);
}
