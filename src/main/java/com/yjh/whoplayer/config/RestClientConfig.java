package com.yjh.whoplayer.config;

import com.yjh.whoplayer.model.type.EternalReturnURL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

/**
 * Created by dale on 2025-01-04.
 */

@Configuration
public class RestClientConfig {

    @Value("${eternal-return-api-key}")
    public String apiKey;

    @Bean
    public RestClient restClient() {
        return RestClient.builder().baseUrl(EternalReturnURL.BASE.getUrl())
                .defaultHeader("x-api-key", apiKey)
                .build();
    }
}
