package com.example.researchcentersystem;

import java.util.ArrayList;

public class MemorySession {
    private static ArrayList<Project> takenProjects;

    private static ArrayList<Project> availableProjects;
    private static ArrayList<Member> members;
    private static ArrayList<Team> teams;
    private static ArrayList<Machine> machines;

    private static ArrayList<String> researchInterest;


    public void addMachine(String name, int machineID,  ArrayList<String> researchInterest){
        Machine newMachine = new Machine(machineID, name);
        newMachine.setResearchInterest(researchInterest);
        machines.add(newMachine);
    }

    public Machine removeMachine(String name) {
        Machine removedMachine = null; //dummy
        for(Machine machine: machines)
        {
            if(machine.getMachineName().equals(name))
                removedMachine = machine;
                machines.remove(machine);
                break;

        }

        return removedMachine;

    }

    public void addMember(String name, String email, String researchInterest, String memberID ){
        Member newMember =new Member(name,email, "Member", memberID,researchInterest );
        this.members.add(newMember);

    }

    public Member removeMember(String name){ //we may assume
        Member removedMember =null;
        for(Member member: members){
            if(member.getUserName().equals(name)){
                removedMember=member;
                this.members.remove(member);
                break;
            }
        }
        for(Team team: teams){
            if(team.isPartOfTeam(removedMember)){
                team.removeTeamMember(removedMember);

            }
        }
        return removedMember;


    }

    public void addProject(String name, Team team){ //either just add the project with no team or assign
        Project newProject = new Project(name);
        if(team ==null)
            availableProjects.add(newProject);
        else
        {
            newProject.assignTeamProject(team);
            ArrayList<Member> teamMembers = team.getMembers();
            for(Member member: teamMembers)
            {
                member.addToMyProjects(newProject);

            }
            takenProjects.add(newProject);

        }

    }

    public Project removeProject(String name){
        Project removedProject =null;
        boolean isInAvailable = false;
        for(Project project: availableProjects){
            if(project.getProjectName().equals(name)){
                removedProject = project;
                availableProjects.remove(project);
                isInAvailable = true;
            }


        }
        if(!isInAvailable){
            for(Project project : takenProjects){
                removedProject = project;
                takenProjects.remove(project);

            }

        }
        if(removedProject.getTeam()!= null){
            ArrayList<Member> members = removedProject.getTeam().getMembers();
            for(Member member: members)
            {
                member.removeFromMyProjects(removedProject);

            }

        }

        return removedProject;



    }

    public void addTeam(ArrayList<Member> members, String teamName, String teamID, String leader){

        Team newTeam = new Team(members,teamID,teamName );
        if(leader!= null)
            newTeam.setLeader(leader);

        teams.add(newTeam);

    }

    public Team removeTeam(String name){
        Team removedTeam =null;
        for(Team team: teams){
            if(team.getTeamName().equals(name)){
                removedTeam = team;
                teams.remove(team);
            }
        }

        ArrayList<Member> members = removedTeam.getMembers();
        for(Member member: members)
        {
            member.removeFromMyTeams(removedTeam);

        }

        for(Project project: takenProjects){
            if(project.getTeam().equals(removedTeam)){ //override equals ..
                project.setTeam(null);

            }
        }
        return removedTeam;

    }

    public ArrayList<Machine> viewAllMachines(){
        return machines;
    }

    public ArrayList<Project> viewAllProjects(){

        ArrayList<Project> combinedProject= new ArrayList<>(availableProjects);
        combinedProject.addAll(takenProjects);
        return combinedProject;
    }

    public ArrayList<Team> viewAllTeams(){
        return teams;
    }

    public Member viewMostActiveMembers(){ //criteria should be determined //ask Dr

        return null; //to avoid error sign
        //to do

    }

    public Project viewProjectWithMostMachine(){ //criteria should be determined //ask Dr

        Project project = takenProjects.get(0);
        int maxMachines = project.viewAvailableMachines().size();

        for(Project p: takenProjects){
            int lenP = p.viewAvailableMachines().size();
            if(lenP > maxMachines){
                project =p;
                maxMachines=lenP;
            }
        }

        return project;

    }

    public Member searchMember(String name){
        for (int i = 0; i<members.size();i++){
            if(members.get(i).getUserName().equals(name)){
                return members.get(i);
            }
        }
        return null;
    }

    public void addTeamToList(Team t1){

    }


//    public Machine viewMostUsedMachine(){ //two arrays, the first is machines, the second is the count of machines
//
//        //or dictionary
//
//
//        HashMap<Machine, Integer> dictionary = new HashMap<>();
//        for(Machine machine: machines){
//            dictionary.put(machine,0);
//        }
//
//
//
//
//    }

}
