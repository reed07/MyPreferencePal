package com.myfitnesspal.feature.exercise.service;

import com.myfitnesspal.feature.search.model.SortOrder;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.exception.DuplicateResourceException;
import com.myfitnesspal.shared.model.v1.Exercise;
import com.myfitnesspal.shared.model.v2.MfpExercise;
import com.myfitnesspal.shared.service.syncv1.DeletedItemsProvider;
import com.uacf.sync.provider.sdk.model.SyncItemHandler;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public interface ExerciseService extends DeletedItemsProvider, SyncItemHandler<MfpExercise> {
    MfpExercise createNewExerciseLocally(MfpExercise mfpExercise);

    MfpExercise createNewExerciseLocallyIfMissing(MfpExercise mfpExercise) throws DuplicateResourceException;

    MfpExercise createNewExerciseRemotely(MfpExercise mfpExercise) throws ApiException;

    Exercise createNewV1ExerciseLocallyIfMissing(Exercise exercise) throws DuplicateResourceException;

    boolean deleteExerciseLocally(Exercise exercise, boolean z);

    boolean deleteExerciseLocally(MfpExercise mfpExercise, boolean z);

    void deleteExerciseRemotely(String str) throws ApiException;

    List<MfpExercise> getAllPublicExercisesOfType(int i);

    int getCountForOwnedExercisesOfType(int i);

    MfpExercise getExerciseLocally(long j);

    MfpExercise getExerciseLocallyWhetherUserOrCustom(String str);

    MfpExercise getExerciseRemotely(String str) throws ApiException;

    MfpExercise getExerciseWithDescriptionForSyncV1(String str);

    Exercise getNonDeletedExerciseV1WithDescription(String str);

    MfpExercise getNonDeletedExerciseWithDescription(String str);

    List<MfpExercise> getOwnedExercisesOfType(int i, @NotNull SortOrder sortOrder);

    MfpExercise getPublicExerciseById(String str);

    Exercise getV1ExerciseLocally(long j);

    Exercise getV1ExerciseLocallyFromMasterId(long j);

    MfpExercise insertExerciseIfNotExists(MfpExercise mfpExercise);

    MfpExercise insertOrUpdateExerciseLocallyForSync(MfpExercise mfpExercise);

    Exercise insertOrUpdateV1ExerciseLocally(Exercise exercise);

    Exercise insertOrUpdateV1ExerciseLocallyForSync(Exercise exercise);

    boolean markExerciseAsDeletedLocally(long j);

    void updateExerciseRemotely(MfpExercise mfpExercise) throws ApiException;

    boolean updateOwnedExerciseOwnerLocalIdFromOwnerMasterId();
}
