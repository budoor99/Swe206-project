package com.example.researchcentersystem;

import java.util.ArrayList;

public class Project {

    private String projectName;

    private Team team;

    private ArrayList<Machine> projectMachines;



    public Project(String projectName) { //1
        this.projectName = projectName;
        this.projectMachines = new ArrayList<>();
        this.team =null;
    }

    public Project(String projectName, Team team) { //2
        this.projectName = projectName;
        this.projectMachines = new ArrayList<>();
        this.team = team;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


    public void setTeam(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public ArrayList<Machine> viewAvailableMachines(){ //getter
        return projectMachines;
    }

    public void addMachine(Machine m1){
        projectMachines.add(m1);
    }

    public boolean removeMachine(Machine m1){
        return projectMachines.remove(m1);
    }

    public void assignTeamProject(Team t1){
        team=t1;
    }

    public String toString(){
        return projectName;

    }

<<<<<<< HEAD
    }

    public String projectToFile() {
        String str = projectName + ",";
        if (team == null) {
=======
    public String projectToFile(){
        String str= projectName+",";
        if(team==null){
>>>>>>> 87add6ac41aa0a21eb79ef407c4604507d3e9aa2
            return str;
        } else {
            str += team.getTeamName();
            return str;
        }
    }

}
