package com.example.lol_battlerecode.model;

import com.google.gson.annotations.SerializedName;
public class SummonerIDInfo {
    @SerializedName("id")
    private String id;

    @SerializedName("accountid")
    private  String accountid;

    @SerializedName("name")
    private String name;

    @SerializedName("summonerlevel")
    private int summonerlevel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSummonerlevel() {
        return summonerlevel;
    }

    public void setSummonerlevel(int summonerlevel) {
        this.summonerlevel = summonerlevel;
    }
}
