package com.example.researchcentersystem.Backend;

public class ResearchInterest
{
    private String[] interests;


    public String[] getInterests() {
        return interests;
    }

    public void setInterests(String[] interests) {
        this.interests = interests;
    }

    public String[] refreshInterests(){return interests;}
}
