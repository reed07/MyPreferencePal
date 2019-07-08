package com.myfitnesspal.feature.challenges.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.ui.view.AchievementsView;
import com.myfitnesspal.feature.challenges.ui.view.ChallengeBannerImageView;
import com.myfitnesspal.feature.challenges.ui.view.PrizesView;
import com.myfitnesspal.feature.challenges.ui.viewmodel.ChallengePrizesTabViewModel;
import com.myfitnesspal.feature.challenges.util.AchievementAdapterType;
import com.myfitnesspal.feature.images.service.ImageService;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Collection;
import javax.inject.Inject;

public class ChallengePrizesFragment extends ChallengeTabFragmentBase {
    @BindView(2131362600)
    FrameLayout achievementContainer;
    @BindView(2131362117)
    ChallengeBannerImageView challengeBannerImageView;
    @Inject
    Lazy<ImageService> imageService;
    @Nullable
    @BindView(2131362603)
    FrameLayout prizeContainer;

    public static ChallengePrizesFragment newInstance(ChallengePrizesTabViewModel challengePrizesTabViewModel) {
        ChallengePrizesFragment challengePrizesFragment = new ChallengePrizesFragment();
        challengePrizesFragment.setViewModel(challengePrizesTabViewModel);
        return challengePrizesFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.challenge_prizes_fragment, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setHasOptionsMenu(true);
        setPrizesView();
    }

    private void setPrizesView() {
        ChallengePrizesTabViewModel challengePrizesTabViewModel = (ChallengePrizesTabViewModel) getViewModel();
        if (challengePrizesTabViewModel != null) {
            this.challengeBannerImageView.setBannerImage(getActivity(), challengePrizesTabViewModel.getBannerImage(), this.imageService);
            if (CollectionUtils.notEmpty((Collection<?>) challengePrizesTabViewModel.getPrizesViewModel().getChallengePrizeList())) {
                ViewUtils.setVisible(true, this.prizeContainer);
                new PrizesView(challengePrizesTabViewModel.getPrizesViewModel(), this.imageService).addView(getActivity(), this.prizeContainer);
            }
            AchievementsView achievementsView = new AchievementsView(challengePrizesTabViewModel.getAchievements(), this.imageService, getNavigationHelper(), challengePrizesTabViewModel.getChallengeName(), challengePrizesTabViewModel.getChallengeId(), challengePrizesTabViewModel.isChallengePrivate(), AchievementAdapterType.Unjoined);
            achievementsView.addView(getActivity(), this.achievementContainer, false);
        }
    }
}
