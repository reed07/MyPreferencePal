package com.myfitnesspal.feature.challenges.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.ui.view.ChallengeBannerImageView;
import com.myfitnesspal.feature.challenges.ui.view.PrizesView;
import com.myfitnesspal.feature.challenges.ui.viewmodel.ChallengePrizesViewModel;
import com.myfitnesspal.feature.images.service.ImageService;
import dagger.Lazy;
import javax.inject.Inject;

public class JoinedPrizesFragment extends ChallengeTabFragmentBase {
    @BindView(2131362117)
    ChallengeBannerImageView bannerImageView;
    @Inject
    Lazy<ImageService> imageService;
    @BindView(2131362608)
    FrameLayout prizesContainer;

    public static JoinedPrizesFragment newInstance(ChallengePrizesViewModel challengePrizesViewModel) {
        JoinedPrizesFragment joinedPrizesFragment = new JoinedPrizesFragment();
        joinedPrizesFragment.setViewModel(challengePrizesViewModel);
        return joinedPrizesFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.joined_prizes_layout, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        ChallengePrizesViewModel challengePrizesViewModel = (ChallengePrizesViewModel) getViewModel();
        this.bannerImageView.setBannerImage(getActivity(), challengePrizesViewModel.getBannerImage(), this.imageService);
        new PrizesView(challengePrizesViewModel, this.imageService).addView(getActivity(), this.prizesContainer);
    }
}
