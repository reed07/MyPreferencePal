package com.myfitnesspal.feature.challenges.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.ui.viewmodel.ChallengeDetailsFriendsViewModel;
import com.myfitnesspal.feature.images.service.ImageService;
import dagger.Lazy;
import javax.inject.Inject;

public class JoinedFriendsFragment extends ChallengeTabFragmentBase {
    @BindView(2131362606)
    FrameLayout friendsContainer;
    @Inject
    Lazy<ImageService> imageService;
    @BindView(2131362607)
    FrameLayout invitesContainer;

    private void setFriendsSection() {
    }

    public static JoinedFriendsFragment newInstance(ChallengeDetailsFriendsViewModel challengeDetailsFriendsViewModel) {
        JoinedFriendsFragment joinedFriendsFragment = new JoinedFriendsFragment();
        joinedFriendsFragment.setViewModel(challengeDetailsFriendsViewModel);
        return joinedFriendsFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.joined_friends_layout, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setHasOptionsMenu(true);
        setFriendsSection();
    }
}
