package com.myfitnesspal.shared.ui.mixin;

import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.myfitnesspal.android.R;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.diary.ui.activity.Diary;
import com.myfitnesspal.feature.explore.ui.activity.ExploreActivity;
import com.myfitnesspal.feature.home.ui.activity.HomeActivity;
import com.myfitnesspal.feature.profile.ui.activity.MeActivity;
import com.myfitnesspal.feature.profile.ui.activity.ProfileView;
import com.myfitnesspal.framework.mixin.RunnerLifecycleMixin;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.activity.FloatingButtonMixin;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.component.MfpUiComponentInterface;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.util.ActivityUtils;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.VersionUtils;
import com.uacf.core.util.ViewUtils;
import com.uacf.floatingbuttonmenu.FloatingButtonMenu.OnStateChangeListener;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;

public class BottomBarMixin extends RunnerLifecycleMixin {
    /* access modifiers changed from: private */
    public static Map<Class<?>, Integer> ACTIVITY_TO_ITEM_ID = new HashMap();
    private static final String ATTR_DIARY = "diary";
    private static final String ATTR_EXPLORE = "explore";
    private static final String ATTR_HOME = "home";
    private static final String ATTR_ME = "me";
    private static final String EVENT_BOTTOM_NAV_TAPPED = "bottom_nav_tapped";
    private static final long FAB_ANIM_DURATION_MS = 500;
    private static Set<Integer> HIDDEN_IN_LANDSCAPE_IDS = new HashSet();
    private static Map<Integer, String> ITEM_ID_TO_ANALYTICS_ATTR = new HashMap();
    private static final String KEY_TAB_NAME = "tab_name";
    private static final int OVERLAY_ANIM_DELAY_MS = 0;
    private static final int OVERLAY_ANIM_DURATION_MS = 100;
    @Inject
    AnalyticsService analytics;
    /* access modifiers changed from: private */
    public BottomNavigationView bottomNav;
    @Inject
    ConfigService configService;
    private int currentItemId;
    /* access modifiers changed from: private */
    public View disabledOverlay;
    private boolean enabled = true;
    private boolean exploreOn = true;
    /* access modifiers changed from: private */
    public FloatingButtonMixin fabMixin;
    private OnStateChangeListener fabStateChangedListener = new OnStateChangeListener() {
        public void onMenuStateChanged(int i) {
            if (BottomBarMixin.this.enabledAndVisible()) {
                boolean z = i == 1 || i == 0;
                if (i == 1 || i == 0 || i == 3) {
                    Menu menu = BottomBarMixin.this.bottomNav.getMenu();
                    for (Integer intValue : BottomBarMixin.ACTIVITY_TO_ITEM_ID.values()) {
                        MenuItem findItem = menu.findItem(intValue.intValue());
                        if (findItem != null) {
                            findItem.setEnabled(!z);
                        }
                    }
                }
                BottomBarMixin.this.bottomNav.setEnabled(!z);
                ViewUtils.setVisible(z, BottomBarMixin.this.disabledOverlay);
            }
        }
    };
    private NavigationHelper navigation;
    private OnNavigationItemSelectedListener navigationListener = new OnNavigationItemSelectedListener() {
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            final int itemId = menuItem.getItemId();
            if (itemId == BottomBarMixin.this.bottomNav.getSelectedItemId()) {
                return true;
            }
            if (BottomBarMixin.this.fabMixin == null || !BottomBarMixin.this.fabMixin.isOpen()) {
                if (BottomBarMixin.this.onPressedListener != null) {
                    BottomBarMixin.this.onPressedListener.onPressed(itemId);
                }
                BottomBarMixin.this.navigateTo(itemId);
                return true;
            }
            BottomBarMixin.this.fabMixin.close();
            BottomBarMixin.this.getHandler().postDelayed(new Runnable() {
                public void run() {
                    BottomBarMixin.this.navigateTo(itemId);
                }
            }, BottomBarMixin.FAB_ANIM_DURATION_MS);
            return true;
        }
    };
    /* access modifiers changed from: private */
    public OnButtonPressedListener onPressedListener;
    @Inject
    Session session;

    public interface OnButtonPressedListener {
        void onPressed(int i);
    }

    static {
        ACTIVITY_TO_ITEM_ID.put(HomeActivity.class, Integer.valueOf(R.id.action_home));
        ACTIVITY_TO_ITEM_ID.put(Diary.class, Integer.valueOf(R.id.action_diary));
        ACTIVITY_TO_ITEM_ID.put(ProfileView.class, Integer.valueOf(R.id.action_me));
        ACTIVITY_TO_ITEM_ID.put(MeActivity.class, Integer.valueOf(R.id.action_me));
        ACTIVITY_TO_ITEM_ID.put(ExploreActivity.class, Integer.valueOf(R.id.action_explore));
        ITEM_ID_TO_ANALYTICS_ATTR.put(Integer.valueOf(R.id.action_home), "home");
        ITEM_ID_TO_ANALYTICS_ATTR.put(Integer.valueOf(R.id.action_diary), "diary");
        ITEM_ID_TO_ANALYTICS_ATTR.put(Integer.valueOf(R.id.action_me), ATTR_ME);
        ITEM_ID_TO_ANALYTICS_ATTR.put(Integer.valueOf(R.id.action_explore), ATTR_EXPLORE);
    }

    public BottomBarMixin(MfpUiComponentInterface mfpUiComponentInterface) {
        super(mfpUiComponentInterface);
        MyFitnessPalApp.getInstance().component().inject(this);
    }

    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        MfpActivity mfpActivity = getMfpActivity();
        ViewGroup viewGroup = (ViewGroup) mfpActivity.findViewById(R.id.bottom_bar_container);
        if (viewGroup != null) {
            LayoutTransition layoutTransition = viewGroup.getLayoutTransition();
            if (layoutTransition != null) {
                for (int i = 0; i <= 4; i++) {
                    layoutTransition.setStartDelay(i, 0);
                    layoutTransition.setDuration(i, 100);
                }
            }
        }
        this.bottomNav = (BottomNavigationView) mfpActivity.findViewById(R.id.bottom_bar);
        this.disabledOverlay = mfpActivity.findViewById(R.id.bottom_bar_overlay);
        this.navigation = mfpActivity.getNavigationHelper();
        if (!getActivity().getResources().getBoolean(R.bool.isTablet)) {
            HIDDEN_IN_LANDSCAPE_IDS.add(Integer.valueOf(R.id.action_diary));
        }
    }

    public void onStop() {
        super.onStop();
        updateBottomNav();
    }

    public void onResume() {
        super.onResume();
        updateBottomNav();
    }

    public void setOnButtonPressedListener(OnButtonPressedListener onButtonPressedListener) {
        this.onPressedListener = onButtonPressedListener;
    }

    public void setFloatingButtonMixin(FloatingButtonMixin floatingButtonMixin) {
        FloatingButtonMixin floatingButtonMixin2 = this.fabMixin;
        if (!(floatingButtonMixin2 == null || floatingButtonMixin == floatingButtonMixin2)) {
            floatingButtonMixin2.removeStateChangedListener(this.fabStateChangedListener);
        }
        if (floatingButtonMixin != null) {
            floatingButtonMixin.addStateChangedListener(this.fabStateChangedListener);
        }
        this.fabMixin = floatingButtonMixin;
    }

    public boolean backPressed() {
        if (!this.enabled || !ConfigUtils.isAppNavBottomBarEnabled(this.configService)) {
            return false;
        }
        this.navigation.finishActivityAfterNavigation().withAnimations(0, 17432577).withIntent(HomeActivity.newStartIntent(getActivity())).withClearTopAndSingleTop().startActivity();
        return true;
    }

    public void setEnabled(boolean z) {
        if (this.enabled != z) {
            this.enabled = z;
            updateBottomNav();
        }
    }

    /* access modifiers changed from: private */
    public boolean enabledAndVisible() {
        return this.enabled && ConfigUtils.isAppNavBottomBarEnabled(this.configService);
    }

    private void updateBottomNav() {
        if (this.bottomNav == null) {
            return;
        }
        if (enabledAndVisible()) {
            ViewUtils.setVisible(this.bottomNav);
            boolean isAppNavExporeEnabled = ConfigUtils.isAppNavExporeEnabled(this.configService);
            if (isAppNavExporeEnabled != this.exploreOn || this.bottomNav.getMenu().size() == 0) {
                this.exploreOn = isAppNavExporeEnabled;
                this.bottomNav.getMenu().clear();
                this.bottomNav.inflateMenu(R.menu.bottom_bar_menu);
                if (!isAppNavExporeEnabled) {
                    this.bottomNav.getMenu().removeItem(R.id.action_explore);
                }
            }
            this.bottomNav.setOnNavigationItemSelectedListener(null);
            this.currentItemId = ((Integer) ACTIVITY_TO_ITEM_ID.get(getActivity().getClass())).intValue();
            this.bottomNav.setSelectedItemId(this.currentItemId);
            this.bottomNav.setOnNavigationItemSelectedListener(this.navigationListener);
            if (ActivityUtils.isLandscape(getActivity()) && HIDDEN_IN_LANDSCAPE_IDS.contains(Integer.valueOf(this.currentItemId)) && !ActivityUtils.isInMultiWindow(getActivity())) {
                this.bottomNav.setVisibility(8);
            }
            disableItemShiftingMode();
            return;
        }
        ViewUtils.setGone(this.bottomNav);
    }

    @SuppressLint({"RestrictedApi"})
    private void disableItemShiftingMode() {
        BottomNavigationMenuView bottomNavigationMenuView = (BottomNavigationMenuView) this.bottomNav.getChildAt(0);
        for (int i = 0; i < bottomNavigationMenuView.getChildCount(); i++) {
            ((BottomNavigationItemView) bottomNavigationMenuView.getChildAt(i)).setShifting(false);
        }
        setFieldValue(bottomNavigationMenuView.getClass(), bottomNavigationMenuView, "mShiftingMode", Boolean.valueOf(false));
        bottomNavigationMenuView.updateMenuView();
    }

    private static void setFieldValue(Class cls, Object obj, String str, Object obj2) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set(obj, obj2);
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: private */
    public void navigateTo(int i) {
        Activity activity = getActivity();
        if (this.currentItemId != R.id.action_home) {
            getActivity().finish();
        }
        if (VersionUtils.isLollipopOrHigher()) {
            this.navigation.withAnimations(-1, R.anim.activity_fade_out);
        } else {
            this.navigation.withAnimations(0, 0);
        }
        switch (i) {
            case R.id.action_diary /*2131361834*/:
                this.navigation.withIntent(Diary.newStartIntent(activity)).startActivity();
                break;
            case R.id.action_explore /*2131361837*/:
                this.navigation.withIntent(ExploreActivity.newStartIntent(activity)).startActivity();
                break;
            case R.id.action_home /*2131361838*/:
                this.navigation.withIntent(HomeActivity.newStartIntent(activity)).startActivity();
                break;
            case R.id.action_me /*2131361840*/:
                if (!ConfigUtils.isAppNavMeEnabled(this.configService)) {
                    this.navigation.withIntent(ProfileView.newStartIntent(activity, this.session.getUser().getUsername())).startActivity();
                    break;
                } else {
                    this.navigation.withIntent(MeActivity.newStartIntent(activity)).startActivity();
                    break;
                }
            default:
                throw new IllegalArgumentException("invalid menu item!");
        }
        this.analytics.reportEvent(EVENT_BOTTOM_NAV_TAPPED, MapUtil.createMap("tab_name", (String) ITEM_ID_TO_ANALYTICS_ATTR.get(Integer.valueOf(i))));
    }
}
