package com.example.marksheet.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginDataList {

    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {return message;}

    public void setMessage(String message) {this.message = message;}

    public String getUserId() {return userId;}

    public void setUserId(String userId) {this.userId = userId;}

    public String getRole() {return role;}

    public void setRole(String role) {this.role = role;}

    public String getToken() {return token;}

    public void setToken(String token) {this.token = token;}
}
