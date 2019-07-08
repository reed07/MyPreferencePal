package com.myfitnesspal.shared.model.v2;

import com.google.gson.annotations.Expose;
import java.util.List;

public class MfpTimelineContentMealEntry implements MfpTimelineContentData {
    @Expose
    private List<MfpFood> foods;
    @Expose
    private String id;
    @Expose
    private String name;
    @Expose
    private MfpNutritionalContents nutritionalContents;

    public String getName() {
        return this.name;
    }

    public MfpNutritionalContents getNutritionalContents() {
        return this.nutritionalContents;
    }

    public List<MfpFood> getFoods() {
        return this.foods;
    }

    public String getId() {
        return this.id;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setNutritionalContents(MfpNutritionalContents mfpNutritionalContents) {
        this.nutritionalContents = mfpNutritionalContents;
    }

    public void setFoods(List<MfpFood> list) {
        this.foods = list;
    }

    public void setId(String str) {
        this.id = str;
    }
}
