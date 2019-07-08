package com.myfitnesspal.feature.diary.model;

import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.diary.ui.listener.OnDiaryDayFetchedListener;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.framework.mvvm.RunnerViewModel;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v2.MfpNutrientGoal;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.util.Debouncer;
import com.uacf.taskrunner.Runner;
import dagger.Lazy;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class DiaryViewModelBase<T> extends RunnerViewModel<T> {
    private static final int MAX_ENTRIES = 7;
    private UpdateDebouncer debouncer = new UpdateDebouncer();
    protected final Map<String, DiaryDayContext> diaryDayCache = new LinkedHashMap<String, DiaryDayContext>(7) {
        /* access modifiers changed from: protected */
        public boolean removeEldestEntry(Entry<String, DiaryDayContext> entry) {
            return size() > 7;
        }
    };
    protected final Lazy<DiaryService> diaryService;
    protected final Lazy<NutrientGoalService> nutrientGoalService;

    private static class UpdateDebouncer extends Debouncer<DiaryDayContext> {
        private static final int DEBOUNCE_DELAY = 350;
        private OnDiaryDayFetchedListener diaryDayFetchedListener;
        private Map<Long, DiaryDayContext> pending = new HashMap();

        UpdateDebouncer() {
            super(DEBOUNCE_DELAY);
        }

        /* access modifiers changed from: protected */
        public void onCalled(DiaryDayContext diaryDayContext) {
            this.pending.put(Long.valueOf(diaryDayContext.getDiaryDay().getDate().getTime()), diaryDayContext);
        }

        /* access modifiers changed from: protected */
        public void onDebounced(DiaryDayContext diaryDayContext) {
            if (this.diaryDayFetchedListener != null) {
                for (DiaryDayContext onDiaryDayFetched : this.pending.values()) {
                    this.diaryDayFetchedListener.onDiaryDayFetched(onDiaryDayFetched);
                }
            }
            this.pending.clear();
        }

        public void setDiaryDayFetchedListener(OnDiaryDayFetchedListener onDiaryDayFetchedListener) {
            this.diaryDayFetchedListener = onDiaryDayFetchedListener;
        }
    }

    /* access modifiers changed from: protected */
    public abstract boolean matchesTask(TaskDetails taskDetails);

    protected DiaryViewModelBase(Runner runner, Lazy<DiaryService> lazy, Lazy<NutrientGoalService> lazy2, OnDiaryDayFetchedListener onDiaryDayFetchedListener) {
        super(runner);
        this.diaryService = lazy;
        this.nutrientGoalService = lazy2;
        this.debouncer.setDiaryDayFetchedListener(onDiaryDayFetchedListener);
    }

    public void setDiaryDayFetchedListener(OnDiaryDayFetchedListener onDiaryDayFetchedListener) {
        this.debouncer.setDiaryDayFetchedListener(onDiaryDayFetchedListener);
    }

    public void load(T... tArr) {
        setState(State.Loading);
    }

    /* access modifiers changed from: protected */
    public void onTaskCompleted(TaskDetails taskDetails) {
        if (taskDetails.isFrom(getRunner()) && matchesTask(taskDetails)) {
            if (!taskDetails.successful()) {
                setError(taskDetails.getFailure());
                return;
            }
            DiaryDayContext diaryDayContext = (DiaryDayContext) taskDetails.getResult();
            addNewDiaryDayToCache(diaryDayContext);
            this.debouncer.call(diaryDayContext);
            setState(State.Loaded);
        }
    }

    /* access modifiers changed from: protected */
    public final void addNewDiaryDayToCache(DiaryDayContext diaryDayContext) {
        this.diaryDayCache.put(getCacheKey(diaryDayContext.getDiaryDay().getDate()), diaryDayContext);
    }

    public boolean isDiaryInfoCacheEntryReady(Date date) {
        return ((DiaryDayContext) this.diaryDayCache.get(getCacheKey(date))) != null;
    }

    public DiaryDay getDiaryDayForDate(Date date) {
        DiaryDayContext diaryDayContext = (DiaryDayContext) this.diaryDayCache.get(getCacheKey(date));
        if (diaryDayContext != null) {
            return diaryDayContext.getDiaryDay();
        }
        return null;
    }

    public MfpNutrientGoal getNutrientGoalForDate(Date date) {
        DiaryDayContext diaryDayContext = (DiaryDayContext) this.diaryDayCache.get(getCacheKey(date));
        if (diaryDayContext != null) {
            return diaryDayContext.getNutrientGoal();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public String getCacheKey(Date date) {
        return DateTimeUtils.getDateStringFromDate(date);
    }
}
