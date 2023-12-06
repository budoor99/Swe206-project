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



    public void setProjectMachines(ArrayList<Machine> projectMachines) {
        this.projectMachines = projectMachines;
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

    //public void assignTeamProject(Team t1, Project p1){}




}
