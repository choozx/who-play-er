package com.yjh.whoplayer.repository;

import com.yjh.whoplayer.entity.CelebrityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by dale on 2025-01-04.
 */
public interface CelebrityRepository extends JpaRepository<CelebrityEntity, Long> {

    List<CelebrityEntity> findAll();
}
