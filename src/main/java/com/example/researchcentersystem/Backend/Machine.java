package com.example.researchcentersystem.Backend;

public class Machine {

    private int machineID;
    private String machineName;

    private String[] researchInterest;
    private ResearchInterest[] myReservations;



    public Machine(int machineID, String machineName){
        this.machineID= machineID;
        this.machineName=machineName;

    }

    public int getMachineID() {
        return machineID;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineID(int machineID){
        this.machineID = machineID;
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








}
