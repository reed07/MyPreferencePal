package com.myfitnesspal.feature.challenges.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.model.ChallengeSocial;
import com.myfitnesspal.feature.challenges.ui.view.ChallengeFriendsSocialView;
import com.myfitnesspal.feature.challenges.util.ChallengesAnalyticsHelper;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil.ChallengeSocialType;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.uacf.core.util.ExtrasUtils;
import dagger.Lazy;
import java.util.ArrayList;
import javax.inject.Inject;

public class JoinChallengeActivity extends MfpActivity {
    @BindView(2131362032)
    View button;
    /* access modifiers changed from: private */
    public String challengeId;
    /* access modifiers changed from: private */
    public String challengeName;
    @Inject
    Lazy<ChallengesAnalyticsHelper> challengesAnalyticsHelper;
    @BindView(2131362205)
    ViewGroup viewGroup;

    public static Intent newStartIntent(Context context, String str, String str2, ArrayList<String> arrayList, ChallengeSocial challengeSocial, String str3) {
        return new Intent(context, JoinChallengeActivity.class).putExtra("challenge_id", str).putExtra("challenge_name", str2).putStringArrayListExtra(Extras.FRIENDS_IN_CHALLENGE, arrayList).putExtra(Extras.CHALLENGE_SOCIAL_DATA, challengeSocial).putExtra("source", str3);
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setResult(0);
        setContentView((int) R.layout.activity_join_challenge);
        Intent intent = getIntent();
        this.challengeId = ExtrasUtils.getString(intent, "challenge_id");
        this.challengeName = ExtrasUtils.getString(intent, "challenge_name");
        ArrayList stringArrayList = ExtrasUtils.getStringArrayList(intent, Extras.FRIENDS_IN_CHALLENGE);
        ChallengeSocial challengeSocial = (ChallengeSocial) ExtrasUtils.getParcelable(getIntent(), Extras.CHALLENGE_SOCIAL_DATA, ChallengeSocial.class.getClassLoader());
        setTitle(R.string.invite_friends_to_challenge);
        initializeUi(stringArrayList, challengeSocial);
    }

    private void initializeUi(ArrayList<String> arrayList, ChallengeSocial challengeSocial) {
        ChallengeFriendsSocialView challengeFriendsSocialView = new ChallengeFriendsSocialView(getActivity(), getMessageBus(), getNavigationHelper(), this.challengesAnalyticsHelper, this.challengeName, this.challengeId, ChallengeSocialType.Invites);
        challengeFriendsSocialView.setInvitesData(arrayList, challengeSocial);
        this.viewGroup.addView(challengeFriendsSocialView);
        this.button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                JoinChallengeActivity.this.button.setEnabled(false);
                ((ChallengesAnalyticsHelper) JoinChallengeActivity.this.challengesAnalyticsHelper.get()).reportJoinInviteFriendsScreenEvent(JoinChallengeActivity.this.challengeName, JoinChallengeActivity.this.challengeId, ChallengesAnalyticsHelper.SCREEN_DONE_CLICKED);
                JoinChallengeActivity.this.onDoneClicked();
            }
        });
        ((ChallengesAnalyticsHelper) this.challengesAnalyticsHelper.get()).reportJoinInviteFriendsScreenEvent(this.challengeName, this.challengeId, ChallengesAnalyticsHelper.SCREEN_VIEWED);
    }

    /* access modifiers changed from: private */
    public void onDoneClicked() {
        setResult(-1);
        finish();
    }

    public boolean backPressed() {
        ((ChallengesAnalyticsHelper) this.challengesAnalyticsHelper.get()).reportJoinInviteFriendsScreenEvent(this.challengeName, this.challengeId, ChallengesAnalyticsHelper.SCREEN_DISMISSED);
        onDoneClicked();
        return super.backPressed();
    }
}
