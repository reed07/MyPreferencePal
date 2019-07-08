package com.myfitnesspal.feature.challenges.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.model.ChallengeImageOutput;
import com.myfitnesspal.feature.challenges.ui.adapter.ChallengeAchievement;
import com.myfitnesspal.feature.challenges.ui.view.ChallengeDetailsFriendsView;
import com.myfitnesspal.feature.challenges.ui.view.ChallengeDetailsSummaryView;
import com.myfitnesspal.feature.challenges.ui.view.ChallengeDynamicLinkView;
import com.myfitnesspal.feature.challenges.ui.view.UnjoinedChallengeDetailSubView;
import com.myfitnesspal.feature.challenges.ui.viewmodel.ChallengeDetailsViewModel;
import com.myfitnesspal.feature.challenges.util.ChallengesAnalyticsHelper;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil;
import com.myfitnesspal.feature.images.service.ImageService;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.DeviceInfo;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

public class ChallengeDetailsFragment extends ChallengeTabFragmentBase {
    @BindView(2131362865)
    ImageView ach1;
    @BindView(2131362866)
    ImageView ach2;
    @BindView(2131362867)
    ImageView ach3;
    @Inject
    Lazy<ChallengesAnalyticsHelper> challengesAnalyticsHelper;
    @Inject
    Lazy<DeviceInfo> deviceInfo;
    @BindView(2131362358)
    View dynamicLinksCardView;
    @BindView(2131362359)
    LinearLayout dynamicLinksContainer;
    @BindView(2131362601)
    FrameLayout friendsContainer;
    @Inject
    Lazy<ImageService> imageService;
    @BindView(2131362602)
    FrameLayout summaryContainer;

    public static ChallengeDetailsFragment newInstance(ChallengeDetailsViewModel challengeDetailsViewModel) {
        ChallengeDetailsFragment challengeDetailsFragment = new ChallengeDetailsFragment();
        challengeDetailsFragment.setViewModel(challengeDetailsViewModel);
        return challengeDetailsFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.challenge_details_fragment, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setHasOptionsMenu(true);
        setSummarySection();
    }

    private void setSummarySection() {
        ChallengeDetailsViewModel challengeDetailsViewModel = (ChallengeDetailsViewModel) getViewModel();
        if (challengeDetailsViewModel != null) {
            ChallengeDetailsSummaryView challengeDetailsSummaryView = new ChallengeDetailsSummaryView(challengeDetailsViewModel.getSummaryViewModel(), this.imageService);
            UnjoinedChallengeDetailSubView unjoinedChallengeDetailSubView = null;
            if (Strings.notEmpty(challengeDetailsViewModel.getChallengeGoal())) {
                unjoinedChallengeDetailSubView = new UnjoinedChallengeDetailSubView(getActivity());
                unjoinedChallengeDetailSubView.setData(challengeDetailsViewModel.getChallengeTitle(), challengeDetailsViewModel.getChallengeSponsor(), challengeDetailsViewModel.getChallengeGoal());
            }
            challengeDetailsSummaryView.addView(getActivity(), this.summaryContainer, unjoinedChallengeDetailSubView);
            if (challengeDetailsViewModel.getFriendsViewModel() != null) {
                ChallengeDetailsFriendsView challengeDetailsFriendsView = new ChallengeDetailsFriendsView(challengeDetailsViewModel.getFriendsViewModel(), this.imageService, getNavigationHelper(), this.challengesAnalyticsHelper, this.deviceInfo, getSession());
                challengeDetailsFriendsView.addView(getActivity(), this.friendsContainer);
            }
            List previewAchievements = challengeDetailsViewModel.getPreviewAchievements();
            setupAchievementImageView(previewAchievements, 0, this.ach1);
            setupAchievementImageView(previewAchievements, 1, this.ach2);
            setupAchievementImageView(previewAchievements, 2, this.ach3);
            setupDynamicLinks(challengeDetailsViewModel);
        }
    }

    private void setupAchievementImageView(List<ChallengeAchievement> list, int i, ImageView imageView) {
        if (i < list.size()) {
            ViewUtils.setVisible(imageView);
            ChallengesUtil.setImageToImageView(getActivity(), new ChallengeImageOutput(((ChallengeAchievement) list.get(i)).getImageUrl()), imageView, this.imageService);
            return;
        }
        ViewUtils.setGone(imageView);
    }

    private void setupDynamicLinks(ChallengeDetailsViewModel challengeDetailsViewModel) {
        ChallengeDynamicLinkView challengeDynamicLinkView = new ChallengeDynamicLinkView();
        if (CollectionUtils.isEmpty((Collection<?>) challengeDetailsViewModel.getDynamicLinks())) {
            ViewUtils.setGone(this.dynamicLinksCardView);
        } else {
            this.dynamicLinksContainer.addView(challengeDynamicLinkView.createViewAndReturn(getActivity(), challengeDetailsViewModel.getDynamicLinks(), challengeDetailsViewModel.getChallengeSponsor(), challengeDetailsViewModel.getChallengeTitle(), challengeDetailsViewModel.getChallengeId(), this.challengesAnalyticsHelper));
        }
    }
}
