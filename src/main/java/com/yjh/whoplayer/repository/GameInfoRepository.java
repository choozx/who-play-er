package com.yjh.whoplayer.repository;

import com.yjh.whoplayer.entity.GameInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameInfoRepository extends JpaRepository<GameInfoEntity, Long> {

    Page<GameInfoEntity> findByErUidOrderByGameIdDesc(long erUid, Pageable pageable);
    List<GameInfoEntity> findByGameIdIn(List<Long> gameId);
}
