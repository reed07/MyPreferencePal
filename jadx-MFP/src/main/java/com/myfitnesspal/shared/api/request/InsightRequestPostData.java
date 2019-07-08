package com.myfitnesspal.shared.api.request;

import com.google.gson.annotations.Expose;
import java.util.List;

public class InsightRequestPostData {
    @Expose
    private List<FoodAnalyzerRequestPostData> analyzerEntries;
    @Expose
    private FoodAnalyzerUserRequestPostData user;

    public InsightRequestPostData() {
    }

    public InsightRequestPostData(FoodAnalyzerUserRequestPostData foodAnalyzerUserRequestPostData, List<FoodAnalyzerRequestPostData> list) {
        this.user = foodAnalyzerUserRequestPostData;
        this.analyzerEntries = list;
    }

    public FoodAnalyzerUserRequestPostData getUser() {
        return this.user;
    }

    public void setUser(FoodAnalyzerUserRequestPostData foodAnalyzerUserRequestPostData) {
        this.user = foodAnalyzerUserRequestPostData;
    }

    public List<FoodAnalyzerRequestPostData> getAnalyzerEntries() {
        return this.analyzerEntries;
    }

    public void setAnalyzerEntries(List<FoodAnalyzerRequestPostData> list) {
        this.analyzerEntries = list;
    }
}
