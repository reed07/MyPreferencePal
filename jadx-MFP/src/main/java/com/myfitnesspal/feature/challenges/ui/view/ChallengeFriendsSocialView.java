package com.myfitnesspal.feature.challenges.ui.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent.Builder;
import com.facebook.share.widget.ShareDialog;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.model.ChallengeSocial;
import com.myfitnesspal.feature.challenges.ui.activity.InviteContactsToChallengeActivity;
import com.myfitnesspal.feature.challenges.util.ChallengesAnalyticsHelper;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil.ChallengeSocialType;
import com.myfitnesspal.shared.constants.Constants.Challenges;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;
import com.uacf.core.util.Ln;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;

public class ChallengeFriendsSocialView extends FrameLayout {
    /* access modifiers changed from: private */
    public Bus bus;
    /* access modifiers changed from: private */
    public String challengeId;
    /* access modifiers changed from: private */
    public String challengeName;
    /* access modifiers changed from: private */
    public ChallengeSocial challengeSocial;
    private Lazy<ChallengesAnalyticsHelper> challengesAnalyticsHelper;
    private String emailBodyShare;
    private String emailSubjectShare;
    /* access modifiers changed from: private */
    public ArrayList<String> friendUserIdsInChallenge;
    @BindView(2131362843)
    ViewGroup inviteEmail;
    @BindView(2131362844)
    ViewGroup inviteFacebook;
    @BindView(2131362845)
    ViewGroup inviteFriends;
    @BindView(2131362846)
    ViewGroup inviteTwitter;
    private boolean isSocialTypeInvite;
    /* access modifiers changed from: private */
    public NavigationHelper navigationHelper;
    private OnClickListener onEmailClickListener = new OnClickListener() {
        /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0023 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onClick(android.view.View r4) {
            /*
                r3 = this;
                com.myfitnesspal.feature.challenges.ui.view.ChallengeFriendsSocialView r4 = com.myfitnesspal.feature.challenges.ui.view.ChallengeFriendsSocialView.this
                java.lang.String r0 = "email"
                r4.reportFriendSelectedEvent(r0)
                com.myfitnesspal.feature.challenges.ui.view.ChallengeFriendsSocialView r4 = com.myfitnesspal.feature.challenges.ui.view.ChallengeFriendsSocialView.this
                java.lang.String r4 = r4.getSubjectForSocialType()
                com.myfitnesspal.feature.challenges.ui.view.ChallengeFriendsSocialView r0 = com.myfitnesspal.feature.challenges.ui.view.ChallengeFriendsSocialView.this
                android.text.Spanned r0 = r0.getEmailBodyBasedOnSocialType()
                com.myfitnesspal.feature.challenges.ui.view.ChallengeFriendsSocialView r1 = com.myfitnesspal.feature.challenges.ui.view.ChallengeFriendsSocialView.this     // Catch:{ Exception -> 0x0023 }
                android.content.Context r1 = r1.getContext()     // Catch:{ Exception -> 0x0023 }
                java.lang.String r2 = "message/rfc8222"
                android.content.Intent r2 = com.myfitnesspal.feature.challenges.ui.view.ChallengeFriendsSocialView.createEmailIntent(r2, r4, r0)     // Catch:{ Exception -> 0x0023 }
                r1.startActivity(r2)     // Catch:{ Exception -> 0x0023 }
                goto L_0x004e
            L_0x0023:
                com.myfitnesspal.feature.challenges.ui.view.ChallengeFriendsSocialView r1 = com.myfitnesspal.feature.challenges.ui.view.ChallengeFriendsSocialView.this     // Catch:{ Exception -> 0x0033 }
                android.content.Context r1 = r1.getContext()     // Catch:{ Exception -> 0x0033 }
                java.lang.String r2 = "text/plain"
                android.content.Intent r4 = com.myfitnesspal.feature.challenges.ui.view.ChallengeFriendsSocialView.createEmailIntent(r2, r4, r0)     // Catch:{ Exception -> 0x0033 }
                r1.startActivity(r4)     // Catch:{ Exception -> 0x0033 }
                goto L_0x004e
            L_0x0033:
                com.myfitnesspal.feature.challenges.ui.view.ChallengeFriendsSocialView r4 = com.myfitnesspal.feature.challenges.ui.view.ChallengeFriendsSocialView.this
                com.squareup.otto.Bus r4 = r4.bus
                com.myfitnesspal.shared.event.AlertEvent r0 = new com.myfitnesspal.shared.event.AlertEvent
                com.myfitnesspal.feature.challenges.ui.view.ChallengeFriendsSocialView r1 = com.myfitnesspal.feature.challenges.ui.view.ChallengeFriendsSocialView.this
                android.content.Context r1 = r1.getContext()
                r2 = 2131886550(0x7f1201d6, float:1.9407682E38)
                java.lang.String r1 = r1.getString(r2)
                r0.<init>(r1)
                r4.post(r0)
            L_0x004e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.challenges.ui.view.ChallengeFriendsSocialView.AnonymousClass1.onClick(android.view.View):void");
        }
    };
    private OnClickListener onFacebookClickListener = new OnClickListener() {
        public void onClick(View view) {
            ChallengeFriendsSocialView.this.reportFriendSelectedEvent("facebook");
            ShareDialog.show((Activity) ChallengeFriendsSocialView.this.getContext(), (ShareContent) ((Builder) new Builder().setContentUrl(Uri.parse(ChallengesUtil.CHALLENGES_DISPATCH_URL))).setContentTitle(ChallengeFriendsSocialView.this.challengeName).setContentDescription(ChallengeFriendsSocialView.this.challengeSocial.getEmailBodyInvite()).build());
        }
    };
    private OnClickListener onMfpFriendsClickListener = new OnClickListener() {
        public void onClick(View view) {
            ChallengeFriendsSocialView.this.reportFriendSelectedEvent(Challenges.CHALLENGE_INVITE_MFP_FRIENDS);
            ChallengeFriendsSocialView.this.navigationHelper.withIntent(InviteContactsToChallengeActivity.newStartIntent(ChallengeFriendsSocialView.this.getContext(), ChallengeFriendsSocialView.this.challengeId, ChallengeFriendsSocialView.this.challengeName, ChallengeFriendsSocialView.this.friendUserIdsInChallenge)).startActivity();
        }
    };
    private OnClickListener onTwitterClickListener = new OnClickListener() {
        public void onClick(View view) {
            ChallengeFriendsSocialView.this.reportFriendSelectedEvent(Challenges.CHALLENGE_INVITE_TWITTER);
            Twitter.initialize(new TwitterConfig.Builder(view.getContext()).twitterAuthConfig(new TwitterAuthConfig("", "")).build());
            new TweetComposer.Builder(ChallengeFriendsSocialView.this.getContext()).text(ChallengeFriendsSocialView.this.getTwitterTextBasedOnSocialType()).show();
        }
    };
    private String twitterShare;

    public ChallengeFriendsSocialView(Context context, Bus bus2, NavigationHelper navigationHelper2, Lazy<ChallengesAnalyticsHelper> lazy, String str, String str2, ChallengeSocialType challengeSocialType) {
        super(context);
        this.navigationHelper = navigationHelper2;
        this.bus = bus2;
        this.challengesAnalyticsHelper = lazy;
        this.challengeName = str;
        this.challengeId = str2;
        this.isSocialTypeInvite = ChallengesUtil.isSocialTypeInvites(challengeSocialType);
        inflate();
    }

    public void setInvitesData(ArrayList<String> arrayList, ChallengeSocial challengeSocial2) {
        this.friendUserIdsInChallenge = arrayList;
        this.challengeSocial = challengeSocial2;
    }

    public void setShareData(String str, String str2, String str3) {
        this.emailBodyShare = str2;
        this.emailSubjectShare = str;
        this.twitterShare = str3;
    }

    private void inflate() {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.fragment_challenge_invite_friends_header, null, false);
        ButterKnife.bind((Object) this, (View) viewGroup);
        initInviteView(this.inviteFriends, R.drawable.ic_invite_mfpfriends, R.string.invite_friend_row_mfp_friends, this.onMfpFriendsClickListener);
        initInviteView(this.inviteEmail, R.drawable.ic_invite_email, R.string.invite_friend_row_email, this.onEmailClickListener);
        initInviteView(this.inviteFacebook, R.drawable.ic_invite_facebook, R.string.invite_friend_row_facebook, this.onFacebookClickListener);
        initInviteView(this.inviteTwitter, R.drawable.ic_invite_twitter, R.string.invite_friend_row_twitter, this.onTwitterClickListener);
        addView(viewGroup);
    }

    private void initInviteView(View view, int i, int i2, OnClickListener onClickListener) {
        ViewUtils.findById(view, R.id.icon).setBackgroundResource(i);
        ((TextView) ViewUtils.findById(view, R.id.text)).setText(getContext().getString(i2));
        view.setOnClickListener(onClickListener);
    }

    /* access modifiers changed from: private */
    public Spanned getEmailBodyBasedOnSocialType() {
        try {
            return Html.fromHtml(this.isSocialTypeInvite ? this.challengeSocial.getEmailBodyInvite() : this.emailBodyShare);
        } catch (Exception e) {
            Ln.e(e);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public String getSubjectForSocialType() {
        return this.isSocialTypeInvite ? this.challengeSocial.getEmailSubjectInvite() : this.emailSubjectShare;
    }

    /* access modifiers changed from: private */
    public String getTwitterTextBasedOnSocialType() {
        return this.isSocialTypeInvite ? ChallengesUtil.getSocialTextWithDispatchUrl(this.challengeSocial.getTwitterInvite()) : this.twitterShare;
    }

    /* access modifiers changed from: private */
    public static Intent createEmailIntent(String str, String str2, Spanned spanned) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType(str);
        intent.putExtra("android.intent.extra.SUBJECT", str2);
        intent.putExtra("android.intent.extra.EMAIL", spanned);
        intent.putExtra("android.intent.extra.TEXT", spanned);
        return intent;
    }

    /* access modifiers changed from: private */
    public void reportFriendSelectedEvent(String str) {
        ((ChallengesAnalyticsHelper) this.challengesAnalyticsHelper.get()).reportJoinInviteFriendsScreenInvitationClickedEvent(this.challengeName, this.challengeId, str, ChallengesAnalyticsHelper.SCREEN_INVITE_METHOD_SELECTED);
    }
}
