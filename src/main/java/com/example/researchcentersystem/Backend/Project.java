package com.example.researchcentersystem.Backend;

import java.util.ArrayList;

public class Project {

    private String projectName;

    //private Team team;

    private ArrayList<Machine> projectMachines;

    public Project(String projectName, ArrayList<Machine> projectMachines) {
        this.projectName = projectName;
        this.projectMachines = projectMachines;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public ArrayList<Machine> getProjectMachines() {
        return projectMachines;
    }

    public void setProjectMachines(ArrayList<Machine> projectMachines) {
        this.projectMachines = projectMachines;
    }

    public ArrayList<Machine> viewAvailableMachines(){
        return null; // to avoid error signs
    }

    public void addMachine(Machine m1){}

    public Machine removeMachine(Machine m1){
        return null; //to avoid error sign}
    }

    public void assignTeamProject(Team t1, Project p1){}




}
