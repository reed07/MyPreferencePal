package com.myfitnesspal.shared.ui.activity.impl.deeplink.mixin;

import android.content.Context;
import android.os.Bundle;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.home.service.NewsFeedAnalyticsHelper;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.service.userdata.UserPropertiesService;
import com.myfitnesspal.shared.ui.component.MfpUiComponentInterface;
import com.squareup.otto.Subscribe;
import dagger.Lazy;
import javax.inject.Inject;

public class SendVerificationEmailMixin extends DeepLinkMixinBase {
    @Inject
    Lazy<NewsFeedAnalyticsHelper> newsFeedAnalyticsHelper;
    @Inject
    Lazy<UserPropertiesService> userPropertiesService;

    private static class SendVerificationEmailTask extends EventedTaskBase<Boolean, ApiException> {
        private final Lazy<UserPropertiesService> userPropertiesService;

        static class CompletedEvent extends TaskEventBase<Boolean, ApiException> {
        }

        private SendVerificationEmailTask(Lazy<UserPropertiesService> lazy) {
            super((TaskEventBase) new CompletedEvent());
            this.userPropertiesService = lazy;
        }

        /* access modifiers changed from: protected */
        public Boolean exec(Context context) throws ApiException {
            ((UserPropertiesService) this.userPropertiesService.get()).resendEmailVerification();
            return Boolean.valueOf(true);
        }
    }

    public SendVerificationEmailMixin(MfpUiComponentInterface mfpUiComponentInterface) {
        super(mfpUiComponentInterface);
        component().inject(this);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        new SendVerificationEmailTask(this.userPropertiesService).run(getRunner());
    }

    @Subscribe
    public void onSendVerificationEmailTaskCompletedEvent(CompletedEvent completedEvent) {
        int i;
        if (completedEvent.successful()) {
            ((NewsFeedAnalyticsHelper) this.newsFeedAnalyticsHelper.get()).reportResendEmailVerification();
            i = R.string.email_verification_email_has_been_sent;
        } else {
            i = R.string.error_occured;
        }
        getMessageBus().post(new AlertEvent(getActivity().getString(i)).asToast());
        getActivity().finish();
    }
}
