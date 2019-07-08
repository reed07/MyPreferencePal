package com.myfitnesspal.feature.challenges.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.ui.activity.InviteSourceListActivity;
import com.myfitnesspal.feature.challenges.ui.adapter.ChallengeParticipantsWithBadgesListAdapter;
import com.myfitnesspal.feature.challenges.ui.view.ChallengeBannerImageView;
import com.myfitnesspal.feature.challenges.ui.viewmodel.ChallengeFriendsAndAchievementsViewModel;
import com.myfitnesspal.feature.challenges.util.ChallengesAnalyticsHelper;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.DeviceInfo;
import com.uacf.core.util.TextViewUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;

public class ChallengeFriendsFragment extends ChallengeTabFragmentBase {
    @BindView(2131362117)
    ChallengeBannerImageView bannerImageView;
    /* access modifiers changed from: private */
    public String challengeId;
    /* access modifiers changed from: private */
    public String challengeName;
    @Inject
    Lazy<ChallengesAnalyticsHelper> challengesAnalyticsHelper;
    @Inject
    Lazy<DeviceInfo> deviceInfo;
    /* access modifiers changed from: private */
    public ArrayList<String> friendUserIdsInChallenge;
    @BindView(2131362974)
    ListView friends;
    private View headerView;
    @Inject
    Lazy<ImageService> imageService;
    @BindView(2131362841)
    View inviteCardContainer;
    /* access modifiers changed from: private */
    public ChallengeFriendsAndAchievementsViewModel viewModel;

    public static ChallengeFriendsFragment newInstance(ChallengeFriendsAndAchievementsViewModel challengeFriendsAndAchievementsViewModel, String str, String str2, ArrayList<String> arrayList, boolean z) {
        ChallengeFriendsFragment challengeFriendsFragment = new ChallengeFriendsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("challenge_id", str);
        bundle.putString("challenge_name", str2);
        bundle.putStringArrayList(Extras.FRIENDS_IN_CHALLENGE, arrayList);
        bundle.putBoolean(Extras.IS_CHALLENGE_PRIVATE, z);
        challengeFriendsFragment.setArguments(bundle);
        challengeFriendsFragment.setViewModel(challengeFriendsAndAchievementsViewModel);
        return challengeFriendsFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.challenge_details_friends_fragment, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setHasOptionsMenu(true);
        MaterialUtils.enableListViewNestedScrolling(this.friends);
        this.challengeId = BundleUtils.getString(getArguments(), "challenge_id");
        this.challengeName = BundleUtils.getString(getArguments(), "challenge_name");
        this.friendUserIdsInChallenge = BundleUtils.getStringArrayList(getArguments(), Extras.FRIENDS_IN_CHALLENGE);
        this.viewModel = (ChallengeFriendsAndAchievementsViewModel) super.getViewModel();
        updateUi();
    }

    private void updateUi() {
        if (this.viewModel != null) {
            this.bannerImageView.setBannerImage(getActivity(), this.viewModel.getBannerImage(), this.imageService);
            ViewUtils.setVisible(!this.viewModel.hasChallengeEnded(), this.inviteCardContainer);
            if (this.headerView == null) {
                this.headerView = View.inflate(getActivity(), R.layout.challenges_invite_friends_header, null);
                TextViewUtils.setText((TextView) ViewUtils.findById(this.headerView, R.id.tv_metric_logged), getString(ChallengesUtil.getStringResourceIdBasedOnCriteriaType(this.viewModel.getCriteriaTypeForChallenge())));
                this.friends.addHeaderView(this.headerView);
            }
            View findById = ViewUtils.findById(this.headerView, R.id.notLoadedDivider);
            View findById2 = ViewUtils.findById(this.headerView, R.id.tvParticipantNotLoaded);
            boolean notEmpty = CollectionUtils.notEmpty((Collection<?>) this.viewModel.getFriendsViewModel().getFriends());
            ViewUtils.setVisible(!notEmpty, findById);
            ViewUtils.setVisible(!notEmpty, findById2);
            this.inviteCardContainer.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ((ChallengesAnalyticsHelper) ChallengeFriendsFragment.this.challengesAnalyticsHelper.get()).reportDetailsFriendsTabInviteFriendsEvent(ChallengeFriendsFragment.this.challengeName, ChallengeFriendsFragment.this.challengeId);
                    ChallengeFriendsFragment.this.getNavigationHelper().withIntent(InviteSourceListActivity.newStartIntent(ChallengeFriendsFragment.this.getActivity(), ChallengeFriendsFragment.this.challengeId, ChallengeFriendsFragment.this.challengeName, ChallengeFriendsFragment.this.friendUserIdsInChallenge, ChallengeFriendsFragment.this.viewModel.getChallengeSocial())).startActivity(18);
                }
            });
            if (notEmpty) {
                TextView textView = (TextView) ViewUtils.findById(this.headerView, R.id.tvFriendsInChallenge);
                textView.setEnabled(false);
                textView.setOnClickListener(null);
            }
            createAndSetAdapter();
        }
    }

    private void createAndSetAdapter() {
        ListView listView = this.friends;
        ChallengeParticipantsWithBadgesListAdapter challengeParticipantsWithBadgesListAdapter = new ChallengeParticipantsWithBadgesListAdapter(getActivity().getLayoutInflater(), this.viewModel.getFriendsViewModel().getFriends(), this.viewModel.getAchievementsViewModel(), this.viewModel.getMainAchievements(), R.layout.challenge_participant_with_badges_item, this.imageService, getActivity(), getNavigationHelper(), this.challengesAnalyticsHelper, (DeviceInfo) this.deviceInfo.get(), getSession().getUser().getUsername(), true);
        listView.setAdapter(challengeParticipantsWithBadgesListAdapter);
    }
}
