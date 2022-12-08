package com.example.demo;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TestEntityService {
    private final TestEntityRepo testEntityRepo;


    private final CacheManager cacheManager;

    public TestEntityService(TestEntityRepo testEntityRepo, CacheManager cacheManager) {
        this.testEntityRepo = testEntityRepo;
        this.cacheManager = cacheManager;
    }


    public TestEntity getTestEntity(String searchIdentifier) {
        Cache cacheParams = cacheManager.getCache("requestparam");
        Cache entityCache = cacheManager.getCache("tests");
        ConcurrentHashMap<String, RequestQueryParamCache> r = (ConcurrentHashMap<String, RequestQueryParamCache>) cacheParams.getNativeCache();
        String params = null;
        for (String s : r.keySet()) {
            params = s;
        }
        System.out.println(params);
        System.out.println(searchIdentifier);
        System.out.println(params.equals(searchIdentifier));
        if (params.equals(searchIdentifier)) {
            System.out.println("Here 1!");
            System.out.println(Optional.ofNullable(entityCache.get(searchIdentifier.split("\\+")[0])).isEmpty());
            if (Optional.ofNullable(entityCache.get(searchIdentifier.split("\\+")[0])).isEmpty()) {
                 return testEntityRepo.findBySearchIdentifier(searchIdentifier.split("\\+")[0]).get();
            }
            Cache.ValueWrapper valueWrapper = entityCache.get(searchIdentifier.split("\\+")[0]);
            return (TestEntity) valueWrapper.get();
        } else {
            System.out.println("Here 2!");
            cleanCache("requestparam");
            cleanCache("tests");
            String[] arr = searchIdentifier.split("\\+");
            saveRequestParamCache(searchIdentifier);
            return testEntityRepo.findBySearchIdentifier(searchIdentifier.split("\\+")[0]).get();
        }
    }

    private void saveRequestParamCache(String searchIdentifier) {
        String[] arr = searchIdentifier.split("\\+");
        cacheManager.getCache("requestparam")
                .put(searchIdentifier, new RequestQueryParamCache(arr[0], arr[1]));
    }

    public TestEntity getTestEntityOne(String searchIdentifier) {
        return testEntityRepo.findBySearchIdentifier(searchIdentifier).get();
    }

    private void cleanCache(String cacheName) {
        Cache cache = this.cacheManager.getCache(cacheName);
        cache.clear();
    }


    public void saveTestEntity(TestEntity t) {
        testEntityRepo.save(t);
    }
}
