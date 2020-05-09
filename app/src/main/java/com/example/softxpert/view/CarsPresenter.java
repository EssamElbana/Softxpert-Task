package com.example.softxpert.view;

import com.example.softxpert.model.Car;
import com.example.softxpert.repository.CallbackInterface;
import com.example.softxpert.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class CarsPresenter implements CarListContract.Presenter {

    private CarListContract.View mView;
    private Repository repository;
    private int currentPageNumber = 0;
    private List<Car> cars;

    public CarsPresenter(CarListContract.View view) {
        mView = view;
        repository = new Repository();
        cars = new ArrayList<>();
    }

    @Override
    public void onViewCreated() {
        getCarsForView(new CallbackInterface() {
            @Override
            public void onSuccess(List<Car> carList) {
                if (mView != null) {
                    mView.showProgressDialog(false);
                    if (cars == null)
                        cars = carList;
                    cars.addAll(carList);
                    mView.showList(cars);
                }
            }

            @Override
            public void onFailure() {
                if (mView != null) {
                    mView.showProgressDialog(false);
                    mView.showErrorMessage();
                }
            }
        });
    }

    @Override
    public void onViewDestroyed() {
        repository = null;
        mView = null;
    }

    @Override
    public void fetchNewCars() {
        getCarsForView(new CallbackInterface() {
            @Override
            public void onSuccess(List<Car> carList) {
                if (mView != null) {
                    mView.showProgressDialog(false);
                    if (carList != null) {
                        if (cars == null)
                            cars = carList;
                        cars.addAll(carList);
                        mView.onNewCarsReady(cars);
                    }
                }
            }

            @Override
            public void onFailure() {
                if (mView != null) {
                    mView.showProgressDialog(false);
                    mView.showErrorMessage();
                }
            }
        });
    }

    private void getCarsForView(CallbackInterface callbackInterface) {
        if (mView != null && repository != null) {
            mView.showProgressDialog(true);
            repository.getCars(++currentPageNumber + "", callbackInterface);
        }
    }
}
