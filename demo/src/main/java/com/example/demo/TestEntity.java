package com.example.demo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;

@Entity
public class TestEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String searchidentifier;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSearchidentifier() {
        return searchidentifier;
    }

    public void setSearchidentifier(String searchidentifier) {
        this.searchidentifier = searchidentifier;
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
