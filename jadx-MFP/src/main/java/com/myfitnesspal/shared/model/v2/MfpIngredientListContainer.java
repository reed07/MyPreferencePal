package com.myfitnesspal.shared.model.v2;

import com.google.gson.annotations.Expose;
import java.util.List;

public class MfpIngredientListContainer {
    @Expose
    private List<MfpIngredient> entries;
    @Expose
    private String link;

    public MfpIngredientListContainer() {
    }

    public MfpIngredientListContainer(List<MfpIngredient> list, String str) {
        this.entries = list;
        this.link = str;
    }

    public List<MfpIngredient> getEntries() {
        return this.entries;
    }

    public void setEntries(List<MfpIngredient> list) {
        this.entries = list;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String str) {
        this.link = str;
    }
}
