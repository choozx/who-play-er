package com.yjh.whoplayer.model.cache;

import com.fasterxml.jackson.annotation.JsonValue;
import com.yjh.whoplayer.support.CodeEnum;

import java.util.Arrays;

/**
 * Created by dale on 2025-01-11.
 */
public enum CacheType implements CodeEnum<String> {
    CACHE_CELEBRITY("CACHE_CELEBRITY", "celebrity", CacheCelebrity.class),
    ;

    private final String code;
    private final String tableName;
    private final Class<?> cacheClass;

    CacheType(String code, String tableName, Class<?> cacheClass) {
        this.code = code;
        this.tableName = tableName;
        this.cacheClass = cacheClass;
    }

    @Override
    @JsonValue
    public String getCode() {
        return code;
    }

    @Override
    public String getKey() {
        return name();
    }

    public Class<?> getCacheClass() {
        return cacheClass;
    }

    public String getTableName() {
        return tableName;
    }

    public static Class<?> of(String tableName){
        return Arrays.stream(values())
                .filter(dictionaryType -> dictionaryType.getTableName().toLowerCase().equals(tableName))
                .findFirst().orElseThrow().getCacheClass();
    }
}
