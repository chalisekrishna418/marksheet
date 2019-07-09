package com.example.marksheet.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Marks {

        @SerializedName("subject")
        @Expose
        private String subject;

        @SerializedName("full_marks")
        @Expose
        private String fullMarks;

        @SerializedName("pass_marks")
        @Expose
        private String passMarks;

        @SerializedName("obtain_marks")
        @Expose
        private String obtainedMarks;

        public String getSubject() {
                return subject;
        }

        public void setSubject(String subject) {
                this.subject = subject;
        }

        public String getFullMarks() {
                return fullMarks;
        }

        public void setFullMarks(String fullMarks) {
                this.fullMarks = fullMarks;
        }

        public String getPassMarks() {
                return passMarks;
        }

        public void setPassMarks(String passMarks) {
                this.passMarks = passMarks;
        }

        public String getObtainedMarks() {
                return obtainedMarks;
        }

        public void setObtainedMarks(String obtainedMarks) {
                this.obtainedMarks = obtainedMarks;
        }


}
