package com.hbtech.cheptel.repository;

import com.hbtech.cheptel.entity.RfidTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RfidTagRepository extends JpaRepository<RfidTag, Long> {
    Optional<RfidTag> findByRfidCode(String rfidCode);
}
