package com.example.researchcentersystem;


import java.util.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.FileWriter;


public class MemorySession {
    public static User currentUser;
    private static ArrayList<Project> takenProjects = new ArrayList<>();
    public static HashMap<String, ArrayList<String>> allReservations = new HashMap<>();


    private static ArrayList<Project> availableProjects = new ArrayList<>();
    private static ArrayList<Member> members = new ArrayList<>();
    private static ArrayList<Team> teams = new ArrayList<>();
    private static ArrayList<Machine> machines = new ArrayList<>();

    private static ArrayList<String> researchInterest = new ArrayList<>();
    private static ArrayList<Admin> admins = new ArrayList<>();

    public static final ArrayList<String> TIME_SLOTS = new ArrayList<>(Arrays.asList(
            "00:00-04:00",
            "04:00-08:00",
            "08:00-12:00",
            "12:00-16:00",
            "16:00-20:00",
            "20:00-00:00"
    ));


    public void addMachine(String name, String machineID, ArrayList<String> researchInterest) {
        Machine newMachine = new Machine(machineID, name);
        newMachine.setResearchInterests(researchInterest);
        machines.add(newMachine);
    }

    public Machine removeMachine(String name) {
        for (Machine m : machines) {
            if (m.getMachineName().equals(name)) {
                machines.remove(m);
                break;
            }
        }
        return null;

    }

    public void addMember(String name, String email, String researchInterest, String memberID) {
        Member newMember = new Member(name, email, "Member", memberID, researchInterest);
        this.members.add(newMember);

    }

    public void addAdmin(String name, String email, String memberID) {
        Admin newAdmin = new Admin(name, email, "Admin", memberID);
        this.admins.add(newAdmin);

    }

    public Member removeMember(String name) { //we may assume
        Member removedMember = null;
        for (Member member : members) {
            if (member.getUserName().equals(name)) {
                removedMember = member;
                this.members.remove(member);
                break;
            }
        }
        for (Team team : teams) {
            if (team.isPartOfTeam(removedMember)) {
                team.removeTeamMember(removedMember);
                break;
            }
        }
        return removedMember;

    }

    public void addProject(String name, Team team) { //either just add the project with no team or assign
        Project newProject = new Project(name);
        if (team == null)
            availableProjects.add(newProject);
        else {
            newProject.assignTeamProject(team);
            ArrayList<Member> teamMembers = team.getMembers();
            for (Member member : teamMembers) {
                member.addToMyProjects(newProject);

            }
            takenProjects.add(newProject);

        }

    }

    public Project removeProject(String name) {
        Project removedProject = null;
        boolean isInAvailable = false;

        ArrayList<Project> AP=availableProjects;
        for (Project project : AP) {
            if (project.getProjectName().equals(name)) {
                removedProject = project;
                isInAvailable = true;
                availableProjects.remove(project);
                break;
            }
        }
        if (!isInAvailable) {
            ArrayList<Project> TP=takenProjects;
            for (Project project : TP) {
                removedProject = project;
                takenProjects.remove(project);
                break;
            }
        }

        if (removedProject.getTeam() != null) {
            ArrayList<Member> members = removedProject.getTeam().getMembers();
            for (Member member : members) {
                member.removeFromMyProjects(removedProject);

            }

        }

        return removedProject;


    }

    public void addTeam(ArrayList<Member> members, String teamName, String teamID, String leader) {

        Team newTeam = new Team(members, teamID, teamName);
        if (leader != null)
            newTeam.setLeader(leader);

        teams.add(newTeam);

    }

    public Team removeTeam(String name) {
        Team removedTeam = null;
        for (Team team : teams) {
            if (team.getTeamName().equals(name)) {
                removedTeam = team;
                teams.remove(team);
                break;
            }
        }

        ArrayList<Member> members = removedTeam.getMembers();
        for (Member member : members) {
            member.removeFromMyTeams(removedTeam);
            break;

        }

        for (Project project : takenProjects) {
            if (project.getTeam().equals(removedTeam)) { //override equals ..
                project.setTeam(null);
                takenProjects.remove(project);
                availableProjects.add(project);
                break;
            }
        }
        return removedTeam;

    }

    public ArrayList<Machine> viewAllMachines() {
        return machines;
    }

    public ArrayList<Project> viewAllProjects() {

        ArrayList<Project> combinedProject = new ArrayList<>(availableProjects);
        combinedProject.addAll(takenProjects);
        return combinedProject;
    }

    public ArrayList<Team> viewAllTeams() {
        return teams;
    }

    public Member viewMostActiveMembers() { //criteria should be determined //ask Dr

        return null; //to avoid error sign
        //to do

    }

    public Project viewProjectWithMostMachine() { //criteria should be determined //ask Dr

        Project project = takenProjects.get(0);
        int maxMachines = project.viewAvailableMachines().size();

        for (Project p : takenProjects) {
            int lenP = p.viewAvailableMachines().size();
            if (lenP > maxMachines) {
                project = p;
                maxMachines = lenP;
            }
        }

        return project;

    }

    public Member searchMember(String name) {
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getUserName().equals(name)) {
                return members.get(i);
            }
        }
        return null;
    }

    public Machine searchMachine(String machineName) {
        for (int i = 0; i < machines.size(); i++) {
            if (machines.get(i).getMachineName().equals(machineName)) {
                return machines.get(i);
            }
        }
        return null;
    }


    public Team searchTeam(String name) {
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getTeamName().equals(name)) {
                return teams.get(i);
            }
        }
        return null;
    }

    public void addTeamToList(Team t1) {
        teams.add(t1);

    }

    public ArrayList<Member> getAllMembers() {
        return this.members;
    }


    public ArrayList<Project> getTakenProjects() {
        return takenProjects;
    }

    public ArrayList<Project> getAvailableProjects() {
        return availableProjects;
    }

    public void addResearchInterest(String r) {
        researchInterest.add(r);
    }

    public ArrayList<Machine> getMachines() {
        return machines;
    }

    public User searchCurrentUser(String name) {
        for (Member m : members) {
            if (m.getUserName().equals(name)) {
                return m;
            }
        }

        for (Admin m : admins) {
            if (m.getUserName().equals(name)) {
                return m;
            }
        }

        return null;

    }

    public static ArrayList<String> getResearchInterest() {
        return researchInterest;
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

    public Member viewMostActiveMember() {
        int maxNumOfProject = 0;
        Member member = members.get(0);
        for (Member m : members) {
            ArrayList<Project> projects = m.viewAssignedProject();
            int p = 0;
            if (projects != null) {
                p = projects.size();
                if (p > maxNumOfProject) {
                    maxNumOfProject = p;
                    member = m;
                }
            }
        }
        return member;
    }

    public ArrayList<Project> getAllProjects() {
        ArrayList<Project> all = new ArrayList<>(takenProjects);
        all.addAll(availableProjects);
        return all;
    }

    public ArrayList<Team> getAllTeam() {
        return teams;
    }

    //   public void assignTeamForProject(){
//
//   }
    public Project searchProject(String na) {
        ArrayList<Project> a = getAllProjects();
        for (Project p : a) {
            if (p.getProjectName().equals(na))
                return p;
        }
        return null;
    }


    //list of reservation Array list of reservation
    public ArrayList<Reservation> getReservations(String s) { //for one machine a timetable
        ArrayList<Reservation> newArray = new ArrayList<>();
        for (Team team : teams) {
            HashMap<String, ArrayList<String>> teamR = team.getTeamReservations();
            for (Map.Entry<String, ArrayList<String>> entry : teamR.entrySet()) {
                String key = entry.getKey();
                if (key.equals(s)) {
                    ArrayList<String> values = entry.getValue();
                    for (String value : values) {
                        String[] info = value.split(",");
                        newArray.add(new Reservation(s, team.getTeamName(), info[1], info[0])); //changed key to s
                    }
                }

            }

        }
        return newArray;
    }

    public String theMostUsedMachine(){
        int num=0;
        String m="";
        for (Map.Entry<String, ArrayList<String>> entry : allReservations.entrySet()) {
            String key = entry.getKey();
            ArrayList<String> values = entry.getValue();
            if(values.size()>num){
                m=key;
                num=values.size();
            }
        }

        return m;
    }

    public String ProjectWithMostMachines(){
        int max=0;
        String p="";
        for (Project project:takenProjects){
            Team team=project.getTeam();
            if(team!=null){
                int num=team.teamMachines.size();
                if(num>max){
                    max=num;
                    p=project.getProjectName();
                }
            }
        }
        return p+","+max;
    }


    public void writeToFiles() {
        try {
            File memberWrite = new File("src/main/java/com/example/researchcentersystem/memberCopy.txt");
            FileWriter outputMembers = new FileWriter(memberWrite);
            for (int i = 0; i < members.size(); i++) {
                outputMembers.write(members.get(i).toString() + "\n");
            }
            outputMembers.close();

            File teamWrite = new File("src/main/java/com/example/researchcentersystem/teamCopy.txt");
            FileWriter outputTeam = new FileWriter(teamWrite);
            for (int i = 0; i < teams.size(); i++) {
                outputTeam.write(teams.get(i).fileOutput() + "\n");
            }
            outputTeam.close();

            File projectWrite = new File("src/main/java/com/example/researchcentersystem/projectCopy.txt");
            FileWriter outputProject = new FileWriter(projectWrite);
            for (int i = 0; i < takenProjects.size(); i++) {
                outputProject.write(takenProjects.get(i).projectToFile() + "\n");
            }
            for (int i = 0; i < availableProjects.size(); i++) {
                outputProject.write(availableProjects.get(i).projectToFile() + "\n");
            }
            outputProject.close();

            File machineWrite = new File("src/main/java/com/example/researchcentersystem/machineCopy.txt");
            FileWriter outputMachine = new FileWriter(machineWrite);
            for (int i = 0; i < machines.size(); i++) {
                outputMachine.write(machines.get(i).toString() + "\n");
            }

            outputMachine.close();

            String date;
            File machineReservationWrite = new File("src/main/java/com/example/researchcentersystem/machineReservationsCopy.txt");
            FileWriter outputReservation = new FileWriter(machineReservationWrite);
            for (int k = 0; k < machines.size(); k++) {
                if (allReservations.containsKey(machines.get(k).getMachineName())) {
                    String machinename = machines.get(k).getMachineName();
                    outputReservation.write(machinename + "\n");
                    for (int i = 0; i < allReservations.get(machinename).size(); i++) {
                        outputReservation.write((allReservations.get(machinename)).get(i) + "\n");
                        System.out.println(allReservations.get(machinename));
                    }
                }
            }

            outputReservation.close();


        } catch (Exception err) {
            System.out.println(err);
        }
    }

}


