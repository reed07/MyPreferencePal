package com.myfitnesspal.shared.ui.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.ContextThemeWrapper;
import android.view.View;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.framework.taskrunner.TaskRunnerUtil;
import com.myfitnesspal.shared.event.DialogDismissedEvent;
import com.myfitnesspal.shared.injection.component.ApplicationComponent;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.component.MfpUiComponentInterface;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.util.InputMethodManagerHelper;
import com.squareup.otto.Bus;
import com.uacf.core.util.Strings;
import com.uacf.taskrunner.LifecycleDelegate;
import com.uacf.taskrunner.Runner;
import com.uacf.taskrunner.Runner.TaskCallbacks;
import com.uacf.taskrunner.Task;
import dagger.Lazy;
import javax.inject.Inject;

public class CustomLayoutBaseDialogFragment extends AppCompatDialogFragment implements TaskCallbacks {
    @Inject
    protected Lazy<AnalyticsService> analyticsService;
    private ContextThemeWrapper dialogContextThemeWrapper;
    private InputMethodManagerHelper immHelper;
    /* access modifiers changed from: protected */
    @Inject
    public Bus messageBus;
    /* access modifiers changed from: protected */
    @Inject
    public NavigationHelper navigationHelper;
    private LifecycleDelegate runnerDelegate;
    @Inject
    protected Lazy<Session> session;

    /* access modifiers changed from: protected */
    public String getAnalyticTag() {
        return null;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        this.runnerDelegate = new LifecycleDelegate(getActivity(), this, getClass(), null);
        this.runnerDelegate.onCreate(bundle);
    }

    public void onPause() {
        super.onPause();
        this.runnerDelegate.onPause();
    }

    public void onResume() {
        super.onResume();
        this.runnerDelegate.onResume();
    }

    public void onDestroy() {
        super.onDestroy();
        this.runnerDelegate.onDestroy();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.runnerDelegate.onSaveInstanceState(bundle);
    }

    public void onTaskCompleted(String str, long j, Task task, Object obj) {
        TaskRunnerUtil.postSuccess((MfpUiComponentInterface) getActivity(), getRunner(), task, str, j, obj);
    }

    public void onTaskError(String str, long j, Task task, Throwable th) {
        TaskRunnerUtil.postFailure((MfpUiComponentInterface) getActivity(), getRunner(), task, str, j, th);
    }

    public Runner getRunner() {
        LifecycleDelegate lifecycleDelegate = this.runnerDelegate;
        if (lifecycleDelegate != null) {
            return lifecycleDelegate.runner();
        }
        return null;
    }

    public ContextThemeWrapper getDialogContextThemeWrapper() {
        return this.dialogContextThemeWrapper;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.dialogContextThemeWrapper = getActivity();
        createImmHelper(this.dialogContextThemeWrapper);
    }

    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }

    public void onStart() {
        super.onStart();
        String analyticTag = getAnalyticTag();
        if (Strings.notEmpty(analyticTag)) {
            ((AnalyticsService) this.analyticsService.get()).reportEvent(analyticTag);
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        this.messageBus.post(new DialogDismissedEvent(dialogInterface));
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
    public void showSoftInput() {
        this.immHelper.showSoftInput();
    }

    /* access modifiers changed from: protected */
    public void hideSoftInputFor(View view) {
        this.immHelper.hideSoftInput(view);
        this.immHelper.hideSoftInput((Activity) getActivity());
    }

    private void createImmHelper(Context context) {
        if (this.immHelper == null) {
            this.immHelper = new InputMethodManagerHelper(context);
        }
    }

    /* access modifiers changed from: protected */
    public boolean isEnabled() {
        return getActivity() != null;
    }

    /* access modifiers changed from: protected */
    public NavigationHelper getNavigationHelper() {
        return this.navigationHelper.withContext(getActivity());
    }

    /* access modifiers changed from: protected */
    public Session getSession() {
        return (Session) this.session.get();
    }
}
