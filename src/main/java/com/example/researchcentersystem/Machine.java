package com.example.researchcentersystem;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;

public class Machine {

    private String machineID;
    private String machineName;

    private ArrayList<String> researchInterest;
    HashMap<String, ArrayList <String>> reservations= new HashMap<>();



    public Machine(String machineID, String machineName){
        this.machineID= machineID;
        this.machineName=machineName;
        this.researchInterest = new ArrayList<>();// we will update it. it is not optional

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

    public void setResearchInterest(ArrayList<String> researchInterest) {
        this.researchInterest = researchInterest;
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








}
