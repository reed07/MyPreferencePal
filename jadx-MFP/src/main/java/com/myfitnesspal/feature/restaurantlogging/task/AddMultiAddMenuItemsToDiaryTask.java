package com.myfitnesspal.feature.restaurantlogging.task;

import android.content.Context;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMultiAddMenuItem;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase.Unchecked;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v2.MfpFood;
import dagger.Lazy;
import java.util.Date;
import java.util.List;

public class AddMultiAddMenuItemsToDiaryTask extends Unchecked<Boolean> {
    private final Date date;
    private final Lazy<DiaryService> diaryService;
    private final String mealName;
    private final List<MfpMultiAddMenuItem> multiAddMenuItems;

    public static class CompletedEvent extends TaskEventBase.Unchecked<Boolean> {
    }

    public AddMultiAddMenuItemsToDiaryTask(Lazy<DiaryService> lazy, List<MfpMultiAddMenuItem> list, String str, Date date2) {
        super(CompletedEvent.class);
        this.diaryService = lazy;
        this.multiAddMenuItems = list;
        this.mealName = str;
        this.date = date2;
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) {
        DiaryDay diaryDayForDateSync = ((DiaryService) this.diaryService.get()).getDiaryDayForDateSync(this.date);
        diaryDayForDateSync.setIsPerformingMultiAdd(true);
        for (MfpMultiAddMenuItem mfpFood : this.multiAddMenuItems) {
            MfpFood mfpFood2 = mfpFood.toMfpFood();
            if (mfpFood2 != null) {
                diaryDayForDateSync.addV2Food(mfpFood2, this.mealName);
            }
        }
        diaryDayForDateSync.setIsPerformingMultiAdd(false);
        diaryDayForDateSync.setJustAddedFoodEntry(null);
        diaryDayForDateSync.setJustAddedMultipleItems(true);
        diaryDayForDateSync.setJustAddedMultipleItemsMealName(this.mealName);
        return Boolean.valueOf(true);
    }
}
