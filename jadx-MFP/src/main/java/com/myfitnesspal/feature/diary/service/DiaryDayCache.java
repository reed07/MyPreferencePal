package com.myfitnesspal.feature.diary.service;

import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v2.MfpNutrientGoal;
import com.myfitnesspal.shared.util.DateTimeUtils;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class DiaryDayCache {
    private static final int MAX_ENTRIES = 7;
    private final Object cacheSyncLock = new Object();
    private final Map<String, DiaryInfoCacheEntry> cachedDiaryDays = new LinkedHashMap<String, DiaryInfoCacheEntry>(7) {
        /* access modifiers changed from: protected */
        public boolean removeEldestEntry(Entry<String, DiaryInfoCacheEntry> entry) {
            return size() > 7;
        }
    };

    public static class DiaryInfoCacheEntry {
        private DataState dataState = DataState.SameAsBefore;
        private final DiaryDay diaryDay;
        private final MfpNutrientGoal nutrientGoal;
        private ObjectState objectState = ObjectState.Blank;

        private enum DataState {
            SameAsBefore,
            Updated
        }

        private enum ObjectState {
            Blank,
            Stale,
            Valid
        }

        public DiaryInfoCacheEntry(DiaryDay diaryDay2, MfpNutrientGoal mfpNutrientGoal) {
            this.diaryDay = diaryDay2;
            this.nutrientGoal = mfpNutrientGoal;
        }

        public DiaryDay getDiaryDay() {
            return this.diaryDay;
        }

        public MfpNutrientGoal getNutrientGoal() {
            return this.nutrientGoal;
        }

        public void markAsStale() {
            if (this.objectState != ObjectState.Blank) {
                this.objectState = ObjectState.Stale;
            }
        }

        public void markAsValid() {
            this.objectState = ObjectState.Valid;
        }

        public void markDataAsUpdated() {
            this.dataState = DataState.Updated;
        }

        public void markDataAsSameAsBefore() {
            this.dataState = DataState.SameAsBefore;
        }

        public boolean isValid() {
            return this.objectState == ObjectState.Valid;
        }

        public boolean isBlank() {
            return this.objectState == ObjectState.Blank;
        }
    }

    private String getKeyForDate(Date date) {
        return DateTimeUtils.getDateStringFromDate(date);
    }

    public DiaryInfoCacheEntry addDiaryDayCacheEntry(Date date, DiaryDay diaryDay, MfpNutrientGoal mfpNutrientGoal) {
        String keyForDate = getKeyForDate(date);
        DiaryInfoCacheEntry diaryInfoCacheEntry = new DiaryInfoCacheEntry(diaryDay, mfpNutrientGoal);
        synchronized (this.cacheSyncLock) {
            this.cachedDiaryDays.put(keyForDate, diaryInfoCacheEntry);
        }
        return diaryInfoCacheEntry;
    }

    public DiaryInfoCacheEntry getDiaryDayCacheEntry(Date date) {
        DiaryInfoCacheEntry diaryInfoCacheEntry;
        String keyForDate = getKeyForDate(date);
        synchronized (this.cacheSyncLock) {
            diaryInfoCacheEntry = (DiaryInfoCacheEntry) this.cachedDiaryDays.get(keyForDate);
        }
        return diaryInfoCacheEntry;
    }

    public void markDiaryDayCacheEntryStaleForDate(Date date) {
        synchronized (this.cacheSyncLock) {
            DiaryInfoCacheEntry diaryDayCacheEntry = getDiaryDayCacheEntry(date);
            if (diaryDayCacheEntry != null) {
                diaryDayCacheEntry.markAsStale();
            }
        }
    }

    public void markAllEntriesAsStale() {
        synchronized (this.cacheSyncLock) {
            for (DiaryInfoCacheEntry markAsStale : this.cachedDiaryDays.values()) {
                markAsStale.markAsStale();
            }
        }
    }

    public void clearCache() {
        synchronized (this.cacheSyncLock) {
            this.cachedDiaryDays.clear();
        }
    }

    public void clearCachedInsights() {
        synchronized (this.cacheSyncLock) {
            for (DiaryInfoCacheEntry diaryDay : this.cachedDiaryDays.values()) {
                diaryDay.getDiaryDay().clearInsights();
            }
        }
    }

    public Map<String, DiaryInfoCacheEntry> getCachedDiaryDays() {
        return new LinkedHashMap(this.cachedDiaryDays);
    }
}
