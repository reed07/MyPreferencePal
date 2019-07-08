package com.myfitnesspal.shared.api.request;

import com.google.gson.annotations.Expose;
import com.uacf.core.constants.DateTime.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class DiaryEntryRequest {
    public static final String YYYY_MM_DD = Format.newIso8601DateFormat().toPattern();
    @Expose
    private String date = new SimpleDateFormat(YYYY_MM_DD).format(new Date());
    @Expose
    private String mealName;
    @Expose
    private Double servings;
    @Expose
    private String type;

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(Calendar calendar) {
        this.date = new SimpleDateFormat(YYYY_MM_DD).format(calendar.getTime());
    }

    public String getMealName() {
        return this.mealName;
    }

    public void setMealName(String str) {
        this.mealName = str;
    }

    public Double getServings() {
        return this.servings;
    }

    public void setServings(Double d) {
        this.servings = d;
    }
}
