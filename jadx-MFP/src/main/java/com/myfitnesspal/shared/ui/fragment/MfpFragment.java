package com.myfitnesspal.shared.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.Observable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.drawer.event.ClearDrawerMenuBadgeEvent;
import com.myfitnesspal.framework.mixin.LifecycleMixin.AttachTarget;
import com.myfitnesspal.framework.mixin.RunnerLifecycleMixin;
import com.myfitnesspal.framework.mvvm.BaseViewModel;
import com.myfitnesspal.framework.mvvm.ViewModelCallback;
import com.myfitnesspal.framework.mvvm.ViewModelComponent;
import com.myfitnesspal.framework.taskrunner.TaskRunnerUtil;
import com.myfitnesspal.shared.injection.component.ApplicationComponent;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.activity.BusEventHandler;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.component.MfpUiComponentDelegate;
import com.myfitnesspal.shared.ui.component.MfpUiComponentInterface;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.ui.view.StateAwareScrollView;
import com.myfitnesspal.shared.util.InputMethodManagerHelper;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.uacf.taskrunner.LifecycleDelegate;
import com.uacf.taskrunner.Runner;
import com.uacf.taskrunner.Runner.TaskCallbacks;
import com.uacf.taskrunner.Task;
import dagger.Lazy;
import java.util.List;
import javax.inject.Inject;

public abstract class MfpFragment extends Fragment implements ViewModelCallback, MfpUiComponentInterface, ViewPagerGuest, TaskCallbacks {
    protected static final String EXTRA_EXTRAS = "extras";
    @Inject
    Lazy<BackgroundJobHelper> backgroundServiceHelper;
    private MfpUiComponentDelegate componentDelegate = new MfpUiComponentDelegate(this);
    @Inject
    Glide glide;
    private Handler handler = new Handler();
    private LifecycleDelegate runnerDelegate;

    private static class BusEventHelper implements BusEventHandler {
        private final MfpFragment parent;

        public BusEventHelper(MfpFragment mfpFragment) {
            this.parent = mfpFragment;
        }

        @Subscribe
        public void onClearDrawerMenuBadgeEvent(ClearDrawerMenuBadgeEvent clearDrawerMenuBadgeEvent) {
            ((BackgroundJobHelper) this.parent.backgroundServiceHelper.get()).updateInAppNotifications();
        }
    }

    public String getAnalyticsScreenTag() {
        return null;
    }

    public ViewModelCallback getViewModelCallback() {
        return this;
    }

    public void onChildViewModelReset(ViewModelComponent viewModelComponent) {
    }

    public void onPageHidden() {
    }

    public void onPageVisible() {
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        return false;
    }

    public void onViewModelChanged(BaseViewModel baseViewModel, BaseViewModel baseViewModel2) {
    }

    public void onViewModelPropertyChanged(Observable observable, int i) {
    }

    public void onViewModelReset() {
    }

    public void onViewModelRestored(BaseViewModel baseViewModel) {
    }

    @Nullable
    public /* bridge */ /* synthetic */ Activity getActivity() {
        return super.getActivity();
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        this.runnerDelegate = new LifecycleDelegate(getActivity(), this, getClass(), null);
        this.runnerDelegate.onCreate(bundle);
        this.componentDelegate.create(bundle);
        this.handler.post(new Runnable(bundle) {
            private final /* synthetic */ Bundle f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                MfpFragment.this.onPostCreate(this.f$1);
            }
        });
    }

    public void onPostCreate(Bundle bundle) {
        this.componentDelegate.postCreate(bundle);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        ButterKnife.bind((Object) this, view);
        StateAwareScrollView.loadScrollState(getView(), bundle);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.componentDelegate.activityResult(i, i2, intent);
    }

    public void onResume() {
        super.onResume();
        registerAllChildrenForBusEvents(true);
        this.componentDelegate.resume();
        this.runnerDelegate.onResume();
        this.handler.post(new Runnable() {
            public final void run() {
                MfpFragment.lambda$onResume$1(MfpFragment.this);
            }
        });
    }

    public static /* synthetic */ void lambda$onResume$1(MfpFragment mfpFragment) {
        if (mfpFragment.isResumed()) {
            mfpFragment.onPostResume();
        }
    }

    public void onPostResume() {
        this.componentDelegate.postResume();
    }

    public void onPause() {
        super.onPause();
        this.runnerDelegate.onPause();
        this.componentDelegate.pause();
        registerAllChildrenForBusEvents(false);
    }

    public void onDestroy() {
        super.onDestroy();
        this.runnerDelegate.onDestroy();
        this.componentDelegate.destroy();
    }

    public void onStart() {
        super.onStart();
        this.componentDelegate.start();
    }

    public void onStop() {
        super.onStop();
        this.componentDelegate.stop();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.componentDelegate.attachedToWindow();
    }

    public void onDetach() {
        super.onDetach();
        this.componentDelegate.detachedFromWindow();
    }

    public boolean isEnabled() {
        return getActivity() != null && this.componentDelegate.isEnabled();
    }

    public AttachTarget getAttachTarget() {
        return AttachTarget.FragmentManager;
    }

    public <T extends RunnerLifecycleMixin> void unregisterMixin(Class<T> cls) {
        this.componentDelegate.unregisterMixin(cls);
    }

    public void registerMixin(RunnerLifecycleMixin... runnerLifecycleMixinArr) {
        this.componentDelegate.registerMixin(runnerLifecycleMixinArr);
    }

    public <T extends RunnerLifecycleMixin> T mixin(Class<T> cls) {
        return this.componentDelegate.mixin(cls);
    }

    public NavigationHelper getNavigationHelper() {
        return this.componentDelegate.getNavigationHelper();
    }

    public Bus getMessageBus() {
        return this.componentDelegate.getMessageBus();
    }

    public Session getSession() {
        return this.componentDelegate.getSession();
    }

    public void postEvent(Object obj) {
        this.componentDelegate.postEvent(obj);
    }

    public void postEventAfterResume(Object obj) {
        this.componentDelegate.postEventAfterResume(obj);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        StateAwareScrollView.saveScrollState(getView(), bundle);
        this.runnerDelegate.onSaveInstanceState(bundle);
        this.componentDelegate.saveInstanceState(bundle);
    }

    public ConfigService getConfigService() {
        return this.componentDelegate.getConfigService();
    }

    public CountryService getCountryService() {
        return this.componentDelegate.getCountryService();
    }

    public InputMethodManagerHelper getImmHelper() {
        return this.componentDelegate.getImmHelper();
    }

    public boolean isBusy() {
        return this.componentDelegate.isBusy();
    }

    public boolean isBusy(int i) {
        return this.componentDelegate.isBusy(i);
    }

    public void setBusy(boolean z) {
        this.componentDelegate.setBusy(z);
    }

    public void setBusy(int i, boolean z) {
        this.componentDelegate.setBusy(i, z);
    }

    public boolean hasResumed() {
        return this.componentDelegate.hasResumed();
    }

    public AnalyticsService getAnalyticsService() {
        return this.componentDelegate.getAnalyticsService();
    }

    public void showDialogFragment(DialogFragment dialogFragment, String str) {
        this.componentDelegate.showDialogFragment(dialogFragment, str);
    }

    public Runner getRunner() {
        LifecycleDelegate lifecycleDelegate = this.runnerDelegate;
        if (lifecycleDelegate != null) {
            return lifecycleDelegate.runner();
        }
        return null;
    }

    public void onTaskCompleted(String str, long j, Task task, Object obj) {
        TaskRunnerUtil.postSuccess(this, getRunner(), task, str, j, obj);
    }

    public void onTaskError(String str, long j, Task task, Throwable th) {
        TaskRunnerUtil.postFailure(this, getRunner(), task, str, j, th);
    }

    public <T extends BaseViewModel> T getViewModel() {
        return this.componentDelegate.getViewModel();
    }

    public <T extends BaseViewModel> T setViewModel(T t) {
        return this.componentDelegate.setViewModel(t);
    }

    public void rebindView() {
        this.componentDelegate.rebindView();
    }

    public Glide getGlide() {
        return this.glide;
    }

    public void addBusEventHandlers(List<BusEventHandler> list) {
        list.add(new BusEventHelper(this));
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

    protected static <T extends Fragment> String tag(Class<T> cls) {
        return cls.getCanonicalName();
    }

    protected static String tag(Fragment fragment) {
        return fragment.getClass().getCanonicalName();
    }

    private void registerAllChildrenForBusEvents(boolean z) {
        this.componentDelegate.registerChildForBusEvents(this, z);
        this.componentDelegate.registerAllChildrenForBusEvents(z);
    }

    /* access modifiers changed from: protected */
    public Toolbar getToolbar() {
        if (getActivity() instanceof MfpActivity) {
            return ((MfpActivity) getActivity()).getToolbar();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void invalidateOptionsMenu() {
        if (hasResumed()) {
            getActivity().supportInvalidateOptionsMenu();
        }
    }

    /* access modifiers changed from: protected */
    public void setTitle(int i, Object... objArr) {
        getActivity().setTitle(getString(i, objArr));
    }
}
