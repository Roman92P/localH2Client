package com.example.demo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestEntityRepo extends JpaRepository<TestEntity, Long> {

    @Cacheable(value = {"tests"})
    @Query(value = "SELECT * From test_entity Where searchIdentifier = :param", nativeQuery = true)
    Optional<TestEntity> findBySearchIdentifier(@Param("param")String searchIdentifier);
}
