package com.example.dell.smartfixapp;

import com.example.dell.smartfixapp.RetrofitModel.MyResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    String Base_URL="https://www.googleapis.com/blogger/v3/blogs/895605063832439983/";
    String key="AIzaSyDmDubK5Pnfod227If83YEfkhCVtK_n534";

    @GET("posts?key="+key)
    Call<MyResponse> getAllPost();

}
