package com.myfitnesspal.feature.diary.service;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v15.CompleteDiaryDayResultObject;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import dagger.Lazy;

public class DiaryCompletedTask extends EventedTaskBase<CompleteDiaryDayResultObject, ApiException> {
    private final DiaryDay diaryDay;
    private final Lazy<DiaryService> diaryService;
    private final Lazy<SyncService> syncService;

    public static class CompletedEvent extends TaskEventBase<CompleteDiaryDayResultObject, ApiException> {
    }

    public DiaryCompletedTask(Lazy<SyncService> lazy, Lazy<DiaryService> lazy2, DiaryDay diaryDay2) {
        super(CompletedEvent.class);
        this.syncService = lazy;
        this.diaryService = lazy2;
        this.diaryDay = diaryDay2;
    }

    /* access modifiers changed from: protected */
    public CompleteDiaryDayResultObject exec(Context context) throws ApiException {
        if (((SyncService) this.syncService.get()).enqueueAndWait(SyncType.Incremental).isSuccessful()) {
            return ((DiaryService) this.diaryService.get()).completeDiaryDayFor(this.diaryDay.getDate());
        }
        throw new ApiException("Failed to post dairy completed", -1);
    }
}
