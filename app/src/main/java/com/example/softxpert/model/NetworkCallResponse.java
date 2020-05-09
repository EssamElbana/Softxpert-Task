package com.example.softxpert.model;

import java.util.List;

public class NetworkCallResponse {
    String status;
    List<Car> data;

    public List<Car> getData() {
        return data;
    }
}
