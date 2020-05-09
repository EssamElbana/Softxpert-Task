package com.example.softxpert.repository;

import android.util.Log;

import com.example.softxpert.model.NetworkCallResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Repository {

    private Retrofit retrofit;
    private ApiCalls apiCalls;

    public Repository() {
        retrofit = NetworkClient.getRetrofitClient();
        apiCalls = retrofit.create(ApiCalls.class);
    }

    public void getCars(String pageNumber, final CallbackInterface callbackInterface) {
        Call<NetworkCallResponse> call = apiCalls.getCars(pageNumber);
        call.enqueue(new Callback<NetworkCallResponse>() {
            @Override
            public void onResponse(Call<NetworkCallResponse> call, Response<NetworkCallResponse> response) {
                if (response.isSuccessful())

                    callbackInterface.onSuccess(response.body().getData());
                else
                    callbackInterface.onFailure();
            }

            @Override
            public void onFailure(Call<NetworkCallResponse> call, Throwable t) {
                Log.v("SOFT_XPRT", t.getMessage());
                callbackInterface.onFailure();
            }
        });
    }

}
