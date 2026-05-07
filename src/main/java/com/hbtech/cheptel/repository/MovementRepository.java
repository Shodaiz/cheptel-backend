package com.hbtech.cheptel.repository;

import com.hbtech.cheptel.entity.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovementRepository extends JpaRepository<Movement, Long> {
    List<Movement> findByAnimalIdOrderByCreatedAtDesc(Long animalId);
    List<Movement> findByFromFarmIdOrToFarmIdOrderByCreatedAtDesc(Long fromFarmId, Long toFarmId);

    default List<Movement> findByAnimalIdOrderByMovementDateDesc(Long animalId) {
        return findByAnimalIdOrderByCreatedAtDesc(animalId);
    }

    default List<Movement> findByFromFarmIdOrToFarmIdOrderByMovementDateDesc(Long fromFarmId, Long toFarmId) {
        return findByFromFarmIdOrToFarmIdOrderByCreatedAtDesc(fromFarmId, toFarmId);
    }
}
