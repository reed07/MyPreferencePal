package com.myfitnesspal.shared.model;

public class LegendData {
    private String actualData;
    private int goalPercentage;
    private String label;
    private int labelColor;
    private int valuePercentage;

    public LegendData(String str, int i, int i2, String str2) {
        this(str, i, i2, str2, 0);
    }

    public LegendData(String str, int i, int i2, String str2, int i3) {
        this.label = str;
        this.labelColor = i;
        this.valuePercentage = i2;
        this.actualData = str2;
        this.goalPercentage = i3;
    }

    public String getLabel() {
        return this.label;
    }

    public int getLabelColor() {
        return this.labelColor;
    }

    public int getValuePercentage() {
        return this.valuePercentage;
    }

    public String getActualData() {
        return this.actualData;
    }

    public int getGoalPercentage() {
        return this.goalPercentage;
    }
}
