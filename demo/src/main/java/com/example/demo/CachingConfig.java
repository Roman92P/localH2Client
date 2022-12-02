package com.example.demo;

import jakarta.annotation.PostConstruct;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CachingConfig {

    private final TestEntityService testEntityService;

    private final CacheManager cacheManager;

    public CachingConfig(TestEntityService testEntityService, CacheManager cacheManager) {
        this.testEntityService = testEntityService;
        this.cacheManager = cacheManager;
    }

//    @Bean
//    public CacheManager cacheManager() {
//        TestEntity t = testEntityService.getTestEntity(1);
//        CacheManager cacheManager = new ConcurrentMapCacheManager();
//        cacheManager.getCache("tests").put(t.getId(), t);
//        return cacheManager;
//    }

    @PostConstruct
    public void initializeCache() {
        TestEntity t = this.testEntityService.getTestEntity(1);
       CacheManager cacheManager = this.cacheManager;
       cacheManager.getCache("tests").put(t.getId(),t);
    }
}
