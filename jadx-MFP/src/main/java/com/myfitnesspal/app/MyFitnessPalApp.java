package com.myfitnesspal.app;

import android.app.Application;
import android.arch.lifecycle.ProcessLifecycleOwner;
import android.content.Context;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.os.StrictMode.VmPolicy;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import com.bumptech.glide.Glide;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.moat.analytics.mobile.und.MoatAnalytics;
import com.moat.analytics.mobile.und.MoatOptions;
import com.myfitnesspal.android.R;
import com.myfitnesspal.build.BuildConfiguration;
import com.myfitnesspal.feature.registration.util.CrashTracker;
import com.myfitnesspal.shared.injection.component.ApplicationComponent;
import com.myfitnesspal.shared.injection.component.DaggerApplicationComponent;
import com.myfitnesspal.shared.injection.module.ApplicationModule;
import com.myfitnesspal.shared.notification.PushNotificationManager;
import com.myfitnesspal.shared.service.analytics.AmplitudeService;
import com.myfitnesspal.shared.service.lifecycle.AppLifecycleObserver;
import com.myfitnesspal.shared.service.premium.PaymentsLogger;
import com.myfitnesspal.shared.uacf.UacfSharedLibrary;
import com.myfitnesspal.shared.util.CrashlyticsUtil;
import com.myfitnesspal.shared.util.MfpJsonSerializers;
import com.myfitnesspal.shared.util.TimeZoneHelper;
import com.uacf.core.database.SQLiteDatabaseWrapperFactory;
import com.uacf.core.util.Ln;
import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.plugins.RxJavaPlugins;
import javax.inject.Inject;

public class MyFitnessPalApp extends MultiDexApplication {
    private static final boolean ENABLE_LEAK_CANARY = false;
    private static final boolean ENABLE_STETHO = false;
    private static MyFitnessPalApp instance;
    @Inject
    public AppLifecycleObserver appLifecycleObserver;
    private ApplicationComponent component;

    public void onCreate() {
        instance = this;
        initDebug();
        AmplitudeService.initialize((Application) this);
        CrashTracker.install(this);
        super.onCreate();
        setTheme(R.style.Mfp_Theme_Default);
        this.component = createComponent();
        MfpJsonSerializers.register();
        CrashlyticsUtil.startIfEnabled(this);
        TimeZoneHelper.setContext(this);
        PaymentsLogger.init(this);
        PushNotificationManager.createNotificationChannels(this);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this.appLifecycleObserver);
        initFacebook();
        initMOATAnalytics();
        initRxJava();
        UacfSharedLibrary.initialize(this);
    }

    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(this).onLowMemory();
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    public void setTestComponent(ApplicationComponent applicationComponent) {
        this.component = applicationComponent;
    }

    public ApplicationComponent component() {
        return this.component;
    }

    /* access modifiers changed from: protected */
    public ApplicationComponent createComponent() {
        ApplicationComponent build = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        build.inject(this);
        return build;
    }

    private void initDebug() {
        if (BuildConfiguration.getBuildConfiguration().isDebug()) {
            StrictMode.setThreadPolicy(new Builder().detectAll().penaltyFlashScreen().penaltyLog().build());
            StrictMode.setVmPolicy(new VmPolicy.Builder().detectAll().penaltyLog().build());
            SQLiteDatabaseWrapperFactory.setLoggingEnabled(true);
        }
    }

    private void initFacebook() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp((Application) this);
    }

    private void initMOATAnalytics() {
        MoatOptions moatOptions = new MoatOptions();
        moatOptions.loggingEnabled = BuildConfiguration.getBuildConfiguration().isDebug();
        MoatAnalytics.getInstance().start(moatOptions, this);
    }

    private void initRxJava() {
        RxJavaPlugins.setErrorHandler($$Lambda$MyFitnessPalApp$C9vcF1AA7NbNEjWjTdSPd6E7ws.INSTANCE);
    }

    static /* synthetic */ void lambda$initRxJava$0(Throwable th) throws Exception {
        if (th instanceof UndeliverableException) {
            th = th.getCause();
        }
        Ln.e(th);
    }

    public static MyFitnessPalApp getInstance() {
        return instance;
    }
}
