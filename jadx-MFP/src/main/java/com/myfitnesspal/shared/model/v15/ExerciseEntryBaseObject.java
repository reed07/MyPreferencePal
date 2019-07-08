package com.myfitnesspal.shared.model.v15;

import java.util.Date;
import java.util.Map;

public abstract class ExerciseEntryBaseObject extends BaseObject {
    private float calories;
    private Date date;
    private ExerciseObject exercise;
    private Map<String, String> extras;
    private int quantity;
    private int sets;
    private float weight;

    public ExerciseObject getExercise() {
        return this.exercise;
    }

    public void setExercise(ExerciseObject exerciseObject) {
        this.exercise = exerciseObject;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date2) {
        this.date = date2;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int i) {
        this.quantity = i;
    }

    public int getSets() {
        return this.sets;
    }

    public void setSets(int i) {
        this.sets = i;
    }

    public float getWeight() {
        return this.weight;
    }

    public void setWeight(float f) {
        this.weight = f;
    }

    public float getCalories() {
        return this.calories;
    }

    public void setCalories(float f) {
        this.calories = f;
    }

    public Map<String, String> getExtras() {
        return this.extras;
    }

    public void setExtras(Map<String, String> map) {
        this.extras = map;
    }

    public String getExtra(String str) {
        return getExtra(str, null);
    }

    public String getExtra(String str, String str2) {
        Map<String, String> map = this.extras;
        return (map == null || !map.containsKey(str)) ? str2 : (String) this.extras.get(str);
    }
}
