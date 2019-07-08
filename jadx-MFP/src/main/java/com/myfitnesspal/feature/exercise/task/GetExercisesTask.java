package com.myfitnesspal.feature.exercise.task;

import android.content.Context;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.exercise.service.ExerciseService;
import com.myfitnesspal.feature.search.model.SortOrder;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseEntryMapper;
import com.myfitnesspal.shared.model.v1.ExerciseEntry;
import com.myfitnesspal.shared.model.v2.MfpExercise;
import com.myfitnesspal.shared.model.v2.MfpExerciseEntry;
import com.myfitnesspal.shared.model.v2.MfpMeasuredValue;
import com.myfitnesspal.shared.model.v2.MfpMeasuredValue.Unit;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GetExercisesTask extends EventedTaskBase<List<MfpExerciseEntry>, ApiException> {
    private final Lazy<DiaryService> diaryService;
    private final Lazy<ExerciseEntryMapper> entryMapper;
    private final Lazy<ExerciseService> exerciseService;
    private final int exerciseType;
    private final Filter filter;
    private final Session session;
    private final SortOrder sortOrder;

    public static class CompletedEvent extends TaskEventBase<List<MfpExerciseEntry>, ApiException> {
    }

    public enum Filter {
        Owned,
        All
    }

    public GetExercisesTask(Lazy<ExerciseService> lazy, Lazy<DiaryService> lazy2, Filter filter2, int i, SortOrder sortOrder2, Session session2, Lazy<ExerciseEntryMapper> lazy3) {
        super(CompletedEvent.class);
        this.exerciseService = lazy;
        this.diaryService = lazy2;
        this.filter = filter2;
        this.exerciseType = i;
        this.sortOrder = sortOrder2;
        this.session = session2;
        this.entryMapper = lazy3;
    }

    /* access modifiers changed from: protected */
    public List<MfpExerciseEntry> exec(Context context) throws ApiException {
        switch (this.filter) {
            case Owned:
                return buildMyResults(((ExerciseService) this.exerciseService.get()).getOwnedExercisesOfType(this.exerciseType, this.sortOrder));
            case All:
                return buildAllResults(((ExerciseService) this.exerciseService.get()).getAllPublicExercisesOfType(this.exerciseType));
            default:
                return null;
        }
    }

    private List<MfpExerciseEntry> buildMyResults(List<MfpExercise> list) {
        return Enumerable.select((Collection<T>) list, (ReturningFunction1<U, T>) new ReturningFunction1() {
            public final Object execute(Object obj) {
                return GetExercisesTask.this.lookUpExerciseEntryForExercise((MfpExercise) obj);
            }
        });
    }

    private List<MfpExerciseEntry> buildAllResults(List<MfpExercise> list) {
        List<MfpExerciseEntry> select = Enumerable.select((Collection<T>) list, (ReturningFunction1<U, T>) new ReturningFunction1() {
            public final Object execute(Object obj) {
                return GetExercisesTask.this.lookUpExerciseEntryForExercise((MfpExercise) obj);
            }
        });
        if (this.sortOrder == SortOrder.ALPHABETICAL_DESCENDING) {
            Collections.sort(select, $$Lambda$GetExercisesTask$LQzcdX2GEKbt33mgty0yopx21I.INSTANCE);
        }
        return select;
    }

    /* access modifiers changed from: private */
    public MfpExerciseEntry lookUpExerciseEntryForExercise(MfpExercise mfpExercise) {
        MfpExerciseEntry latestExerciseEntryForExercise = ((DiaryService) this.diaryService.get()).getLatestExerciseEntryForExercise(mfpExercise.hasVersion() ? mfpExercise.getVersion() : mfpExercise.getId(), mfpExercise.getMasterId());
        if (latestExerciseEntryForExercise != null) {
            latestExerciseEntryForExercise.setExercise(mfpExercise);
        } else {
            latestExerciseEntryForExercise = checkDiaryDayForExerciseEntry(mfpExercise);
            if (latestExerciseEntryForExercise == null) {
                latestExerciseEntryForExercise = initAsDefaultForExercise(mfpExercise);
            }
        }
        latestExerciseEntryForExercise.setDate(this.session.getUser().getActiveDate());
        return latestExerciseEntryForExercise;
    }

    private MfpExerciseEntry checkDiaryDayForExerciseEntry(MfpExercise mfpExercise) {
        for (ExerciseEntry exerciseEntry : ((DiaryService) this.diaryService.get()).getDiaryDayForActiveDateSync().getExerciseEntries()) {
            if (Strings.equals((Object) mfpExercise.getId(), (Object) Long.valueOf(exerciseEntry.getExercise().getLocalId()))) {
                return (MfpExerciseEntry) ((ExerciseEntryMapper) this.entryMapper.get()).tryMapFrom(exerciseEntry);
            }
        }
        return null;
    }

    private MfpExerciseEntry initAsDefaultForExercise(MfpExercise mfpExercise) {
        MfpExerciseEntry mfpExerciseEntry = new MfpExerciseEntry();
        mfpExerciseEntry.setExercise(mfpExercise);
        mfpExerciseEntry.setDuration(0);
        mfpExerciseEntry.setSets(0);
        mfpExerciseEntry.setRepsPerSet(0);
        mfpExerciseEntry.setWeightPerSet(new MfpMeasuredValue(Unit.POUNDS, BitmapDescriptorFactory.HUE_RED));
        mfpExerciseEntry.setEnergy(new MfpMeasuredValue("calories", BitmapDescriptorFactory.HUE_RED));
        return mfpExerciseEntry;
    }
}
