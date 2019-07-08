package com.myfitnesspal.feature.home.ui.view;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import butterknife.BindView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.ui.activity.UserAchievementsActivity;
import com.myfitnesspal.feature.challenges.util.ChallengesAnalyticsHelper;
import com.myfitnesspal.feature.diary.ui.activity.FriendDiary;
import com.myfitnesspal.feature.friends.ui.activity.ComposeMessageActivity;
import com.myfitnesspal.feature.friends.ui.activity.FriendsActivity;
import com.myfitnesspal.feature.friends.ui.activity.InviteFriendActivity;
import com.myfitnesspal.feature.home.model.NewsFeedItem;
import com.myfitnesspal.feature.home.model.ProfileHeaderModel;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.ui.activity.PremiumUpsellActivity;
import com.myfitnesspal.feature.profile.ui.fragment.ProfileFragment.UserType;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.model.mapper.impl.MiniUserInfoMapper;
import com.myfitnesspal.shared.model.v1.MiniUserInfo;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.premium.PremiumAnalyticsHelper;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.strings.GrammarService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.service.userdata.UserWeightService.WeightType;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.ui.view.MfpImageView;
import com.myfitnesspal.shared.ui.view.RecyclerViewHolder;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.DateUtil;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Date;

public class ProfileHeaderViewHolder extends RecyclerViewHolder<NewsFeedItem> {
    private static final String PROFILE_SCREEN_CTA_PREMIUM_ATTRIBUTE = "profile_screen_cta";
    private OnClickListener achievementClickListener = new OnClickListener() {
        public void onClick(View view) {
            ((ChallengesAnalyticsHelper) ProfileHeaderViewHolder.this.challengesAnalyticsHelper.get()).reportUserProfileViewAchievementsEvent(ProfileHeaderViewHolder.this.userInfo == null || !ProfileHeaderViewHolder.this.userInfo.isCurrentUser(ProfileHeaderViewHolder.this.session));
            ProfileHeaderViewHolder.this.navigationHelper.withIntent(UserAchievementsActivity.newStartIntent(ProfileHeaderViewHolder.this.context, ProfileHeaderViewHolder.this.userInfo == null ? ProfileHeaderViewHolder.this.userUid : ProfileHeaderViewHolder.this.userInfo.getUid())).startActivity();
        }
    };
    private View addFriend;
    private OnClickListener addFriendClickListener = new OnClickListener() {
        public void onClick(View view) {
            ProfileHeaderViewHolder.this.navigationHelper.withIntent(InviteFriendActivity.newStartIntent(ProfileHeaderViewHolder.this.context, ProfileHeaderViewHolder.this.username)).startActivity(18);
        }
    };
    /* access modifiers changed from: private */
    public final Lazy<ChallengesAnalyticsHelper> challengesAnalyticsHelper;
    private final Lazy<ConfigService> configService;
    private View friendsButton;
    private final Lazy<GrammarService> grammarService;
    private final boolean isChallengesAvailable;
    @BindView(2131362891)
    TextView loginDate;
    @BindView(2131362970)
    TextView lostLabel;
    private final MiniUserInfoMapper miniUserInfoMapper;
    /* access modifiers changed from: private */
    public final NavigationHelper navigationHelper;
    private final Lazy<PremiumAnalyticsHelper> premiumAnalyticsHelper;
    private OnClickListener premiumCtaClickListener = new OnClickListener() {
        public void onClick(View view) {
            ProfileHeaderViewHolder.this.navigationHelper.withIntent(PremiumUpsellActivity.newStartIntent(ProfileHeaderViewHolder.this.context, ProfileHeaderViewHolder.PROFILE_SCREEN_CTA_PREMIUM_ATTRIBUTE)).startActivity();
        }
    };
    @BindView(2131363295)
    View premiumCtaContainer;
    private boolean premiumCtaEventReported;
    private final Lazy<PremiumService> premiumService;
    @BindView(2131362659)
    FrameLayout profileActions;
    @BindView(2131363310)
    MfpImageView profileImageView;
    @BindView(2131363311)
    View profileLayout;
    private View selfActionsDivider;
    private View sendMessage;
    private OnClickListener sendMessageClickListener = new OnClickListener() {
        public void onClick(View view) {
            ProfileHeaderViewHolder.this.navigationHelper.withIntent(ComposeMessageActivity.newStartIntentWithRecipient(ProfileHeaderViewHolder.this.context, ProfileHeaderViewHolder.this.username, "friend_profile")).startActivity(112);
        }
    };
    /* access modifiers changed from: private */
    public Session session;
    @BindView(2131364081)
    TextView units;
    /* access modifiers changed from: private */
    public final MiniUserInfo userInfo;
    private UserType userType;
    /* access modifiers changed from: private */
    public final String userUid;
    private final Lazy<UserWeightService> userWeightService;
    /* access modifiers changed from: private */
    public final String username;
    @BindView(2131364113)
    TextView usernameTextView;
    private View viewAchievements;
    private View viewDiary;
    private OnClickListener viewDiaryClickListener = new OnClickListener() {
        public void onClick(View view) {
            if (!ProfileHeaderViewHolder.this.userInfo.diaryRequiresPassword().booleanValue()) {
                ProfileHeaderViewHolder.this.viewDiaryWithPassword("");
            } else {
                ProfileHeaderViewHolder.this.promptForPassword();
            }
        }
    };
    private OnClickListener viewFriendsClickListener = new OnClickListener() {
        public void onClick(View view) {
            ProfileHeaderViewHolder.this.navigationHelper.withIntent(FriendsActivity.newStartIntent(ProfileHeaderViewHolder.this.context)).startActivity(14);
        }
    };

    public ProfileHeaderViewHolder(ViewGroup viewGroup, NavigationHelper navigationHelper2, Lazy<ConfigService> lazy, MiniUserInfo miniUserInfo, UserType userType2, String str, ProfileHeaderModel profileHeaderModel, Session session2) {
        super(R.layout.profile_header_v2, viewGroup);
        this.navigationHelper = navigationHelper2;
        this.configService = lazy;
        this.userWeightService = profileHeaderModel.getUserWeightService();
        this.grammarService = profileHeaderModel.getGrammarService();
        this.challengesAnalyticsHelper = profileHeaderModel.getChallengesAnalyticsHelper();
        this.premiumService = profileHeaderModel.getPremiumService();
        this.premiumAnalyticsHelper = profileHeaderModel.getPremiumAnalyticsHelper();
        this.userInfo = miniUserInfo;
        this.miniUserInfoMapper = profileHeaderModel.getMiniUserInfoMapper();
        this.userType = userType2;
        this.isChallengesAvailable = profileHeaderModel.isChallengesAvailable();
        this.username = str;
        this.userUid = profileHeaderModel.getUserUid();
        this.session = session2;
        this.profileImageView.setTransformCircular(true);
    }

    public void setUserType(UserType userType2) {
        this.userType = userType2;
    }

    public void setData(NewsFeedItem newsFeedItem, int i) {
        this.profileImageView.setUrl(this.userInfo.getFullsizeImageURL());
        this.usernameTextView.setText(this.userInfo.getUsername());
        this.loginDate.setText(DateUtil.getTimestamp(this.context, this.userInfo.getLastLoginDate(), false));
        setProfileActionsBasedOnUserType();
        setProfileWeightLostGainView(this.userInfo.getPoundsLost());
        if (this.userType == UserType.Self) {
            showWeightProgressAndRefresh();
        } else if (this.userType == UserType.Friend) {
            showDiaryIfPermitted();
            if (this.userInfo.isProfileViewable()) {
                showWeightProgressAndRefresh();
            }
        }
        showPremiumCtaIfValid();
    }

    private void setProfileWeightLostGainView(float f) {
        boolean z = true;
        int i = (((UserWeightService) this.userWeightService.get()).getGoalPerWeekWeight() > BitmapDescriptorFactory.HUE_RED ? 1 : (((UserWeightService) this.userWeightService.get()).getGoalPerWeekWeight() == BitmapDescriptorFactory.HUE_RED ? 0 : -1));
        boolean z2 = i >= 0;
        Resources resources = this.context.getResources();
        UserType userType2 = this.userType;
        UserType userType3 = UserType.Friend;
        int i2 = R.string.lost;
        if (userType2 == userType3) {
            if (f <= BitmapDescriptorFactory.HUE_RED) {
                z = false;
            }
            this.lostLabel.setText(R.string.lost);
        } else {
            if (i >= 0) {
                f = Math.max(BitmapDescriptorFactory.HUE_RED, f);
                if (f <= BitmapDescriptorFactory.HUE_RED) {
                    z = false;
                }
            } else {
                f = Math.min(BitmapDescriptorFactory.HUE_RED, f);
                if (f >= BitmapDescriptorFactory.HUE_RED) {
                    z = false;
                }
            }
            TextView textView = this.lostLabel;
            if (!z2) {
                i2 = R.string.gained;
            }
            textView.setText(i2);
        }
        this.units.setText(((UserWeightService) this.userWeightService.get()).getDisplayableString(WeightType.JUST_WEIGHT, Math.abs(f)));
        if (z) {
            this.units.setTextColor(resources.getColor(R.color.diary_green));
        }
    }

    private void showPremiumCtaIfValid() {
        ViewUtils.setGone(this.premiumCtaContainer);
        if (this.userType == UserType.Self && ConfigUtils.showProfileScreenPremiumCta((ConfigService) this.configService.get()) && ((PremiumService) this.premiumService.get()).isPremiumAvailable() && !((PremiumService) this.premiumService.get()).isPremiumSubscribed()) {
            ViewUtils.setVisible(this.premiumCtaContainer);
            this.premiumCtaContainer.setOnClickListener(this.premiumCtaClickListener);
            if (!this.premiumCtaEventReported) {
                ((PremiumAnalyticsHelper) this.premiumAnalyticsHelper.get()).reportProfileScreenPremiumCtaViewed();
                this.premiumCtaEventReported = true;
            }
        }
    }

    private void showDiaryIfPermitted() {
        if (!this.userInfo.allowsDiaryToBeViewedByUser(this.session).booleanValue()) {
            disableViewDiary();
        }
    }

    private void showWeightProgressAndRefresh() {
        ViewUtils.setVisible(this.lostLabel);
        ViewUtils.setVisible(this.units);
    }

    private void setProfileActionsBasedOnUserType() {
        setProfileActions(LayoutInflater.from(this.context));
        hookUpUIEventListeners();
    }

    private void hookUpUIEventListeners() {
        ViewUtils.setVisible(this.isChallengesAvailable, this.viewAchievements);
        if (this.isChallengesAvailable) {
            View view = this.viewAchievements;
            if (view != null) {
                view.setOnClickListener(this.achievementClickListener);
            }
        }
        View view2 = this.sendMessage;
        if (view2 != null) {
            view2.setOnClickListener(this.sendMessageClickListener);
        }
        if (this.userType == UserType.NonFriend || this.userType == UserType.FriendRequest) {
            this.addFriend.setOnClickListener(this.addFriendClickListener);
        }
        if (this.viewDiary != null && this.userInfo.allowsDiaryToBeViewedByUser(this.session).booleanValue()) {
            this.viewDiary.setOnClickListener(this.viewDiaryClickListener);
        }
        if (this.userType == UserType.Self) {
            View view3 = this.friendsButton;
            if (view3 != null) {
                view3.setOnClickListener(this.viewFriendsClickListener);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void setProfileActions(LayoutInflater layoutInflater) {
        View inflate = layoutInflater.inflate(getProfileLayout(), null, false);
        this.profileActions.removeAllViews();
        this.profileActions.addView(inflate);
        initViewBasedOnUserType(inflate, this.isChallengesAvailable);
    }

    private int getProfileLayout() {
        if (this.userType == UserType.Self) {
            return R.layout.profile_self_actions;
        }
        return this.userType == UserType.Friend ? R.layout.profile_friend_actions : R.layout.profile_non_friend_actions;
    }

    private void initViewBasedOnUserType(View view, boolean z) {
        if (this.userType == UserType.Self) {
            this.viewAchievements = view.findViewById(R.id.view_achievements);
            this.friendsButton = view.findViewById(R.id.friends_button);
            this.selfActionsDivider = view.findViewById(R.id.self_actions_divider);
            if (!z) {
                ViewUtils.setGone(this.viewAchievements.findViewById(R.id.icon));
                ViewUtils.setGone(this.viewAchievements.findViewById(R.id.caption));
                ViewUtils.setGone(this.viewAchievements);
                ViewUtils.setGone(this.selfActionsDivider);
                LayoutParams layoutParams = new LayoutParams(0, 100);
                layoutParams.weight = 2.0f;
                this.friendsButton.setLayoutParams(layoutParams);
            }
        } else if (this.userType == UserType.Friend) {
            this.viewDiary = view.findViewById(R.id.view_diary);
            this.sendMessage = view.findViewById(R.id.send_message);
            this.viewAchievements = view.findViewById(R.id.view_achievements);
            if (!z) {
                ViewUtils.setGone(this.viewAchievements.findViewById(R.id.iconAchievement));
                ViewUtils.setGone(this.viewAchievements);
                ViewUtils.setVisible(this.sendMessage.findViewById(R.id.captionMsg));
                LayoutParams layoutParams2 = new LayoutParams(0, 100);
                layoutParams2.weight = 2.0f;
                this.sendMessage.setLayoutParams(layoutParams2);
            }
        } else {
            this.sendMessage = view.findViewById(R.id.send_message);
            this.addFriend = view.findViewById(R.id.add_friend);
        }
    }

    private void disableViewDiary() {
        ((ImageView) this.viewDiary.findViewById(R.id.icon)).setImageResource(R.drawable.ic_diary_icon_inactive);
    }

    /* access modifiers changed from: private */
    public void promptForPassword() {
        MfpAlertDialogBuilder mfpAlertDialogBuilder = new MfpAlertDialogBuilder(this.context);
        mfpAlertDialogBuilder.setTitle((int) R.string.password_required);
        mfpAlertDialogBuilder.setMessage((CharSequence) this.context.getString(((GrammarService) this.grammarService.get()).isSpecialCaseForPossessive(this.userInfo.getUsername()) ? R.string.locked_diary_password_request_special : R.string.locked_diary_password_request, new Object[]{this.userInfo.getUsername()}));
        final EditText editText = new EditText(this.context);
        editText.setHint(R.string.enter_password);
        editText.setInputType(129);
        mfpAlertDialogBuilder.setView(editText);
        mfpAlertDialogBuilder.setPositiveButton((int) R.string.ok, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ProfileHeaderViewHolder.this.viewDiaryWithPassword(editText.getText().toString());
            }
        });
        mfpAlertDialogBuilder.setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        mfpAlertDialogBuilder.show();
    }

    /* access modifiers changed from: private */
    public void viewDiaryWithPassword(String str) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Extras.MINI_USER_INFO, this.miniUserInfoMapper.mapFrom(this.userInfo));
        bundle.putLong("data", new Date().getTime());
        bundle.putString(Extras.PASS, str);
        this.navigationHelper.withExtras(bundle).withIntent(FriendDiary.newStartIntent(this.context)).startActivity();
    }
}
