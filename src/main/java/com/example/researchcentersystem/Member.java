package com.example.researchcentersystem;

import java.util.ArrayList;

public class Member extends User {
    private String researchInterest; //should be a list
    private ArrayList<Team> myTeams = new ArrayList<>();
    private ArrayList<Project> myProjects=new ArrayList<>();

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

    public ArrayList<Project> viewAssignedProject(){ //may be changed
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

    public String toString(){
        return super.getUserName()+","+super.getUserEmail()+",member,"+super.getUserID()+","+researchInterest;
    }

}
