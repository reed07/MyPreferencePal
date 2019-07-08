package com.brightcove.player.model;

public class Block extends StyledElement {
    protected Integer beginTime;
    protected Integer endTime;
    protected String region;

    public int getBeginTime() {
        return this.beginTime.intValue();
    }

    public void setBeginTime(int i) {
        this.beginTime = Integer.valueOf(i);
    }

    public int getEndTime() {
        return this.endTime.intValue();
    }

    public void setEndTime(int i) {
        this.endTime = Integer.valueOf(i);
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String str) {
        this.region = str;
    }
}
