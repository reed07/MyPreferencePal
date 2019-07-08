package com.myfitnesspal.shared.service.steps;

import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v15.StepsEntryObject;
import com.myfitnesspal.shared.model.v2.MfpExerciseMetadataForSteps;
import com.myfitnesspal.shared.model.v2.MfpStepSource;
import com.uacf.core.util.Function1;
import java.util.Date;
import java.util.List;

public interface StepService {
    public static final int BUCKET_BY_MINS = 5;

    List<MfpStepSource> checkAndUpdateDeviceIdForClientSideStepSources(List<MfpStepSource> list) throws ApiException;

    List<MfpExerciseMetadataForSteps> fetchStepsEntriesByDateRange(Date date, Date date2);

    MfpExerciseMetadataForSteps fetchStepsEntry(Date date, MfpStepSource mfpStepSource);

    MfpStepSource getPrimaryStepSource();

    MfpStepSource getStepSource(String str);

    List<MfpStepSource> getStepSources();

    List<MfpStepSource> registerClientSideStepSource(MfpStepSource mfpStepSource) throws ApiException;

    List<MfpStepSource> removeClientSideStepSourceFromList(MfpStepSource mfpStepSource, List<MfpStepSource> list);

    void save(StepsEntryObject stepsEntryObject);

    void setPrimaryStepSource(Function1<List<MfpStepSource>> function1, Function1<List<Exception>> function12, MfpStepSource mfpStepSource);

    boolean shouldTrackSteps();

    List<MfpStepSource> unregisterClientSideStepSource(MfpStepSource mfpStepSource) throws ApiException;
}
