package com.myfitnesspal.feature.challenges.ui.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.myfitnesspal.feature.challenges.ui.fragment.ChallengeDetailsFragment;
import com.myfitnesspal.feature.challenges.ui.fragment.ChallengePrizesFragment;
import com.myfitnesspal.feature.challenges.ui.fragment.ChallengeTabFragmentBase;
import com.myfitnesspal.feature.challenges.ui.fragment.ChallengeWebViewFragment;
import com.myfitnesspal.feature.challenges.ui.viewmodel.ChallengeDetailsViewModel;
import com.myfitnesspal.feature.challenges.ui.viewmodel.ChallengePrizesTabViewModel;
import com.myfitnesspal.feature.challenges.ui.viewmodel.ChallengeViewModel;
import java.util.List;

public class UnjoinedChallengePagerAdapter extends FragmentPagerAdapter {
    private static final int ITEM_ID_OFFSET = 100;
    private static final int MIN_TABS_FOR_SINGLE_CHALLENGE = 3;
    private final ChallengeViewModel parentChallengeViewModel;
    private final int tabCount = 3;
    private final List<String> tabTitles;

    public long getItemId(int i) {
        return (long) (i + 100);
    }

    public UnjoinedChallengePagerAdapter(ChallengeViewModel challengeViewModel, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.parentChallengeViewModel = challengeViewModel;
        this.tabTitles = challengeViewModel.getTabTitlesForChallenge();
    }

    public ChallengeTabFragmentBase getItem(int i) {
        switch (i) {
            case 0:
                return ChallengeDetailsFragment.newInstance(new ChallengeDetailsViewModel(this.parentChallengeViewModel));
            case 1:
                ChallengePrizesTabViewModel challengePrizesTabViewModel = new ChallengePrizesTabViewModel(this.parentChallengeViewModel.getBannerImage(), this.parentChallengeViewModel.getChallengeAchievements(false), this.parentChallengeViewModel.getPrizesViewModel(), this.parentChallengeViewModel.getChallengeTitle(), this.parentChallengeViewModel.getChallengeId(), this.parentChallengeViewModel.isChallengePrivate());
                return ChallengePrizesFragment.newInstance(challengePrizesTabViewModel);
            default:
                return ChallengeWebViewFragment.newInstance(this.parentChallengeViewModel.getRulesUrl(), this.parentChallengeViewModel.getBannerImage());
        }
    }

    public int getCount() {
        return this.tabCount;
    }

    public CharSequence getPageTitle(int i) {
        return (CharSequence) this.tabTitles.get(i);
    }
}
