package com.yjh.whoplayer.service;

import com.yjh.whoplayer.model.cache.CacheCelebrity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

/**
 * Created by dale on 2025-01-11.
 */

@Slf4j
@SpringBootTest
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class CacheDataServiceTest {

    @Test
    public void 케싱_데이터_불러오기() {
        CacheCelebrity cacheCelebrity = CacheDataService.getDic(CacheCelebrity.class);

        for (var celeb : cacheCelebrity.getSortedList()) {
            log.info("{}", celeb);
        }
    }
}
