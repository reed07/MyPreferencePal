package com.myfitnesspal.feature.home.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.drawer.ui.view.LeftDrawerMenu;
import com.myfitnesspal.feature.friends.ui.activity.ComposeMessageActivity;
import com.myfitnesspal.feature.friends.ui.activity.MessagesActivity.Type;
import com.myfitnesspal.feature.friends.ui.fragment.MessagesFragment;
import com.myfitnesspal.feature.friends.ui.fragment.MessagesFragment.OnMessagesChangedListener;
import com.myfitnesspal.feature.friends.ui.fragment.MessagesFragment.ViewType;
import com.myfitnesspal.feature.notificationinbox.ui.fragment.NotificationInboxFragment;
import com.myfitnesspal.shared.model.v1.InboxMessage;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeMessagesActivity extends MfpActivity {
    private static final String ATTR_KEY_TAB_NAME = "tab_name";
    private static final String ATTR_VAL_MESSAGES = "messages";
    private static final String ATTR_VAL_NOTIF_INBOX = "notif_inbox";
    private static final String EVENT_TAB_TAPPED = "notif_tab_tapped";
    private static final String EXTRA_INITIAL_TAB = "extra_initial_tab";
    private static final String EXTRA_MESSAGES_TITLE = "extra_messages_title";
    private static final int POSITION_MESSAGES = 1;
    private static final int POSITION_NOTIFICATIONS = 0;
    private static final int TAB_COUNT = 2;
    /* access modifiers changed from: private */
    public PagerAdapter adapter;
    @BindView(2131362546)
    FloatingActionButton fab;
    /* access modifiers changed from: private */
    public OnMessagesChangedListener onMessagesChanged = new OnMessagesChangedListener() {
        public void onMessagesChanged(List<InboxMessage> list, Type type) {
            if (type == Type.ReceivedMessages) {
                int unreadCount = InboxMessage.getUnreadCount(list);
                if (unreadCount > 0) {
                    HomeMessagesActivity.this.adapter.setPageTitle(1, HomeMessagesActivity.this.getString(R.string.messages_tab_message_with_count, new Object[]{Integer.valueOf(unreadCount)}));
                    return;
                }
                HomeMessagesActivity.this.adapter.setPageTitle(1, null);
            }
        }
    };
    final OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {
        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            if (i == 1) {
                HomeMessagesActivity.this.fab.show();
            } else {
                HomeMessagesActivity.this.fab.hide();
            }
            HomeMessagesActivity.this.getAnalyticsService().reportEvent(HomeMessagesActivity.EVENT_TAB_TAPPED, MapUtil.createMap("tab_name", i == 0 ? HomeMessagesActivity.ATTR_VAL_NOTIF_INBOX : "messages"));
        }
    };
    @BindView(2131363774)
    TabLayout tabLayout;
    @BindView(2131362658)
    ViewPager viewPager;

    private class PagerAdapter extends FragmentPagerAdapter {
        private Context context;
        private Map<Integer, String> customTitles = new HashMap();

        public int getCount() {
            return 2;
        }

        PagerAdapter(Context context2, FragmentManager fragmentManager) {
            super(fragmentManager);
            this.context = context2.getApplicationContext();
        }

        public void loadInstanceState(Bundle bundle) {
            if (bundle != null) {
                setPageTitle(1, bundle.getString(HomeMessagesActivity.EXTRA_MESSAGES_TITLE, ""));
            }
        }

        public void saveInstanceState(Bundle bundle) {
            if (this.customTitles.get(Integer.valueOf(1)) != null) {
                bundle.putString(HomeMessagesActivity.EXTRA_MESSAGES_TITLE, (String) this.customTitles.get(Integer.valueOf(1)));
            }
        }

        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return NotificationInboxFragment.newInstance();
                case 1:
                    MessagesFragment newInstance = MessagesFragment.newInstance(Type.ReceivedMessages, ViewType.Switchable);
                    newInstance.setOnMessagesChangedListener(HomeMessagesActivity.this.onMessagesChanged);
                    return newInstance;
                default:
                    throw new IllegalArgumentException("position is invalid");
            }
        }

        public CharSequence getPageTitle(int i) {
            String str = (String) this.customTitles.get(Integer.valueOf(i));
            if (str != null) {
                return str;
            }
            switch (i) {
                case 0:
                    return this.context.getString(R.string.messages_tab_notifications);
                case 1:
                    return this.context.getString(R.string.messages_tab_messages);
                default:
                    throw new IllegalArgumentException("position is invalid");
            }
        }

        /* access modifiers changed from: 0000 */
        public void setPageTitle(int i, String str) {
            if (Strings.isEmpty(str)) {
                this.customTitles.remove(Integer.valueOf(i));
            } else {
                this.customTitles.put(Integer.valueOf(i), str);
            }
            HomeMessagesActivity.this.tabLayout.setupWithViewPager(HomeMessagesActivity.this.viewPager);
        }
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, HomeMessagesActivity.class);
    }

    public static Intent newStartIntentForInbox(Context context) {
        return new Intent(context, HomeMessagesActivity.class).putExtra(EXTRA_INITIAL_TAB, 1);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.generic_tabbed_activity_with_fab);
        MaterialUtils.enableAppBarScrollIfLollipop(this);
        MaterialUtils.setFixedFooterScrollingBehavior(getActivity(), true);
        MaterialUtils.removeDefaultToolbarElevation(this);
        initUi(bundle);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (isFabVisible()) {
            MaterialUtils.applyPaddingAndShowFabAfterDelay(this.fab);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        LeftDrawerMenu.invalidateBadges();
        if (isFabVisible()) {
            this.fab.hide();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.adapter.saveInstanceState(bundle);
    }

    private boolean isFabVisible() {
        return this.tabLayout.getSelectedTabPosition() == 1;
    }

    private void initUi(Bundle bundle) {
        this.fab.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                HomeMessagesActivity.this.getNavigationHelper().withIntent(ComposeMessageActivity.newStartIntent(HomeMessagesActivity.this, "messages")).startActivity(112);
            }
        });
        this.adapter = new PagerAdapter(this, getSupportFragmentManager());
        this.adapter.loadInstanceState(bundle);
        this.viewPager.setAdapter(this.adapter);
        this.viewPager.addOnPageChangeListener(this.onPageChangeListener);
        this.tabLayout.setupWithViewPager(this.viewPager);
        if (bundle == null) {
            int intExtra = getIntent().getIntExtra(EXTRA_INITIAL_TAB, 0);
            if (intExtra > 0) {
                this.viewPager.setCurrentItem(intExtra);
            }
        }
    }
}
