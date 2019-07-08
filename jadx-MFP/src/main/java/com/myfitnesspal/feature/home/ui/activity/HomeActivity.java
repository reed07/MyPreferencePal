package com.myfitnesspal.feature.home.ui.activity;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewPropertyAnimator;
import butterknife.BindView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.home.ui.fragment.HomeFragment;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.model.AdUnit;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.fragment.HideBannerAdsListener;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.ui.fragment.Refreshable;
import com.myfitnesspal.shared.ui.mixin.BottomBarMixin;
import com.myfitnesspal.shared.ui.mixin.BottomBarMixin.OnButtonPressedListener;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Map;
import java.util.UUID;
import javax.inject.Inject;

public class HomeActivity extends MfpActivity implements HideBannerAdsListener {
    public static final String EXTRA_FLOW_ID = "flow_id";
    public static final String HOME_FRAGMENT_TAG = "home_fragment";
    private static final boolean PROTOTYPE_ENABLED = false;
    @BindView(2131361966)
    View bottomBarOnboarding;
    private boolean drawerMenuHidden;
    private String flowId;
    private MfpFragment fragment;
    @Inject
    Lazy<GlobalSettingsService> globalSettings;
    @Inject
    Lazy<UserApplicationSettingsService> userApplicationSettings;

    public String getAnalyticsScreenTag() {
        return Screens.HOME;
    }

    public boolean showAsTopLevelActivity() {
        return true;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, HomeActivity.class).addFlags(603979776);
    }

    public static Intent newStartIntentWithFabShowing(Context context) {
        return newStartIntent(context).putExtra(HomeFragment.EXTRA_SHOW_FAB_ON_RESUME, true);
    }

    public static Intent newStartIntentDiaryAddDeeplinkSelectMeal(Context context) {
        return newStartIntent(context).putExtra(HomeFragment.EXTRA_SHOW_DIARY_ADD_DEEPLINK_SELECT_MEAL_ON_RESUME, true);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        registerMixin(new BottomBarMixin(this));
        super.onCreate(bundle);
        setContentView((int) R.layout.home_activity);
        this.drawerMenuHidden = ConfigUtils.isLeftDrawerHidden(getConfigService());
        this.flowId = BundleUtils.getString(bundle, "flow_id", UUID.randomUUID().toString());
        initUi();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("flow_id", this.flowId);
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        MfpFragment mfpFragment = this.fragment;
        if (mfpFragment instanceof Refreshable) {
            ((Refreshable) mfpFragment).refresh();
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        updateBottomBarOnboardingVisibility(false);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        initFragment();
        updateDrawerMenu();
    }

    public boolean shouldShowTitle() {
        return !ConfigUtils.isLeftDrawerHidden(getConfigService());
    }

    public boolean showLeftDrawerIfTopLevel() {
        return !ConfigUtils.isLeftDrawerHidden(getConfigService());
    }

    public Map<String, String> getAnalyticsScreenAttributes() {
        return MapUtil.createMap("flow_id", this.flowId);
    }

    public AdUnit getAdUnit() {
        return getAdUnitService().getHomeScreenAdUnitValue();
    }

    public boolean backPressed() {
        if (!ViewUtils.isVisible(this.bottomBarOnboarding)) {
            return super.backPressed();
        }
        setBottomBarOnboardingDismissed();
        updateBottomBarOnboardingVisibility(true);
        return true;
    }

    /* access modifiers changed from: private */
    public void updateBottomBarOnboardingVisibility(boolean z) {
        if (!ConfigUtils.isAppNavExporeEnabled(getConfigService())) {
            ViewUtils.setGone(this.bottomBarOnboarding);
            return;
        }
        final boolean z2 = !((UserApplicationSettingsService) this.userApplicationSettings.get()).isBottomBarOnboardingDismissed();
        if (z) {
            ViewUtils.setVisible(true, this.bottomBarOnboarding);
            View view = this.bottomBarOnboarding;
            float f = BitmapDescriptorFactory.HUE_RED;
            view.setAlpha(z2 ? BitmapDescriptorFactory.HUE_RED : 1.0f);
            ViewPropertyAnimator animate = this.bottomBarOnboarding.animate();
            if (z2) {
                f = 1.0f;
            }
            animate.alpha(f).setListener(new AnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    ViewUtils.setVisible(z2, HomeActivity.this.bottomBarOnboarding);
                }
            });
        } else {
            ViewUtils.setVisible(z2, this.bottomBarOnboarding);
        }
    }

    /* access modifiers changed from: private */
    public void setBottomBarOnboardingDismissed() {
        ((UserApplicationSettingsService) this.userApplicationSettings.get()).setBottomBarOnboardingDismissed(true);
    }

    private void initUi() {
        MaterialUtils.enableAppBarScrollIfLollipop(this);
        MaterialUtils.setFixedFooterScrollingBehavior(getActivity(), true);
        ((BottomBarMixin) mixin(BottomBarMixin.class)).setOnButtonPressedListener(new OnButtonPressedListener() {
            public void onPressed(int i) {
                HomeActivity.this.setBottomBarOnboardingDismissed();
            }
        });
        this.bottomBarOnboarding.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                HomeActivity.this.setBottomBarOnboardingDismissed();
                HomeActivity.this.updateBottomBarOnboardingVisibility(true);
            }
        });
    }

    private void initFragment() {
        MfpFragment mfpFragment = this.fragment;
        if (mfpFragment == null || !mfpFragment.isVisible()) {
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            this.fragment = (MfpFragment) supportFragmentManager.findFragmentByTag(HOME_FRAGMENT_TAG);
            FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
            if (this.fragment == null) {
                this.fragment = HomeFragment.newInstance(this.flowId);
                beginTransaction.add(R.id.home_root_container_id, this.fragment, HOME_FRAGMENT_TAG);
            }
            beginTransaction.show(this.fragment);
            beginTransaction.commitAllowingStateLoss();
        }
    }

    private void updateDrawerMenu() {
        if (this.drawerMenuHidden != ConfigUtils.isLeftDrawerHidden(getConfigService())) {
            this.drawerMenuHidden = !this.drawerMenuHidden;
            setupToolbar();
        }
    }

    public void hideBannerAds() {
        super.setHideBannerAds();
    }
}
