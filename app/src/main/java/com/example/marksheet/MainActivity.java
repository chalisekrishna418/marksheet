package com.example.marksheet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marksheet.Models.Auth;
import com.example.marksheet.domain.LoginDataList;
import com.example.marksheet.utils.Urls;

import com.example.marksheet.utils.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button loginButton;
    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Auth auth = Urls.getInstance().create(Auth.class);

                usernameEditText = findViewById(R.id.username);
                String username = usernameEditText.getText().toString();
                passwordEditText = findViewById(R.id.password);
                String password = passwordEditText.getText().toString();

                System.out.println("username: " + username);
                System.out.println("password: " + password);

                Call<LoginDataList> call = auth.login(username, password);

                call.enqueue(new Callback<LoginDataList>() {
                    @Override
                    public void onResponse(Call<LoginDataList> call, Response<LoginDataList> response) {
                        Log.d("Logs", "post submitted to API.status code: " + response.body().toString());
                        LoginDataList loginDataList = response.body();

                        if (response.isSuccessful()) {
                            Session.userId = loginDataList.getUserId();
                            Session.role = loginDataList.getRole();
                            Session.token = loginDataList.getToken();
                            Intent dashboardIntent = new Intent(MainActivity.this, DashboardActivity.class);
                            startActivity(dashboardIntent);
                        } else {
                            Toast.makeText(MainActivity.this, "Wrong Credentials!!!", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<LoginDataList> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Network error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
