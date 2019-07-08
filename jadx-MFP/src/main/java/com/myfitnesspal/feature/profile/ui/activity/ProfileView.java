package com.myfitnesspal.feature.profile.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.profile.ui.fragment.ProfileFragment;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.event.ProfileDialogEvent;
import com.myfitnesspal.shared.model.AdUnit;
import com.myfitnesspal.shared.model.v1.MiniUserInfo;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.mixin.BottomBarMixin;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Strings;

public class ProfileView extends MfpActivity {
    private static final String ANALYTICS_SCREEN_TAG = "Profile";

    public String getAnalyticsScreenTag() {
        return ANALYTICS_SCREEN_TAG;
    }

    private static Intent newStartIntent(Context context) {
        return new Intent(context, ProfileView.class);
    }

    public static Intent newStartIntent(Context context, String str) {
        return newStartIntent(context).putExtra("username", str);
    }

    public static Intent newStartIntent(Context context, String str, String str2) {
        return newStartIntent(context, str).putExtra(Extras.USER_UID, str2);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        registerMixin(new BottomBarMixin(this));
        super.onCreate(bundle);
        setContentView((int) R.layout.profile_view);
        Intent intent = getIntent();
        String string = ExtrasUtils.getString(intent, "username");
        boolean isMyProfile = isMyProfile();
        ((BottomBarMixin) mixin(BottomBarMixin.class)).setEnabled(isMyProfile);
        String string2 = getString(ConfigUtils.isAppNavBottomBarEnabled(getConfigService()) ? R.string.menu_me : R.string.menu_my_profile);
        if (!Strings.isEmpty(string) && !isMyProfile) {
            string2 = string;
        }
        setTitle(string2);
        if (bundle == null) {
            performTransaction(ProfileFragment.newInstance(string, ExtrasUtils.getString(intent, Extras.USER_UID), (MiniUserInfo) ExtrasUtils.getParcelable(intent, Extras.USER_INFO, MiniUserInfo.class.getClassLoader()), ExtrasUtils.getBoolean(intent, Extras.IS_FRIEND_REQUEST)));
        }
    }

    private boolean isMyProfile() {
        return Strings.equals(getSession().getUser().getUsername(), ExtrasUtils.getString(getIntent(), "username"));
    }

    public AdUnit getAdUnit() {
        return getAdUnitService().getFriendsTabProfileScreenAdUnitValue();
    }

    public void performTransaction(Fragment fragment) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.add((int) R.id.fragment_placeholder, fragment);
        beginTransaction.commit();
    }

    /* access modifiers changed from: protected */
    public int getAdsContainerLayoutId() {
        if (!ConfigUtils.isAppNavBottomBarEnabled(getConfigService()) || !isMyProfile()) {
            return super.getAdsContainerLayoutId();
        }
        return R.id.top_ads_container;
    }

    public boolean backPressed() {
        return ((BottomBarMixin) mixin(BottomBarMixin.class)).backPressed() || super.backPressed();
    }

    @Subscribe
    public void onProfileDialogEvent(ProfileDialogEvent profileDialogEvent) {
        profileDialogEvent.getDialogFragment().show(getSupportFragmentManager().beginTransaction(), "dialog");
    }

    public boolean showAsTopLevelActivity() {
        return ConfigUtils.isAppNavBottomBarEnabled(getConfigService()) && isMyProfile();
    }
}
