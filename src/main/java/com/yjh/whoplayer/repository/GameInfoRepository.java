package com.yjh.whoplayer.repository;

import com.yjh.whoplayer.entity.GameInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameInfoRepository extends JpaRepository<GameInfoEntity, Long> {
}
