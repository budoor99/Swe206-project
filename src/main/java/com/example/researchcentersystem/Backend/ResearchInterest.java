package com.example.researchcentersystem.Backend;

import java.util.ArrayList;

public class ResearchInterest
{
    private ArrayList<String> interests;


    public ArrayList<String> getInterests() {
        return interests;
    }

    public void setInterests(ArrayList<String> interests) {
        this.interests = interests;
    }

    public ArrayList<String> refreshInterests(){return interests;}


}
