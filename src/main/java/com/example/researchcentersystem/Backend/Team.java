package com.example.researchcentersystem.Backend;

import java.util.ArrayList;

public class Team {
    private ArrayList<Member> members;
    private String leader;
    public Team(){
        this.members=new ArrayList<>();
    }
    public Team(ArrayList<Member> members,String leader){
        this.members=members;
        this.leader=leader;
    }
    public void addTeamMember(Member member){
        this.members.add(member);
    }
    public boolean removeTeamMember(Member member){
        return this.members.remove(member);
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public String getLeader() {
        return leader;
    }
    
}
