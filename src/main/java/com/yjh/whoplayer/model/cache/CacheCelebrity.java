package com.yjh.whoplayer.model.cache;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by dale on 2025-01-11.
 */
public class CacheCelebrity implements CacheData<CacheCelebrity.Celebrity>{

    private final List<Celebrity> sortedList;
    private final Map<Long, Celebrity> celebrityMap;

    public CacheCelebrity(List<Celebrity> rawList) {
        this.sortedList = rawList.stream().sorted(Comparator.comparing(Celebrity::getIdx)).collect(Collectors.toUnmodifiableList());
        this.celebrityMap = Collections.unmodifiableMap(sortedList.stream().collect(Collectors.toMap(Celebrity::getUserNum, Function.identity())));
    }

    @Override
    public List<Celebrity> getSortedList() {
        return sortedList;
    }

    public boolean isCelebErUid(long erUid) {
        return celebrityMap.containsKey(erUid);
    }

    public Celebrity getCeleb(long erUid) {
        return celebrityMap.get(erUid);
    }

    @Data
    public static class Celebrity {
        private long idx;
        private long userNum;
        private String erNickname;
        private String streamerName;

    }
}
