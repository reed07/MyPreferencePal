package com.myfitnesspal.feature.restaurantlogging.model;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpRecipe;

public class MfpMultiAddMenuItem {
    @Expose
    private String itemId;
    @Expose
    private MfpMenuItemMatch match;
    @Expose
    private String menuId;

    public static class API_RESPONSE_MAPPER extends ApiResponse<MfpMultiAddMenuItem> {
    }

    public MfpMultiAddMenuItem() {
    }

    public MfpMultiAddMenuItem(String str, String str2, MfpMenuItemMatch mfpMenuItemMatch) {
        this.itemId = str;
        this.menuId = str2;
        this.match = mfpMenuItemMatch;
    }

    public String getItemId() {
        return this.itemId;
    }

    public void setItemId(String str) {
        this.itemId = str;
    }

    public String getMenuId() {
        return this.menuId;
    }

    public void setMenuId(String str) {
        this.menuId = str;
    }

    public MfpMenuItemMatch getMatch() {
        return this.match;
    }

    public void setMatch(MfpMenuItemMatch mfpMenuItemMatch) {
        this.match = mfpMenuItemMatch;
    }

    public MfpFood toMfpFood() {
        MfpMenuItemMatch mfpMenuItemMatch = this.match;
        MfpFood mfpFood = null;
        if (mfpMenuItemMatch == null) {
            return null;
        }
        MfpMenuItemMatchData logMatchData = mfpMenuItemMatch.getLogMatchData();
        if (logMatchData instanceof MfpFood) {
            mfpFood = (MfpFood) logMatchData;
        } else if (logMatchData instanceof FoodGroup) {
            mfpFood = ((FoodGroup) logMatchData).getSelectedFood();
        } else if (logMatchData instanceof MfpRecipe) {
            MfpRecipe mfpRecipe = (MfpRecipe) logMatchData;
            mfpFood = mfpRecipe.getMfpFood();
            mfpFood.setSelectedServingAmount(mfpRecipe.getServings().floatValue());
        }
        return mfpFood;
    }
}
