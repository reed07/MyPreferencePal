package com.myfitnesspal.framework.mixin;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.shared.injection.component.ApplicationComponent;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.component.MfpUiComponentInterface;
import com.squareup.otto.Bus;
import com.uacf.taskrunner.Runner;

public abstract class RunnerLifecycleMixin extends BaseLifecycleMixin {
    private MfpUiComponentInterface owner;

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        return false;
    }

    public RunnerLifecycleMixin(MfpUiComponentInterface mfpUiComponentInterface) {
        this.owner = mfpUiComponentInterface;
    }

    public void onPause() {
        getMessageBus().unregister(this);
        super.onPause();
    }

    public void onResume() {
        getMessageBus().register(this);
        super.onResume();
    }

    /* access modifiers changed from: protected */
    @NonNull
    public MyFitnessPalApp application() {
        return MyFitnessPalApp.getInstance();
    }

    /* access modifiers changed from: protected */
    @NonNull
    public ApplicationComponent component() {
        return application().component();
    }

    /* access modifiers changed from: protected */
    public final Runner getRunner() {
        return this.owner.getRunner();
    }

    /* access modifiers changed from: protected */
    public final MfpUiComponentInterface getComponentInterface() {
        return this.owner;
    }

    /* access modifiers changed from: protected */
    public final Activity getActivity() {
        return this.owner.getActivity();
    }

    /* access modifiers changed from: protected */
    public final MfpActivity getMfpActivity() {
        return (MfpActivity) this.owner.getActivity();
    }

    /* access modifiers changed from: protected */
    public final Bus getMessageBus() {
        return this.owner.getMessageBus();
    }
}
