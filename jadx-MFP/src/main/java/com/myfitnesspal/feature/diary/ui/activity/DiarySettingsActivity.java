package com.myfitnesspal.feature.diary.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.ViewGroup;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.settings.ui.fragment.DiarySettingsFragment;
import com.myfitnesspal.feature.settings.ui.fragment.RemindersFragment;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.fragment.ViewPagerGuest;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.MapUtil;
import java.util.HashMap;
import java.util.Map;

public class DiarySettingsActivity extends MfpActivity {
    private static final String ATTR_KEY_SOURCE = "source";
    private static final String ATTR_KEY_TAB_NAME = "tab_name";
    private static final String ATTR_VAL_REMINDERS = "reminders";
    private static final String ATTR_VAL_SETTINGS = "settings";
    private static final String EVENT_TAB_TAPPED = "diary_settings_tab_tapped";
    private static final String EXTRA_SOURCE = "extra_source";
    private static final int POSITION_DIARY = 0;
    private static final int POSITION_REMINDERS = 1;
    private static final String SCREEN_TAG = "SCREEN_DiarySettings";
    private static final int TAB_COUNT = 2;
    /* access modifiers changed from: private */
    public PagerAdapter adapter;
    private OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {
        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            AnalyticsService analyticsService = DiarySettingsActivity.this.getAnalyticsService();
            String str = DiarySettingsActivity.EVENT_TAB_TAPPED;
            String[] strArr = new String[2];
            strArr[0] = "tab_name";
            strArr[1] = i == 0 ? "settings" : "reminders";
            analyticsService.reportEvent(str, MapUtil.createMap(strArr));
            DiarySettingsActivity.this.adapter.setVisibleIndex(i);
        }
    };
    @BindView(2131363774)
    TabLayout tabLayout;
    @BindView(2131362658)
    ViewPager viewPager;

    private static class PagerAdapter extends FragmentPagerAdapter {
        private Context context;
        private Map<Integer, ViewPagerGuest> pages = new HashMap();
        private int visibleIndex = -1;

        public int getCount() {
            return 2;
        }

        PagerAdapter(Context context2, FragmentManager fragmentManager) {
            super(fragmentManager);
            this.context = context2.getApplicationContext();
        }

        /* access modifiers changed from: 0000 */
        public void setVisibleIndex(int i) {
            ViewPagerGuest viewPagerGuest = (ViewPagerGuest) this.pages.get(Integer.valueOf(i));
            if (viewPagerGuest != null) {
                viewPagerGuest.onPageVisible();
            }
            if (this.pages.containsKey(Integer.valueOf(this.visibleIndex))) {
                ((ViewPagerGuest) this.pages.get(Integer.valueOf(this.visibleIndex))).onPageHidden();
            }
            this.visibleIndex = i;
        }

        public Fragment getItem(int i) {
            Fragment fragment;
            switch (i) {
                case 0:
                    fragment = DiarySettingsFragment.newInstance();
                    break;
                case 1:
                    fragment = RemindersFragment.newInstance();
                    break;
                default:
                    throw new IllegalArgumentException("position is invalid");
            }
            this.pages.put(Integer.valueOf(i), fragment);
            return fragment;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            super.destroyItem(viewGroup, i, obj);
            this.pages.remove(Integer.valueOf(i));
        }

        public CharSequence getPageTitle(int i) {
            switch (i) {
                case 0:
                    return this.context.getString(R.string.diary);
                case 1:
                    return this.context.getString(R.string.menu_reminders);
                default:
                    throw new IllegalArgumentException("position is invalid");
            }
        }
    }

    public interface Source {
        public static final String DIARY = "diary";
        public static final String SETTINGS = "settings";
    }

    public static Intent newStartIntent(Context context, String str) {
        return new Intent(context, DiarySettingsActivity.class).putExtra(EXTRA_SOURCE, str);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.generic_tabbed_activity);
        MaterialUtils.enableAppBarScrollIfLollipop(this);
        MaterialUtils.setFixedFooterScrollingBehavior(getActivity(), true);
        MaterialUtils.removeDefaultToolbarElevation(this);
        this.adapter = new PagerAdapter(this, getSupportFragmentManager());
        this.viewPager.setAdapter(this.adapter);
        this.viewPager.addOnPageChangeListener(this.onPageChangeListener);
        this.tabLayout.setupWithViewPager(this.viewPager);
        if (bundle == null) {
            String string = BundleUtils.getString(getIntent().getExtras(), EXTRA_SOURCE, "");
            getAnalyticsService().reportEvent(SCREEN_TAG, MapUtil.createMap("source", string));
        }
    }
}
