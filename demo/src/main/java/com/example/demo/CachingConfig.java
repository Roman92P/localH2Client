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
        CacheManager cacheManager = this.cacheManager;
        RequestQueryParamCache requestQueryParamCache = new RequestQueryParamCache();
        requestQueryParamCache.setSearchIdentifier("code1");
        requestQueryParamCache.setSearchIdentifierTwo("hola");

        cacheManager.getCache("requestparam").put("code1+hola", requestQueryParamCache);
        TestEntity t = this.testEntityService.getTestEntity("code1+hola");
        cacheManager.getCache("tests").put(t.getSearchidentifier(), t);
    }
}
