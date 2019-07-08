package com.myfitnesspal.feature.diary.service;

import com.myfitnesspal.feature.diary.model.FriendDiaryRequestData;
import com.myfitnesspal.feature.diary.service.DiaryDayCache.DiaryInfoCacheEntry;
import com.myfitnesspal.feature.search.model.SortOrder;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.request.DiaryLogRequest;
import com.myfitnesspal.shared.api.v2.MfpV2ApiErrorCallback;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v1.ExerciseEntry;
import com.myfitnesspal.shared.model.v15.BinaryApiSerializable;
import com.myfitnesspal.shared.model.v15.CompleteDiaryDayResultObject;
import com.myfitnesspal.shared.model.v2.MfpExerciseEntry;
import com.myfitnesspal.shared.model.v2.MfpLogEntry;
import com.myfitnesspal.shared.model.v2.MfpStepSource;
import com.myfitnesspal.shared.model.v2.MfpStepsEntryV2;
import com.myfitnesspal.shared.service.syncv1.DeletedItemsProvider;
import com.uacf.core.util.Function1;
import com.uacf.sync.provider.sdk.model.SyncItemHandler;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public interface DiaryService extends DeletedItemsProvider, SyncItemHandler<MfpExerciseEntry> {
    MfpExerciseEntry buildExerciseEntryForExternalSync(String str, long j, long j2, int i, String str2, String str3);

    MfpExerciseEntry buildExerciseEntryForExternalSync(String str, long j, long j2, String str2);

    void clearCachedInsights();

    void clearDiaryDayCache();

    CompleteDiaryDayResultObject completeDiaryDayFor(Date date) throws ApiException;

    ExerciseEntry createNewExerciseEntryFromExisting(ExerciseEntry exerciseEntry, Date date);

    MfpExerciseEntry createNewExerciseEntryLocally(MfpExerciseEntry mfpExerciseEntry);

    MfpExerciseEntry createNewExerciseEntryRemotely(MfpExerciseEntry mfpExerciseEntry) throws ApiException;

    ExerciseEntry createNewV1ExerciseEntryLocally(ExerciseEntry exerciseEntry);

    boolean deleteExerciseEntryLocally(ExerciseEntry exerciseEntry);

    boolean deleteExerciseEntryLocally(MfpExerciseEntry mfpExerciseEntry);

    void deleteExerciseEntryRemotely(MfpExerciseEntry mfpExerciseEntry) throws ApiException;

    void endExerciseLoggingFlow(String str, int i);

    void endFoodLoggingFlow(Map<String, String> map);

    int eraseAllEntriesForExerciseId(long j);

    List<String> extractMealNamesFromSummaryObject(List<BinaryApiSerializable> list);

    Map<String, DiaryInfoCacheEntry> getCachedDiaryDays();

    DiaryInfoCacheEntry getDiaryDayCacheEntry(Date date);

    DiaryDay getDiaryDayForActiveDateSync();

    void getDiaryDayForDate(Date date, Function1<DiaryDay> function1);

    DiaryDay getDiaryDayForDateSync(Date date);

    Map<String, MfpExerciseEntry> getExerciseEntriesOnOrAfter(String str, String str2, long j);

    MfpExerciseEntry getExerciseEntryLocally(long j);

    List<MfpExerciseEntry> getFavoriteExercisesOfType(int i, int i2, @NotNull SortOrder sortOrder);

    MfpExerciseEntry getLatestExerciseEntryForExercise(String str, long j);

    List<ExerciseEntry> getV1ExerciseEntriesForDate(Date date);

    ExerciseEntry getV1ExerciseEntry(long j);

    List<MfpExerciseEntry> getV2ExerciseEntriesForDate(Date date);

    boolean hasExerciseAlreadyStored(@NotNull MfpExerciseEntry mfpExerciseEntry);

    MfpExerciseEntry insertOrUpdateExerciseEntryLocally(MfpExerciseEntry mfpExerciseEntry);

    ExerciseEntry insertOrUpdateV1ExerciseEntryLocally(ExerciseEntry exerciseEntry);

    void logEntriesAsync(DiaryLogRequest diaryLogRequest, Function1<MfpLogEntry> function1, MfpV2ApiErrorCallback mfpV2ApiErrorCallback);

    void markDiaryDayCacheEntryStaleForDate(Date date);

    void markDiaryDayCacheStale();

    void postSteps(List<MfpStepsEntryV2> list, MfpStepSource mfpStepSource) throws ApiException;

    void postStepsAsync(List<MfpStepsEntryV2> list, MfpStepSource mfpStepSource, Function1<Boolean> function1);

    DiaryDay retrieveDiaryDayForOtherUser(FriendDiaryRequestData friendDiaryRequestData) throws ApiException;

    void startLoggingFlow(String str);

    void startLoggingFlowIfNecessary(String str);

    MfpExerciseEntry updateExerciseEntryLocally(MfpExerciseEntry mfpExerciseEntry);

    void updateExerciseEntryRemotely(MfpExerciseEntry mfpExerciseEntry) throws ApiException;

    ExerciseEntry updateV1ExerciseEntryLocally(ExerciseEntry exerciseEntry);
}
