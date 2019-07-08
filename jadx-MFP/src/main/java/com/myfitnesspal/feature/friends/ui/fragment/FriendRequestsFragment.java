package com.myfitnesspal.feature.friends.ui.fragment;

import android.content.DialogInterface.OnClickListener;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.friends.ui.activity.FriendsActivity;
import com.myfitnesspal.feature.friends.ui.adapter.FriendRequestsAdapter;
import com.myfitnesspal.feature.friends.ui.adapter.FriendRequestsAdapter.OnFriendRequestClickListener;
import com.myfitnesspal.feature.friends.ui.viewmodel.FriendsRequestsViewModel;
import com.myfitnesspal.feature.friends.ui.viewmodel.FriendsRequestsViewModel.OnFriendRequestCompletedListener;
import com.myfitnesspal.feature.friends.ui.viewmodel.FriendsRequestsViewModel.RequestType;
import com.myfitnesspal.feature.profile.ui.activity.ProfileView;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.Property;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.model.v15.FriendRequestObject;
import com.myfitnesspal.shared.service.friends.FriendService;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

public class FriendRequestsFragment extends MfpFragment {
    @BindView(2131362676)
    TextView currentlyNoFriendsRequests;
    /* access modifiers changed from: private */
    public FriendRequestsAdapter friendRequestsAdapter;
    @Inject
    Lazy<FriendService> friendService;
    @BindView(2131362677)
    View loadingFriendsRequestsProgressIndicatorLayout;
    @Inject
    Lazy<NavigationHelper> navigationHelper;
    private final OnFriendRequestCompletedListener requestActionCompletedListener = new FriendRequestCompletedListener();
    @BindView(2131362678)
    RecyclerView requestsList;
    /* access modifiers changed from: private */
    public FriendsRequestsViewModel viewModel;

    private class FriendRequestCompletedListener implements OnFriendRequestCompletedListener {
        private FriendRequestCompletedListener() {
        }

        public void onSuccess(RequestType requestType, long j) {
            int i;
            if (requestType == RequestType.Accept) {
                i = 2;
                if (FriendRequestsFragment.this.getActivity() instanceof FriendsActivity) {
                    ((FriendsActivity) FriendRequestsFragment.this.getActivity()).updateFriendsList();
                }
            } else {
                i = 1;
            }
            FriendRequestsFragment.this.friendRequestsAdapter.updateRequestStatus(i, j);
        }

        public void onFailure(RequestType requestType, long j, Throwable th) {
            FriendRequestsFragment.this.friendRequestsAdapter.updateRequestStatus(0, j);
            FriendRequestsFragment.this.showErrorMessage(requestType, th);
        }
    }

    public static FriendRequestsFragment newInstance() {
        return new FriendRequestsFragment();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.requests_subview, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        initViews();
        initViewModel();
    }

    public void onViewModelPropertyChanged(Observable observable, int i) {
        super.onViewModelPropertyChanged(observable, i);
        if (i == Property.LOAD_STATE) {
            if (this.viewModel.getState() == State.Loading) {
                showLoading();
            } else if (this.viewModel.getState() == State.Error) {
                setRequests(null);
                new MfpAlertDialogBuilder(getContext()).setTitle((int) R.string.alert).setMessage((CharSequence) getString(R.string.failLoadingFriends)).setPositiveButton((int) R.string.dismiss, (OnClickListener) null).create().show();
            }
        } else if (i == FriendsRequestsViewModel.Property.FRIEND_REQUEST_LIST) {
            setRequests(this.viewModel.getFriendRequests());
        }
    }

    public void onResume() {
        super.onResume();
        this.viewModel.setRequestActionCompletedListener(this.requestActionCompletedListener);
        this.viewModel.load(new String[0]);
    }

    public void onPause() {
        super.onPause();
        this.viewModel.setRequestActionCompletedListener(null);
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z) {
            FriendsRequestsViewModel friendsRequestsViewModel = this.viewModel;
            if (friendsRequestsViewModel != null) {
                friendsRequestsViewModel.load(new String[0]);
            }
        }
    }

    private void initViewModel() {
        this.viewModel = (FriendsRequestsViewModel) getViewModel();
        if (this.viewModel == null) {
            this.viewModel = (FriendsRequestsViewModel) setViewModel(new FriendsRequestsViewModel(getRunner(), this.friendService));
        }
    }

    private void initViews() {
        this.requestsList.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
    }

    private void setRequests(List<FriendRequestObject> list) {
        this.loadingFriendsRequestsProgressIndicatorLayout.setVisibility(8);
        if (CollectionUtils.isEmpty((Collection<?>) list)) {
            this.currentlyNoFriendsRequests.setVisibility(0);
            return;
        }
        this.friendRequestsAdapter = new FriendRequestsAdapter(getContext(), list);
        this.friendRequestsAdapter.setRequestClickListener(new OnFriendRequestClickListener() {
            public void onFriendClick(FriendRequestObject friendRequestObject) {
                String str;
                Bundle bundle = new Bundle();
                bundle.putBoolean(Extras.IS_FRIEND_REQUEST, true);
                if (Strings.notEmpty(friendRequestObject.getBody())) {
                    str = friendRequestObject.getBody();
                } else {
                    str = FriendRequestsFragment.this.getContext().getString(R.string.no_message);
                }
                bundle.putString(Extras.MESSAGE_BODY, str.replace("\r", ""));
                ((NavigationHelper) FriendRequestsFragment.this.navigationHelper.get()).withContext(FriendRequestsFragment.this.getContext()).withExtras(bundle).withIntent(ProfileView.newStartIntent(FriendRequestsFragment.this.getContext(), friendRequestObject.getOtherPartyUsername(), friendRequestObject.getOtherPartyUid())).startActivity(113);
            }

            public void onRequestAccepted(FriendRequestObject friendRequestObject) {
                FriendRequestsFragment.this.viewModel.acceptFriendRequest(friendRequestObject.getRequestMasterId(), friendRequestObject.getRequestUid());
            }

            public void onRequestDeclined(FriendRequestObject friendRequestObject) {
                FriendRequestsFragment.this.viewModel.declineFriendRequest(friendRequestObject.getRequestMasterId(), friendRequestObject.getRequestUid());
            }
        });
        this.requestsList.setVisibility(0);
        this.requestsList.setAdapter(this.friendRequestsAdapter);
    }

    /* access modifiers changed from: private */
    public void showErrorMessage(RequestType requestType, Throwable th) {
        String str;
        Ln.e(th, requestType == RequestType.Accept ? "Could not accept friend request" : "Could not reject friend request", new Object[0]);
        if (Strings.notEmpty(th.getMessage())) {
            str = th.getMessage();
        } else if (requestType == RequestType.Accept) {
            str = getContext().getString(R.string.failAcceptFriendRequest);
        } else {
            str = getContext().getString(R.string.failRejectFriendRequest);
        }
        new MfpAlertDialogBuilder(getContext()).setTitle((int) R.string.request_failed).setMessage((CharSequence) str).setPositiveButton((int) R.string.dismiss, (OnClickListener) null).create().show();
    }

    private void showLoading() {
        this.loadingFriendsRequestsProgressIndicatorLayout.setVisibility(0);
        this.currentlyNoFriendsRequests.setVisibility(8);
        this.requestsList.setVisibility(8);
    }
}
