package com.example.marksheet.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MarksheetData {

    @SerializedName("_id")
    @Expose
    private String _id;

    @SerializedName("subjects")
    private List<Marks> marks;

    @SerializedName("student")
    @Expose
    private StudentDataList student;

    @SerializedName("serial_number")
    @Expose
    private int serialNum;

    @SerializedName("standard")
    @Expose
    private String standard;

    @SerializedName("date")
    @Expose
    private String dor;

    @SerializedName("term")
    @Expose
    private String term;

    public String get_id() { return _id; }

    public void set_id(String _id) { this._id = _id; }

    public List<Marks> getMarks() { return marks; }

    public void setMarks(List<Marks> marks) { this.marks = marks; }

    public StudentDataList getStudent() { return student; }

    public void setStudent(StudentDataList student) { this.student = student; }

    public int getSerialNum() { return serialNum; }

    public void setSerialNum(int serialNum) { this.serialNum = serialNum; }

    public String getStandard() { return standard; }

    public void setStandard(String standard) { this.standard = standard; }

    public String getDor() { return dor; }

    public void setDor(String dor) { this.dor = dor; }

    public String getTerm() { return term; }

    public void setTerm(String term) { this.term = term; }

}
