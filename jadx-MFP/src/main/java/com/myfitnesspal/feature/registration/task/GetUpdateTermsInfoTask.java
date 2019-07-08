package com.myfitnesspal.feature.registration.task;

import android.content.Context;
import com.myfitnesspal.feature.registration.model.UpdatedTermsInfo;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.constants.Constants.Uri;
import javax.inject.Provider;

public class GetUpdateTermsInfoTask extends EventedTaskBase<UpdatedTermsInfo, ApiException> {
    private final Provider<MfpV2Api> apiProvider;

    public static class CompletedEvent extends TaskEventBase<UpdatedTermsInfo, ApiException> {
    }

    public GetUpdateTermsInfoTask(Provider<MfpV2Api> provider) {
        super(CompletedEvent.class);
        this.apiProvider = provider;
    }

    /* access modifiers changed from: protected */
    public UpdatedTermsInfo exec(Context context) throws ApiException {
        return (UpdatedTermsInfo) ((MfpV2Api) ((MfpV2Api) this.apiProvider.get()).withOutputType(UpdatedTermsInfo.class)).get(Uri.T_AND_P_UPDATE);
    }
}
