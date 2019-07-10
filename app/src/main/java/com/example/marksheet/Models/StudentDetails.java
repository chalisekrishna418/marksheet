package com.example.marksheet.Models;

import com.example.marksheet.domain.StudentDataList;
import com.example.marksheet.utils.Session;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StudentDetails {

    @GET("api/students/findById/{student_id}")
    Call<StudentDataList> getStudentDetails(@Path("student_id") String userId);

    @GET("api/students/datatable")
    Call<List<StudentDataList>> getAllStudents(@Header("Authorization") String token);

    @POST("api/students/update/{student_id}")
    @FormUrlEncoded
    Call<StudentDataList> updateStudentDetails(@Header("Authorization") String token,
                                               @Path("student_id") String userId,
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
