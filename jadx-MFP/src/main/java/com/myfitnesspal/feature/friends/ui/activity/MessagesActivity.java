package com.myfitnesspal.feature.friends.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.drawer.ui.view.LeftDrawerMenu;
import com.myfitnesspal.feature.friends.ui.fragment.MessagesFragment;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.util.MaterialUtils;

public class MessagesActivity extends MfpActivity {
    @BindView(2131362177)
    FloatingActionButton composeMessage;
    @BindView(2131363057)
    ViewPager messagesPager;
    @BindView(2131363774)
    TabLayout tabLayout;

    private class MessagesPagerAdapter extends FragmentPagerAdapter {
        MessagesPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            return MessagesFragment.newInstance(Type.values()[i]);
        }

        public int getCount() {
            return Type.values().length;
        }

        public CharSequence getPageTitle(int i) {
            return MessagesActivity.this.getString(Type.values()[i].getTitleResId());
        }
    }

    public enum Type {
        ReceivedMessages(R.string.inboxBtn, com.myfitnesspal.shared.ui.view.EmptyStateView.Type.Inbox),
        SentMessages(R.string.sentBtn, com.myfitnesspal.shared.ui.view.EmptyStateView.Type.SentMessages);
        
        private final com.myfitnesspal.shared.ui.view.EmptyStateView.Type emptyStateType;
        private final int titleResId;

        private Type(int i, com.myfitnesspal.shared.ui.view.EmptyStateView.Type type) {
            this.titleResId = i;
            this.emptyStateType = type;
        }

        public int getTitleResId() {
            return this.titleResId;
        }

        public com.myfitnesspal.shared.ui.view.EmptyStateView.Type getEmptyStateType() {
            return this.emptyStateType;
        }
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, MessagesActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.messages);
        MaterialUtils.removeDefaultToolbarElevation(this);
        MaterialUtils.enableAppBarScrollIfLollipop(this);
        MaterialUtils.setFixedFooterScrollingBehavior(this, true);
        this.messagesPager.setAdapter(new MessagesPagerAdapter(getSupportFragmentManager()));
        this.tabLayout.setupWithViewPager(this.messagesPager);
        setupListeners();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        LeftDrawerMenu.invalidateBadges();
    }

    private void setupListeners() {
        this.composeMessage.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MessagesActivity.this.getNavigationHelper().withIntent(ComposeMessageActivity.newStartIntent(MessagesActivity.this, "messages")).startActivity(112);
            }
        });
        this.messagesPager.addOnPageChangeListener(new SimpleOnPageChangeListener() {
            public void onPageSelected(int i) {
                MessagesActivity.this.composeMessage.show();
            }
        });
    }
}
