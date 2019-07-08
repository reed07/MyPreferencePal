package com.myfitnesspal.feature.challenges.model;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

public class AvailableAchievementCriteria {
    @Expose
    private String goalName;
    @Expose
    private String id;
    @Expose
    private int minimumAmount;
    @Expose
    private List<String> objectIds;
    @Expose
    private String type;

    public String getGoalName() {
        return this.goalName;
    }

    public void setGoalName(String str) {
        this.goalName = str;
    }

    public List<String> getObjectIds() {
        return this.objectIds;
    }

    public void setObjectIds(List<String> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.objectIds = list;
    }

    public int getMinimumAmount() {
        return this.minimumAmount;
    }

    public void setMinimumAmount(int i) {
        this.minimumAmount = i;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }
}
