package com.myfitnesspal.shared.service.config;

import com.google.gson.annotations.Expose;

public class AppConfig {
    @Expose
    private String adFreeFaqUrl;
    @Expose
    private String calorieAdjustmentFaqUrl;
    @Expose
    private String changeMealNamesUrl;
    @Expose
    private String faqUrl;
    @Expose
    private String foodAnalysisFaqUrl;
    @Expose
    private String goalRecalcualtionFaqUrl;
    @Expose
    private String offlineSearchFaqUrl;
    @Expose
    private String prioritySupportFaqUrl;
    @Expose
    private String quickAddFaqUrl;
    @Expose
    private String strengthCaloriesUrl;
    @Expose
    private String termsAndPrivacyUrl;
    @Expose
    private String userPotentiallyChargedUrl;

    public String getTermsAndPrivacyUrl() {
        return this.termsAndPrivacyUrl;
    }

    public void setTermsAndPrivacyUrl(String str) {
        this.termsAndPrivacyUrl = str;
    }

    public void setFaqUrl(String str) {
        this.faqUrl = str;
    }

    public String getFaqUrl() {
        return this.faqUrl;
    }

    public void setOfflineSearchFaqUrl(String str) {
        this.offlineSearchFaqUrl = str;
    }

    public String getOfflineSearchFaqUrl() {
        return this.offlineSearchFaqUrl;
    }

    public void setCalorieAdjustmentFaqUrl(String str) {
        this.calorieAdjustmentFaqUrl = str;
    }

    public String getCalorieAdjustmentFaqUrl() {
        return this.calorieAdjustmentFaqUrl;
    }

    public void setGoalRecalcualtionFaqUrl(String str) {
        this.goalRecalcualtionFaqUrl = str;
    }

    public String getGoalRecalcualtionFaqUrl() {
        return this.goalRecalcualtionFaqUrl;
    }

    public void setAdFreeFaqUrl(String str) {
        this.adFreeFaqUrl = str;
    }

    public String getAdFreeFaqUrl() {
        return this.adFreeFaqUrl;
    }

    public void setQuickAddFaqUrl(String str) {
        this.quickAddFaqUrl = str;
    }

    public String getQuickAddFaqUrl() {
        return this.quickAddFaqUrl;
    }

    public void setFoodAnalysisFaqUrl(String str) {
        this.foodAnalysisFaqUrl = str;
    }

    public String getFoodAnalysisFaqUrl() {
        return this.foodAnalysisFaqUrl;
    }

    public String getUserPotentiallyChargedUrl() {
        return this.userPotentiallyChargedUrl;
    }

    public void setUserPotentiallyChargedUrl(String str) {
        this.userPotentiallyChargedUrl = str;
    }

    public void setPrioritySupportFaqUrl(String str) {
        this.prioritySupportFaqUrl = str;
    }

    public String getPrioritySupportFaqUrl() {
        return this.prioritySupportFaqUrl;
    }

    public void setChangeMealNamesUrl(String str) {
        this.changeMealNamesUrl = str;
    }

    public String getChangeMealNamesUrl() {
        return this.changeMealNamesUrl;
    }

    public String getStrengthCaloriesUrl() {
        return this.strengthCaloriesUrl;
    }

    public void setStrengthCaloriesUrl(String str) {
        this.strengthCaloriesUrl = str;
    }
}
