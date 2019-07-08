package com.myfitnesspal.shared.ui.component;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.Observable;
import android.databinding.Observable.OnPropertyChangedCallback;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.diary.ui.activity.Diary;
import com.myfitnesspal.framework.mixin.LifecycleMixin;
import com.myfitnesspal.framework.mixin.LifecycleMixin.AttachTarget;
import com.myfitnesspal.framework.mixin.MixinContainer;
import com.myfitnesspal.framework.mixin.RunnerLifecycleMixin;
import com.myfitnesspal.framework.mvvm.BaseViewModel;
import com.myfitnesspal.framework.mvvm.ViewModelCache;
import com.myfitnesspal.framework.mvvm.ViewModelCallback;
import com.myfitnesspal.framework.mvvm.ViewModelComponent;
import com.myfitnesspal.framework.mvvm.ViewModelLifecycleDelegate;
import com.myfitnesspal.shared.event.NewStatusPostedEvent;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.activity.BusEventHandler;
import com.myfitnesspal.shared.ui.activity.busymanager.BusyManager;
import com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.util.InputMethodManagerHelper;
import com.squareup.otto.Bus;
import com.uacf.core.exception.UacfNotImplementedException;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Function1;
import com.uacf.taskrunner.Runner;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;

public class MfpUiComponentDelegate implements MfpUiComponentInterface {
    private static Handler sHandler;
    @Inject
    protected Lazy<AnalyticsService> analyticsService;
    @Inject
    protected Lazy<BusyManager> busyManager;
    @Inject
    protected Lazy<ConfigService> configService;
    private Set<String> dialogFragmentTags = new HashSet();
    private ArrayList<BusEventHandler> handlers;
    private InputMethodManagerHelper immHelper;
    private boolean isInstanceStateSaved;
    private boolean isResumed;
    @Inject
    protected Lazy<CountryService> lazyCountryService;
    @Inject
    protected Lazy<Bus> messageBus;
    private final MixinContainer mixinContainer;
    @Inject
    protected Lazy<NavigationHelper> navigationHelper;
    private OnPropertyChangedCallback onViewModelChangedCallback = new OnPropertyChangedCallback() {
        public void onPropertyChanged(Observable observable, int i) {
            ViewModelCallback viewModelCallback = MfpUiComponentDelegate.this.getViewModelCallback();
            BaseViewModel viewModel = MfpUiComponentDelegate.this.viewModelDelegate.getViewModel();
            if (viewModelCallback != null && observable == viewModel) {
                viewModelCallback.onViewModelPropertyChanged(observable, i);
            }
        }
    };
    private final MfpUiComponentInterface owner;
    private List<Object> pendingEvents = new ArrayList();
    @Inject
    protected Lazy<Session> session;
    @Inject
    protected Lazy<ViewModelCache> viewModelCache;
    /* access modifiers changed from: private */
    public final ViewModelLifecycleDelegate viewModelDelegate;

    public void addBusEventHandlers(List<BusEventHandler> list) {
    }

    public void onChildViewModelReset(ViewModelComponent viewModelComponent) {
    }

    public MfpUiComponentDelegate(MfpUiComponentInterface mfpUiComponentInterface) {
        this.owner = mfpUiComponentInterface;
        this.viewModelDelegate = new ViewModelLifecycleDelegate(mfpUiComponentInterface, this);
        this.mixinContainer = new MixinContainer();
        this.mixinContainer.register(new LegacyAlertMixin(this));
        MyFitnessPalApp.getInstance().component().inject(this);
    }

    public Activity getActivity() {
        return this.owner.getActivity();
    }

    public void attachedToWindow() {
        this.mixinContainer.onAttached(getAttachTarget());
    }

    public void create(Bundle bundle) {
        if (this.handlers == null) {
            this.handlers = new ArrayList<>();
            addBusEventHandlers(this.handlers);
            this.owner.addBusEventHandlers(this.handlers);
        }
        if (sHandler == null) {
            sHandler = new Handler();
        }
        this.mixinContainer.onCreate(bundle);
        readDialogFragmentState(bundle);
        reloadViewModel(bundle);
    }

    public void postCreate(Bundle bundle) {
        this.mixinContainer.onPostCreate(bundle);
    }

    public void start() {
        this.mixinContainer.onStart();
    }

    public void resume() {
        this.viewModelDelegate.onResume();
        this.mixinContainer.onResume();
        this.isInstanceStateSaved = false;
        getMessageBus().register(this);
        this.isResumed = true;
    }

    public void postResume() {
        deliverPendingEventsIfPossible();
    }

    public void pause() {
        this.isResumed = false;
        getMessageBus().unregister(this);
        this.mixinContainer.onPause();
        this.viewModelDelegate.onPause();
    }

    public void stop() {
        this.mixinContainer.onStop();
    }

    public void saveInstanceState(Bundle bundle) {
        writeDialogFragmentState(bundle);
        this.viewModelDelegate.onSaveInstanceState(bundle);
        this.mixinContainer.onSaveInstanceState(bundle);
        this.isInstanceStateSaved = true;
    }

    public void detachedFromWindow() {
        this.mixinContainer.onDetached(getAttachTarget());
    }

    public void destroy() {
        this.mixinContainer.onDestroy();
        this.viewModelDelegate.onDestroy(getRunner(), (ViewModelCache) this.viewModelCache.get());
    }

    public void configurationChanged(Configuration configuration) {
        this.mixinContainer.onConfigurationChanged(configuration);
    }

    public boolean isBusy() {
        return getBusyManager().isBusy();
    }

    public boolean isBusy(int i) {
        return getBusyManager().isBusy(i);
    }

    public void setBusy(boolean z) {
        getBusyManager().setBusy(z);
    }

    public void setBusy(int i, boolean z) {
        getBusyManager().setBusy(i, z);
    }

    private BusyManager getBusyManager() {
        return ((BusyManager) this.busyManager.get()).setContext(getActivity());
    }

    public AnalyticsService getAnalyticsService() {
        return (AnalyticsService) this.analyticsService.get();
    }

    public Bus getMessageBus() {
        return (Bus) this.messageBus.get();
    }

    public Session getSession() {
        return (Session) this.session.get();
    }

    public void postEvent(Object obj) {
        getMessageBus().post(obj);
    }

    public void postEventAfterResume(Object obj) {
        if (hasResumed()) {
            postEvent(obj);
        } else {
            this.pendingEvents.add(obj);
        }
    }

    public ConfigService getConfigService() {
        return (ConfigService) this.configService.get();
    }

    public CountryService getCountryService() {
        return (CountryService) this.lazyCountryService.get();
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        return this.owner.onRebindDialogFragment(dialogFragment, str);
    }

    public void reportEvent(String str) {
        getAnalyticsService().reportEvent(str);
    }

    public void reportEvent(String str, Map<String, String> map) {
        getAnalyticsService().reportEvent(str, map);
    }

    public NavigationHelper getNavigationHelper() {
        return ((NavigationHelper) this.navigationHelper.get()).withContext(getActivity());
    }

    public void activityResult(int i, int i2, Intent intent) {
        this.mixinContainer.onActivityResult(i, i2, intent);
        if (i != 32 && i != 43) {
            if (!(i == 105 || i == 111)) {
                switch (i) {
                    case 34:
                    case 35:
                        break;
                    default:
                        return;
                }
            }
            if (i2 == -1) {
                postEventAfterResume(new NewStatusPostedEvent());
            }
        } else if (i2 == -1) {
            getNavigationHelper().withIntent(Diary.newStartIntentWithReferrer(getActivity(), "navigation")).withFlags(67108864, 0).finishActivityAfterNavigation().startActivity();
        }
    }

    public boolean hasResumed() {
        return this.isResumed;
    }

    public boolean isEnabled() {
        return hasResumed();
    }

    public void showDialogFragment(DialogFragment dialogFragment, String str) {
        if (!this.isInstanceStateSaved && isEnabled() && !this.isInstanceStateSaved) {
            Activity activity = getActivity();
            if (activity instanceof FragmentActivity) {
                this.dialogFragmentTags.add(str);
                FragmentManager supportFragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
                if (supportFragmentManager.findFragmentByTag(str) == null) {
                    dialogFragment.show(supportFragmentManager, str);
                }
            }
        }
    }

    public Set<String> getDialogFragmentTags() {
        return this.dialogFragmentTags;
    }

    public InputMethodManagerHelper getImmHelper() {
        if (this.immHelper == null) {
            this.immHelper = new InputMethodManagerHelper(getActivity());
        }
        return this.immHelper;
    }

    public <T extends BaseViewModel> T getViewModel() {
        return this.viewModelDelegate.getViewModel();
    }

    public <T extends BaseViewModel> T setViewModel(T t) {
        return this.viewModelDelegate.setViewModel(t, this.onViewModelChangedCallback, getRunner());
    }

    public void rebindView() {
        BaseViewModel viewModel = this.viewModelDelegate.getViewModel();
        if (viewModel != null) {
            viewModel.onRebindView();
        }
    }

    public void onViewModelReset() {
        throw new UacfNotImplementedException();
    }

    public void onViewModelRestored(BaseViewModel baseViewModel) {
        throw new UacfNotImplementedException();
    }

    public void onViewModelChanged(BaseViewModel baseViewModel, BaseViewModel baseViewModel2) {
        throw new UacfNotImplementedException();
    }

    public ViewModelCallback getViewModelCallback() {
        return this.owner.getViewModelCallback();
    }

    public void registerMixin(RunnerLifecycleMixin... runnerLifecycleMixinArr) {
        this.mixinContainer.register((LifecycleMixin[]) runnerLifecycleMixinArr);
    }

    public <T extends RunnerLifecycleMixin> void unregisterMixin(Class<T> cls) {
        this.mixinContainer.unregister(cls);
    }

    public <T extends RunnerLifecycleMixin> T mixin(Class<T> cls) {
        return (RunnerLifecycleMixin) this.mixinContainer.get(cls);
    }

    public AttachTarget getAttachTarget() {
        return this.owner.getAttachTarget();
    }

    public Runner getRunner() {
        return this.owner.getRunner();
    }

    public final void registerChildForBusEvents(Object obj, boolean z) {
        if (obj != null) {
            Bus messageBus2 = getMessageBus();
            if (messageBus2 != null) {
                if (z) {
                    messageBus2.register(obj);
                } else {
                    messageBus2.unregister(obj);
                }
            }
        }
    }

    public void registerAllChildrenForBusEvents(boolean z) {
        Enumerable.forEach((Collection<T>) this.handlers, (Function1<T>) new Function1(z) {
            private final /* synthetic */ boolean f$1;

            {
                this.f$1 = r2;
            }

            public final void execute(Object obj) {
                MfpUiComponentDelegate.this.registerChildForBusEvents((BusEventHandler) obj, this.f$1);
            }
        });
        registerChildForBusEvents(this, z);
        if (z) {
            deliverPendingEventsIfPossible();
        }
    }

    private void deliverPendingEventsIfPossible() {
        if (this.isResumed && getMessageBus() != null) {
            Enumerable.forEach((Collection<T>) this.pendingEvents, (Function1<T>) new Function1() {
                public final void execute(Object obj) {
                    MfpUiComponentDelegate.this.postEvent(obj);
                }
            });
            this.pendingEvents.clear();
        }
    }

    private String getExtraDialogFragmentTags() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append("_dialog_fragment_tags");
        return sb.toString();
    }

    /* access modifiers changed from: private */
    public void rebindDialogListeners() {
        if (CollectionUtils.notEmpty((Collection<?>) this.dialogFragmentTags)) {
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            if (supportFragmentManager != null) {
                for (String str : this.dialogFragmentTags) {
                    DialogFragment dialogFragment = (DialogFragment) supportFragmentManager.findFragmentByTag(str);
                    if (dialogFragment != null) {
                        onRebindDialogFragment(dialogFragment, str);
                    }
                }
            }
        }
    }

    private void writeDialogFragmentState(Bundle bundle) {
        if (CollectionUtils.notEmpty((Collection<?>) this.dialogFragmentTags)) {
            String extraDialogFragmentTags = getExtraDialogFragmentTags();
            Set<String> set = this.dialogFragmentTags;
            bundle.putStringArray(extraDialogFragmentTags, (String[]) set.toArray(new String[set.size()]));
        }
    }

    private void readDialogFragmentState(Bundle bundle) {
        if (bundle != null && bundle.containsKey(getExtraDialogFragmentTags())) {
            this.dialogFragmentTags = new HashSet(Arrays.asList(bundle.getStringArray(getExtraDialogFragmentTags())));
            sHandler.post(new Runnable() {
                public final void run() {
                    MfpUiComponentDelegate.this.rebindDialogListeners();
                }
            });
        }
    }

    private FragmentManager getSupportFragmentManager() {
        Activity activity = getActivity();
        if (activity instanceof FragmentActivity) {
            return ((FragmentActivity) activity).getSupportFragmentManager();
        }
        return null;
    }

    private void reloadViewModel(Bundle bundle) {
        this.viewModelDelegate.onCreate(bundle, (ViewModelCache) this.viewModelCache.get(), this.onViewModelChangedCallback, getRunner());
    }
}
