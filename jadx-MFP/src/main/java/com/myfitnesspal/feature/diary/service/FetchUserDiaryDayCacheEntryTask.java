package com.myfitnesspal.feature.diary.service;

import android.content.Context;
import com.myfitnesspal.feature.diary.model.DiaryDayContext;
import com.myfitnesspal.feature.diary.service.DiaryDayCache.DiaryInfoCacheEntry;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase.Unchecked;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.taskrunner.Runner.CacheMode;
import com.uacf.taskrunner.Runner.DedupeMode;
import dagger.Lazy;
import java.util.Date;

public class FetchUserDiaryDayCacheEntryTask extends Unchecked<DiaryDayContext> {
    private static final String TASK_NAME_BASE = "FetchDiaryDayCacheEntry-";
    private final Date date;
    private final Lazy<DiaryService> diaryService;

    public static class CompletedEvent extends TaskEventBase.Unchecked<DiaryInfoCacheEntry> {
        private Date date;

        public Date getDate() {
            return this.date;
        }

        /* access modifiers changed from: private */
        public void setDate(Date date2) {
            this.date = date2;
        }
    }

    public FetchUserDiaryDayCacheEntryTask(Lazy<DiaryService> lazy, Date date2) {
        super(CompletedEvent.class);
        this.diaryService = lazy;
        this.date = date2;
    }

    public static boolean matches(TaskDetails taskDetails) {
        return taskDetails.getTaskName().startsWith(TASK_NAME_BASE);
    }

    /* access modifiers changed from: protected */
    public DiaryDayContext exec(Context context) throws RuntimeException {
        DiaryInfoCacheEntry diaryDayCacheEntry = ((DiaryService) this.diaryService.get()).getDiaryDayCacheEntry(this.date);
        ((CompletedEvent) getEvent()).setDate(this.date);
        return new DiaryDayContext(diaryDayCacheEntry.getDiaryDay(), diaryDayCacheEntry.getNutrientGoal());
    }

    /* access modifiers changed from: protected */
    public CacheMode getDefaultCacheMode() {
        return CacheMode.None;
    }

    /* access modifiers changed from: protected */
    public String getTaskName() {
        StringBuilder sb = new StringBuilder();
        sb.append(TASK_NAME_BASE);
        sb.append(DateTimeUtils.getDateStringFromDate(this.date));
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public DedupeMode getDefaultDedupeMode() {
        return DedupeMode.CancelExisting;
    }
}
