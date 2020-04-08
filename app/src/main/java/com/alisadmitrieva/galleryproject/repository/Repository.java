package com.alisadmitrieva.galleryproject.repository;

import com.alisadmitrieva.galleryproject.data.api.APIService;
import com.alisadmitrieva.galleryproject.utils.Constants;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    public Observable<List<String>> getDataFromAPI() {
        APIService apiService = createAPIService();
        return apiService.getPicturesData();
    }

    private APIService createAPIService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(APIService.class);
    }

}
