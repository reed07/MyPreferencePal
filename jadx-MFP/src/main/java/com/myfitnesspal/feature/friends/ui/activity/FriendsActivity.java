package com.myfitnesspal.feature.friends.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addfriends.ui.activity.AddFriendsParent;
import com.myfitnesspal.feature.friends.ui.fragment.FriendRequestsFragment;
import com.myfitnesspal.feature.friends.ui.fragment.FriendsListFragment;
import com.myfitnesspal.feature.friends.ui.viewmodel.FriendsViewModel;
import com.myfitnesspal.feature.friends.ui.viewmodel.FriendsViewModel.Property;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.event.RefreshAdEvent;
import com.myfitnesspal.shared.model.AdUnit;
import com.myfitnesspal.shared.model.mapper.impl.MiniUserInfoMapper;
import com.myfitnesspal.shared.notification.MfpNotificationHandler;
import com.myfitnesspal.shared.service.friends.FriendService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.ExtrasUtils;
import dagger.Lazy;
import javax.inject.Inject;

public class FriendsActivity extends MfpActivity {
    private static final int MENU_ADD_FRIENDS = 907;
    public static final String SELECTED_TAB = "selectedTab";
    @Inject
    Lazy<FriendService> friendService;
    /* access modifiers changed from: private */
    public int friendsCount = 0;
    private final FriendsPagerAdapter friendsPagerAdapter = new FriendsPagerAdapter(getSupportFragmentManager());
    @BindView(2131362688)
    ViewPager friendsViewPager;
    @Inject
    Lazy<MfpNotificationHandler> mfpNotificationHandler;
    @Inject
    Lazy<MiniUserInfoMapper> miniUserInfoMapper;
    private final OnPageChangeListener onPageChangeListener = new PageChangeListener();
    @BindView(2131362687)
    TabLayout tabContainer;
    private FriendsViewModel viewModel;

    private class FriendsPagerAdapter extends FragmentPagerAdapter {
        private static final int POSITION_FRIENDS_LIST = 0;
        private static final int POSITION_FRIEND_REQUESTS = 1;
        private static final int TAB_COUNT = 2;
        private final SparseArray<MfpFragment> instantiatedFragments = new SparseArray<>();

        public int getCount() {
            return 2;
        }

        FriendsPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return FriendsListFragment.newInstance();
                case 1:
                    return FriendRequestsFragment.newInstance();
                default:
                    return null;
            }
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            MfpFragment mfpFragment = (MfpFragment) super.instantiateItem(viewGroup, i);
            this.instantiatedFragments.put(i, mfpFragment);
            return mfpFragment;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            this.instantiatedFragments.remove(i);
            super.destroyItem(viewGroup, i, obj);
        }

        public CharSequence getPageTitle(int i) {
            return getTabTitleForPosition(i);
        }

        /* access modifiers changed from: 0000 */
        public FriendsListFragment getListFragment() {
            return (FriendsListFragment) this.instantiatedFragments.get(0);
        }

        private String getTabTitleForPosition(int i) {
            Tab tab = Tab.values()[i];
            String string = FriendsActivity.this.getString(tab.getTitleResId());
            if (tab != Tab.Friends || FriendsActivity.this.friendsCount <= 0) {
                return string;
            }
            return String.format(FriendsActivity.this.getString(R.string.tab_friends_with_number), new Object[]{Integer.valueOf(FriendsActivity.this.friendsCount)});
        }
    }

    private class PageChangeListener implements OnPageChangeListener {
        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        private PageChangeListener() {
        }

        public void onPageSelected(int i) {
            Tab tab = Tab.values()[i];
            FriendsActivity.this.postEvent(new RefreshAdEvent());
            FriendsActivity.this.supportInvalidateOptionsMenu();
            FriendsActivity.this.getAnalyticsService().reportEvent(tab.getEventName());
        }
    }

    public enum Tab {
        Friends(R.string.friends, Events.FRIENDS_TAB_FRIENDLIST),
        Requests(R.string.requests, Events.FRIENDS_TAB_REQUESTS);
        
        private final String eventName;
        private final int titleResId;

        private Tab(int i, String str) {
            this.titleResId = i;
            this.eventName = str;
        }

        public int getTitleResId() {
            return this.titleResId;
        }

        public String getEventName() {
            return this.eventName;
        }
    }

    public String getAnalyticsScreenTag() {
        return Screens.FRIENDS;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, FriendsActivity.class);
    }

    public static Intent newStartIntent(Context context, long j, Tab tab) {
        return newStartIntent(context).putExtra(Extras.OBJECT_MASTER_DATABASE_ID, j).putExtra(SELECTED_TAB, tab.ordinal());
    }

    public static Intent newStartIntentForRequests(Context context) {
        return new Intent(context, FriendsActivity.class).putExtra(SELECTED_TAB, Tab.Requests.ordinal());
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.friends_view);
        component().inject(this);
        initViewModel();
        MaterialUtils.removeDefaultToolbarElevation(this);
        MaterialUtils.enableAppBarScrollIfLollipop(this);
        this.friendsViewPager.setAdapter(this.friendsPagerAdapter);
        int intExtra = ExtrasUtils.getIntExtra(getIntent(), "notification_id", Integer.MIN_VALUE);
        if (intExtra != Integer.MIN_VALUE) {
            ((MfpNotificationHandler) this.mfpNotificationHandler.get()).removeNotificationWithId(intExtra);
        }
        initViewPagerSelectionAndListener(BundleUtils.getInt(bundle, SELECTED_TAB, getTabOrdinalFromIntent(getIntent())));
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.viewModel.load(new Void[0]);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(SELECTED_TAB, getSelectedTab().ordinal());
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initViewPagerSelectionAndListener(getTabOrdinalFromIntent(intent));
    }

    public AdUnit getAdUnit() {
        switch (getSelectedTab()) {
            case Friends:
                return getAdUnitService().getFriendsTabFriendsScreenAdUnitValue();
            case Requests:
                return getAdUnitService().getFriendsTabRequestsScreenAdUnitValue();
            default:
                return getAdUnitService().getFriendsTabFriendsScreenAdUnitValue();
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!super.onPrepareOptionsMenu(menu)) {
            return false;
        }
        if (AnonymousClass1.$SwitchMap$com$myfitnesspal$feature$friends$ui$activity$FriendsActivity$Tab[getSelectedTab().ordinal()] == 1) {
            menu.add(0, MENU_ADD_FRIENDS, 0, R.string.addfriends_title).setIcon(R.drawable.ic_add_friend_22dp).setShowAsAction(2);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != MENU_ADD_FRIENDS) {
            return super.onOptionsItemSelected(menuItem);
        }
        navigateToAddToFriends();
        return true;
    }

    public void onViewModelPropertyChanged(Observable observable, int i) {
        super.onViewModelPropertyChanged(observable, i);
        FriendsListFragment listFragment = this.friendsPagerAdapter.getListFragment();
        if (listFragment != null) {
            if (i == Property.LOAD_STATE) {
                listFragment.updateLoadingState(this.viewModel.getState());
            } else if (i == Property.FRIENDS_LIST) {
                this.friendsCount = this.viewModel.getFriendsList().size();
                updateFriendsTabTitle();
                listFragment.processFriendsList(this.viewModel.getFriendsList());
            }
        }
    }

    public void updateFriendsList() {
        this.viewModel.load(new Void[0]);
    }

    private void initViewModel() {
        this.viewModel = (FriendsViewModel) getViewModel();
        if (this.viewModel == null) {
            this.viewModel = (FriendsViewModel) setViewModel(new FriendsViewModel(getRunner(), this.friendService, this.miniUserInfoMapper));
        }
    }

    private void initViewPagerSelectionAndListener(int i) {
        this.friendsViewPager.clearOnPageChangeListeners();
        this.friendsViewPager.setCurrentItem(i);
        this.friendsViewPager.addOnPageChangeListener(this.onPageChangeListener);
        this.tabContainer.setupWithViewPager(this.friendsViewPager);
    }

    private int getTabOrdinalFromIntent(Intent intent) {
        return ExtrasUtils.getInt(intent, SELECTED_TAB, Tab.Friends.ordinal());
    }

    private void navigateToAddToFriends() {
        getNavigationHelper().withIntent(AddFriendsParent.newStartIntent(this)).startActivity();
    }

    private void updateFriendsTabTitle() {
        android.support.design.widget.TabLayout.Tab tabAt = this.tabContainer.getTabAt(Tab.Friends.ordinal());
        if (tabAt != null) {
            tabAt.setText((CharSequence) String.format(getString(R.string.tab_friends_with_number), new Object[]{Integer.valueOf(this.friendsCount)}));
        }
    }

    private Tab getSelectedTab() {
        return Tab.values()[this.friendsViewPager.getCurrentItem()];
    }
}
