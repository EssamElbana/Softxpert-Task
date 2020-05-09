package com.example.softxpert.repository;

import com.example.softxpert.model.Car;

import java.util.List;

public interface CallbackInterface {
    void onSuccess(List<Car> carList);
    void onFailure();
}
