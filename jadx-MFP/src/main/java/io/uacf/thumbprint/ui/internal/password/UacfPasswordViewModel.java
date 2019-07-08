package io.uacf.thumbprint.ui.internal.password;

import android.app.Application;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import com.uacf.core.tasks.ExecutorType;
import com.uacf.core.tasks.Result;
import com.uacf.core.tasks.Task;
import com.uacf.core.tasks.Task.Observer;
import com.uacf.core.tasks.Task.Status;
import com.uacf.core.tasks.TaskDisposer;
import io.uacf.core.interfaces.UacfClientEventsCallback;
import io.uacf.thumbprint.ui.internal.analytics.ThumbprintAnalyticsAttributes.PasswordChangeAttempted;
import io.uacf.thumbprint.ui.internal.analytics.ThumbprintAnalyticsAttributes.ScreenName;
import io.uacf.thumbprint.ui.internal.analytics.ThumbprintAnalyticsAttributes.ScreenViewed;
import io.uacf.thumbprint.ui.internal.base.SingleLiveEvent;
import io.uacf.thumbprint.ui.internal.base.UacfBaseViewModel;
import io.uacf.thumbprint.ui.sdk.UacfThumbprintUiSdkImpl;
import io.uacf.thumbprint.ui.sdk.UacfThumbprintUiSdkManager;

public class UacfPasswordViewModel extends UacfBaseViewModel {
    private SingleLiveEvent<Boolean> changePasswordEnabled = new SingleLiveEvent<>();
    private UacfClientEventsCallback clientEventsCallback = ((UacfThumbprintUiSdkImpl) UacfThumbprintUiSdkManager.getInstance()).getClientEventsCallback();
    /* access modifiers changed from: private */
    public MutableLiveData<ChangePasswordState> currentState = new MutableLiveData<>();
    private String newPassword = "";
    private boolean newPasswordEntered = false;
    private SingleLiveEvent<Boolean> passwordLengthError = new SingleLiveEvent<>();
    private SingleLiveEvent<Boolean> passwordsDontMatchError = new SingleLiveEvent<>();
    private String retypePassword = "";
    private TaskDisposer taskDisposer = new TaskDisposer();

    /* renamed from: io.uacf.thumbprint.ui.internal.password.UacfPasswordViewModel$2 reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
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
                com.uacf.core.tasks.Task$Status r1 = com.uacf.core.tasks.Task.Status.RUNNING     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$uacf$core$tasks$Task$Status     // Catch:{ NoSuchFieldError -> 0x001f }
                com.uacf.core.tasks.Task$Status r1 = com.uacf.core.tasks.Task.Status.SUCCESS     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.uacf.thumbprint.ui.internal.password.UacfPasswordViewModel.AnonymousClass2.<clinit>():void");
        }
    }

    enum ChangePasswordState {
        FAILED,
        SUCCESS,
        NETWORK_ERROR,
        LOADING
    }

    public UacfPasswordViewModel(@NonNull Application application) {
        super(application);
    }

    public void changePasswordClicked() {
        if (!isInternetConnectionAvailible()) {
            this.currentState.setValue(ChangePasswordState.NETWORK_ERROR);
            return;
        }
        if (UacfPasswordValidator.isValidPassword(this.newPassword)) {
            this.taskDisposer.add(Task.fromCallable(new ChangePasswordCallable(this.newPassword)).executeOn(ExecutorType.BACKGROUND_THREAD).observeOn(ExecutorType.UI_THREAD).observe(new Observer<Boolean>() {
                public void result(Result<Boolean> result) {
                    switch (AnonymousClass2.$SwitchMap$com$uacf$core$tasks$Task$Status[result.getStatus().ordinal()]) {
                        case 1:
                            UacfPasswordViewModel.this.currentState.setValue(ChangePasswordState.LOADING);
                            return;
                        case 2:
                            UacfPasswordViewModel.this.currentState.setValue(ChangePasswordState.SUCCESS);
                            UacfPasswordViewModel.this.reportPasswordChangeAttemptedEvent(true);
                            return;
                        default:
                            Log.e("PasswordViewModel", "request failed", result.getException());
                            UacfPasswordViewModel.this.currentState.setValue(ChangePasswordState.FAILED);
                            UacfPasswordViewModel.this.reportPasswordChangeAttemptedEvent(false);
                            return;
                    }
                }
            }));
        }
    }

    public void observeCurrentState(LifecycleOwner lifecycleOwner, android.arch.lifecycle.Observer<ChangePasswordState> observer) {
        this.currentState.observe(lifecycleOwner, observer);
    }

    public void observePasswordLengthError(LifecycleOwner lifecycleOwner, android.arch.lifecycle.Observer<Boolean> observer) {
        this.passwordLengthError.observe(lifecycleOwner, observer);
    }

    public void observePasswordsDontMatchError(LifecycleOwner lifecycleOwner, android.arch.lifecycle.Observer<Boolean> observer) {
        this.passwordsDontMatchError.observe(lifecycleOwner, observer);
    }

    public void observeChangePasswordEnabled(LifecycleOwner lifecycleOwner, android.arch.lifecycle.Observer<Boolean> observer) {
        this.changePasswordEnabled.observe(lifecycleOwner, observer);
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        super.onCleared();
        this.taskDisposer.dispose();
    }

    public void setNewPassword(String str) {
        this.newPassword = str;
        checkPasswordLengthError();
        checkPasswordsMatchError();
        setChangePasswordButtonEnabled();
    }

    public void setRetypePassword(String str) {
        this.retypePassword = str;
        checkPasswordsMatchError();
        setChangePasswordButtonEnabled();
    }

    public void setNewPasswordEntered() {
        this.newPasswordEntered = true;
        checkPasswordLengthError();
        checkPasswordsMatchError();
        setChangePasswordButtonEnabled();
    }

    public void reportPasswordChangeScreenViewedEvent(long j) {
        UacfClientEventsCallback uacfClientEventsCallback = this.clientEventsCallback;
        if (uacfClientEventsCallback != null) {
            uacfClientEventsCallback.reportEvent("screen_viewed", new ScreenViewed(ScreenName.PASSWORD_CHANGE, j));
        }
    }

    public void reportPasswordChangeAttemptedEvent(boolean z) {
        UacfClientEventsCallback uacfClientEventsCallback = this.clientEventsCallback;
        if (uacfClientEventsCallback != null) {
            uacfClientEventsCallback.reportEvent("password_change_attempted", new PasswordChangeAttempted(z));
        }
    }

    private void setChangePasswordButtonEnabled() {
        this.changePasswordEnabled.setValue(Boolean.valueOf(UacfPasswordValidator.isValidPassword(this.newPassword) && this.newPassword.equals(this.retypePassword)));
    }

    private void checkPasswordLengthError() {
        this.passwordLengthError.setValue(Boolean.valueOf(!UacfPasswordValidator.meetsLengthRequirement(this.newPassword) && this.newPasswordEntered));
    }

    private void checkPasswordsMatchError() {
        this.passwordsDontMatchError.setValue(Boolean.valueOf(!this.retypePassword.isEmpty() && !this.retypePassword.equals(this.newPassword)));
    }
}
