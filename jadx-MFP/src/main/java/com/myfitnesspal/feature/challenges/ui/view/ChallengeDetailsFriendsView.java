package com.myfitnesspal.feature.challenges.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.ui.adapter.ChallengeParticipantsWithBadgesListAdapter;
import com.myfitnesspal.feature.challenges.ui.viewmodel.ChallengeAchievementsViewModel;
import com.myfitnesspal.feature.challenges.ui.viewmodel.ChallengeDetailsFriendsViewModel;
import com.myfitnesspal.feature.challenges.ui.viewmodel.ChallengeFriendsAndAchievementsViewModel;
import com.myfitnesspal.feature.challenges.util.ChallengesAnalyticsHelper;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.ui.view.LinearLayoutListAdapterView;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.DeviceInfo;
import com.uacf.core.util.TextViewUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Collection;
import java.util.List;

public class ChallengeDetailsFriendsView {
    private final Lazy<ChallengesAnalyticsHelper> challengesAnalyticsHelper;
    private Lazy<DeviceInfo> deviceInfo;
    private final Lazy<ImageService> imageService;
    private final NavigationHelper navigationHelper;
    private Session session;
    private final ChallengeFriendsAndAchievementsViewModel viewModel;

    public ChallengeDetailsFriendsView(ChallengeFriendsAndAchievementsViewModel challengeFriendsAndAchievementsViewModel, Lazy<ImageService> lazy, NavigationHelper navigationHelper2, Lazy<ChallengesAnalyticsHelper> lazy2, Lazy<DeviceInfo> lazy3, Session session2) {
        this.viewModel = challengeFriendsAndAchievementsViewModel;
        this.imageService = lazy;
        this.navigationHelper = navigationHelper2;
        this.challengesAnalyticsHelper = lazy2;
        this.deviceInfo = lazy3;
        this.session = session2;
    }

    public void addView(Context context, ViewGroup viewGroup) {
        ViewGroup viewGroup2;
        LayoutInflater from = LayoutInflater.from(context);
        ChallengeDetailsFriendsViewModel friendsViewModel = this.viewModel.getFriendsViewModel();
        if (CollectionUtils.notEmpty((Collection<?>) friendsViewModel.getFriends())) {
            ViewGroup viewGroup3 = (ViewGroup) from.inflate(R.layout.challenge_details_friends, viewGroup, false);
            setupFriendsView(context, from, viewGroup3);
            viewGroup2 = viewGroup3;
        } else {
            viewGroup2 = (ViewGroup) from.inflate(R.layout.particpant_not_loaded, viewGroup, false);
            if (friendsViewModel.getState() == State.Loaded) {
                ViewUtils.setVisible(false, viewGroup);
            }
        }
        viewGroup.addView(viewGroup2);
    }

    private void setupFriendsView(Context context, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        ViewGroup viewGroup2 = viewGroup;
        List friends = this.viewModel.getFriendsViewModel().getFriends();
        LinearLayoutListAdapterView linearLayoutListAdapterView = (LinearLayoutListAdapterView) ViewUtils.findById(viewGroup2, R.id.llFriends);
        TextViewUtils.setText((TextView) ViewUtils.findById(viewGroup2, R.id.tv_metric_logged), context.getString(ChallengesUtil.getStringResourceIdBasedOnCriteriaType(this.viewModel.getCriteriaTypeForChallenge())));
        linearLayoutListAdapterView.setDivider((int) R.color.separator_light_grey, 1.0f);
        ChallengeAchievementsViewModel achievementsViewModel = this.viewModel.getAchievementsViewModel();
        List mainAchievements = this.viewModel.getMainAchievements();
        Lazy<ImageService> lazy = this.imageService;
        NavigationHelper navigationHelper2 = this.navigationHelper;
        Lazy<ChallengesAnalyticsHelper> lazy2 = this.challengesAnalyticsHelper;
        DeviceInfo deviceInfo2 = (DeviceInfo) this.deviceInfo.get();
        ChallengeParticipantsWithBadgesListAdapter challengeParticipantsWithBadgesListAdapter = r3;
        LinearLayoutListAdapterView linearLayoutListAdapterView2 = linearLayoutListAdapterView;
        ChallengeParticipantsWithBadgesListAdapter challengeParticipantsWithBadgesListAdapter2 = new ChallengeParticipantsWithBadgesListAdapter(layoutInflater, friends, achievementsViewModel, mainAchievements, R.layout.challenge_participant_with_badges_item, lazy, context, navigationHelper2, lazy2, deviceInfo2, this.session.getUser().getUsername(), false);
        linearLayoutListAdapterView2.setAdapter(challengeParticipantsWithBadgesListAdapter);
        int size = CollectionUtils.size((Collection<?>) friends) - 5;
        if (size > 0) {
            View findById = ViewUtils.findById(viewGroup2, R.id.more_container);
            TextView textView = (TextView) ViewUtils.findById(findById, R.id.tv_more_friends);
            findById.setVisibility(0);
            textView.setText(context.getResources().getQuantityString(R.plurals.more_friends, size, new Object[]{Integer.valueOf(size)}));
        }
    }
}
