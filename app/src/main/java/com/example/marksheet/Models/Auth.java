package com.example.marksheet.Models;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import com.example.marksheet.domain.LoginDataList;


public interface Auth {

    @POST("api/auth/signin")
    @FormUrlEncoded
    Call<LoginDataList> login(@Field("email") String username,
                              @Field("password") String password);

}
