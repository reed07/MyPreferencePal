package com.myfitnesspal.feature.appgallery.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.appgallery.event.NavigateToGoogleFitPermissionsEvent;
import com.myfitnesspal.feature.appgallery.event.NavigateToPartnerAppDetailsEvent;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.appgallery.service.DisconnectAppTask.CompletedEvent;
import com.myfitnesspal.feature.appgallery.ui.GoogleFitPermissionsFragment.GoogleFitPermissionScopedConnectionEstablishedEvent;
import com.myfitnesspal.shared.api.ApiResponseBase;
import com.myfitnesspal.shared.api.v2.MfpV2ApiErrorCallback;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Function1;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.List;
import javax.inject.Inject;

public class AppGalleryActivity extends MfpActivity {
    private static final String APP_ID = "app_Id";
    @Inject
    Lazy<AppGalleryService> appGalleryService;

    public static Intent newStartIntent(Context context) {
        return new Intent(context, AppGalleryActivity.class);
    }

    public static Intent newStartIntent(Context context, String str) {
        return newStartIntent(context).putExtra(APP_ID, str);
    }

    public static Intent newStartIntentForHardwareTrackers(Context context) {
        return new Intent(context, AppGalleryActivity.class).putExtra(Extras.HARDWARE_TRACKERS_ONLY, true);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.fragment_container);
        component().inject(this);
        if (bundle == null) {
            String string = ExtrasUtils.getString(getIntent(), APP_ID);
            if (Strings.notEmpty(string)) {
                initFirstFragmentFromAppId(string);
            } else {
                initFirstFragment();
            }
        }
    }

    /* access modifiers changed from: private */
    public void initFirstFragment() {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(0, 0, R.anim.slide_in_left_100_medium, 0).replace(R.id.container, AppsHomeFragment.newInstance(getIntent()), tag(AppsHomeFragment.class)).commit();
    }

    /* access modifiers changed from: private */
    public void navigateToAppDetails(MfpFragment mfpFragment) {
        String name = mfpFragment.getClass().getName();
        if (getSupportFragmentManager().findFragmentByTag(tag(AppsHomeFragment.class)) != null) {
            getSupportFragmentManager().beginTransaction().addToBackStack(name).setCustomAnimations(R.anim.slide_in_right_100_medium, R.anim.slide_out_left_100_medium, R.anim.slide_in_left_100_medium, R.anim.slide_out_right_100_medium).replace(R.id.container, mfpFragment, name).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, mfpFragment, name).commit();
        }
    }

    private void initFirstFragmentFromAppId(final String str) {
        setBusy(true);
        ((AppGalleryService) this.appGalleryService.get()).getAppListAsync("all", new Function1<List<MfpPlatformApp>>() {
            public void execute(List<MfpPlatformApp> list) {
                MfpPlatformApp access$000 = AppGalleryActivity.findSelectedApp(str, list);
                if (access$000 != null) {
                    AppGalleryActivity.this.navigateToAppDetails(AppDetailFragment.newInstance(access$000));
                } else {
                    AppGalleryActivity.this.initFirstFragment();
                }
                AppGalleryActivity.this.setBusy(false);
            }
        }, new MfpV2ApiErrorCallback() {
            public void execute(ApiResponseBase apiResponseBase) {
                AppGalleryActivity.this.initFirstFragment();
                AppGalleryActivity.this.setBusy(false);
            }
        });
    }

    /* access modifiers changed from: private */
    public static MfpPlatformApp findSelectedApp(final String str, List<MfpPlatformApp> list) {
        final int tryParseInt = NumberUtils.tryParseInt(str, -1);
        return (MfpPlatformApp) Enumerable.firstOrDefault(list, new ReturningFunction1<Boolean, MfpPlatformApp>() {
            public Boolean execute(MfpPlatformApp mfpPlatformApp) {
                return Boolean.valueOf(Strings.equals(mfpPlatformApp.getAppId(), str) || mfpPlatformApp.getId() == tryParseInt);
            }
        });
    }

    @Subscribe
    public void onNavigateToPartnerAppDetailsEvent(NavigateToPartnerAppDetailsEvent navigateToPartnerAppDetailsEvent) {
        getImmHelper().hideSoftInput();
        navigateToAppDetails(AppDetailFragment.newInstance(navigateToPartnerAppDetailsEvent.getApp()));
    }

    @Subscribe
    public void onDisconnectAppApiResponseEvent(CompletedEvent completedEvent) {
        setBusy(false);
        if (completedEvent.getFailure() == null) {
            backPressed();
        } else {
            getMessageBus().post(new AlertEvent(getString(R.string.cannot_disconnect_app)));
        }
    }

    @Subscribe
    public void onNavigateToGoogleFitPermissionsEvent(NavigateToGoogleFitPermissionsEvent navigateToGoogleFitPermissionsEvent) {
        navigateToAppDetails(GoogleFitPermissionsFragment.newInstance());
        MaterialUtils.setFixedFooterScrollingBehavior(this, true);
    }

    @Subscribe
    public void onGoogleFitPermissionScoppedConnectionEstablishedEvent(GoogleFitPermissionScopedConnectionEstablishedEvent googleFitPermissionScopedConnectionEstablishedEvent) {
        getSupportFragmentManager().popBackStack();
    }
}
