package com.myfitnesspal.shared.model.v1;

public class TrackedNutrient extends DatabaseObject {
    private String name;
    private long nutrientNameId;
    private int position;

    public long getNutrientNameId() {
        return this.nutrientNameId;
    }

    public void setNutrientNameId(long j) {
        this.nutrientNameId = j;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }
}
