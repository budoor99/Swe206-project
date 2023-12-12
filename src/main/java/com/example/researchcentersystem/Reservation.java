package com.example.researchcentersystem;

import com.example.researchcentersystem.Machine;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reservation {

    private String machineName;

    private String teamName;

    private String time;
    private String date;

    public Reservation(String machineName, String teamName, String time, String date) {
        this.machineName = machineName;
        this.teamName = teamName;
        this.time = time;
        this.date = date;
    }

    public String getMachineName() {
        return machineName;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }
}
