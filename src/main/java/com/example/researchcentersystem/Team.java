package com.example.researchcentersystem;

import java.util.*;

public class Team {
    private ArrayList<Member> members = new ArrayList<>();
    private String leader;
    private ArrayList<Project> projects=new ArrayList<>();


    public HashMap<String, ArrayList <String>> teamMachines= new HashMap<>();

    public void addReservation(String m,String d, String t){
        MemorySession database = new MemorySession();
        String c=d+","+t;
        if(teamMachines.containsKey(m)) {
            teamMachines.get(m).add(c);
            }
        else{
            ArrayList<String> addNewDate = new ArrayList<>();
            addNewDate.add(c);
            teamMachines.put(m, addNewDate);
        }
        if(database.allReservations.containsKey(m)){

                database.allReservations.get(m).add(d+","+teamName+"/"+t);
        }else{
            ArrayList<String> res = new ArrayList<>();
            res.add(d+","+teamName+"/"+t);
            database.allReservations.put(m,res );
        }
    }

    public HashMap<String, ArrayList<String>> getTeamMachines() {
        return teamMachines;
    }

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
        boolean choice = false;
        String [] timeInfo = dateAndTime.split(",");
        if(teamMachines.containsKey(machineName)) {
            if (!teamMachines.get(machineName).contains(dateAndTime)) {
                teamMachines.get(machineName).add(dateAndTime);
                choice =  true;
            }
        }else{
            ArrayList<String> addNewDate = new ArrayList<>();
            addNewDate.add(dateAndTime);
            teamMachines.put(machineName, addNewDate);
            choice = true;
        }

        return choice;

    }


    @Override
    public String toString(){
        return teamName;
    }
    


    //get machines method for specific team
    public HashMap<String, ArrayList <String>> getTeamReservations() {
        HashMap<String, ArrayList<String>> teamReservations = new HashMap<>();

        for (String machine : teamMachines.keySet()) {
            ArrayList<String> machineReservations = new ArrayList<>(teamMachines.get(machine));
            teamReservations.put(machine, machineReservations);
        }

        return teamReservations;
    }


    public void addProject(Project p){
        projects.add(p);
    }

    public String getTeamID() {
        return teamID;
    }

    public String getProjects() {
        return projects.toString();
    }




    public String fileOutput(){
        String str = teamName+","+teamID+",";
        for (int i = 0; i<members.size();i++){
            if(i == members.size()-1){
                str= str+members.get(i).getUserName();
            }else {
                str = str + members.get(i).getUserName() + ",";
            }
        }
        return str;
    }


}
