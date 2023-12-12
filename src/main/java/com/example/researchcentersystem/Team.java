package com.example.researchcentersystem;

import com.example.researchcentersystem.Member;

import java.util.ArrayList;
import java.util.HashMap;

public class Team {
    private ArrayList<Member> members = new ArrayList<>();
    private String leader;
    public HashMap<String, ArrayList <String>> teamMachines= new HashMap<>();


    public String getTeamName() {
        return teamName;
    }

    private String teamID;
    private String teamName;


    public Team(String teamName, String teamID){
        this.teamID=teamID;
        this.teamName=teamName;
        this.members=new ArrayList<>();
    }

    public Team(ArrayList<Member> members, String leader, String teamID, String teamName) {
        this.members = members;
        this.leader = leader;
        this.teamID = teamID;
        this.teamName = teamName;
    }

    public Team(ArrayList<Member> members, String teamID, String teamName) {
        this.members = members;
        this.teamID = teamID;
        this.teamName = teamName;
        this.leader=null;
    }



    public void addTeamMember(Member member){
        this.members.add(member);
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }



    public boolean removeTeamMember(Member member){
        return this.members.remove(member);
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public String getLeader() {
        return leader;
    }

    public boolean isPartOfTeam(Member m){
        return members.contains(m);

    }

    public boolean addMachine(String machineName, String dateAndTime){
        if(teamMachines.containsKey(machineName)) {
            if (!teamMachines.get(machineName).contains(dateAndTime)) {
                teamMachines.get(machineName).add(dateAndTime);
                return true;
            }
        }else{
            ArrayList<String> addNewDate = new ArrayList<>();
            addNewDate.add(dateAndTime);
            teamMachines.put(machineName, addNewDate);
            return true;
        }
        return false;

    }

    @Override
    public String toString(){
        return teamName;
    }
    
}
