package com.myfitnesspal.shared.model.v1;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.util.UnitsUtils.Energy;
import com.myfitnesspal.shared.util.UnitsUtils.Length;
import com.myfitnesspal.shared.util.UnitsUtils.Water;
import com.myfitnesspal.shared.util.UnitsUtils.Weight;

public class PreferredUnits {
    public static final PreferredUnits PREFERRED_UNITS_DEFAULT = newInstance(Energy.CALORIES, Weight.KILOGRAMS, Length.CENTIMETERS, Length.KILOMETERS, Water.CUPS);
    @Expose
    private Length distance = Length.KILOMETERS;
    @Expose
    private Energy energy = Energy.CALORIES;
    @Expose
    private Length height = Length.CENTIMETERS;
    @Expose
    private Water water = Water.MILLILITERS;
    @Expose
    private Weight weight = Weight.KILOGRAMS;

    public static PreferredUnits newInstance(Energy energy2, Weight weight2, Length length, Length length2, Water water2) {
        PreferredUnits preferredUnits = new PreferredUnits();
        preferredUnits.energy = energy2;
        preferredUnits.weight = weight2;
        preferredUnits.height = length;
        preferredUnits.distance = length2;
        preferredUnits.water = water2;
        return preferredUnits;
    }

    public final Energy getEnergy() {
        return this.energy;
    }

    public final Weight getWeight() {
        return this.weight;
    }

    public final Length getHeight() {
        return this.height;
    }

    public final Length getDistance() {
        return this.distance;
    }

    public final Water getWater() {
        return this.water;
    }

    public void setEnergy(Energy energy2) {
        this.energy = energy2;
    }

    public void setWeight(Weight weight2) {
        this.weight = weight2;
    }

    public void setHeight(Length length) {
        this.height = length;
    }

    public void setDistance(Length length) {
        this.distance = length;
    }

    public void setWater(Water water2) {
        this.water = water2;
    }
}
