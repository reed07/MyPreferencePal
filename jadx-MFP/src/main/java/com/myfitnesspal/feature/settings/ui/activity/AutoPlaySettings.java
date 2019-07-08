package com.myfitnesspal.feature.settings.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.feature.video.util.VideoUtil;
import com.myfitnesspal.shared.constants.Constants.ABTest.NewsFeedVideo;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import javax.inject.Inject;

public class AutoPlaySettings extends MfpActivity {
    @Inject
    Lazy<ConfigService> configService;
    @BindView(2131363142)
    Switch newsfeedAdAutoPlaySwitch;
    @BindView(2131363143)
    Switch newsfeedVideoAutoPlaySwitch;
    @Inject
    Lazy<PremiumService> premiumService;
    @Inject
    Lazy<UserApplicationSettingsService> userApplicationSettingsService;

    public String getAnalyticsScreenTag() {
        return Screens.NATIVE_ADS_SETTINGS;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, AutoPlaySettings.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        setContentView((int) R.layout.native_ads_setting);
        setupUi();
    }

    private void setupUi() {
        if (!((PremiumService) this.premiumService.get()).isFeatureSubscribed(PremiumFeature.AdFree)) {
            ViewUtils.setVisible(this.newsfeedAdAutoPlaySwitch);
            this.newsfeedAdAutoPlaySwitch.setChecked(((UserApplicationSettingsService) this.userApplicationSettingsService.get()).isAutoplayNewsfeedAdsEnabled());
            this.newsfeedAdAutoPlaySwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    AutoPlaySettings.lambda$setupUi$0(AutoPlaySettings.this, compoundButton, z);
                }
            });
        } else {
            ViewUtils.setGone(this.newsfeedAdAutoPlaySwitch);
        }
        if (isAutoPlayOrClickToPlay(((ConfigService) this.configService.get()).getVariantIfLanguageSupported(NewsFeedVideo.NAME))) {
            ViewUtils.setVisible(this.newsfeedVideoAutoPlaySwitch);
            this.newsfeedVideoAutoPlaySwitch.setChecked(VideoUtil.isVideoAutoPlayOn(this.userApplicationSettingsService, this.configService));
            this.newsfeedVideoAutoPlaySwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    AutoPlaySettings.lambda$setupUi$1(AutoPlaySettings.this, compoundButton, z);
                }
            });
            return;
        }
        ViewUtils.setGone(this.newsfeedVideoAutoPlaySwitch);
    }

    public static /* synthetic */ void lambda$setupUi$0(AutoPlaySettings autoPlaySettings, CompoundButton compoundButton, boolean z) {
        autoPlaySettings.reportNativeAdVideoAutoPlaySettingToggledEvent(z);
        ((UserApplicationSettingsService) autoPlaySettings.userApplicationSettingsService.get()).setAutoplayNewsfeedAdsEnabled(z);
    }

    public static /* synthetic */ void lambda$setupUi$1(AutoPlaySettings autoPlaySettings, CompoundButton compoundButton, boolean z) {
        autoPlaySettings.reportNewsfeedVideoAutoPlaySettingToggledEvent(z);
        ((UserApplicationSettingsService) autoPlaySettings.userApplicationSettingsService.get()).setAutoplayNewsfeedVideosEnabled(z);
    }

    private void reportNativeAdVideoAutoPlaySettingToggledEvent(boolean z) {
        Object[] objArr = new Object[2];
        objArr[0] = "setting";
        objArr[1] = z ? "on" : "off";
        getAnalyticsService().reportEvent(Events.AUTO_PLAY_SETTING_TOGGLED, CollectionUtils.nameValuePairsToMap(objArr));
    }

    private void reportNewsfeedVideoAutoPlaySettingToggledEvent(boolean z) {
        Object[] objArr = new Object[2];
        objArr[0] = Attributes.NEWSFEED_VIDEO_SETTING;
        objArr[1] = z ? "on" : "off";
        getAnalyticsService().reportEvent(Events.AUTO_PLAY_VIDEO_SETTING_TOGGLED, CollectionUtils.nameValuePairsToMap(objArr));
    }

    private boolean isAutoPlayOrClickToPlay(String str) {
        return Strings.equals(NewsFeedVideo.AUTO_PLAY, str) || Strings.equals(NewsFeedVideo.CLICK_TO_PLAY, str);
    }
}
