package com.example.marksheet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marksheet.Models.StudentDetails;
import com.example.marksheet.domain.LoginDataList;
import com.example.marksheet.domain.StudentDataList;
import com.example.marksheet.utils.Session;
import com.example.marksheet.utils.StudentData;
import com.example.marksheet.utils.Urls;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {
    EditText fname, mname, lname, parentPhone, dob, gender, address, bloodGroup;
    private Button submitUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        fname = findViewById(R.id.fnameEditText);
        mname = findViewById(R.id.mnameEditText);
        lname = findViewById(R.id.lnameEditText);
        parentPhone = findViewById(R.id.parentPhoneEditText);
        dob = findViewById(R.id.dobEditText);
        gender = findViewById(R.id.genderEditText);
        address = findViewById(R.id.addressEditText);
        bloodGroup = findViewById(R.id.bloodGroupEditText);

        fname.setText(StudentData.fname);
        mname.setText(StudentData.mname);
        lname.setText(StudentData.lname);
        parentPhone.setText(StudentData.parent_phone);
        dob.setText(StudentData.dob);
        gender.setText(StudentData.gender);
        address.setText(StudentData.address);
        bloodGroup.setText(StudentData.blood_group);

        submitUpdate = findViewById(R.id.saveStudentUpdate);
        submitUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentDetails studentDetails = Urls.getInstance().create(StudentDetails.class);

                Call<StudentDataList> call = studentDetails.updateStudentDetails(
                        "Bearer " + Session.token,
                        Session.userId,
                        fname.getText().toString(),
                        mname.getText().toString(), lname.getText().toString(),
                        parentPhone.getText().toString(), dob.getText().toString(),
                        gender.getText().toString(), address.getText().toString(),
                        bloodGroup.getText().toString());
                call.enqueue(new Callback<StudentDataList>() {
                    @Override
                    public void onResponse(Call<StudentDataList> call, Response<StudentDataList> response) {
                        Log.d("URL","url used:" + call.request().url());
                        Log.d("Session","token: " + Session.token);
                        Log.d("Logs", "post submitted to API.status code: " + response.body());

                        if (response.isSuccessful()) {
                            Log.d("Logs", "post submitted to API.status code: " + response.body().toString());
                            Toast.makeText(EditProfileActivity.this, "Profile Update successful!!!", Toast.LENGTH_SHORT).show();
                            Intent dashboardIntent = new Intent(EditProfileActivity.this, DashboardActivity.class);
                            startActivity(dashboardIntent);
                        } else {
                            Toast.makeText(EditProfileActivity.this, "Wrong Credentials!!!", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<StudentDataList> call, Throwable t) {
                        Toast.makeText(EditProfileActivity.this, "Network error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
