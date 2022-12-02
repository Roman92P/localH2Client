package com.example.demo;

import jakarta.annotation.PostConstruct;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TestEntityService {
    private final TestEntityRepo testEntityRepo;

    public TestEntityService(TestEntityRepo testEntityRepo) {
        this.testEntityRepo = testEntityRepo;
    }

    @Cacheable("tests")
    public TestEntity getTestEntity(long id) {
        return testEntityRepo.findById(id).get();
    }

    public void  saveTestEntity(TestEntity t) {
        testEntityRepo.save(t);
    }

//    @PostConstruct
//    public void init() {
//       TestEntity t = getTestEntity(1);
//       t.setName("blblblblbl");
//       saveTestEntity(t);
//    }

}
