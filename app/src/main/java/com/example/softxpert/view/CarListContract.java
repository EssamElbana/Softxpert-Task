package com.example.softxpert.view;

import com.example.softxpert.model.Car;

import java.util.List;

public interface CarListContract {

    interface View {
        void showList(List<Car> carList);

        void showProgressDialog(boolean visibilityState);

        void showErrorMessage();

        void onNewCarsReady(List<Car> cars);
    }

    interface Presenter {
        void onViewCreated();

        void onViewDestroyed();

        void fetchNewCars();
    }
}
