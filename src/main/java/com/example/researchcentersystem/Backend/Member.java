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

    public void addToMyProjects(Project p1){
        myProjects.add(p1);

    }

    public void removeFromMyProjects(Project p1){
        myProjects.remove(p1);
    }

    public void addToMyTeams(Team t1){myTeams.add(t1);} //still not used

    public void removeFromMyTeams(Team t1){myTeams.remove(t1);}

}
