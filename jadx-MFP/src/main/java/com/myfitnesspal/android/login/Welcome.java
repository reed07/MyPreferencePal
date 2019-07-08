package com.myfitnesspal.android.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import com.myfitnesspal.android.R;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.home.ui.activity.HomeActivity;
import com.myfitnesspal.feature.registration.ui.activity.LoginActivity;
import com.myfitnesspal.feature.registration.ui.activity.PrefetchActivity;
import com.myfitnesspal.feature.registration.util.CrashTracker;
import com.myfitnesspal.feature.registration.util.StartupManager;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.uacf.core.logging.LogConfig;
import com.uacf.core.logging.Printer;
import com.uacf.core.util.Ln;
import dagger.Lazy;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;

public class Welcome extends FragmentActivity {
    private static AtomicBoolean sAlreadyBootstrapped = new AtomicBoolean(false);
    private static AtomicBoolean sAlreadyStarted = new AtomicBoolean(false);
    @Inject
    Lazy<ConfigService> configService;
    private Handler handler;
    @Inject
    Lazy<LogConfig> logConfig;
    @Inject
    Lazy<NavigationHelper> navigationHelper;
    @Inject
    Lazy<Printer> printer;
    @Inject
    Lazy<Session> session;
    @Inject
    Lazy<StartupManager> startupManager;

    public static Intent newStartIntent(Context context) {
        return new Intent(context, Welcome.class);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        MyFitnessPalApp.getInstance().component().inject(this);
        if (!getIntent().hasCategory("android.intent.category.LAUNCHER") || !"android.intent.action.MAIN".equals(getIntent().getAction()) || isTaskRoot()) {
            this.handler = new Handler();
            if (!sAlreadyBootstrapped.get()) {
                setContentView(R.layout.welcome);
                bootstrapInBackground();
            } else {
                onBootstrapped();
            }
            return;
        }
        finish();
    }

    public static void setHomeViewed(boolean z) {
        sAlreadyStarted.set(z);
    }

    private void bootstrapInBackground() {
        AnonymousClass1 r0 = new Thread() {
            public void run() {
                Ln.setConfig((LogConfig) Welcome.this.logConfig.get());
                Ln.setPrinter((Printer) Welcome.this.printer.get());
                if (CrashTracker.lastSessionCrashed() || !((ConfigService) Welcome.this.configService.get()).hasValidConfiguration()) {
                    try {
                        Ln.d("app crash detected or we don't have a valid config. forcing a blocking config refresh", new Object[0]);
                        ((ConfigService) Welcome.this.configService.get()).refresh();
                    } catch (ApiException unused) {
                    }
                } else {
                    Ln.d("app crash not detected. refreshing config in the background", new Object[0]);
                    ((ConfigService) Welcome.this.configService.get()).refreshIfExpiredAsync();
                }
                ((StartupManager) Welcome.this.startupManager.get()).doStartupTasksIfNecessary(Welcome.this);
                Welcome.this.onBootstrapped();
            }
        };
        r0.setName("Welcome.boostrap");
        r0.setPriority(10);
        r0.start();
    }

    /* access modifiers changed from: private */
    public void onBootstrapped() {
        sAlreadyBootstrapped.set(true);
        $$Lambda$Welcome$73zBXOKwjR557SaGa3oucaFFBHQ r0 = new Runnable() {
            public final void run() {
                Welcome.lambda$onBootstrapped$0(Welcome.this);
            }
        };
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            r0.run();
        } else {
            this.handler.post(r0);
        }
    }

    public static /* synthetic */ void lambda$onBootstrapped$0(Welcome welcome) {
        if (!((Session) welcome.session.get()).getUser().isLoggedIn()) {
            sAlreadyStarted.set(false);
            welcome.navigateToLogin();
        } else if (sAlreadyStarted.get()) {
            welcome.navigateToHome();
        } else {
            welcome.navigateToPrefetch();
        }
        welcome.overridePendingTransition(0, 0);
    }

    private void navigateToLogin() {
        Intent intent = getIntent();
        ((NavigationHelper) this.navigationHelper.get()).withContext(this).finishActivityAfterNavigation().withNoAnimations().withExtras(intent.getExtras()).withAction(intent.getAction()).withClearTopAndSingleTop().withIntent(LoginActivity.newStartIntentFromWelcome(this)).startActivity();
    }

    private void navigateToPrefetch() {
        startActivity(PrefetchActivity.getStartIntent(this, !sAlreadyStarted.get()));
        finish();
    }

    private void navigateToHome() {
        ((NavigationHelper) this.navigationHelper.get()).withContext(this).withNoAnimations().finishActivityAfterNavigation(true).withIntent(HomeActivity.newStartIntent(this)).startActivity();
    }
}
