package com.myfitnesspal.feature.profile.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.profile.ui.fragment.MyInfoFragment;
import com.myfitnesspal.feature.profile.ui.fragment.MyItemsFragment;
import com.myfitnesspal.feature.profile.ui.fragment.ProfileFragment;
import com.myfitnesspal.feature.profile.ui.view.MeHeaderViews;
import com.myfitnesspal.feature.settings.ui.activity.SettingsActivity;
import com.myfitnesspal.shared.model.AdUnit;
import com.myfitnesspal.shared.service.userdata.UserSummaryService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.mixin.BottomBarMixin;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.ViewUtils;
import javax.inject.Inject;

public class MeActivity extends MfpActivity {
    private static final String ANALYTICS_TAG = "Me";
    private static final String ATTR_KEY_TAB_NAME = "tab_name";
    private static final String ATTR_VALUE_MY_INFO = "my_info";
    private static final String ATTR_VALUE_MY_ITEMS = "my_items";
    private static final String ATTR_VALUE_MY_POSTS = "my_posts";
    private static final String EVENT_TAB_CHANGED = "me_nav_tapped";
    private static final String EXTRA_INITIAL_TAB = "extra_initial_tab";
    private static final int TAB_COUNT = 3;
    public static final int TAB_INFO = 0;
    public static final int TAB_ITEMS = 1;
    public static final int TAB_POSTS = 2;
    @BindView(2131362658)
    ViewPager fragmentPager;
    private MeHeaderViews headerViews;
    private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {
        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            String str = "my_info";
            switch (i) {
                case 0:
                    str = "my_info";
                    break;
                case 1:
                    str = "my_items";
                    break;
                case 2:
                    str = MeActivity.ATTR_VALUE_MY_POSTS;
                    break;
            }
            MeActivity.this.getAnalyticsService().reportEvent(MeActivity.EVENT_TAB_CHANGED, MapUtil.createMap("tab_name", str));
        }
    };
    @Inject
    PremiumService premiumService;
    @BindView(2131363774)
    TabLayout tabLayout;
    @Inject
    UserSummaryService userSummaryService;

    private class PagerAdapter extends FragmentPagerAdapter {
        final Context context;

        public int getCount() {
            return 3;
        }

        PagerAdapter(Context context2, FragmentManager fragmentManager) {
            super(fragmentManager);
            this.context = context2.getApplicationContext();
        }

        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return MyInfoFragment.newInstance();
                case 1:
                    return MyItemsFragment.newInstance();
                case 2:
                    return ProfileFragment.newInstanceWithNoHeader(MeActivity.this.getSession().getUser().getUsername());
                default:
                    throw new IllegalArgumentException("position is invalid");
            }
        }

        public CharSequence getPageTitle(int i) {
            switch (i) {
                case 0:
                    return title(R.string.me_my_info_tab);
                case 1:
                    return title(R.string.me_my_items_tab);
                case 2:
                    return title(R.string.me_my_posts_tab);
                default:
                    throw new IllegalArgumentException("position is invalid");
            }
        }

        private String title(int i) {
            return this.context.getString(i);
        }
    }

    /* access modifiers changed from: protected */
    public int getAdsContainerLayoutId() {
        return R.id.top_ads_container;
    }

    public String getAnalyticsScreenTag() {
        return ANALYTICS_TAG;
    }

    public int getCustomBaseLayoutResId() {
        return R.layout.me_activity_base_layout;
    }

    public int getCustomToolbarLayoutResId() {
        return R.layout.me_toolbar;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, MeActivity.class);
    }

    public static Intent newStartIntent(Context context, int i) {
        return new Intent(context, MeActivity.class).putExtra(EXTRA_INITIAL_TAB, i);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        registerMixin(new BottomBarMixin(this));
        super.onCreate(bundle);
        setContentView((int) R.layout.me_activity);
        ViewUtils.setVisible(showPremiumUpsell(), getAdsContainerLayoutId(), new View[0]);
        this.headerViews = new MeHeaderViews(this, this.userSummaryService);
        MaterialUtils.removeDefaultToolbarElevation(this);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.fragmentPager.setAdapter(new PagerAdapter(this, getSupportFragmentManager()));
        this.fragmentPager.addOnPageChangeListener(this.pageChangeListener);
        this.tabLayout.setupWithViewPager(this.fragmentPager);
        if (bundle == null) {
            Bundle extras = getIntent().getExtras();
            if (extras.containsKey(EXTRA_INITIAL_TAB)) {
                this.fragmentPager.setCurrentItem(extras.getInt(EXTRA_INITIAL_TAB, 0));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.headerViews.resume();
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.me_activity_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.action_settings) {
            return super.onOptionsItemSelected(menuItem);
        }
        getNavigationHelper().withIntent(SettingsActivity.newStartIntent(getConfigService(), getActivity())).startActivity();
        return true;
    }

    public boolean backPressed() {
        return ((BottomBarMixin) mixin(BottomBarMixin.class)).backPressed() || super.backPressed();
    }

    public boolean showPremiumUpsell() {
        return !this.premiumService.isPremiumSubscribed();
    }

    public boolean showAsTopLevelActivity() {
        return ConfigUtils.isAppNavBottomBarEnabled(getConfigService());
    }

    public AdUnit getAdUnit() {
        return getAdUnitService().getFriendsTabProfileScreenAdUnitValue();
    }
}
