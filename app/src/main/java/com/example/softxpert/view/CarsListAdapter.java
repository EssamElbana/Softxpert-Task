package com.example.softxpert.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.softxpert.R;
import com.example.softxpert.model.Car;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CarsListAdapter extends RecyclerView.Adapter<CarsListAdapter.ViewHolder> {

    private List<Car> mData;
    private LayoutInflater mInflater;
    private AdapterCommunicator adapterCommunicator;
    CarsListAdapter(Context context, List<Car> data, AdapterCommunicator adapterCommunicator) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.adapterCommunicator = adapterCommunicator;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.car_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(position == mData.size()-1)
        {
            // todo do this in a thread.
            adapterCommunicator.fetchNewData();
        }
        Car car = mData.get(position);
        holder.carBrandTextView.setText(car.getBrand());
        holder.carConstructionYear.setText(car.getConstructionYear());
        holder.carCurrentState.setText(car.getUsed());
        holder.setCarImage(car.getImageUrl());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView carBrandTextView, carConstructionYear, carCurrentState;
        ImageView carImage;

        ViewHolder(View itemView) {
            super(itemView);
            carBrandTextView = itemView.findViewById(R.id.car_brand);
            carConstructionYear = itemView.findViewById(R.id.car_construction_year);
            carCurrentState = itemView.findViewById(R.id.car_current_state);
            carImage = itemView.findViewById(R.id.car_image);
        }
        void setCarImage(String url) {
            Picasso.get().load(url).into(carImage);
        }
    }

    public void setCarsList(List<Car> cars) {
        this.mData = cars;
    }
}