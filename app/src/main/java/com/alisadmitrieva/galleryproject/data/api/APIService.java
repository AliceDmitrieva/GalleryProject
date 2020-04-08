package com.alisadmitrieva.galleryproject.data.api;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface APIService {

    @GET("list.php")
    Observable<List<String>> getPicturesData();

}
