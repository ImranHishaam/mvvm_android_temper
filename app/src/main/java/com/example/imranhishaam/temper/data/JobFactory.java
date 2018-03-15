package com.example.imranhishaam.temper.data;

/**
 * Created by imranhishaam on 3/13/18.
 */

import com.example.imranhishaam.temper.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class JobFactory {

  public static JobService create() {
    Retrofit retrofit = new Retrofit.Builder().baseUrl(BuildConfig.HOST_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();
    return retrofit.create(JobService.class);
  }
}


