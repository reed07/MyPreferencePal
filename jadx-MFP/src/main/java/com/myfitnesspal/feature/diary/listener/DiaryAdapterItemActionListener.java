package com.myfitnesspal.feature.diary.listener;

import android.view.View;
import com.myfitnesspal.feature.diary.ui.item.SectionHeaderItem;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.shared.model.v1.DatabaseObject;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import java.util.Date;

public interface DiaryAdapterItemActionListener {
    void onBannerAdLoaded();

    void onBottomRowAddFoodClick(String str);

    void onBottomRowSaveMealActionsClick(String str);

    void onBottomRowShowMoreActionsClick(String str);

    void onCompleteEntryClick(String str);

    void onEntryClick(DiaryDay diaryDay, DatabaseObject databaseObject, View view);

    void onEntryItemCheckChanged(DatabaseObject databaseObject, boolean z);

    boolean onEntryLongClick(DatabaseObject databaseObject);

    void onMealCaloriesClick(SectionHeaderItem sectionHeaderItem);

    void onMealHeaderTipCloseClick(MealHeaderTip mealHeaderTip);

    void onNotesClick();

    void onNutritionClick(String str);

    void onPromoCallActionClick(PremiumFeature premiumFeature);

    void onSectionHeaderCheckChanged(DiaryDay diaryDay, String str, boolean z);

    void onTimestampClick(String str, Date date);

    void onToggleMealMacrosUnitClick();
}
