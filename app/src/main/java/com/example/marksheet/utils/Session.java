package com.example.marksheet.utils;

public class Session {

    public static String userId;
    public static String parentUserId;
    public static String token;
    public static String role;
    public static int serialNum;

    public static String getUserId() { return userId; }

    public static void setUserId(String userId) { userId = userId; }

    public static String getToken() { return token; }

    public static void setToken(String token) { token = token; }

    public static String getRole() { return role; }

    public static void setRole(String role) { role = role; }

}
