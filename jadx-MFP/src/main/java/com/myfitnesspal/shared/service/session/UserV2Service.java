package com.myfitnesspal.shared.service.session;

import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v2.MfpGoalDisplay;
import com.myfitnesspal.shared.model.v2.MfpGoalPreferences;
import com.myfitnesspal.shared.model.v2.MfpProfile;
import com.myfitnesspal.shared.model.v2.MfpStepSource;
import com.myfitnesspal.shared.model.v2.UserV2;
import com.uacf.core.util.Function1;
import com.uacf.sync.provider.sdk.model.SyncItemHandler;
import java.util.List;

public interface UserV2Service extends SyncItemHandler<UserV2> {
    UserV2 fetchFromBackend() throws ApiException;

    UserV2 fetchFromDiskSync();

    void logout();

    UserV2 patchMfpProfile(MfpProfile mfpProfile) throws ApiException;

    void updateGoalDisplaysAsync(Function1<List<MfpGoalDisplay>> function1, Function1<List<Exception>> function12, MfpGoalDisplay mfpGoalDisplay);

    void updateGoalPreferencesAsync(Function1<MfpGoalPreferences> function1, Function1<List<Exception>> function12, MfpGoalPreferences mfpGoalPreferences);

    List<MfpStepSource> updateStepsSources(List<MfpStepSource> list) throws ApiException;

    void updateStepsSourcesAsync(Function1<List<MfpStepSource>> function1, Function1<List<Exception>> function12, List<MfpStepSource> list);

    void writeToDisk(UserV2 userV2, Function1<Boolean> function1);
}
