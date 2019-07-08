package com.myfitnesspal.feature.diary.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.diary.ui.fragment.UserDiaryFragment;
import com.myfitnesspal.feature.home.ui.activity.HomeActivity;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.model.AdUnit;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.mixin.BottomBarMixin;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.util.ActivityUtils;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import javax.inject.Inject;

public class Diary extends MfpActivity {
    public static final String DIARY = "diary";
    private static final String DIARY_FRAGMENT_TAG = "diary_fragment_tag";
    private static final String EXTRA_FORCE_HOME_ON_BACK = "force_home_on_back";
    @Inject
    Lazy<DiaryService> diaryService;

    public String getAnalyticsScreenTag() {
        return "Diary";
    }

    public static Intent newStartIntent(Context context) {
        return newStartIntentWithReferrer(context, null);
    }

    public static Intent newStartIntentWithDate(Context context, String str) {
        return newStartIntentWithReferrer(context, null).putExtra("date", str);
    }

    public static Intent newStartIntentWithReferrer(Context context, String str) {
        if (!Strings.notEmpty(str)) {
            str = "unknown";
        }
        return new Intent(context, Diary.class).putExtra("referrer", str);
    }

    public static Intent newStartIntentWithReferrerAndForceHomeOnBack(Context context, String str) {
        return newStartIntentWithReferrer(context, str).putExtra(EXTRA_FORCE_HOME_ON_BACK, true);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        registerMixin(new BottomBarMixin(this));
        super.onCreate(bundle);
        setContentView((int) R.layout.diary);
        MaterialUtils.setFixedFooterScrollingBehavior(this, true);
        boolean z = ExtrasUtils.getBoolean(getIntent(), Extras.IS_WALK_THROUGH, false);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (supportFragmentManager.findFragmentByTag(DIARY_FRAGMENT_TAG) == null) {
            String string = ExtrasUtils.getString(getIntent(), "date", (String) null);
            int i = ExtrasUtils.getInt(getIntent(), "notification_id", Integer.MIN_VALUE);
            StringBuilder sb = new StringBuilder();
            sb.append(ExtrasUtils.getString(getIntent(), "referrer", "unknown"));
            sb.append(Events.DIARY);
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, UserDiaryFragment.newInstance(string, i, z, sb.toString()), DIARY_FRAGMENT_TAG).commit();
        }
        ViewUtils.setVisible(shouldDisplayAds(), findById(R.id.ads_container));
    }

    public boolean shouldDisplayAds() {
        boolean z = false;
        if (!super.shouldDisplayAds() || ConfigUtils.isHideBannerAdsDiaryOn(getConfigService())) {
            return false;
        }
        if (ExtrasUtils.getBoolean(getIntent(), Extras.IS_WALK_THROUGH, false) || getAdsContainerLayoutId() == 0 || ActivityUtils.isLandscape(this)) {
            z = true;
        }
        return !z;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        getImmHelper().hideSoftInput();
    }

    public boolean backPressed() {
        if (((BottomBarMixin) mixin(BottomBarMixin.class)).backPressed()) {
            return true;
        }
        if (!ExtrasUtils.getBoolean(getIntent(), Extras.STARTED_FROM_DEEP_LINK) && !ExtrasUtils.getBoolean(getIntent(), EXTRA_FORCE_HOME_ON_BACK)) {
            return super.backPressed();
        }
        NavigationHelper navigationHelper = getNavigationHelper();
        if (ConfigUtils.isAppNavBottomBarEnabled(getConfigService())) {
            navigationHelper.withAnimations(0, 17432577);
        }
        getNavigationHelper().finishActivityAfterNavigation().withExtra(Extras.DISABLE_EXIT_TO_LAUNCHER, true).withIntent(HomeActivity.newStartIntent(this)).startActivity();
        finish();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {
            ((DiaryService) this.diaryService.get()).clearCachedInsights();
        }
    }

    public AdUnit getAdUnit() {
        return getAdUnitService().getDiaryScreenAdUnitValue();
    }

    /* access modifiers changed from: protected */
    public int getAdsContainerLayoutId() {
        if (ConfigUtils.isAppNavUpdateDiaryEnabled(getConfigService())) {
            return R.id.top_ads_container;
        }
        return super.getAdsContainerLayoutId();
    }

    public boolean showAsTopLevelActivity() {
        return ConfigUtils.isAppNavBottomBarEnabled(getConfigService());
    }
}
