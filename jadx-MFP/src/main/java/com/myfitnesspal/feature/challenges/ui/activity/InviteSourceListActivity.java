package com.myfitnesspal.feature.challenges.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class InviteSourceListActivity extends MfpActivity {
    @Inject
    Lazy<ChallengesAnalyticsHelper> challengesAnalyticsHelper;
    private ChallengeFriendsSocialView inviteView;

    public static Intent newStartIntent(Context context, String str, String str2, ArrayList<String> arrayList, ChallengeSocial challengeSocial) {
        return new Intent(context, InviteSourceListActivity.class).putExtra("challenge_id", str).putExtra("challenge_name", str2).putExtra(Extras.FRIENDS_IN_CHALLENGE, arrayList).putExtra(Extras.CHALLENGE_SOCIAL_DATA, challengeSocial);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        String string = ExtrasUtils.getString(getIntent(), "challenge_id");
        String string2 = ExtrasUtils.getString(getIntent(), "challenge_name");
        ArrayList stringArrayList = ExtrasUtils.getStringArrayList(getIntent(), Extras.FRIENDS_IN_CHALLENGE);
        ChallengeSocial challengeSocial = (ChallengeSocial) ExtrasUtils.getParcelable(getIntent(), Extras.CHALLENGE_SOCIAL_DATA, ChallengeSocial.class.getClassLoader());
        ChallengeFriendsSocialView challengeFriendsSocialView = new ChallengeFriendsSocialView(this, getMessageBus(), getNavigationHelper(), this.challengesAnalyticsHelper, string2, string, ChallengeSocialType.Invites);
        this.inviteView = challengeFriendsSocialView;
        this.inviteView.setInvitesData(stringArrayList, challengeSocial);
        setContentView((View) this.inviteView);
        setTitle(R.string.invite_friends_to_challenge);
    }
}
