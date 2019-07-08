package com.myfitnesspal.feature.exercise.task;

import android.content.Context;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.search.service.SearchService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseMapper;
import com.myfitnesspal.shared.model.v1.Exercise;
import com.myfitnesspal.shared.model.v15.SearchResult;
import com.myfitnesspal.shared.model.v2.MfpExercise;
import com.myfitnesspal.shared.model.v2.MfpExerciseEntry;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ReturningFunction1;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ExerciseSearchTask extends EventedTaskBase<List<MfpExerciseEntry>, ApiException> {
    private final Lazy<DiaryService> diaryService;
    /* access modifiers changed from: private */
    public final Lazy<ExerciseMapper> exerciseMapper;
    private final int exerciseType;
    private final int maxResults;
    private final String query;
    private final Lazy<SearchService> searchService;
    private final Session session;

    public static class CompletedEvent extends TaskEventBase<List<MfpExerciseEntry>, ApiException> {
    }

    public ExerciseSearchTask(Lazy<SearchService> lazy, Lazy<ExerciseMapper> lazy2, Lazy<DiaryService> lazy3, Session session2, String str, int i, int i2) {
        super(CompletedEvent.class);
        this.searchService = lazy;
        this.exerciseType = i;
        this.query = str;
        this.maxResults = i2;
        this.exerciseMapper = lazy2;
        this.diaryService = lazy3;
        this.session = session2;
    }

    /* access modifiers changed from: protected */
    public List<MfpExerciseEntry> exec(Context context) throws ApiException {
        return new ArrayList(Enumerable.select((Collection<T>) ((SearchService) this.searchService.get()).searchForExercise(this.exerciseType, this.query, this.maxResults), (ReturningFunction1<U, T>) new ReturningFunction1<MfpExerciseEntry, SearchResult>() {
            public MfpExerciseEntry execute(SearchResult searchResult) {
                ExerciseSearchTask exerciseSearchTask = ExerciseSearchTask.this;
                return exerciseSearchTask.lookUpExerciseEntryForExercise((MfpExercise) ((ExerciseMapper) exerciseSearchTask.exerciseMapper.get()).tryMapFrom((Exercise) searchResult.getData()));
            }
        }));
    }

    /* access modifiers changed from: private */
    public MfpExerciseEntry lookUpExerciseEntryForExercise(MfpExercise mfpExercise) {
        if (mfpExercise == null) {
            return null;
        }
        MfpExerciseEntry latestExerciseEntryForExercise = ((DiaryService) this.diaryService.get()).getLatestExerciseEntryForExercise(mfpExercise.hasVersion() ? mfpExercise.getVersion() : mfpExercise.getId(), mfpExercise.getMasterId());
        if (latestExerciseEntryForExercise != null) {
            latestExerciseEntryForExercise.setExercise(mfpExercise);
        } else {
            latestExerciseEntryForExercise = new MfpExerciseEntry(mfpExercise);
        }
        latestExerciseEntryForExercise.setDate(this.session.getUser().getActiveDate());
        return latestExerciseEntryForExercise;
    }
}
