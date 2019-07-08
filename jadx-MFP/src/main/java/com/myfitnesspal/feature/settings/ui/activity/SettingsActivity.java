package com.myfitnesspal.feature.settings.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.build.BuildConfiguration;
import com.myfitnesspal.feature.appgallery.ui.AppGalleryActivity;
import com.myfitnesspal.feature.appgallery.ui.OurOtherAppsActivity;
import com.myfitnesspal.feature.consents.util.ConsentsAnalyticsHelper;
import com.myfitnesspal.feature.debug.ui.activity.AdvancedDebuggingActivity;
import com.myfitnesspal.feature.deleteaccount.service.DeleteAccountAnalyticsHelper;
import com.myfitnesspal.feature.deleteaccount.ui.activity.DeleteAccountActivity;
import com.myfitnesspal.feature.deleteaccount.ui.activity.DeleteAccountActivity.ExportMode;
import com.myfitnesspal.feature.deleteaccount.ui.activity.DeleteAccountPremiumActivity;
import com.myfitnesspal.feature.diary.ui.activity.DiarySettingsActivity;
import com.myfitnesspal.feature.goals.ui.activity.Goals;
import com.myfitnesspal.feature.help.ui.activity.AboutUs;
import com.myfitnesspal.feature.help.ui.activity.DebugLogs;
import com.myfitnesspal.feature.help.ui.activity.Faq;
import com.myfitnesspal.feature.help.ui.activity.FeedbackActivity;
import com.myfitnesspal.feature.help.ui.activity.SamsungGearHelp;
import com.myfitnesspal.feature.payments.service.PaymentAnalyticsHelperImpl;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.ui.activity.PremiumUpsellActivity;
import com.myfitnesspal.feature.premium.ui.activity.SubscriptionStatus;
import com.myfitnesspal.feature.profile.analytics.MeAnalytics.CardType;
import com.myfitnesspal.feature.registration.ui.activity.LogoutActivity;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.Settings.App.URLs;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.ui.navigation.SharedIntents;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.SnackbarBuilder;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.Holder;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import com.uacf.sync.engine.UacfScheduleFailedInfo;
import com.uacf.sync.engine.UacfScheduleFinishedInfo;
import dagger.Lazy;
import javax.inject.Inject;

public class SettingsActivity extends MfpActivity {
    private static final String ANALYTICS_SCREEN_TAG = "MeSettings";
    private static final String ANALYTIC_EVENT_TAPPED = "me_settings_tapped";
    private static final String ANALYTIC_KEY_ROW_TYPE = "row_type";
    private static final String SHARE_URL = "https://play.google.com/store/apps/details?id=com.myfitnesspal.android";
    private static final String TAG_SELECTABLE = "selectable";
    /* access modifiers changed from: private */
    public static SparseArray<String> VIEW_TO_ANALYTICS = new SparseArray<>();
    @BindView(2131363085)
    ViewGroup accountCard;
    private BuildConfiguration build = new BuildConfiguration();
    @Inject
    Lazy<ConsentsAnalyticsHelper> consentsAnalyticsHelper;
    @Inject
    Lazy<DeleteAccountAnalyticsHelper> deleteAccountAnalyticsHelper;
    @BindView(2131362737)
    ViewGroup helpCard;
    private OnClickListener onRowClickedListener = new OnClickListener() {
        public void onClick(View view) {
            Intent intent;
            Activity activity = SettingsActivity.this.getActivity();
            NavigationHelper navigationHelper = SettingsActivity.this.getNavigationHelper();
            Session session = SettingsActivity.this.getSession();
            if (SettingsActivity.VIEW_TO_ANALYTICS.get(view.getId()) != null) {
                SettingsActivity.this.getAnalyticsService().reportEvent(SettingsActivity.ANALYTIC_EVENT_TAPPED, MapUtil.createMap(SettingsActivity.ANALYTIC_KEY_ROW_TYPE, (String) SettingsActivity.VIEW_TO_ANALYTICS.get(view.getId())));
            }
            switch (view.getId()) {
                case R.id.aboutUs /*2131361809*/:
                    navigationHelper.withIntent(AboutUs.newStartIntent(activity)).startActivity();
                    return;
                case R.id.advancedDebugging /*2131361883*/:
                    navigationHelper.withIntent(AdvancedDebuggingActivity.newStartIntent(activity)).startActivity();
                    return;
                case R.id.appsDevices /*2131361912*/:
                    navigationHelper.withIntent(AppGalleryActivity.newStartIntent(activity)).startActivity();
                    return;
                case R.id.betaFeedback /*2131361939*/:
                    navigationHelper.withIntent(FeedbackActivity.newStartIntentForBetaFeedback(activity)).startActivity();
                    return;
                case R.id.betaJoin /*2131361940*/:
                    navigationHelper.withIntent(SharedIntents.newUriIntent(URLs.BETA_SIGN_UP)).startActivity();
                    return;
                case R.id.changePassword /*2131362123*/:
                    navigationHelper.withIntent(ChangePasswordActivity.newStartIntent(activity)).startActivity();
                    return;
                case R.id.debugLogs /*2131362283*/:
                    navigationHelper.withIntent(DebugLogs.newStartIntent(activity)).startActivity();
                    return;
                case R.id.deleteAccount /*2131362288*/:
                    ((DeleteAccountAnalyticsHelper) SettingsActivity.this.deleteAccountAnalyticsHelper.get()).reportAccountDeleteStarted("", "settings");
                    if (((PremiumService) SettingsActivity.this.premiumService.get()).isPremiumSubscribed()) {
                        intent = DeleteAccountPremiumActivity.newStartIntent(activity, ExportMode.Disabled, "settings");
                    } else {
                        intent = DeleteAccountActivity.newStartIntent(activity, ExportMode.Disabled, "settings");
                    }
                    navigationHelper.withIntent(intent).startActivity();
                    return;
                case R.id.diarySettings /*2131362315*/:
                    SettingsActivity.this.getNavigationHelper().withIntent(DiarySettingsActivity.newStartIntent(SettingsActivity.this.getActivity(), "settings")).startActivity();
                    return;
                case R.id.editProfile /*2131362365*/:
                    navigationHelper.withIntent(EditProfile.newStartIntent(activity, session)).startActivity();
                    return;
                case R.id.faq /*2131362555*/:
                    navigationHelper.withIntent(Faq.newStartIntent(activity)).startActivity();
                    return;
                case R.id.logOut /*2131362957*/:
                    new MfpAlertDialogBuilder(SettingsActivity.this.getActivity()).setTitle((int) R.string.logout_title).setMessage((int) R.string.logout_message).setPositiveButton((int) R.string.yesBtn, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(activity) {
                        private final /* synthetic */ Activity f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void onClick(DialogInterface dialogInterface, int i) {
                            NavigationHelper.this.withIntent(LogoutActivity.newStartIntent(this.f$1)).startActivity();
                        }
                    }).setNegativeButton((int) R.string.noBtn, (DialogInterface.OnClickListener) $$Lambda$SettingsActivity$1$ekteflqNkESGiqZ4_1Jecrr1to.INSTANCE).show();
                    return;
                case R.id.myGoals /*2131363086*/:
                    navigationHelper.withIntent(Goals.newStartIntent(activity)).startActivity();
                    return;
                case R.id.ourOtherApps /*2131363228*/:
                    navigationHelper.withIntent(OurOtherAppsActivity.newStartIntent(activity)).startActivity();
                    return;
                case R.id.premiumSubscription /*2131363291*/:
                    if (((PremiumService) SettingsActivity.this.premiumService.get()).isPremiumSubscribed()) {
                        navigationHelper.withIntent(SubscriptionStatus.newStartIntent(activity)).startActivity();
                        return;
                    } else {
                        navigationHelper.withIntent(PremiumUpsellActivity.newStartIntent((Context) activity, PaymentAnalyticsHelperImpl.ANALYTICS_MENU_DRAWER)).startActivity();
                        return;
                    }
                case R.id.privacyCenter /*2131363306*/:
                    ((ConsentsAnalyticsHelper) SettingsActivity.this.consentsAnalyticsHelper.get()).reportPrivacyCenterSee();
                    navigationHelper.withIntent(PrivacyCenterActivity.newStartIntent(SettingsActivity.this)).startActivity();
                    return;
                case R.id.pushNotifications /*2131363352*/:
                    SettingsActivity.this.getNavigationHelper().withIntent(NotificationSettingsView.newStartIntent(activity)).startActivity();
                    return;
                case R.id.reminders /*2131363448*/:
                    navigationHelper.withIntent(RemindersActivity.newStartIntent(activity)).startActivity();
                    return;
                case R.id.samsungGearHelp /*2131363520*/:
                    navigationHelper.withIntent(SamsungGearHelp.newStartIntent(activity)).startActivity();
                    return;
                case R.id.steps /*2131363734*/:
                    navigationHelper.withIntent(StepsSettings.newStartIntent(activity)).startActivity();
                    return;
                case R.id.troubleshooting /*2131363887*/:
                    navigationHelper.withIntent(TroubleshootingActivity.newStartIntent(activity)).startActivity();
                    return;
                case R.id.weeklyNutrition /*2131364207*/:
                    navigationHelper.withIntent(WeeklyNutritionSettings.newStartIntent(activity)).startActivity();
                    return;
                default:
                    return;
            }
        }
    };
    @Inject
    Lazy<PremiumService> premiumService;
    @BindView(2131363621)
    ViewGroup settingsCard;
    @BindView(2131363635)
    View shareCard;
    private Animation syncAnimation;
    @BindView(2131363769)
    View syncButton;
    @BindView(2131363770)
    View syncIcon;
    @Inject
    Lazy<SyncService> syncService;
    private String syncToken = null;

    public String getAnalyticsScreenTag() {
        return ANALYTICS_SCREEN_TAG;
    }

    static {
        VIEW_TO_ANALYTICS.put(R.id.editProfile, "edit_profile");
        VIEW_TO_ANALYTICS.put(R.id.myGoals, "my_goals");
        VIEW_TO_ANALYTICS.put(R.id.premiumSubscription, "premium_subscription");
        VIEW_TO_ANALYTICS.put(R.id.appsDevices, CardType.APPS_DEVICES);
        VIEW_TO_ANALYTICS.put(R.id.deleteAccount, "delete_account");
        VIEW_TO_ANALYTICS.put(R.id.logOut, "log_out");
        VIEW_TO_ANALYTICS.put(R.id.changePassword, "change_password");
        VIEW_TO_ANALYTICS.put(R.id.diarySettings, Extras.DIARY_SETTINGS_PARENT);
        VIEW_TO_ANALYTICS.put(R.id.reminders, "reminders");
        VIEW_TO_ANALYTICS.put(R.id.privacyCenter, "privacy_center");
        VIEW_TO_ANALYTICS.put(R.id.weeklyNutrition, "weekly_nutrition_settings");
        VIEW_TO_ANALYTICS.put(R.id.steps, "steps");
        VIEW_TO_ANALYTICS.put(R.id.pushNotifications, "push_notifications");
        VIEW_TO_ANALYTICS.put(R.id.aboutUs, "about_us");
        VIEW_TO_ANALYTICS.put(R.id.ourOtherApps, "our_other_apps");
        VIEW_TO_ANALYTICS.put(R.id.betaJoin, "join_beta");
        VIEW_TO_ANALYTICS.put(R.id.betaFeedback, "beta_feedback");
        VIEW_TO_ANALYTICS.put(R.id.faq, "faq");
        VIEW_TO_ANALYTICS.put(R.id.troubleshooting, "troubleshooting");
        VIEW_TO_ANALYTICS.put(R.id.samsungGearHelp, "samsung_gear_help");
        VIEW_TO_ANALYTICS.put(R.id.debugLogs, "debug_logs");
        VIEW_TO_ANALYTICS.put(R.id.advancedDebugging, "advanced_debugging");
    }

    public static Intent newStartIntent(ConfigService configService, Context context) {
        if (ConfigUtils.isAppNavMeEnabled(configService)) {
            return new Intent(context, SettingsActivity.class);
        }
        return new Intent(context, LegacySettingsActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.settings_activity);
        hideOrShowDynamicRows();
        bindListeners();
    }

    @Subscribe
    public void onSyncFinished(UacfScheduleFinishedInfo<SyncType> uacfScheduleFinishedInfo) {
        clearSyncState(uacfScheduleFinishedInfo.getScheduleId(), uacfScheduleFinishedInfo.isSuccessful());
    }

    @Subscribe
    public void onSyncFailed(UacfScheduleFailedInfo<SyncType> uacfScheduleFailedInfo) {
        clearSyncState(uacfScheduleFailedInfo.getScheduleId(), false);
    }

    private void hideOrShowDynamicRows() {
        setRowVisibility(this.accountCard, R.id.premiumSubscription, ((PremiumService) this.premiumService.get()).isPremiumAvailable());
        boolean z = false;
        setRowVisibility(this.helpCard, R.id.advancedDebugging, this.build.isDebug() || this.build.isQABuild());
        ViewGroup viewGroup = this.helpCard;
        if (!this.build.isBetaBuild() && ConfigUtils.isBetaSignupProgramEnabled(getConfigService())) {
            z = true;
        }
        setRowVisibility(viewGroup, R.id.betaJoin, z);
        setRowVisibility(this.helpCard, R.id.betaFeedback, this.build.isBetaBuild());
        setRowVisibility(this.settingsCard, R.id.privacyCenter, true);
        setRowVisibility(this.accountCard, R.id.changePassword, ConfigUtils.isChangePasswordSettingsEnabled(getConfigService()));
    }

    private void setRowVisibility(ViewGroup viewGroup, int i, boolean z) {
        View findViewById = findViewById(i);
        int indexOfChild = viewGroup.indexOfChild(findViewById);
        ViewUtils.setVisible(z, findViewById);
        int i2 = indexOfChild + 1;
        if (viewGroup.getChildCount() > i2) {
            ViewUtils.setVisible(z, viewGroup.getChildAt(i2));
        }
    }

    private void bindListeners() {
        this.syncButton.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                SettingsActivity.this.scheduleSyncAndAnimateIcon();
            }
        });
        this.shareCard.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                SettingsActivity.this.shareApp();
            }
        });
        bindListeners(this.accountCard);
        bindListeners(this.settingsCard);
        bindListeners(this.helpCard);
    }

    /* access modifiers changed from: private */
    public void shareApp() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.share_message));
        sb.append(" ");
        sb.append(SHARE_URL);
        intent.putExtra("android.intent.extra.TEXT", sb.toString());
        startActivity(Intent.createChooser(intent, getString(R.string.share)));
    }

    private void clearSyncState(String str, boolean z) {
        if (Strings.equals(str, this.syncToken)) {
            this.syncToken = null;
            if (this.syncAnimation != null) {
                this.syncIcon.setAnimation(null);
                this.syncAnimation.cancel();
                this.syncAnimation = null;
            }
            this.syncButton.setEnabled(true);
            Holder holder = new Holder();
            holder.setValue(new SnackbarBuilder(findViewById(16908290)).setAction((int) R.string.ok, (OnClickListener) new OnClickListener() {
                public final void onClick(View view) {
                    ((Snackbar) Holder.this.getValue()).dismiss();
                }
            }).setMessage(z ? R.string.settings_sync_finished : R.string.settings_sync_failed).setDuration(0).setBackgroundColorResId(z ? 0 : R.color.red_light).setActionTextColorResId(z ? 0 : R.color.white).build());
            ((Snackbar) holder.getValue()).show();
        }
    }

    /* access modifiers changed from: private */
    public void scheduleSyncAndAnimateIcon() {
        if (Strings.isEmpty(this.syncToken)) {
            this.syncToken = ((SyncService) this.syncService.get()).enqueue(SyncType.Incremental);
            this.syncAnimation = AnimationUtils.loadAnimation(this, R.anim.sync_rotate_360);
            this.syncIcon.startAnimation(this.syncAnimation);
            this.syncButton.setEnabled(false);
        }
    }

    private void bindListeners(ViewGroup viewGroup) {
        for (TextView textView : ViewUtils.findByType(viewGroup, TextView.class)) {
            if (TAG_SELECTABLE.equals(textView.getTag())) {
                textView.setOnClickListener(this.onRowClickedListener);
            }
        }
    }
}
