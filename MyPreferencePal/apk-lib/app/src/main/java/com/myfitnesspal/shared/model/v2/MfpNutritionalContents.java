package com.myfitnesspal.shared.model.v2;

import lanchon.dexpatcher.annotation.*;

@DexEdit(defaultAction = DexAction.IGNORE, contentOnly = true)
public class MfpNutritionalContents {
    @DexWrap
    public static MfpNutritionalContents fromNutritionalValuesArray(float[] fArr) {

        if (fArr.length > 20)
        {
            float[] newarray = new float[20];
            System.arraycopy(fArr,0,newarray,0,20);
            return MfpNutritionalContents.fromNutritionalValuesArray(newarray);
        }
        else
            return MfpNutritionalContents.fromNutritionalValuesArray(fArr);
    }

    @DexAdd
    public float calculateNetCarbs(float[] values)
    {
        float netCarbs = values[10];
        if (((double) values[11]) < 0.0d)
            netCarbs -= values[11];
        if (((double) values[19]) < 0.0d)
            netCarbs -= values[19];
        return netCarbs;
    }

    @DexAdd
    public Double calculateNetCarbs()
    {
        Double netCarbs = getCarbohydrates();
        if (getFiber() > 0.0d)
            netCarbs -= getFiber();
        if (getSugarAlcohols() > 0.0d)
            netCarbs -= getSugarAlcohols();
        return netCarbs;
    }

    @DexIgnore
    public Double getFiber() {
        return null;
    }

    @DexIgnore
    public Double getCarbohydrates() {
        return null;
    }

    @DexIgnore
    public Double getSugarAlcohols() {
        return null;
    }
}
