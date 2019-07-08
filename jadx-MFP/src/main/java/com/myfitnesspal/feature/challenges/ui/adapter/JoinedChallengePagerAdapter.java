package com.myfitnesspal.feature.challenges.ui.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.myfitnesspal.feature.challenges.model.ChallengeDynamicTab;
import com.myfitnesspal.feature.challenges.ui.fragment.ChallengeFriendsFragment;
import com.myfitnesspal.feature.challenges.ui.fragment.ChallengeTabFragmentBase;
import com.myfitnesspal.feature.challenges.ui.fragment.ChallengeWebViewFragment;
import com.myfitnesspal.feature.challenges.ui.fragment.JoinedChallengeSummaryFragment;
import com.myfitnesspal.feature.challenges.ui.fragment.JoinedPrizesFragment;
import com.myfitnesspal.feature.challenges.ui.viewmodel.ChallengeViewModel;
import com.myfitnesspal.feature.challenges.ui.viewmodel.JoinedSummaryViewModel;
import com.uacf.core.util.CollectionUtils;
import java.util.Collection;
import java.util.List;

public class JoinedChallengePagerAdapter extends FragmentPagerAdapter {
    public static final int DEFAULT_MIN_TABS_FOR_JOINED_CHALLENGE = 4;
    public static final int MIN_TABS_FOR_JOINED_CHALLENGE_NO_PRIZE = 3;
    private int minTabsForJoinedChallenge = 4;
    private ChallengeViewModel parentChallengeViewModel;
    private final int tabCount;
    private List<String> tabTitles;
    private List<ChallengeDynamicTab> tabs;

    public JoinedChallengePagerAdapter(ChallengeViewModel challengeViewModel, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.parentChallengeViewModel = challengeViewModel;
        this.tabs = challengeViewModel.getChallengeDynamicTabs();
        if (!challengeViewModel.shouldAddPrizesTab()) {
            this.minTabsForJoinedChallenge = 3;
        }
        this.tabCount = CollectionUtils.size((Collection<?>) this.tabs) + this.minTabsForJoinedChallenge;
        this.tabTitles = challengeViewModel.getTabTitlesForChallenge();
    }

    public ChallengeTabFragmentBase getItem(int i) {
        ChallengeTabFragmentBase challengeTabFragmentBase;
        ChallengeWebViewFragment challengeWebViewFragment;
        switch (i) {
            case 0:
                return JoinedChallengeSummaryFragment.newInstance(new JoinedSummaryViewModel(this.parentChallengeViewModel));
            case 1:
                return ChallengeFriendsFragment.newInstance(this.parentChallengeViewModel.getChallengeFriendsAndAchievementsViewModel(), this.parentChallengeViewModel.getChallengeId(), this.parentChallengeViewModel.getChallengeTitle(), this.parentChallengeViewModel.getFriendUserIdsInChallenge(), this.parentChallengeViewModel.isChallengePrivate());
            case 2:
                if (this.parentChallengeViewModel.shouldAddPrizesTab()) {
                    challengeTabFragmentBase = JoinedPrizesFragment.newInstance(this.parentChallengeViewModel.getPrizesViewModel());
                } else {
                    challengeTabFragmentBase = ChallengeWebViewFragment.newInstance(this.parentChallengeViewModel.getRulesUrl(), this.parentChallengeViewModel.getBannerImage());
                }
                return challengeTabFragmentBase;
            case 3:
                if (this.parentChallengeViewModel.shouldAddPrizesTab()) {
                    challengeWebViewFragment = ChallengeWebViewFragment.newInstance(this.parentChallengeViewModel.getRulesUrl(), this.parentChallengeViewModel.getBannerImage());
                } else {
                    challengeWebViewFragment = ChallengeWebViewFragment.newInstanceForSponsor(((ChallengeDynamicTab) this.tabs.get(i - this.minTabsForJoinedChallenge)).getUrl());
                }
                return challengeWebViewFragment;
            default:
                return ChallengeWebViewFragment.newInstanceForSponsor(((ChallengeDynamicTab) this.tabs.get(i - this.minTabsForJoinedChallenge)).getUrl());
        }
    }

    public int getCount() {
        return this.tabCount;
    }

    public CharSequence getPageTitle(int i) {
        return (CharSequence) this.tabTitles.get(i);
    }
}
