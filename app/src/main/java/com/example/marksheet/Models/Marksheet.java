package com.example.marksheet.Models;

import com.example.marksheet.domain.MarksheetData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface Marksheet {
    @GET("api/results/datatable/")
    Call<List<MarksheetData>> getMarksheet(@Header("Authorization") String token);
}
