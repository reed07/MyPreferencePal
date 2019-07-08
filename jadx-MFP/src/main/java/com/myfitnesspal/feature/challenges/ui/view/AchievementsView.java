package com.myfitnesspal.feature.challenges.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.ui.activity.ChallengeAchievementActivity;
import com.myfitnesspal.feature.challenges.ui.adapter.ChallengeAchievement;
import com.myfitnesspal.feature.challenges.ui.adapter.ChallengeAchievementsListAdapter;
import com.myfitnesspal.feature.challenges.util.AchievementAdapterType;
import com.myfitnesspal.feature.challenges.util.ChallengesAnalyticsHelper;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.ui.view.LinearLayoutAdapterView.OnItemClickListener;
import com.myfitnesspal.shared.ui.view.LinearLayoutListAdapterView;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.List;

public class AchievementsView {
    private AchievementAdapterType achievementAdapterType;
    /* access modifiers changed from: private */
    public List<ChallengeAchievement> achievementList;
    /* access modifiers changed from: private */
    public String challengeId;
    /* access modifiers changed from: private */
    public String challengeName;
    private Lazy<ImageService> imageService;
    /* access modifiers changed from: private */
    public boolean isChallengePrivate;
    /* access modifiers changed from: private */
    public NavigationHelper navigationHelper;

    public AchievementsView(List<ChallengeAchievement> list, Lazy<ImageService> lazy, NavigationHelper navigationHelper2, String str, String str2, boolean z, AchievementAdapterType achievementAdapterType2) {
        this.achievementList = list;
        this.imageService = lazy;
        this.navigationHelper = navigationHelper2;
        this.challengeName = str;
        this.challengeId = str2;
        this.isChallengePrivate = z;
        this.achievementAdapterType = achievementAdapterType2;
    }

    public void addView(Context context, ViewGroup viewGroup, boolean z) {
        LayoutInflater from = LayoutInflater.from(context);
        ViewGroup viewGroup2 = (ViewGroup) from.inflate(R.layout.challenge_prizes_prizes, viewGroup, false);
        LinearLayoutListAdapterView linearLayoutListAdapterView = (LinearLayoutListAdapterView) ViewUtils.findById(viewGroup2, R.id.lvPrizes);
        ((TextView) ViewUtils.findById(viewGroup2, R.id.tvTitle)).setText(context.getString(R.string.achievements));
        ChallengeAchievementsListAdapter challengeAchievementsListAdapter = new ChallengeAchievementsListAdapter(from, this.achievementList, R.layout.challenge_achievement_summary_item, this.imageService, context.getApplicationContext(), z, this.achievementAdapterType);
        linearLayoutListAdapterView.setAdapter(challengeAchievementsListAdapter);
        setAchievementClickListener(linearLayoutListAdapterView, z);
        viewGroup.addView(viewGroup2);
    }

    private void setAchievementClickListener(LinearLayoutListAdapterView linearLayoutListAdapterView, final boolean z) {
        linearLayoutListAdapterView.setOnItemClickListener(new OnItemClickListener() {
            public void onClick(View view, int i) {
                AchievementsView.this.navigationHelper.withIntent(ChallengeAchievementActivity.newStartIntent(AchievementsView.this.navigationHelper.getContext(), AchievementsView.this.isChallengePrivate, z, AchievementsView.this.challengeId, AchievementsView.this.challengeName, ChallengesAnalyticsHelper.ACHIEVEMENT_LIST_SOURCE, (ChallengeAchievement) AchievementsView.this.achievementList.get(i))).startActivity();
            }
        });
    }
}
