package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class RequestQueryParamCache implements Serializable {

    private String searchIdentifier;
    private String searchIdentifierTwo;

    public RequestQueryParamCache(String searchIdentifier) {
        this.searchIdentifier = searchIdentifier;
    }

    public RequestQueryParamCache(String searchIdentifier, String searchIdentifierTwo) {
        this.searchIdentifier = searchIdentifier;
        this.searchIdentifierTwo = searchIdentifierTwo;
    }

    public RequestQueryParamCache() {
    }

    public String getSearchIdentifier() {
        return searchIdentifier;
    }

    public void setSearchIdentifier(String searchIdentifier) {
        this.searchIdentifier = searchIdentifier;
    }

    public String getSearchIdentifierTwo() {
        return searchIdentifierTwo;
    }

    public void setSearchIdentifierTwo(String searchIdentifierTwo) {
        this.searchIdentifierTwo = searchIdentifierTwo;
    }

    @Override
    public String toString() {
        return searchIdentifier + " " + searchIdentifierTwo;
    }
}
