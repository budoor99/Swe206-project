package com.example.researchcentersystem.Backend;

import java.util.ArrayList;

public class Member extends User{
    private String researchInterest;
    private ArrayList<Team> myTeams;
    private ArrayList<Project> myProjects;

    public Member(String userName, String userEmail, String userType,String userID, String researchInterest) {
        super(userName, userEmail, userType, userID);
        this.researchInterest = researchInterest;
    }

    public void setResearchInterest(String researchInterest) {
        this.researchInterest = researchInterest;
    }

    public String getResearchInterest() {
        return researchInterest;
    }

    public ArrayList<Project> viewAssignedProject(){
        return myProjects;
    }
    public ArrayList<Team> viewTeams(){
        return myTeams;
    }

}
