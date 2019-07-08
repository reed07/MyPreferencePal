package com.myfitnesspal.feature.diary.ui.item;

import android.view.View.OnClickListener;
import com.myfitnesspal.feature.diary.model.DiarySectionNutritionTotals;
import com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter.ViewType;

public class SectionHeaderItem implements DiaryAdapterItem {
    private String dayOfWeek;
    private int diarySection;
    private String headerName;
    private boolean isFooter;
    private boolean isSelectable;
    private boolean isSelected;
    private String localizedHeaderName;
    private DiarySectionNutritionTotals macroTotals;
    private String mealGoalCalories;
    private boolean showMealGoals;
    private OnClickListener toolsBtnClickListener;
    private int totalCalories;

    public SectionHeaderItem(String str, String str2, int i, int i2, boolean z, OnClickListener onClickListener) {
        this(str, str2, i, i2, null, null, z, false, false, false, null, onClickListener);
    }

    public SectionHeaderItem(String str, String str2, int i, int i2, String str3, DiarySectionNutritionTotals diarySectionNutritionTotals, boolean z, boolean z2, boolean z3, boolean z4, String str4, OnClickListener onClickListener) {
        this.headerName = str;
        this.localizedHeaderName = str2;
        this.diarySection = i;
        this.totalCalories = i2;
        this.mealGoalCalories = str3;
        this.macroTotals = diarySectionNutritionTotals;
        this.isFooter = z;
        this.isSelectable = z2;
        this.isSelected = z3;
        this.showMealGoals = z4;
        this.dayOfWeek = str4;
        this.toolsBtnClickListener = onClickListener;
    }

    public ViewType getItemType() {
        return ViewType.SectionHeader;
    }

    public boolean isFooter() {
        return this.isFooter;
    }

    public OnClickListener getToolsBtnClickListener() {
        return this.toolsBtnClickListener;
    }

    public String getHeaderName() {
        return this.headerName;
    }

    public int getDiarySection() {
        return this.diarySection;
    }

    public int getTotalCalories() {
        return this.totalCalories;
    }

    public String getMealGoalCalories() {
        return this.mealGoalCalories;
    }

    public DiarySectionNutritionTotals getMacroTotals() {
        return this.macroTotals;
    }

    public String getLocalizeddHeaderName() {
        return this.localizedHeaderName;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setIsSelected(boolean z) {
        this.isSelected = z;
    }

    public boolean isSelectable() {
        return this.isSelectable;
    }

    public boolean isShowMealGoals() {
        return this.showMealGoals;
    }

    public String getDayOfWeek() {
        return this.dayOfWeek;
    }
}
