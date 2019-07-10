package com.example.marksheet.Models;

import com.example.marksheet.domain.FeedbackData;
import com.example.marksheet.domain.MarksheetData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Marksheet {
    @GET("api/results/datatable/")
    Call<List<MarksheetData>> getMarksheet(@Header("Authorization") String token);

    @POST("api/results/feedback-save")
    @FormUrlEncoded
    Call<FeedbackData> provideFeedback(@Header("Authorization") String token,
                                       @Field("user_id") String userId,
                                       @Field("feedback") String feedback,
                                       @Field("serial_number") String userSerialNumber);
}
