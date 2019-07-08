package com.myfitnesspal.feature.explore.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.blog.ui.fragment.BlogFragment;
import com.myfitnesspal.feature.challenges.ui.fragment.ChallengesFragment;
import com.myfitnesspal.feature.community.ui.fragment.CommunityFragment;
import com.myfitnesspal.feature.explore.analytics.ExploreAnalytics.CardType;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;

public class PrototypeExploreFragment extends MfpFragment {
    @BindView(2131363774)
    TabLayout tabLayout;
    @BindView(2131362658)
    ViewPager viewPager;

    private static class PagerAdapter extends FragmentPagerAdapter {
        public int getCount() {
            return 3;
        }

        PagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return ChallengesFragment.newInstance();
                case 1:
                    return BlogFragment.newInstance(null, null, null);
                case 2:
                    return CommunityFragment.newInstance(null);
                default:
                    throw new IllegalArgumentException("position is invalid");
            }
        }

        public CharSequence getPageTitle(int i) {
            switch (i) {
                case 0:
                    return "challenges";
                case 1:
                    return CardType.BLOG;
                case 2:
                    return "community";
                default:
                    throw new IllegalArgumentException("position is invalid");
            }
        }
    }

    public static PrototypeExploreFragment newInstance() {
        return new PrototypeExploreFragment();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.explore_fragment, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        ButterKnife.bind((Object) this, view);
        this.viewPager.setAdapter(new PagerAdapter(getChildFragmentManager()));
        this.tabLayout.setupWithViewPager(this.viewPager);
    }
}
