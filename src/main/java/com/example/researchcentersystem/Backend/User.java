package com.example.researchcentersystem.Backend;

public class User {
    private String UserName;
    private String UserEmail;
    private String UserType;

    public User(String userName, String userEmail, String userType) {
        UserName = userName;
        UserEmail = userEmail;
        UserType = userType;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public String getUserType() {
        return UserType;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }
}
