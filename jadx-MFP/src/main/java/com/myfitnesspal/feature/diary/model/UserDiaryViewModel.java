package com.myfitnesspal.feature.diary.model;

import com.myfitnesspal.feature.diary.service.DiaryDayCache.DiaryInfoCacheEntry;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.diary.service.FetchUserDiaryDayCacheEntryTask;
import com.myfitnesspal.feature.diary.ui.listener.OnDiaryDayFetchedListener;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.uacf.taskrunner.Runner;
import dagger.Lazy;
import java.util.Date;
import java.util.Map.Entry;

public class UserDiaryViewModel extends DiaryViewModelBase<Date> {
    public UserDiaryViewModel(Runner runner, Lazy<DiaryService> lazy, Lazy<NutrientGoalService> lazy2, OnDiaryDayFetchedListener onDiaryDayFetchedListener) {
        super(runner, lazy, lazy2, onDiaryDayFetchedListener);
        prePopulateWithDiaryServiceCache();
    }

    private void prePopulateWithDiaryServiceCache() {
        for (Entry value : ((DiaryService) this.diaryService.get()).getCachedDiaryDays().entrySet()) {
            DiaryInfoCacheEntry diaryInfoCacheEntry = (DiaryInfoCacheEntry) value.getValue();
            if (!diaryInfoCacheEntry.isBlank()) {
                addNewDiaryDayToCache(new DiaryDayContext(diaryInfoCacheEntry.getDiaryDay(), diaryInfoCacheEntry.getNutrientGoal()));
            }
        }
    }

    public void load(Date... dateArr) {
        super.load(dateArr);
        new FetchUserDiaryDayCacheEntryTask(this.diaryService, dateArr[0]).run(getRunner());
    }

    /* access modifiers changed from: protected */
    public boolean matchesTask(TaskDetails taskDetails) {
        return FetchUserDiaryDayCacheEntryTask.matches(taskDetails);
    }
}
