package com.example.researchcentersystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Machine {

    private String machineID;
    private String machineName;

    private ArrayList<String> researchInterests;
    public HashMap<String, ArrayList <String>> reservations= new HashMap<>();



    public Machine(String machineID, String machineName){
        this.machineID= machineID;
        this.machineName=machineName;
        this.researchInterests = new ArrayList<>();// we will update it. it is not optional

    }

    public String getMachineID() {
        return machineID;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineID(String machineID){
        this.machineID = machineID;
    }

    public void setResearchInterests(ArrayList<String> researchInterests) {
        this.researchInterests = researchInterests;
    }


    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public Reservation reserveMachine(){
        return null; //to avoid error sign
    }

    public String showSchedule(){
        return null; //to avoid error sign
    }

    public String createSchedule(){
        return null; //to avoid error sign
    }

    public boolean createReservation (String date, String time){
        if(reservations.containsKey(date)) {
            if (!reservations.get(date).contains(time)) {
                reservations.get(date).add(time);
                return true;
            }
        }else{
            ArrayList<String> addNewDate = new ArrayList<>();
            addNewDate.add(time);
            reservations.put(date, addNewDate);
            return true;
        }
        return false;

    }
    public String getResearchInterests(){
        return researchInterests.toString();
    }

    public String toString(){
        String str = machineName+","+machineID+",";
        for(int i = 0; i<researchInterests.size();i++){
            if(i==researchInterests.size()-1){
                str+=researchInterests.get(i);
            }else{
                str = str+researchInterests.get(i)+",";
            }
        }
        return str;
    }




    //return the machines
    public List<String> getReservedMachines() {
        return new ArrayList<>(reservations.keySet());
    }




    /*public boolean createReservationMember (String date, String time){
        if(reservations.containsKey(date)) {
            if (!reservations.get(date).contains(time)) {
                reservations.get(date).add(time);
                return true;
            }
        }else{
            ArrayList<String> addNewDate = new ArrayList<>();
            addNewDate.add(time);
            reservations.put(date, addNewDate);
            return true;
        }
        return false;

    }*/








}
