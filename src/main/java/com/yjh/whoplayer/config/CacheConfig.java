package com.yjh.whoplayer.config;

import com.yjh.whoplayer.model.cache.CacheType;
import com.yjh.whoplayer.service.CacheDataService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dale on 2025-01-11.
 */

@Configuration
public class CacheConfig {

    @Bean(name = "dictionaryJdbcTemplate")
    public JdbcTemplate dictionaryJdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setQueryTimeout(3000);
        return jdbcTemplate;
    }

    @Bean
    public CacheDataService bookLoader(@Qualifier("dictionaryJdbcTemplate") JdbcTemplate commonJdbcTemplate) {
        List<CacheType> list = new ArrayList<>();
        list.add(CacheType.CACHE_CELEBRITY);

        return new CacheDataService(commonJdbcTemplate, list);
    }
}
