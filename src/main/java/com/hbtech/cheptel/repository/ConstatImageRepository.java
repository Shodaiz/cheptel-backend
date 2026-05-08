package com.hbtech.cheptel.repository;

import com.hbtech.cheptel.entity.ConstatImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConstatImageRepository extends JpaRepository<ConstatImage, Long> {
    List<ConstatImage> findByConstatId(Long constatId);
}
