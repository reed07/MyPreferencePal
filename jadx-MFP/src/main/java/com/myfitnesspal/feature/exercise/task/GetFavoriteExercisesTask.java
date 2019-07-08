package com.myfitnesspal.feature.exercise.task;

import android.content.Context;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.search.model.SortOrder;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v2.MfpExerciseEntry;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ReturningFunction1;
import dagger.Lazy;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class GetFavoriteExercisesTask extends EventedTaskBase<List<MfpExerciseEntry>, ApiException> {
    private final Date activeDate;
    private final Lazy<DiaryService> diaryService;
    private final int exerciseType;
    private final int limit;
    private final SortOrder sortOrder;

    public static class CompletedEvent extends TaskEventBase<List<MfpExerciseEntry>, ApiException> {
    }

    public GetFavoriteExercisesTask(Lazy<DiaryService> lazy, Date date, int i, SortOrder sortOrder2, int i2) {
        super((TaskEventBase) new CompletedEvent());
        this.diaryService = lazy;
        this.activeDate = date;
        this.exerciseType = i;
        this.sortOrder = sortOrder2;
        this.limit = i2;
    }

    /* access modifiers changed from: protected */
    public List<MfpExerciseEntry> exec(Context context) throws ApiException {
        return Enumerable.select((Collection<T>) ((DiaryService) this.diaryService.get()).getFavoriteExercisesOfType(this.exerciseType, this.limit + 1, this.sortOrder), (ReturningFunction1<U, T>) new ReturningFunction1() {
            public final Object execute(Object obj) {
                return ((MfpExerciseEntry) obj).setDate(GetFavoriteExercisesTask.this.activeDate);
            }
        });
    }
}
