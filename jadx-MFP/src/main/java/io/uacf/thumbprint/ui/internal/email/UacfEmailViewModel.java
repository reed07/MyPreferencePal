package io.uacf.thumbprint.ui.internal.email;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import com.uacf.core.tasks.ExecutorType;
import com.uacf.core.tasks.PollingStrategy;
import com.uacf.core.tasks.Result;
import com.uacf.core.tasks.Task;
import com.uacf.core.tasks.Task.Observer;
import com.uacf.core.tasks.Task.Status;
import com.uacf.core.tasks.TaskDisposer;
import com.uacf.identity.sdk.UacfIdentitySdk;
import com.uacf.identity.sdk.model.UacfProfileEmail;
import com.uacf.identity.sdk.model.UacfProfileEmails;
import io.uacf.core.interfaces.UacfClientEventsCallback;
import io.uacf.thumbprint.ui.R;
import io.uacf.thumbprint.ui.internal.analytics.ThumbprintAnalyticsAttributes.LinkClicked;
import io.uacf.thumbprint.ui.internal.analytics.ThumbprintAnalyticsAttributes.LinkClicked.LinkName;
import io.uacf.thumbprint.ui.internal.analytics.ThumbprintAnalyticsAttributes.ScreenClosed;
import io.uacf.thumbprint.ui.internal.analytics.ThumbprintAnalyticsAttributes.ScreenClosed.Action;
import io.uacf.thumbprint.ui.internal.analytics.ThumbprintAnalyticsAttributes.ScreenName;
import io.uacf.thumbprint.ui.internal.analytics.ThumbprintAnalyticsAttributes.ScreenViewed;
import io.uacf.thumbprint.ui.internal.analytics.ThumbprintAnalyticsAttributes.ScreenViewed.Source;
import io.uacf.thumbprint.ui.internal.base.SingleLiveEvent;
import io.uacf.thumbprint.ui.internal.base.UacfBaseViewModel;
import io.uacf.thumbprint.ui.internal.email.UacfEmailVerificationActivity.Actions;
import io.uacf.thumbprint.ui.sdk.UacfThumbprintUiSdkImpl;
import io.uacf.thumbprint.ui.sdk.UacfThumbprintUiSdkManager;
import java.util.concurrent.TimeUnit;

public final class UacfEmailViewModel extends UacfBaseViewModel {
    private Actions actions = ((UacfThumbprintUiSdkImpl) UacfThumbprintUiSdkManager.getInstance()).getEmailVerificationActions();
    private UacfClientEventsCallback clientEventsCallback = ((UacfThumbprintUiSdkImpl) UacfThumbprintUiSdkManager.getInstance()).getClientEventsCallback();
    MutableLiveData<String> currentEmail;
    MutableLiveData<State> currentState;
    private UacfIdentitySdk identitySdk = ((UacfThumbprintUiSdkImpl) UacfThumbprintUiSdkManager.getInstance()).getIdentitySdk();
    /* access modifiers changed from: private */
    public Task pollingTask;
    /* access modifiers changed from: private */
    public long sentVerificationEmailTimestamp;
    SingleLiveEvent<String> showBottomBarMessage;
    private TaskDisposer taskDisposer = new TaskDisposer();

    /* renamed from: io.uacf.thumbprint.ui.internal.email.UacfEmailViewModel$3 reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$uacf$core$tasks$Task$Status = new int[Status.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.uacf.core.tasks.Task$Status[] r0 = com.uacf.core.tasks.Task.Status.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$uacf$core$tasks$Task$Status = r0
                int[] r0 = $SwitchMap$com$uacf$core$tasks$Task$Status     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.uacf.core.tasks.Task$Status r1 = com.uacf.core.tasks.Task.Status.SUCCESS     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$uacf$core$tasks$Task$Status     // Catch:{ NoSuchFieldError -> 0x001f }
                com.uacf.core.tasks.Task$Status r1 = com.uacf.core.tasks.Task.Status.ERROR     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.uacf.thumbprint.ui.internal.email.UacfEmailViewModel.AnonymousClass3.<clinit>():void");
        }
    }

    private class EmailVerificationPollingStrategy extends PollingStrategy {
        /* access modifiers changed from: protected */
        public int getMax() {
            return 50;
        }

        private EmailVerificationPollingStrategy() {
        }

        /* access modifiers changed from: protected */
        public long getNextInterval() {
            return TimeUnit.SECONDS.toMillis(5);
        }
    }

    public enum State {
        EMAIL_UNVERIFIED,
        EMAIL_VERIFIED
    }

    public UacfEmailViewModel(@NonNull Application application) {
        super(application);
        init();
    }

    private void init() {
        this.currentState = new MutableLiveData<>();
        this.currentState.setValue(State.EMAIL_UNVERIFIED);
        this.currentEmail = new MutableLiveData<>();
        this.currentEmail.setValue(getPrimaryEmail(this.identitySdk.getCachedCurrentUser().getProfileEmails()).getEmail());
        this.showBottomBarMessage = new SingleLiveEvent<>();
    }

    public void sendVerificationEmailClicked() {
        reportLinkClickedEvent(LinkName.RESEND);
        if (System.currentTimeMillis() - this.sentVerificationEmailTimestamp > TimeUnit.SECONDS.toMillis(10)) {
            sendVerificationEmail();
        }
        this.showBottomBarMessage.setValue(getString(R.string.emailVerification_verificationEmailSent));
    }

    public void sendVerificationEmail() {
        this.taskDisposer.add(Task.fromCallable(new SendVerificationEmailCallable((String) this.currentEmail.getValue())).executeOn(ExecutorType.BACKGROUND_THREAD).observeOn(ExecutorType.UI_THREAD).observe(new Observer<Boolean>() {
            public void result(Result<Boolean> result) {
                switch (AnonymousClass3.$SwitchMap$com$uacf$core$tasks$Task$Status[result.getStatus().ordinal()]) {
                    case 1:
                        UacfEmailViewModel.this.sentVerificationEmailTimestamp = System.currentTimeMillis();
                        return;
                    case 2:
                        Log.e("UacfEmailViewModel", "request failed", result.getException());
                        return;
                    default:
                        return;
                }
            }
        }));
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        this.taskDisposer.dispose();
        super.onCleared();
    }

    public void startPolling() {
        if (this.currentState.getValue() != State.EMAIL_VERIFIED) {
            this.pollingTask = Task.fromCallable(new RefreshEmailVerificationStatusCallable()).pollingStrategy(new EmailVerificationPollingStrategy()).executeOn(ExecutorType.BACKGROUND_THREAD).observeOn(ExecutorType.UI_THREAD).observe(new Observer<EmailVerificationStatus>() {
                public void result(Result<EmailVerificationStatus> result) {
                    switch (AnonymousClass3.$SwitchMap$com$uacf$core$tasks$Task$Status[result.getStatus().ordinal()]) {
                        case 1:
                            if (result.getData() != null && ((EmailVerificationStatus) result.getData()).getUser() != null && ((EmailVerificationStatus) result.getData()).getUser().getProfileEmails() != null) {
                                UacfProfileEmail access$100 = UacfEmailViewModel.this.getPrimaryEmail(((EmailVerificationStatus) result.getData()).getUser().getProfileEmails());
                                if (access$100 != null) {
                                    UacfEmailViewModel.this.currentEmail.setValue(access$100.getEmail());
                                }
                                if (((EmailVerificationStatus) result.getData()).isVerified()) {
                                    UacfEmailViewModel.this.pollingTask.cancel();
                                }
                                UacfEmailViewModel.this.currentState.setValue(((EmailVerificationStatus) result.getData()).isVerified() ? State.EMAIL_VERIFIED : State.EMAIL_UNVERIFIED);
                                return;
                            }
                            return;
                        case 2:
                            Log.e("UacfEmailViewModel", "request failed", result.getException());
                            return;
                        default:
                            return;
                    }
                }
            });
            this.taskDisposer.add(this.pollingTask);
        }
    }

    public void stopPolling() {
        this.pollingTask.cancel();
    }

    /* access modifiers changed from: private */
    public UacfProfileEmail getPrimaryEmail(UacfProfileEmails uacfProfileEmails) {
        for (UacfProfileEmail uacfProfileEmail : uacfProfileEmails.getEmails()) {
            if (uacfProfileEmail.isPrimary()) {
                return uacfProfileEmail;
            }
        }
        return null;
    }

    public LiveData<State> getCurrentState() {
        return this.currentState;
    }

    public LiveData<String> getCurrentEmail() {
        return this.currentEmail;
    }

    public LiveData<String> getShowBottomBarMessage() {
        return this.showBottomBarMessage;
    }

    public void reportEmailVerificationScreenClosedEvent(Action action) {
        UacfClientEventsCallback uacfClientEventsCallback = this.clientEventsCallback;
        if (uacfClientEventsCallback != null) {
            uacfClientEventsCallback.reportEvent("screen_closed", new ScreenClosed(ScreenName.VERIFY_EMAIL, action));
        }
    }

    public void reportEmailVerificationScreenViewedEvent(Source source) {
        UacfClientEventsCallback uacfClientEventsCallback = this.clientEventsCallback;
        if (uacfClientEventsCallback != null) {
            uacfClientEventsCallback.reportEvent("screen_viewed", new ScreenViewed(ScreenName.VERIFY_EMAIL, source));
        }
    }

    public void reportLinkClickedEvent(LinkName linkName) {
        UacfClientEventsCallback uacfClientEventsCallback = this.clientEventsCallback;
        if (uacfClientEventsCallback != null) {
            uacfClientEventsCallback.reportEvent("link_clicked", new LinkClicked(ScreenName.VERIFY_EMAIL, linkName));
        }
    }

    public void editEmailClicked() {
        if (this.actions != null) {
            reportLinkClickedEvent(LinkName.EDIT);
            this.actions.editEmailClicked();
        }
    }
}
