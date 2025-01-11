package com.yjh.whoplayer.model.cache;

import java.util.List;

/**
 * Created by dale on 2025-01-11.
 */
public interface CacheData<T> {
    List<T> getSortedList();
}
