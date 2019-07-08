package com.myfitnesspal.feature.debug.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.debug.util.DebugSettingsService;
import com.myfitnesspal.feature.diary.model.MealMacrosDisplayUnit;
import com.myfitnesspal.feature.premium.service.ProductService;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.uacf.UacfEmailVerificationManager;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.Toaster;
import com.uacf.core.util.Ln;
import java.util.Locale;
import javax.inject.Inject;

public class FlagResetActivity extends MfpActivity {
    @Inject
    AppSettings appSettings;
    @Inject
    DebugSettingsService debugSettingsService;
    @Inject
    UacfEmailVerificationManager emailVerificationManager;
    @Inject
    LocalSettingsService localSettingsService;
    @Inject
    ProductService productService;
    @Inject
    UserApplicationSettingsService userApplicationSettingsService;

    public static Intent newStartIntent(Context context) {
        return new Intent(context, FlagResetActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.flag_reset_activity);
        findViewById(R.id.resetAcceptedTerms).setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                FlagResetActivity.lambda$onCreate$0(FlagResetActivity.this, view);
            }
        });
        findViewById(R.id.resetMealMacrosTip).setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                FlagResetActivity.lambda$onCreate$1(FlagResetActivity.this, view);
            }
        });
        findViewById(R.id.resetMealGoalsTip).setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                FlagResetActivity.lambda$onCreate$2(FlagResetActivity.this, view);
            }
        });
        findViewById(R.id.resetMealGoalsPromoCard).setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                FlagResetActivity.lambda$onCreate$3(FlagResetActivity.this, view);
            }
        });
        findViewById(R.id.resetPremiumInterstitialShown).setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                FlagResetActivity.lambda$onCreate$4(FlagResetActivity.this, view);
            }
        });
        findViewById(R.id.resetBottomBarTipShown).setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                FlagResetActivity.lambda$onCreate$5(FlagResetActivity.this, view);
            }
        });
        findViewById(R.id.resetAppSettings).setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                FlagResetActivity.lambda$onCreate$6(FlagResetActivity.this, view);
            }
        });
        findViewById(R.id.toggleFreeTrialFlag).setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                FlagResetActivity.lambda$onCreate$7(FlagResetActivity.this, view);
            }
        });
        findViewById(R.id.resetChangePasswordFlag).setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                FlagResetActivity.lambda$onCreate$8(FlagResetActivity.this, view);
            }
        });
        findViewById(R.id.resetEmailVerificationAppLaunchCount).setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                FlagResetActivity.lambda$onCreate$9(FlagResetActivity.this, view);
            }
        });
        findViewById(R.id.resetEmailVerificationLastShownTimestamp).setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                FlagResetActivity.lambda$onCreate$10(FlagResetActivity.this, view);
            }
        });
        findById(R.id.resetUserSawTimestampFeature).setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                FlagResetActivity.this.localSettingsService.setUserSawTimestampFeature(false);
            }
        });
        findById(R.id.resetLoginStreakDays).setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                FlagResetActivity.lambda$onCreate$12(FlagResetActivity.this, view);
            }
        });
        findById(R.id.resetUserSeenPremiumCrownTooltip).setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                FlagResetActivity.lambda$onCreate$13(FlagResetActivity.this, view);
            }
        });
        findViewById(R.id.resetAlexaInterstialLogWaterSeen).setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                FlagResetActivity.this.userApplicationSettingsService.setDidSeeAlexaInterstitialForLogWater(false);
            }
        });
        findViewById(R.id.resetAlexaInterstialLogWeightSeen).setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                FlagResetActivity.this.userApplicationSettingsService.setDidSeeAlexaInterstitialForLogWeight(false);
            }
        });
        updateAcceptedTermsButtonText();
        updateFreeTrialButtonText();
    }

    public static /* synthetic */ void lambda$onCreate$0(FlagResetActivity flagResetActivity, View view) {
        flagResetActivity.getSession().getUser().resetTOSAcceptedVersion();
        flagResetActivity.updateAcceptedTermsButtonText();
    }

    public static /* synthetic */ void lambda$onCreate$1(FlagResetActivity flagResetActivity, View view) {
        flagResetActivity.localSettingsService.setShowMealMacrosTip(true);
        flagResetActivity.localSettingsService.setMealMacrosDisplayUnit(MealMacrosDisplayUnit.Percent);
        Toaster.showShort(flagResetActivity.getActivity(), "Meal Macro Flags reset. You should now see the tip in the Diary again and the unit as %");
    }

    public static /* synthetic */ void lambda$onCreate$2(FlagResetActivity flagResetActivity, View view) {
        flagResetActivity.localSettingsService.setShowMealGoalsTip(true);
        Toaster.showShort(flagResetActivity.getActivity(), "Meal Goals Flags reset. You should now see the tip in the Diary again");
    }

    public static /* synthetic */ void lambda$onCreate$3(FlagResetActivity flagResetActivity, View view) {
        flagResetActivity.userApplicationSettingsService.setMealGoalsBuyUpsellDismissed(false);
        Toaster.showShort(flagResetActivity.getActivity(), "Meal Goals Promo Card Flag reset. You should now see the meal goal promo card in the Diary again");
    }

    public static /* synthetic */ void lambda$onCreate$4(FlagResetActivity flagResetActivity, View view) {
        flagResetActivity.localSettingsService.setPremiumAdDisplayed(false);
        Toaster.showShort(flagResetActivity.getActivity(), "Flag reset. You should see the interstitial again when you go back to the home screen");
    }

    public static /* synthetic */ void lambda$onCreate$5(FlagResetActivity flagResetActivity, View view) {
        flagResetActivity.userApplicationSettingsService.setBottomBarOnboardingDismissed(false);
        Toaster.showShort(flagResetActivity.getActivity(), "Flag reset. You should see the bottom bar tip again");
    }

    public static /* synthetic */ void lambda$onCreate$6(FlagResetActivity flagResetActivity, View view) {
        flagResetActivity.appSettings.reset();
        Toaster.showShort(flagResetActivity.getActivity(), "AppSettings store reset.");
    }

    public static /* synthetic */ void lambda$onCreate$7(FlagResetActivity flagResetActivity, View view) {
        try {
            flagResetActivity.productService.setFreeTrialFlagAndCacheWebView(!flagResetActivity.productService.areFreeTrialsEnabled(), flagResetActivity.productService.refreshProductsIfCacheExpired());
        } catch (ApiException e) {
            Ln.e(e);
        }
        flagResetActivity.updateFreeTrialButtonText();
    }

    public static /* synthetic */ void lambda$onCreate$8(FlagResetActivity flagResetActivity, View view) {
        flagResetActivity.localSettingsService.setPasswordResetDateTime(DateTimeUtils.getDateTimeFromNow(12, -1));
        Toaster.showShort(flagResetActivity.getActivity(), "Change Password Reset.");
    }

    public static /* synthetic */ void lambda$onCreate$9(FlagResetActivity flagResetActivity, View view) {
        flagResetActivity.emailVerificationManager.resetAppLaunchCount();
        Toaster.showShort(flagResetActivity.getActivity(), "Email Verification App Launch Count Set Reset.");
    }

    public static /* synthetic */ void lambda$onCreate$10(FlagResetActivity flagResetActivity, View view) {
        flagResetActivity.emailVerificationManager.resetInterstitialShownTimestamp();
        Toaster.showShort(flagResetActivity.getActivity(), "Email Verification Last Shown Timestamp Reset.");
    }

    public static /* synthetic */ void lambda$onCreate$12(FlagResetActivity flagResetActivity, View view) {
        flagResetActivity.debugSettingsService.setLoginStreakDays(0);
        Toaster.showShort(flagResetActivity.getActivity(), "Login streak days param was erased.");
    }

    public static /* synthetic */ void lambda$onCreate$13(FlagResetActivity flagResetActivity, View view) {
        flagResetActivity.localSettingsService.setPremiumCrownTooltipShownTime(0);
        flagResetActivity.localSettingsService.setPremiumCrownTooltipShown(false);
    }

    private void updateFreeTrialButtonText() {
        ((Button) findViewById(R.id.toggleFreeTrialFlag)).setText(String.format("toggle free trial flag (current: %s)", new Object[]{String.valueOf(this.productService.areFreeTrialsEnabled())}));
    }

    private void updateAcceptedTermsButtonText() {
        User user = getSession().getUser();
        ((Button) findViewById(R.id.resetAcceptedTerms)).setText(String.format(Locale.ENGLISH, "reset accepted terms (current: %d, accepted: %d)", new Object[]{Integer.valueOf(user.getTOSCurrentVersion()), Integer.valueOf(user.getTOSAcceptedVersion())}));
    }
}
