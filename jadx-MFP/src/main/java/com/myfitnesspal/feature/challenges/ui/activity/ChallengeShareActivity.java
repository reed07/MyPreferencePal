package com.myfitnesspal.feature.challenges.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.ui.view.ChallengeFriendsSocialView;
import com.myfitnesspal.feature.challenges.util.ChallengesAnalyticsHelper;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil.ChallengeSocialType;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.uacf.core.util.BundleUtils;
import dagger.Lazy;
import javax.inject.Inject;

public class ChallengeShareActivity extends MfpActivity {
    private String challengeId;
    private String challengeName;
    @Inject
    Lazy<ChallengesAnalyticsHelper> challengesAnalyticsHelper;
    private String emailBodyShare;
    private String emailSubjectShare;
    @BindView(2131363762)
    ViewGroup svChallengeShareView;
    @BindView(2131363833)
    TextView titleTextView;
    private String twitterShare;

    public String getAnalyticsScreenTag() {
        return Screens.CHALLENGE_ACHIEVEMENT_SHARE;
    }

    public static Intent newStartIntent(Context context, String str, String str2, String str3, String str4, String str5) {
        Intent intent = new Intent(context, ChallengeShareActivity.class);
        intent.putExtra("challenge_id", str);
        intent.putExtra("challenge_name", str2);
        intent.putExtra(Extras.CHALLENGE_EMAIL_BODY_SHARE, str3);
        intent.putExtra(Extras.CHALLENGE_EMAIL_SUBJECT_SHARE, str4);
        intent.putExtra(Extras.CHALLENGE_TWITTER_SHARE, str5);
        return intent;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.challenge_achievement_share_activity);
        component().inject(this);
        Bundle extras = getIntent().getExtras();
        this.challengeId = BundleUtils.getString(extras, "challenge_id");
        this.challengeName = BundleUtils.getString(extras, "challenge_name");
        this.emailBodyShare = BundleUtils.getString(extras, Extras.CHALLENGE_EMAIL_BODY_SHARE);
        this.emailSubjectShare = BundleUtils.getString(extras, Extras.CHALLENGE_EMAIL_SUBJECT_SHARE);
        this.twitterShare = BundleUtils.getString(extras, Extras.CHALLENGE_TWITTER_SHARE);
        setupShareView();
        setTitle(R.string.challenge_share_activity_title);
    }

    private void setupShareView() {
        ChallengeFriendsSocialView challengeFriendsSocialView = new ChallengeFriendsSocialView(this, getMessageBus(), getNavigationHelper(), this.challengesAnalyticsHelper, this.challengeName, this.challengeId, ChallengeSocialType.Share);
        challengeFriendsSocialView.setShareData(this.emailSubjectShare, this.emailBodyShare, this.twitterShare);
        this.svChallengeShareView.addView(challengeFriendsSocialView);
    }
}
