package com.myfitnesspal.feature.friends.ui.fragment;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.databinding.Observable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addfriends.ui.activity.AddFriendsSplash;
import com.myfitnesspal.feature.friends.ui.adapter.FriendsListAdapter;
import com.myfitnesspal.feature.friends.ui.adapter.FriendsListAdapter.OnItemClickListener;
import com.myfitnesspal.feature.friends.ui.viewmodel.FriendsListViewModel;
import com.myfitnesspal.feature.profile.ui.activity.ProfileView;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.Property;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.model.mapper.impl.MiniUserInfoMapper;
import com.myfitnesspal.shared.model.v1.MiniUserInfo;
import com.myfitnesspal.shared.service.friends.FriendService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import dagger.Lazy;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class FriendsListFragment extends MfpFragment {
    @Inject
    Lazy<FriendService> friendService;
    @BindView(2131362685)
    View friendsProgressBar;
    @BindView(2131362686)
    RecyclerView friendsRecyclerView;
    @Inject
    Lazy<MiniUserInfoMapper> miniUserInfoMapper;
    @Inject
    Lazy<NavigationHelper> navigationHelper;
    @BindView(2131362684)
    ViewGroup noFriendsLayout;
    @Inject
    Lazy<UserWeightService> userWeightService;
    private FriendsListViewModel viewModel;

    public static FriendsListFragment newInstance() {
        return new FriendsListFragment();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        initViewModel();
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.friends_subview, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        initViews();
    }

    public void onViewModelPropertyChanged(Observable observable, int i) {
        super.onViewModelPropertyChanged(observable, i);
        if (i == Property.LOAD_STATE) {
            if (this.viewModel.getState() == State.Loading) {
                showLoading();
            } else if (this.viewModel.getState() == State.Error) {
                showError();
            }
        } else if (i == FriendsListViewModel.Property.FRIENDS_LIST) {
            showListOrEmptyState(this.viewModel.friendsInfoMap());
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick({2131362684})
    public void onInviteFriendsClick() {
        ((NavigationHelper) this.navigationHelper.get()).withContext(getContext()).withIntent(AddFriendsSplash.newStartIntent(getContext())).startActivity(10);
    }

    public void updateLoadingState(State state) {
        if (isResumed()) {
            if (state == State.Loading) {
                showLoading();
            } else if (state == State.Error) {
                showError();
            }
        }
    }

    public void processFriendsList(List<MiniUserInfo> list) {
        if (isResumed()) {
            this.viewModel.load((List<MiniUserInfo>[]) new List[]{list});
        }
    }

    private void initViewModel() {
        this.viewModel = (FriendsListViewModel) getViewModel();
        if (this.viewModel == null) {
            this.viewModel = (FriendsListViewModel) setViewModel(new FriendsListViewModel(getRunner()));
        }
    }

    private void initViews() {
        this.friendsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
    }

    private void refreshFriendsList(Map<Character, List<MiniUserInfo>> map) {
        final Context context = getContext();
        FriendsListAdapter friendsListAdapter = new FriendsListAdapter(getContext(), map, (UserWeightService) this.userWeightService.get());
        friendsListAdapter.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(MiniUserInfo miniUserInfo) {
                ((NavigationHelper) FriendsListFragment.this.navigationHelper.get()).withContext(context).withExtra(Extras.USER_INFO, (Parcelable) miniUserInfo).withIntent(ProfileView.newStartIntent(context, miniUserInfo.getUsername(), miniUserInfo.getUid())).startActivity(36);
            }
        });
        this.friendsRecyclerView.setAdapter(friendsListAdapter);
    }

    private void showLoading() {
        this.friendsProgressBar.setVisibility(0);
        this.friendsRecyclerView.setVisibility(8);
        this.noFriendsLayout.setVisibility(8);
    }

    private void showError() {
        showListOrEmptyState(null);
        new MfpAlertDialogBuilder(getContext()).setTitle((int) R.string.alert).setMessage((int) R.string.failLoadingFriends).setPositiveButton((int) R.string.ok, (OnClickListener) null).create().show();
    }

    private void showListOrEmptyState(Map<Character, List<MiniUserInfo>> map) {
        this.friendsProgressBar.setVisibility(8);
        if (map == null || map.isEmpty()) {
            this.noFriendsLayout.setVisibility(0);
            this.friendsRecyclerView.setVisibility(8);
            return;
        }
        refreshFriendsList(map);
        this.noFriendsLayout.setVisibility(8);
        this.friendsRecyclerView.setVisibility(0);
    }
}
