package com.manoj.spring.springrest.config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfig {

    public static final String clientIds = "clientIds";

    private static final Logger log = LoggerFactory.getLogger(CacheConfig.class);

    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager(clientIds);
        return cacheManager;
    }

    @CacheEvict(allEntries = true, value = {clientIds})
    @Scheduled(cron = "${cron.expression.interval}")
    public void reportCacheEvict() {
        log.info("ClientId Cache refreshed at " + sdf.format(LocalDateTime.now()));
    }

}
