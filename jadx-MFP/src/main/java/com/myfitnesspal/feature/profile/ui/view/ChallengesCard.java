package com.myfitnesspal.feature.profile.ui.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.model.ChallengeSummary;
import com.myfitnesspal.feature.challenges.ui.activity.ChallengeDetailActivity;
import com.myfitnesspal.feature.challenges.ui.activity.ChallengesActivity;
import com.myfitnesspal.feature.challenges.ui.adapter.ChallengeListItem;
import com.myfitnesspal.feature.challenges.ui.adapter.ChallengeSummaryListAdapter;
import com.myfitnesspal.feature.challenges.ui.adapter.ChallengeSummaryListAdapter.ChallengeViewHolder;
import com.myfitnesspal.feature.challenges.ui.adapter.ChallengeSummaryListAdapter.ItemDecorator;
import com.myfitnesspal.feature.challenges.ui.adapter.ChallengeSummaryListItem;
import com.myfitnesspal.feature.challenges.ui.viewmodel.ChallengeSummaryViewModel;
import com.myfitnesspal.feature.challenges.util.ChallengesAnalyticsHelper;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.profile.service.ProfileAggregatorService.ProfileChallenges;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.view.LinearLayoutAdapterView.OnItemClickListener;
import com.myfitnesspal.shared.ui.view.LinearLayoutListAdapterView;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ChallengesCard extends MeCardBase {
    private Lazy<ChallengesAnalyticsHelper> anayticsHelper;
    private TextView badgeCountTextView;
    private ChallengeSummaryListAdapter challengeAdapter;
    private LinearLayoutListAdapterView challengesListView;
    /* access modifiers changed from: private */
    public ProfileChallenges data;
    private View emptyView;
    private boolean hasData;
    private ItemDecorator itemDecorator = new ItemDecorator() {
        public void decorate(ChallengeViewHolder challengeViewHolder, ChallengeSummaryViewModel challengeSummaryViewModel) {
            int dimensionPixelSize = challengeViewHolder.mainView.getResources().getDimensionPixelSize(R.dimen.material_padding_16);
            int i = dimensionPixelSize / 2;
            challengeViewHolder.mainView.setPadding(dimensionPixelSize, i, dimensionPixelSize, i);
            challengeViewHolder.daysLeftAndParticipants.setPadding(0, 0, 0, 0);
            ViewUtils.setGone(challengeViewHolder.divider);
        }
    };
    private OnClickListener navigateToChallengesOnClickListener;

    public String getAnalyticsType() {
        return "challenges";
    }

    /* access modifiers changed from: protected */
    public int getButtonTextId() {
        return R.string.me_card_button_view_challenges;
    }

    /* access modifiers changed from: protected */
    public int getContentLayoutId() {
        return R.layout.me_card_challenges;
    }

    /* access modifiers changed from: protected */
    public int getLeftBadgeLayoutId() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public int getRightBadgeLayoutId() {
        return R.layout.me_card_challenges_badge;
    }

    /* access modifiers changed from: protected */
    public int getTitleTextId() {
        return R.string.me_card_title_active_challenges;
    }

    public ChallengesCard(@NonNull Context context) {
        super(context);
    }

    public ChallengesCard(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ChallengesCard(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i) {
        super(context, attributeSet, i);
    }

    public void redrawForExplore(Lazy<ImageService> lazy, Lazy<ChallengesAnalyticsHelper> lazy2, ConfigService configService, List<ChallengeSummary> list) {
        redrawInternal(lazy, lazy2, configService, list, 0);
        ViewUtils.setGone(this.rightBadgeContainer);
        this.title.setText(R.string.explore_card_title_challenges);
    }

    public void redrawForMe(Lazy<ImageService> lazy, Lazy<ChallengesAnalyticsHelper> lazy2, ConfigService configService, ProfileChallenges profileChallenges) {
        List list;
        int i;
        if (profileChallenges != null) {
            this.data = profileChallenges;
        }
        if (profileChallenges == null) {
            list = null;
        } else {
            list = profileChallenges.getActiveChallenges();
        }
        if (profileChallenges == null) {
            i = 0;
        } else {
            i = profileChallenges.getNewChallengeCount();
        }
        redrawInternal(lazy, lazy2, configService, list, i);
        this.title.setText(R.string.me_card_title_active_challenges);
    }

    private void redrawInternal(Lazy<ImageService> lazy, Lazy<ChallengesAnalyticsHelper> lazy2, ConfigService configService, List<ChallengeSummary> list, int i) {
        if (!ChallengesUtil.isChallengesAvailable(configService)) {
            ViewUtils.setGone(this);
            return;
        }
        ViewUtils.setVisible(this);
        this.anayticsHelper = lazy2;
        if (!CollectionUtils.isEmpty((Collection<?>) list)) {
            drawContent();
            ChallengeSummaryListAdapter challengeSummaryListAdapter = new ChallengeSummaryListAdapter(LayoutInflater.from(getContext()), toAdapterList(list), lazy, this.itemDecorator, getContext());
            this.challengeAdapter = challengeSummaryListAdapter;
            this.challengesListView.setAdapter(this.challengeAdapter);
            this.hasData = true;
        } else if (!this.hasData) {
            drawEmpty();
        }
        if (i > 0) {
            ViewUtils.setVisible(this.rightBadgeContainer);
            this.badgeCountTextView.setText(String.valueOf(i));
        } else if (!this.hasData) {
            ViewUtils.setGone(this.rightBadgeContainer);
        }
    }

    private List<ChallengeListItem> toAdapterList(List<ChallengeSummary> list) {
        ArrayList arrayList = new ArrayList();
        for (ChallengeSummary challengeSummaryViewModel : list) {
            arrayList.add(new ChallengeSummaryListItem(new ChallengeSummaryViewModel(challengeSummaryViewModel, "new_challenge")));
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void onInflated() {
        this.badgeCountTextView = (TextView) findViewById(R.id.badgeCount);
        this.emptyView = findViewById(R.id.emptyView);
        this.challengesListView = (LinearLayoutListAdapterView) findViewById(R.id.activeChallengesList);
        this.challengesListView.setShowDividerAfterLastItem(false);
        this.challengesListView.setDivider((Drawable) new ColorDrawable(getResources().getColor(R.color.light_divider)), 1.0f);
        this.challengesListView.setDividerHorizontalPadding(R.dimen.material_margin_16);
        this.challengesListView.setOnItemClickListener(new OnItemClickListener() {
            public void onClick(View view, int i) {
                ChallengesCard.this.onChallengeClicked(i);
            }
        });
        this.navigateToChallengesOnClickListener = new OnClickListener() {
            public void onClick(View view) {
                ChallengesCard challengesCard = ChallengesCard.this;
                challengesCard.reportNewChallengesTapped(challengesCard.data.getNewChallengeCount());
                ChallengesCard.this.getNavigationHelper().withIntent(ChallengesActivity.newStartIntent(ChallengesCard.this.getContext())).startActivity();
            }
        };
        this.rightBadgeContainer.setOnClickListener(this.navigateToChallengesOnClickListener);
    }

    /* access modifiers changed from: protected */
    public void reportChallengeTapped(String str, String str2) {
        getAnalytics().reportActiveChallengeTapped(str, str2);
    }

    /* access modifiers changed from: protected */
    public void reportNewChallengesTapped(int i) {
        getAnalytics().reportNewChallengesTapped(i);
    }

    private void drawEmpty() {
        ViewUtils.setVisible(this.emptyView);
        ViewUtils.setGone(this.challengesListView);
        setOnContentViewClickListener(this.navigateToChallengesOnClickListener);
    }

    private void drawContent() {
        ViewUtils.setVisible(this.challengesListView);
        ViewUtils.setGone(this.emptyView);
        setOnContentViewClickListener(null);
    }

    /* access modifiers changed from: private */
    public void onChallengeClicked(int i) {
        ChallengeSummaryViewModel challengeSummary = ((ChallengeSummaryListItem) this.challengeAdapter.getItem(i)).getChallengeSummary();
        if (challengeSummary != null) {
            reportChallengeTapped(challengeSummary.getChallengeName(), challengeSummary.getChallengeId());
            MfpActivity mfpActivity = (MfpActivity) getContext();
            String challengeId = challengeSummary.getChallengeId();
            ((ChallengesAnalyticsHelper) this.anayticsHelper.get()).reportChallengeSelectedEvent(challengeSummary.getListType(), challengeSummary.getChallengeName(), challengeId);
            if (challengeId != null) {
                getNavigationHelper().withIntent(ChallengeDetailActivity.newStartIntent(mfpActivity, challengeId, ChallengesAnalyticsHelper.SOURCE_MY_INFO)).withDisableStartFromFragment(true).startActivity(RequestCodes.CHALLENGE_DETAIL);
            }
        }
    }
}
