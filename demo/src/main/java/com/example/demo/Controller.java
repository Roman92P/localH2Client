package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private TestEntityService testEntityService;


//    public Controller(TestEntityService testEntityService) {
//        this.testEntityService = testEntityService;
//    }

    @GetMapping(path = "/test/{searchIdentifier}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<TestEntity> getTestEntity (@PathVariable String searchIdentifier) {
        return ResponseEntity.ok(testEntityService.getTestEntity(searchIdentifier));
    }
}
