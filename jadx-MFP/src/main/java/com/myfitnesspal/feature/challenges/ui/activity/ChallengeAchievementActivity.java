package com.myfitnesspal.feature.challenges.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.model.ChallengeImageOutput;
import com.myfitnesspal.feature.challenges.ui.adapter.ChallengeAchievement;
import com.myfitnesspal.feature.challenges.util.ChallengesAnalyticsHelper;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.TextViewUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import javax.inject.Inject;

public class ChallengeAchievementActivity extends MfpActivity {
    private static final String ACHIEVEMENT = "achievement";
    private static final String CHALLENGE_ID = "challenge_id";
    private static final String CHALLENGE_NAME = "challenge_name";
    private static final String IS_CHALLENGE_PRIVATE = "is_challenge_private";
    private static final int MENU_SHARE = 1001;
    private static final String SHOW_PROGRESS = "show_progress";
    private static final String SOURCE = "source";
    @BindView(2131361814)
    View achContainer;
    @BindView(2131361815)
    TextView achDescription;
    @BindView(2131361816)
    ImageView achImage;
    @BindView(2131361817)
    ProgressBar achProgressBar;
    @BindView(2131361818)
    TextView achProgression;
    @BindView(2131361819)
    TextView achProgressionType;
    @BindView(2131361820)
    TextView achTarget;
    @BindView(2131361821)
    TextView achTitle;
    @BindView(2131361822)
    WebView achWebView;
    private ChallengeAchievement achievement;
    private String challengeId;
    private String challengeName;
    @Inject
    Lazy<ChallengesAnalyticsHelper> challengesAnalyticsHelper;
    @Inject
    Lazy<ImageService> imageService;
    private boolean isChallengePrivate;
    @BindView(2131363327)
    View progressContainer;
    private boolean showProgress;
    private String source;

    static /* synthetic */ boolean lambda$setupAchievementWebView$0(View view) {
        return true;
    }

    static /* synthetic */ boolean lambda$setupAchievementWebView$1(View view) {
        return true;
    }

    public static Intent newStartIntent(Context context, boolean z, boolean z2, String str, String str2, String str3, ChallengeAchievement challengeAchievement) {
        Intent intent = new Intent(context, ChallengeAchievementActivity.class);
        intent.putExtra("is_challenge_private", z);
        intent.putExtra(SHOW_PROGRESS, z2);
        intent.putExtra("challenge_id", str);
        intent.putExtra("achievement", challengeAchievement);
        intent.putExtra("challenge_name", str2);
        intent.putExtra("source", str3);
        return intent;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.challenge_achievement_activity);
        component().inject(this);
        Bundle extras = getIntent().getExtras();
        this.isChallengePrivate = BundleUtils.getBoolean(extras, "is_challenge_private");
        this.showProgress = BundleUtils.getBoolean(extras, SHOW_PROGRESS);
        this.challengeId = BundleUtils.getString(extras, "challenge_id");
        this.achievement = (ChallengeAchievement) BundleUtils.getParcelable(extras, "achievement", ChallengeAchievement.class.getClassLoader());
        this.challengeName = BundleUtils.getString(extras, "challenge_name");
        this.source = BundleUtils.getString(extras, "source");
        ((ChallengesAnalyticsHelper) this.challengesAnalyticsHelper.get()).reportChallengeAchievementViewsEvent(this.source, this.challengeId, this.challengeName, this.achievement.getTitle());
        setTitle(this.achievement.getTitle());
        setupAchievementView();
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (!this.isChallengePrivate && this.achievement.isEarned()) {
            MenuItemCompat.setShowAsAction(menu.add(0, 1001, 0, R.string.sendBtn).setIcon(R.drawable.ic_share_white_24dp), 2);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 1001) {
            return super.onOptionsItemSelected(menuItem);
        }
        onSharePressed();
        return true;
    }

    private void setupAchievementView() {
        boolean notEmpty = Strings.notEmpty(this.achievement.getDetailsUrl());
        ViewUtils.setVisible(notEmpty, this.achWebView);
        ViewUtils.setVisible(!notEmpty, this.achContainer);
        if (notEmpty) {
            setupAchievementWebView();
        } else {
            setupAchievementCustomView();
        }
        invalidateOptionsMenu();
    }

    private void setupAchievementCustomView() {
        ChallengesUtil.setImageToImageView(this, new ChallengeImageOutput(this.achievement.getImageUrl()), this.achImage, this.imageService);
        TextViewUtils.setText(this.achTitle, this.achievement.getTitle());
        TextViewUtils.setText(this.achDescription, this.achievement.getDescription());
        ViewUtils.setVisible(this.showProgress, this.progressContainer);
        if (this.showProgress) {
            TextViewUtils.setText(this.achProgression, Integer.valueOf(this.achievement.getProgress()));
            TextViewUtils.setText(this.achTarget, Integer.valueOf(this.achievement.getProgressMax()));
            TextViewUtils.setText(this.achProgressionType, this.achievement.getCriteriaType());
            this.achProgressBar.setMax(this.achievement.getProgressMax());
            this.achProgressBar.setProgress(this.achievement.getProgress());
        }
    }

    private void setupAchievementWebView() {
        this.achWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (!ChallengesUtil.isUrlForPDF(str)) {
                    return false;
                }
                ChallengeAchievementActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            }
        });
        this.achWebView.getSettings().setJavaScriptEnabled(true);
        this.achWebView.loadUrl(this.achievement.getDetailsUrl());
        this.achWebView.setOnLongClickListener($$Lambda$ChallengeAchievementActivity$I97Z4YMjfUs4QQZPjUdH_OMN8A.INSTANCE);
        if (VERSION.SDK_INT >= 23) {
            this.achWebView.setOnContextClickListener($$Lambda$ChallengeAchievementActivity$YlLr3zCCpsJJRT1zwhQZF5XdLk.INSTANCE);
        }
    }

    private void onSharePressed() {
        ((ChallengesAnalyticsHelper) this.challengesAnalyticsHelper.get()).reportChallengeAchievementShareClicked(this.challengeName, this.achievement.getTitle(), Strings.notEmpty(this.achievement.getDetailsUrl()), this.challengeId);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.TEXT", this.achievement.getTwitterShare());
        intent.putExtra("android.intent.extra.SUBJECT", this.achievement.getEmailSubjectShare());
        intent.setType("text/plain");
        startActivity(intent);
    }
}
