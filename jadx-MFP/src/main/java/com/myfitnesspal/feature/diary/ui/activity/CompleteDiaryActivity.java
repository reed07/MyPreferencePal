package com.myfitnesspal.feature.diary.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.ui.activity.PremiumUpsellActivity;
import com.myfitnesspal.feature.settings.model.AdsSettings;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.shared.constants.Constants.Ads.Keywords;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.LocalizedStrings;
import com.myfitnesspal.shared.model.AdNetworkType;
import com.myfitnesspal.shared.model.AdUnit;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.model.v2.MfpMeasuredValue;
import com.myfitnesspal.shared.service.ads.AdsAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.location.LocationService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.service.userdata.UserWeightService.WeightType;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.util.AdsHelper;
import com.myfitnesspal.shared.util.AdsHelper.Builder;
import com.myfitnesspal.shared.util.AdsHelper.DfpAdsListener;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.myfitnesspal.shared.util.UnitsUtils.Energy;
import com.myfitnesspal.shared.util.UserUtil;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Collection;
import java.util.HashMap;
import javax.inject.Inject;

public class CompleteDiaryActivity extends MfpActivity implements DfpAdsListener {
    private boolean aboveDailyGoal;
    @Inject
    Lazy<AdsAnalyticsHelper> adsAnalyticsHelper;
    @BindView(2131363049)
    ViewGroup adsContainer;
    private AdsHelper adsHelper;
    @Inject
    Lazy<AdsSettings> adsSettings;
    @Inject
    Lazy<AnalyticsService> analyticsService;
    @BindView(2131361949)
    View blueBanner;
    @Inject
    Lazy<ConfigService> configService;
    @BindView(2131362335)
    TextView disclaimerText;
    @BindView(2131362361)
    TextView eatingDisorderText;
    @BindView(2131362705)
    View getPremiumContainer;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    @Inject
    Lazy<LocationService> locationService;
    @BindView(2131362988)
    View mainContainer;
    @Inject
    Lazy<NutrientGoalService> nutrientGoalService;
    @Inject
    Lazy<PremiumService> premiumService;
    @BindView(2131363339)
    TextView projectedWeightText;
    @Inject
    Lazy<Session> session;
    @Inject
    Lazy<UserApplicationSettingsService> userApplicationSettingsService;
    @Inject
    Lazy<UserEnergyService> userEnergyService;
    @Inject
    Lazy<UserWeightService> userWeightService;

    public String getAnalyticsScreenTag() {
        return Screens.DIARY_COMPLETE;
    }

    public int getNavigationIcon() {
        return R.drawable.ic_close_white_24dp;
    }

    public boolean shouldDisplayAds() {
        return false;
    }

    public static Intent newStartIntent(Context context, float f) {
        return new Intent(context, CompleteDiaryActivity.class).putExtra(Extras.PROJECTED_WEIGHT, f);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.diary_complete);
        MaterialUtils.removeDefaultToolbarElevation(this);
        MaterialUtils.enableAppBarScrollIfLollipop(this);
        MaterialUtils.setFixedFooterScrollingBehavior(this, true);
        initListeners();
        rebindUi();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        AdsHelper adsHelper2 = this.adsHelper;
        if (adsHelper2 != null) {
            adsHelper2.resume(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        AdsHelper adsHelper2 = this.adsHelper;
        if (adsHelper2 != null) {
            adsHelper2.pause();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        AdsHelper adsHelper2 = this.adsHelper;
        if (adsHelper2 != null) {
            adsHelper2.destroy();
        }
    }

    public AdUnit getAdUnit() {
        return getAdUnitService().getCompleteEntryScreenAdUnitValue();
    }

    public void onAdLoaded() {
        ViewUtils.setVisible(false, this.getPremiumContainer);
        ViewUtils.setVisible(true, this.adsContainer);
    }

    public void onAdFailedToLoad() {
        if (!((PremiumService) this.premiumService.get()).isPremiumSubscribed()) {
            ViewUtils.setVisible(true, this.getPremiumContainer);
            ViewUtils.setVisible(false, this.adsContainer);
        }
    }

    private void initListeners() {
        this.getPremiumContainer.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                CompleteDiaryActivity.this.getNavigationHelper().withIntent(PremiumUpsellActivity.newStartIntent((Context) CompleteDiaryActivity.this, (String) null, Events.DIARY_COMPLETE_UPSELL)).startActivity();
            }
        });
    }

    private void rebindUi() {
        DiaryDay activeDiaryDay = ((Session) this.session.get()).getUser().getActiveDiaryDay();
        float minimuCalorieForUserBasedOnGender = (float) UserUtil.getMinimuCalorieForUserBasedOnGender(getSession());
        float caloriesConsumed = activeDiaryDay.caloriesConsumed(false);
        boolean z = caloriesConsumed < minimuCalorieForUserBasedOnGender;
        MfpDailyGoal cachedDefaultGoal = ((NutrientGoalService) this.nutrientGoalService.get()).getCachedDefaultGoal();
        this.aboveDailyGoal = caloriesConsumed > ((UserEnergyService) this.userEnergyService.get()).getEnergy(Energy.CALORIES, cachedDefaultGoal != null ? cachedDefaultGoal.getEnergy() : new MfpMeasuredValue("calories", BitmapDescriptorFactory.HUE_RED));
        int size = CollectionUtils.size((Collection<?>) activeDiaryDay.getFoodEntries());
        ViewUtils.setVisible(!z, this.blueBanner);
        if (!z) {
            this.disclaimerText.setText(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.RECOMMENDATION_NORMAL, this.userEnergyService.get()));
            this.projectedWeightText.setText(((UserWeightService) this.userWeightService.get()).getDisplayableString(WeightType.JUST_WEIGHT, ExtrasUtils.getFloat(getIntent(), Extras.PROJECTED_WEIGHT)));
        } else {
            this.eatingDisorderText.setText(Html.fromHtml(String.format(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.RECOMMENDATION_LOW_NEW, this.userEnergyService.get()), new Object[]{((UserEnergyService) this.userEnergyService.get()).getDisplayableEnergy((double) minimuCalorieForUserBasedOnGender)})));
        }
        updateViewVisibility(z);
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.DIARY_COMPLETE_VIEWED, MapUtil.createMap(Attributes.ABOVE_DIALY_CALORIES_GOAL, Boolean.toString(this.aboveDailyGoal), Attributes.BELOW_GENDER_MINIMUM, Boolean.toString(z), Attributes.NUMBER_FOODS_LOGGED, String.valueOf(size), Attributes.HAS_AD, Boolean.toString(ViewUtils.isVisible(this.adsContainer))));
    }

    private void updateViewVisibility(boolean z) {
        ViewUtils.setVisible(!z, this.mainContainer);
        ViewUtils.setVisible(z, this.eatingDisorderText);
        if (!z) {
            boolean isPremiumAvailable = ((PremiumService) this.premiumService.get()).isPremiumAvailable();
            if (((PremiumService) this.premiumService.get()).isPremiumSubscribed()) {
                ViewUtils.setGone(this.getPremiumContainer);
                ViewUtils.setGone(this.adsContainer);
            } else if (!isPremiumAvailable) {
                ViewUtils.setGone(this.getPremiumContainer);
                ViewUtils.setVisible(this.adsContainer);
                addOrReloadDfpView();
            } else if (!ConfigUtils.isCompleteDairyAdEnabled(getConfigService())) {
                ViewUtils.setVisible(this.getPremiumContainer);
                ViewUtils.setGone(this.adsContainer);
            } else {
                ViewUtils.setGone(this.getPremiumContainer);
                ViewUtils.setVisible(this.adsContainer);
                addOrReloadDfpView();
            }
        }
    }

    private void addOrReloadDfpView() {
        HashMap hashMap = new HashMap();
        boolean isAutoplayNewsfeedAdsEnabled = ((UserApplicationSettingsService) this.userApplicationSettingsService.get()).isAutoplayNewsfeedAdsEnabled();
        hashMap.put(Keywords.CALORIE_GOAL, this.aboveDailyGoal ? "b" : "a");
        if (!isAutoplayNewsfeedAdsEnabled) {
            hashMap.put(Keywords.AUTO_PLAY, "n");
        }
        hashMap.put("user_id", ((Session) this.session.get()).getUser().getUid());
        if (this.adsHelper == null) {
            Builder builder = new Builder(this.adsContainer, getAdUnit(), getAnalyticsScreenTag(), AdNetworkType.AMAZON, this.configService, this.localSettingsService, this.locationService, this.adsSettings, this.adsAnalyticsHelper, getNavigationHelper(), ((Session) this.session.get()).getUser().isProfileCountryUS());
            this.adsHelper = builder.setCustomTargeting(hashMap).build();
            this.adsHelper.setAllowPremiumUpsell(false);
        }
        this.adsHelper.loadAds();
    }
}
