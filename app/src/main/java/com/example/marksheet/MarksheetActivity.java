package com.example.marksheet;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marksheet.Models.Marksheet;
import com.example.marksheet.domain.Marks;
import com.example.marksheet.domain.MarksheetData;
import com.example.marksheet.domain.StudentDataList;
import com.example.marksheet.utils.Session;
import com.example.marksheet.utils.Urls;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarksheetActivity extends AppCompatActivity {
    private TextView studentName, studentClass, parentName, serialNo, subject, fullMarks, passMarks, obtainedMarks;
    private ListView marksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marksheet);

        studentName = findViewById(R.id.student_name);
        studentClass = findViewById(R.id.student_class);
        parentName = findViewById(R.id.parent_name);
        serialNo = findViewById(R.id.serial_no);
        subject = findViewById(R.id.subject);
        fullMarks = findViewById(R.id.full_marks);
        passMarks = findViewById(R.id.pass_marks);
        obtainedMarks = findViewById(R.id.obtained_marks);
        marksList = findViewById(R.id.marksList);


        final Marksheet marksheet = Urls.getInstance().create(Marksheet.class);
        Call<List<MarksheetData>> call = marksheet.getMarksheet("Bearer " + Session.token);
        call.enqueue(new Callback<List<MarksheetData>>() {
            @Override
            public void onResponse(Call<List<MarksheetData>> call, Response<List<MarksheetData>> response) {
                Log.d("URL","url used:" + call.request().url());

                if (response.isSuccessful()){
                    List<MarksheetData> marksheetDataList = response.body();
                    Log.v("Log", "Student Data Response: " + marksheetDataList);
                    for ( MarksheetData marksheetData : marksheetDataList) {
                        Log.d("Data", "Reponse Data: " + marksheetData);
                        Log.d("Data", "student id: " + marksheetData.getStudent().get_id());
                        Log.d("Data", "session UserId Value: " + Session.getUserId());


                        if (marksheetData.getStudent().get_id().equalsIgnoreCase("5d20eb1c9e1abe79c593dee9")){
                            Log.d("Data", "student Data: " + marksheetData.getStudent());
                            studentName.setText(marksheetData.getStudent().getFname()
                                    + " " + marksheetData.getStudent().getMname()
                                    + " " + marksheetData.getStudent().getLname());
                            studentClass.setText(marksheetData.getStandard());
                            parentName.setText(marksheetData.getStudent().getParent_name());
                            List<Marks> marksList = marksheetData.getMarks();
                            int i = 1;
                            for ( Marks marks: marksList) {
                                serialNo.setText(Integer.toString(i));
                                subject.setText(marks.getSubject());
                                obtainedMarks.setText(marks.getObtainedMarks());
                                fullMarks.setText(marks.getFullMarks());
                                passMarks.setText(marks.getPassMarks());
                                i++;
                            }
                        }
                    }

                } else {
                    Toast.makeText(MarksheetActivity.this, "Cannot Load Data!!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<MarksheetData>> call, Throwable t) {
                Toast.makeText(MarksheetActivity.this, "Network Error!!!", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
