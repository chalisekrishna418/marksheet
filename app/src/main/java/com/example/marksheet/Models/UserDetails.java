package com.example.marksheet.Models;

import com.example.marksheet.domain.MarksheetData;
import com.example.marksheet.domain.UserDataList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface UserDetails {
    @GET("api/teachers/findById/{parentUserId}")
    Call<UserDataList> getUserDetail(@Path("parentUserId") String parentUId);
}
