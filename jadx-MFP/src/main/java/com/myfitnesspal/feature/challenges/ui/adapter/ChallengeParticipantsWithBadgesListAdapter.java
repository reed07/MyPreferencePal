package com.myfitnesspal.feature.challenges.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.model.AvailableAchievementCriteria;
import com.myfitnesspal.feature.challenges.model.AvailableAchievementProgress;
import com.myfitnesspal.feature.challenges.model.ChallengeAvailableAchievement;
import com.myfitnesspal.feature.challenges.model.ChallengeParticipant;
import com.myfitnesspal.feature.challenges.model.EarnedAchievement;
import com.myfitnesspal.feature.challenges.ui.viewmodel.ChallengeAchievementsViewModel;
import com.myfitnesspal.feature.challenges.util.ChallengesAnalyticsHelper;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.profile.ui.activity.ProfileView;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.ui.view.MfpImageView;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.DeviceInfo;
import com.uacf.core.util.Strings;
import com.uacf.core.util.TextViewUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ChallengeParticipantsWithBadgesListAdapter extends BaseAdapter {
    private static final int ITEM_PADDING_DP = 8;
    public static final int MAX_ITEM_COUNT = 5;
    private ChallengeAchievementsViewModel challengeAchievementsViewModel;
    private Lazy<ChallengesAnalyticsHelper> challengesAnalyticsHelper;
    private Context context;
    private final DeviceInfo deviceInfo;
    private Lazy<ImageService> imageService;
    private LayoutInflater inflater;
    private int layoutId;
    private List<ChallengeParticipant> list;
    private String loggedinUsername;
    private List<ChallengeAvailableAchievement> mainAchievements;
    private NavigationHelper navigationHelper;
    private boolean shouldShowAllParticipants;

    private static class ViewHolder {
        private LinearLayout badges;
        private Lazy<ImageService> imageService;
        private ImageView imageView;
        private TextView name;
        private TextView progress;
        private View view;

        public ViewHolder(View view2, Lazy<ImageService> lazy) {
            this.view = view2;
            this.name = (TextView) ViewUtils.findById(view2, R.id.tvName);
            this.progress = (TextView) ViewUtils.findById(view2, R.id.tvProgress);
            this.imageView = (ImageView) ViewUtils.findById(view2, R.id.ivImage);
            this.badges = (LinearLayout) ViewUtils.findById(view2, R.id.llBadges);
            this.imageService = lazy;
        }

        /* access modifiers changed from: 0000 */
        public void bind(Context context, NavigationHelper navigationHelper, ChallengeParticipant challengeParticipant, ChallengeAchievementsViewModel challengeAchievementsViewModel, List<ChallengeAvailableAchievement> list, int i, Lazy<ChallengesAnalyticsHelper> lazy, String str) {
            ChallengesUtil.setImageToImageView(context, challengeParticipant.getUser().getMainImage(), this.imageView, this.imageService, (int) R.drawable.ic_profile);
            String username = challengeParticipant.getUser().getUsername();
            boolean equals = Strings.equals(str, username);
            TextView textView = this.name;
            if (equals) {
                username = context.getString(R.string.you_info);
            }
            TextViewUtils.setText(textView, username);
            this.view.setBackgroundColor(context.getResources().getColor(equals ? R.color.challenges_bg : R.color.white));
            setMainProgressValue(this.progress, list, challengeParticipant.getProgressions());
            this.progress.setTextColor(context.getResources().getColor(equals ? R.color.blue : R.color.black));
            setAchievementImages(context, challengeParticipant, challengeAchievementsViewModel, i);
            View view2 = this.view;
            final Lazy<ChallengesAnalyticsHelper> lazy2 = lazy;
            final ChallengeAchievementsViewModel challengeAchievementsViewModel2 = challengeAchievementsViewModel;
            final NavigationHelper navigationHelper2 = navigationHelper;
            final Context context2 = context;
            final ChallengeParticipant challengeParticipant2 = challengeParticipant;
            AnonymousClass1 r0 = new OnClickListener() {
                public void onClick(View view) {
                    ((ChallengesAnalyticsHelper) lazy2.get()).reportDetailsFriendsTabFriendSelectedEvent(challengeAchievementsViewModel2.getChallengeName(), challengeAchievementsViewModel2.getChallengeId());
                    navigationHelper2.withIntent(ProfileView.newStartIntent(context2, challengeParticipant2.getUser().getUsername(), challengeParticipant2.getUser().getId())).startActivity(36);
                }
            };
            view2.setOnClickListener(r0);
        }

        private void setAchievementImages(Context context, ChallengeParticipant challengeParticipant, ChallengeAchievementsViewModel challengeAchievementsViewModel, int i) {
            this.badges.removeAllViews();
            if (CollectionUtils.notEmpty((Collection<?>) challengeParticipant.getEarnedAchievements())) {
                Map achievementsMap = ChallengesUtil.getAchievementsMap(challengeAchievementsViewModel.getAvailableAchievementList());
                for (EarnedAchievement earnedAchievement : challengeParticipant.getEarnedAchievements()) {
                    MfpImageView mfpImageView = new MfpImageView(context);
                    ChallengesUtil.setImageToImageView(context, ((ChallengeAvailableAchievement) achievementsMap.get(earnedAchievement.getAvailableAchievementId())).getIconImage(), mfpImageView, this.imageService);
                    int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.challenge_friend_achievement_image_height_width);
                    LayoutParams layoutParams = new LayoutParams(dimensionPixelSize, dimensionPixelSize);
                    layoutParams.setMargins(0, 0, i, 0);
                    mfpImageView.setLayoutParams(layoutParams);
                    this.badges.addView(mfpImageView);
                }
            }
        }

        private void setMainProgressValue(TextView textView, List<ChallengeAvailableAchievement> list, List<AvailableAchievementProgress> list2) {
            int i = 0;
            AvailableAchievementProgress achievementCriteriaProgressBasedOnCriteriaId = ChallengesUtil.getAchievementCriteriaProgressBasedOnCriteriaId(((AvailableAchievementCriteria) ((ChallengeAvailableAchievement) list.get(0)).getCriteria().get(0)).getId(), list2);
            if (achievementCriteriaProgressBasedOnCriteriaId != null) {
                i = achievementCriteriaProgressBasedOnCriteriaId.getValue();
            }
            TextViewUtils.setText(textView, Strings.toString(Integer.valueOf(i)));
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public ChallengeParticipantsWithBadgesListAdapter(LayoutInflater layoutInflater, List<ChallengeParticipant> list2, ChallengeAchievementsViewModel challengeAchievementsViewModel2, List<ChallengeAvailableAchievement> list3, int i, Lazy<ImageService> lazy, Context context2, NavigationHelper navigationHelper2, Lazy<ChallengesAnalyticsHelper> lazy2, DeviceInfo deviceInfo2, String str, boolean z) {
        this.inflater = layoutInflater;
        this.list = list2;
        this.challengeAchievementsViewModel = challengeAchievementsViewModel2;
        this.layoutId = i;
        this.imageService = lazy;
        this.context = context2;
        this.navigationHelper = navigationHelper2;
        this.mainAchievements = list3;
        this.challengesAnalyticsHelper = lazy2;
        this.deviceInfo = deviceInfo2;
        this.loggedinUsername = str;
        this.shouldShowAllParticipants = z;
    }

    public int getCount() {
        int size = CollectionUtils.size((Collection<?>) this.list);
        return this.shouldShowAllParticipants ? size : Math.min(size, 5);
    }

    public Object getItem(int i) {
        return this.list.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.inflater.inflate(this.layoutId, viewGroup, false);
            view.setTag(new ViewHolder(view, this.imageService));
        }
        ((ViewHolder) view.getTag()).bind(this.context, this.navigationHelper, (ChallengeParticipant) this.list.get(i), this.challengeAchievementsViewModel, this.mainAchievements, this.deviceInfo.toPixels(8), this.challengesAnalyticsHelper, this.loggedinUsername);
        return view;
    }
}
