package com.example.marksheet;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marksheet.Models.StudentDetails;
import com.example.marksheet.domain.StudentDataList;
import com.example.marksheet.utils.Session;
import com.example.marksheet.utils.StudentData;
import com.example.marksheet.utils.Urls;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    public static TextView userName, userRole;
    String fullName="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerLayout = navigationView.getHeaderView(0);
        userName = headerLayout.findViewById(R.id.user_name);
        userRole = headerLayout.findViewById(R.id.user_role);
        navigationView.setNavigationItemSelectedListener(this);


        System.out.println("UserId: " + Session.getUserId());
        StudentDetails studentDetails = Urls.getInstance().create(StudentDetails.class);
        Call<StudentDataList> call = studentDetails.getStudentDetails(Session.userId);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        call.enqueue(new Callback<StudentDataList>() {
            @Override
            public void onResponse(Call<StudentDataList> call, Response<StudentDataList> response) {
                Log.d("Logs","data posted");
                Log.d("URL","url used:" + call.request().url());

                if (response.isSuccessful()){
                    Log.i("HTTP","Response:" + response.body());
                    StudentDataList studentDataList = response.body();
                    StudentData.fname = studentDataList.getFname();
                    StudentData.mname = studentDataList.getMname();
                    StudentData.lname = studentDataList.getLname();
                    StudentData.parent_phone = studentDataList.getParent_phone();
                    StudentData.dob = studentDataList.getDob();
                    StudentData.gender = studentDataList.getGender();
                    StudentData.address = studentDataList.getAddress();
                    StudentData.blood_group = studentDataList.getBlood_group();
                    Log.i("Log", "Student Data Response: " + studentDataList);
                    Log.i("Log", "First Name" + studentDataList.getParent_name());
                    userName.setText(studentDataList.getParent_name());
                    userRole.setText("parent");

                } else {
                    Toast.makeText(DashboardActivity.this, "Cannot Load Data!!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StudentDataList> call, Throwable t) {
                Toast.makeText(DashboardActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.open_navigation_drawer, R.string.close_navigation_drawer);
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutSchoolFragment()).commit();
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.marksheet:
                Intent marksheetIntent = new Intent(DashboardActivity.this, MarksheetActivity.class);
                startActivity(marksheetIntent);
                break;
            case R.id.aboutschool:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutSchoolFragment()).commit();
                break;
            case R.id.edit_profile:
                Intent dashboardIntent = new Intent(DashboardActivity.this, EditProfileActivity.class);
                startActivity(dashboardIntent);
                break;
            case R.id.logout:
                Session.userId=Session.parentUserId=Session.role=Session.token="";
                Intent mainActivity = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(mainActivity);
                break;
            case R.id.feedback:
                Toast.makeText(this, "Feedback Button", Toast.LENGTH_SHORT).show();
                break;
            case R.id.aboutus:
                Toast.makeText(this, "About App Button Clicked", Toast.LENGTH_SHORT).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed(){
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
