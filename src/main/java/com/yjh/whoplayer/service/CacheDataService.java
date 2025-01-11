package com.yjh.whoplayer.service;

import com.yjh.whoplayer.model.cache.CacheData;
import com.yjh.whoplayer.model.cache.CacheType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dale on 2025-01-11.
 */

@Slf4j
public class CacheDataService {

    private final JdbcTemplate jdbcTemplate;
    private final List<Class<?>> cacheClassList;
    private final Map<Class<?>, Pair<String, String>> cacheClassTypeMap;
    private static final Map<Class<?>, Object> cacheClassMap = new ConcurrentHashMap<>();

    public CacheDataService(JdbcTemplate commonJdbcTemplate, List<CacheType> dicTypeList) {
        this.jdbcTemplate = commonJdbcTemplate;

        List<Class<?>> classList = new ArrayList<>();
        Map<Class<?>, Pair<String, String>> classTypeMap = new ConcurrentHashMap<>();

        dicTypeList.forEach(dicKind -> {
            classList.add(dicKind.getCacheClass());
            Object prev = classTypeMap.put(dicKind.getCacheClass(), Pair.of(dicKind.getCode(), dicKind.getTableName()));

            if (prev != null) log.warn("[cache] 키 중복 발생!! key:{}, code:{}, tableName:{}", dicKind.getCacheClass(), dicKind.getCode(), dicKind.getTableName());
        });

        this.cacheClassList = Collections.unmodifiableList(classList);
        this.cacheClassTypeMap = Collections.unmodifiableMap(classTypeMap);

        reloadAll();
    }

    public int reloadAll() {
        return reloadList(cacheClassList);
    }

    private int reloadList(List<Class<?>> classList){
        log.trace("-----------------------------------------------------");
        log.trace("[Dictionary] Dictionary Table Loading !!");
        log.trace("-----------------------------------------------------");
        int numReloaded = 0;
        for (Class<?> dictionaryClass : classList) {
            boolean reloaded = false;
            try {
                reloaded = reload(dictionaryClass);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }

            if (reloaded) numReloaded++;
        }

        log.trace("-----------------------------------------------------");
        return numReloaded;
    }

    public <T> boolean reload(Class<T> cacheClass) throws Exception {

        Pair<String, String> dicTypePair = cacheClassTypeMap.get(cacheClass);
        String typeName = dicTypePair.getFirst();
        String tableName = dicTypePair.getSecond();
        if (typeName == null) {
            log.warn("[cache] 타입정보 무 존재! class:{}", cacheClass);
            return false;
        }

        Type[] interfaceTypes = cacheClass.getGenericInterfaces();
        for (Type interfaceType : interfaceTypes) {
            if (! (interfaceType instanceof ParameterizedType)) continue;

            ParameterizedType pt = ((ParameterizedType) interfaceType);
            if (pt.getRawType().getTypeName().equals(CacheData.class.getTypeName())) { // rawType 은 cacheTable 까지를 의미
                Type typeArgType = pt.getActualTypeArguments()[0]; // cacheTable<> 내의 인자
                Class<?> dicItemType = Class.forName(typeArgType.getTypeName());

                // rawItemList 조회
                List<?> rawItemList = selectItemList(tableName, dicItemType);

                // cache 객체 생성: 조회된 rawItemList 를 인자로 받는 생성자를 reflection
                Constructor<T> rawListArgConstructor = cacheClass.getConstructor(List.class);
                T cache = rawListArgConstructor.newInstance(rawItemList);

                cacheClassMap.put(cacheClass, cache);
                log.info("[cache] class:{}, itemList.size:{}", cacheClass.getName(), rawItemList.size());

                return true;
            }
        }

        throw new AssertionError("[cache] class({})는 cache<XxxXxx> Interface 를 반드시 구현해야 한다!!");
    }

    public static <T> T getDic(Class<T> cacheClass) {
        T cache = (T) cacheClassMap.get(cacheClass);
        if (cache == null) throw new IllegalArgumentException("[cache] Not found!! class:" + cacheClass.getName());
        return cache;
    }

    private <T> List<T> selectItemList(String tableName, Class<T> itemType) {
        String query = "SELECT * FROM " + tableName;
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(itemType));
    }
}
