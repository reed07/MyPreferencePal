package com.myfitnesspal.shared.api.request;

import com.google.gson.annotations.Expose;
import java.util.Date;

public class FoodQuestionRequestPostData {
    @Expose
    private String answer;
    @Expose
    private Date date;
    @Expose
    private String foodId;
    @Expose
    private String mealName;
    @Expose
    private int questionId;
    @Expose
    private String userId;

    public FoodQuestionRequestPostData() {
    }

    public FoodQuestionRequestPostData(String str, Date date2, String str2, String str3, int i, String str4) {
        this.userId = str;
        this.date = date2;
        this.mealName = str2;
        this.foodId = str3;
        this.questionId = i;
        this.answer = str4;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date2) {
        this.date = date2;
    }

    public String getMealName() {
        return this.mealName;
    }

    public void setMealName(String str) {
        this.mealName = str;
    }

    public String getFoodId() {
        return this.foodId;
    }

    public void setFoodId(String str) {
        this.foodId = str;
    }

    public int getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(int i) {
        this.questionId = i;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String str) {
        this.answer = str;
    }
}
