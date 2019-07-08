package com.myfitnesspal.feature.externalsync.service;

import com.myfitnesspal.shared.model.v2.MfpExerciseEntry;

public interface ExternalExerciseService extends ExternalService {
    void onExerciseEntryDeleted(MfpExerciseEntry mfpExerciseEntry, String str);

    void onExerciseEntryInserted(MfpExerciseEntry mfpExerciseEntry, String str);

    void onExerciseEntryUpdated(MfpExerciseEntry mfpExerciseEntry, String str);
}
