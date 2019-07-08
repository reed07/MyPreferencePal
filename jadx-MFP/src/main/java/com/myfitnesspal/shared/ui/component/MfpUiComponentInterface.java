package com.myfitnesspal.shared.ui.component;

import android.app.Activity;
import android.support.v4.app.DialogFragment;
import com.myfitnesspal.framework.mixin.LifecycleMixin.AttachTarget;
import com.myfitnesspal.framework.mixin.RunnerLifecycleMixin;
import com.myfitnesspal.framework.mvvm.ViewModelComponent;
import com.myfitnesspal.framework.mvvm.ViewModelParent;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.activity.BusEventHandler;
import com.myfitnesspal.shared.ui.activity.busymanager.SupportsBusyStates;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.util.InputMethodManagerHelper;
import com.squareup.otto.Bus;
import java.util.List;

public interface MfpUiComponentInterface extends ViewModelComponent, ViewModelParent, SupportsBusyStates {
    void addBusEventHandlers(List<BusEventHandler> list);

    Activity getActivity();

    AnalyticsService getAnalyticsService();

    AttachTarget getAttachTarget();

    ConfigService getConfigService();

    CountryService getCountryService();

    InputMethodManagerHelper getImmHelper();

    Bus getMessageBus();

    NavigationHelper getNavigationHelper();

    Session getSession();

    boolean hasResumed();

    boolean isEnabled();

    <T extends RunnerLifecycleMixin> T mixin(Class<T> cls);

    boolean onRebindDialogFragment(DialogFragment dialogFragment, String str);

    void postEvent(Object obj);

    void postEventAfterResume(Object obj);

    void registerMixin(RunnerLifecycleMixin... runnerLifecycleMixinArr);

    void showDialogFragment(DialogFragment dialogFragment, String str);

    <T extends RunnerLifecycleMixin> void unregisterMixin(Class<T> cls);
}
