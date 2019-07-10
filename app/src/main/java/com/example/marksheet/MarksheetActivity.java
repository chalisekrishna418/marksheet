package com.example.marksheet;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marksheet.Models.Marksheet;
import com.example.marksheet.domain.FeedbackData;
import com.example.marksheet.domain.Marks;
import com.example.marksheet.domain.MarksheetData;
import com.example.marksheet.domain.StudentDataList;
import com.example.marksheet.utils.Session;
import com.example.marksheet.utils.Urls;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarksheetActivity extends AppCompatActivity {
    TextView studentName, studentClass, parentName, percentageView, divisionView, totalPassMarksView, totalFullMarksView, totalObtainedMarksView;
    LinearLayout marksListLayout;
    EditText resultFeedbackText;
    Button feedBackSubmitButton;
    String result="passed";
    int totalFullMarks = 0, totalObtainedMarks = 0, totalPassMarks = 0;
    float percentage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marksheet);

        studentName = findViewById(R.id.student_name);
        studentClass = findViewById(R.id.student_class);
        parentName = findViewById(R.id.parent_name);
        marksListLayout = findViewById(R.id.marks_list);
        percentageView = findViewById(R.id.percentage);
        divisionView = findViewById(R.id.division);
        totalFullMarksView = findViewById(R.id.total_full_marks);
        totalPassMarksView = findViewById(R.id.total_pass_marks);
        totalObtainedMarksView = findViewById(R.id.total_obtained_marks);
        resultFeedbackText = findViewById(R.id.result_feedback);
        feedBackSubmitButton = findViewById(R.id.result_feedback_submit);


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

                        final LinearLayout lm = (LinearLayout) findViewById(R.id.marks_list);
                        Log.i("Data", "marksheet Data: " + marksheetData.getStudent());
                        Log.i("Data", "Sesson User Id: " + Session.userId);


                        if (marksheetData.getStudent().get_id().equalsIgnoreCase(Session.userId)){
                            Log.d("Data", "student Data: " + marksheetData.getStudent());
                            String full_name = marksheetData.getStudent().getFname() + " " + marksheetData.getStudent().getMname() + " " + marksheetData.getStudent().getLname();
                            studentName.setText(full_name);
                            studentClass.setText(marksheetData.getStandard());
                            parentName.setText(marksheetData.getStudent().getParent_name());
                            List<Marks> marksList = marksheetData.getMarks();
                            Session.serialNum = marksheetData.getSerialNum();
                            int i = 1;
                            for ( Marks marks: marksList) {
                                LinearLayout ll = new LinearLayout(MarksheetActivity.this);
                                ll.setOrientation(LinearLayout.HORIZONTAL);

                                TextView count = new TextView(MarksheetActivity.this);
                                count.setTypeface(Typeface.DEFAULT_BOLD);
                                count.setWidth(200);
                                count.setPadding(1,1,1,1);
                                count.setText(Integer.toString(i));
                                ll.addView(count);

                                TextView subjectData = new TextView(MarksheetActivity.this);
                                subjectData.setTypeface(Typeface.DEFAULT_BOLD);
                                subjectData.setWidth(500);
                                subjectData.setPadding(1,1,1,1);
                                subjectData.setText(marks.getSubject());
                                ll.addView(subjectData);

                                TextView fullMarksData = new TextView(MarksheetActivity.this);
                                fullMarksData.setTypeface(Typeface.DEFAULT_BOLD);
                                fullMarksData.setWidth(200);
                                fullMarksData.setPadding(1,1,1,1);
                                fullMarksData.setText(Integer.toString(marks.getFullMarks()));
                                ll.addView(fullMarksData);

                                TextView passMarksData = new TextView(MarksheetActivity.this);
                                passMarksData.setTypeface(Typeface.DEFAULT_BOLD);
                                passMarksData.setWidth(200);
                                passMarksData.setPadding(1,1,1,1);
                                passMarksData.setText(Integer.toString(marks.getPassMarks()));
                                ll.addView(passMarksData);

                                TextView obtainedMarksData = new TextView(MarksheetActivity.this);
                                obtainedMarksData.setTypeface(Typeface.DEFAULT_BOLD);
                                obtainedMarksData.setWidth(200);
                                obtainedMarksData.setPadding(1,1,1,1);
                                obtainedMarksData.setText(Integer.toString(marks.getObtainedMarks()));
                                ll.addView(obtainedMarksData);

                                if (result.equalsIgnoreCase("passed") && marks.getPassMarks() > marks.getObtainedMarks()) {
                                    result = "failed";
                                }

                                totalFullMarks += marks.getFullMarks();
                                totalPassMarks += marks.getPassMarks();
                                totalObtainedMarks += marks.getObtainedMarks();

                                lm.addView(ll);

                                i++;
                            }

                            percentage = ( (float) totalObtainedMarks * 100 ) /  ( (float) totalFullMarks) ;
                            Log.d("Marks","percentage: "+ percentage);
                            String division = "failed";
                            if ( percentage >= 80 ) {
                                division = "Distinction";
                            } else if ( percentage >= 60 ) {
                                division = "First Division";
                            } else if ( percentage >= 45 ) {
                                division = "Second Division";
                            }else if ( percentage >= 40 ) {
                                division = "Third Division";
                            } else {
                                division = "failed";
                            }
                            totalFullMarksView.setText(Integer.toString(totalFullMarks));
                            totalPassMarksView.setText(Integer.toString(totalPassMarks));
                            totalObtainedMarksView.setText(Integer.toString(totalObtainedMarks));

                            percentageView.setText(Float.toString(percentage));
                            divisionView.setText(division);
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

        feedBackSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Marksheet marksheet = Urls.getInstance().create(Marksheet.class);
                Call<FeedbackData> call = marksheet.provideFeedback("Bearer " + Session.token,
                        Session.parentUserId, resultFeedbackText.getText().toString(), Integer.toString(Session.serialNum));
                call.enqueue(new Callback<FeedbackData>() {
                    @Override
                    public void onResponse(Call<FeedbackData> call, Response<FeedbackData> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(MarksheetActivity.this, "Feedback stored Successfully!!!", Toast.LENGTH_SHORT).show();
                            resultFeedbackText.setText("");
                        } else {
                            Toast.makeText(MarksheetActivity.this, "couldnot post feedback!!!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<FeedbackData> call, Throwable t) {
                        Toast.makeText(MarksheetActivity.this, "Network error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

}
