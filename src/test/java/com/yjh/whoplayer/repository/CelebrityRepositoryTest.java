package com.yjh.whoplayer.repository;

import com.yjh.whoplayer.entity.CelebrityEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dale on 2025-01-04.
 */

@Slf4j
@SpringBootTest
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class CelebrityRepositoryTest {

    private final CelebrityRepository celebrityRepository;

    @Test
    @Transactional
    public void 셀럽_저장() {
        CelebrityEntity celebrityEntity = CelebrityEntity.newEntity(2, "테슼트", "테테스트");
        celebrityRepository.save(celebrityEntity);
    }

    @Test
    @Transactional
    public void 셀럽_저장후_불러오기() {
        CelebrityEntity celebrityEntity = CelebrityEntity.newEntity(2, "쌍문동곡갱이", "씁하");
        celebrityRepository.save(celebrityEntity);

        List<CelebrityEntity> celebrityEntityList = celebrityRepository.findAll();
        for (var celeb : celebrityEntityList) {
            log.info("{}", celeb);
        }
    }
}