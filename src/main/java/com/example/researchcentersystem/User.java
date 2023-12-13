package com.example.researchcentersystem;

public class User {
    private String UserName;
    private String UserEmail;
    private String UserType;
    private String UserID;

    public User(String userName, String userEmail, String userType,String userID) {
        UserName = userName;
        UserEmail = userEmail;
        UserType = userType;
        UserID=userID;
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
    public String getUserID(){
        return UserID;
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

    public String toString(){
        return UserName;
    }

}
