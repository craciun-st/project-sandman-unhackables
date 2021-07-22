package com.codecool.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventAsJson {
    private String name;
    private String info;
    private String rewardValue;

    public EventAsJson(String name, String info, String rewardValue) {
        this.name = name;
        this.info = info;
        this.rewardValue = rewardValue;
    }

    public EventAsJson() {};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getRewardValue() {
        return rewardValue;
    }

    public void setRewardValue(String rewardValue) {
        this.rewardValue = rewardValue;
    }
}
