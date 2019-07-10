package com.example.marksheet.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Marks {

        @SerializedName("subject")
        @Expose
        private String subject;

        @SerializedName("full_marks")
        @Expose
        private int fullMarks;

        @SerializedName("pass_marks")
        @Expose
        private int passMarks;

        @SerializedName("obtained_marks")
        @Expose
        private int obtainedMarks;

        public String getSubject() {
                return subject;
        }

        public void setSubject(String subject) {
                this.subject = subject;
        }

        public int getFullMarks() {
                return fullMarks;
        }

        public void setFullMarks(int fullMarks) {
                this.fullMarks = fullMarks;
        }

        public int getPassMarks() {
                return passMarks;
        }

        public void setPassMarks(int passMarks) {
                this.passMarks = passMarks;
        }

        public int getObtainedMarks() {
                return obtainedMarks;
        }

        public void setObtainedMarks(int obtainedMarks) {
                this.obtainedMarks = obtainedMarks;
        }


}
