package com.example.marksheet.Models;

import com.example.marksheet.domain.StudentDataList;
import com.example.marksheet.utils.Session;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StudentDetails {

    @GET("api/students/findById/5d20eb1c9e1abe79c593dee9")
    Call<StudentDataList> getStudentDetails();

    @POST("api/students/update/5d20eb1c9e1abe79c593dee9")
    @FormUrlEncoded
    Call<StudentDataList> updateStudentDetails(@Header("Authorization") String token,
                                               @Field("fname") String fname,
                                               @Field("mname") String mname,
                                               @Field("lname") String lname,
                                               @Field("parent_phone") String parentPhone,
                                               @Field("dob") String dob,
                                               @Field("gender") String gender,
                                               @Field("address") String address,
                                               @Field("blood_group") String blood_group
                                               );
}
