package com.example.marksheet.utils;

import android.content.Intent;
import android.util.Log;
import android.util.Printer;
import android.widget.Toast;

import com.example.marksheet.DashboardActivity;
import com.example.marksheet.MainActivity;
import com.example.marksheet.Models.StudentDetails;
import com.example.marksheet.Models.UserDetails;
import com.example.marksheet.domain.LoginDataList;
import com.example.marksheet.domain.StudentDataList;
import com.example.marksheet.domain.UserDataList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FunctionalOperations {

    private static String email = "";

    public static String getUserId(String parentUserId) {
        String userId = "";
        getEmailAddress(parentUserId);
        StudentDetails studentDetails = Urls.getInstance().create(StudentDetails.class);
        Call<List<StudentDataList>> call = studentDetails.getAllStudents("Bearer " + Session.token);
        call.enqueue(new Callback<List<StudentDataList>>() {
            @Override
            public void onResponse(Call<List<StudentDataList>> call, Response<List<StudentDataList>> response) {
                for (StudentDataList studentDataList : response.body()) {
                }
            }

            @Override
            public void onFailure(Call<List<StudentDataList>> call, Throwable t) {

            }
        });

        return userId;
    }

    public static void getEmailAddress(String parentUserId) {
        UserDetails userDetails = Urls.getInstance().create(UserDetails.class);
        Call<UserDataList> call = userDetails.getUserDetail(parentUserId);
        call.enqueue(new Callback<UserDataList>() {
            @Override
            public void onResponse(Call<UserDataList> call, Response<UserDataList> response) {
                if (response.isSuccessful()) {
                    Log.d("Logs", "post submitted to API.status code: " + response.body().toString());
                    email = response.body().getEmail();

                } else {

                }
            }

            @Override
            public void onFailure(Call<UserDataList> call, Throwable t) {

            }
        });
    }
}
