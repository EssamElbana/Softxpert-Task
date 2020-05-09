package com.example.softxpert.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softxpert.R;
import com.example.softxpert.model.Car;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CarListContract.View, AdapterCommunicator {

    private RecyclerView mRecyclerView;
    private CarsListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private CarListContract.Presenter mPresenter;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new CarsPresenter(this);
        initUIElements();
        mPresenter.onViewCreated();
    }

    private void initUIElements() {
        mProgressDialog = new ProgressDialog(this);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    @Override
    public void showList(List<Car> carList) {
        mAdapter = new CarsListAdapter(this, carList, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showProgressDialog(boolean visibilityState) {
        if (visibilityState)
            mProgressDialog.show();
        else
            mProgressDialog.hide();
    }

    @Override
    public void showErrorMessage() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("").setCancelable(false)
                .setMessage("Something Went Wrong Sry About That")
                .setPositiveButton("Alright, Retry", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        if (mPresenter != null)
                            mPresenter.onViewCreated();
                    }
                })
                .setNegativeButton("Close App", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }).show();

    }

    @Override
    public void onNewCarsReady(List<Car> cars) {
        mAdapter.setCarsList(cars);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void fetchNewData() {
        mPresenter.fetchNewCars();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onViewDestroyed();
        mPresenter = null;
    }
}
