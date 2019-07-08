package com.myfitnesspal.feature.home.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.ui.navigation.SharedIntents;
import dagger.Lazy;
import java.util.Calendar;
import javax.inject.Inject;

public class AppRatingDialogFragment extends CustomLayoutBaseDialogFragment {
    @Inject
    AnalyticsService analyticsService;
    @Inject
    Lazy<GlobalSettingsService> globalSettingsService;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    @Inject
    NavigationHelper navigationHelper;

    public static AppRatingDialogFragment newInstance() {
        AppRatingDialogFragment appRatingDialogFragment = new AppRatingDialogFragment();
        appRatingDialogFragment.setArguments(new Bundle());
        return appRatingDialogFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        ContextThemeWrapper dialogContextThemeWrapper = getDialogContextThemeWrapper();
        LinearLayout linearLayout = new LinearLayout(dialogContextThemeWrapper);
        View inflate = LayoutInflater.from(dialogContextThemeWrapper).inflate(R.layout.review_app, linearLayout);
        MfpAlertDialogBuilder mfpAlertDialogBuilder = new MfpAlertDialogBuilder(getActivity());
        mfpAlertDialogBuilder.setView(inflate).setCancelable(true);
        final AlertDialog create = mfpAlertDialogBuilder.create();
        linearLayout.findViewById(R.id.btnYes).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                AppRatingDialogFragment.this.navigationHelper.withContext(AppRatingDialogFragment.this.getActivity()).withIntent(SharedIntents.newUriIntent("market://details?id=com.myfitnesspal.android")).startActivity();
                ((GlobalSettingsService) AppRatingDialogFragment.this.globalSettingsService.get()).setUserHasReviewedApp(true);
                ((LocalSettingsService) AppRatingDialogFragment.this.localSettingsService.get()).setDontAskForReview(true);
                create.cancel();
            }
        });
        linearLayout.findViewById(R.id.btnNever).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ((LocalSettingsService) AppRatingDialogFragment.this.localSettingsService.get()).setDontAskForReview(true);
                create.cancel();
            }
        });
        linearLayout.findViewById(R.id.btnRemind).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ((LocalSettingsService) AppRatingDialogFragment.this.localSettingsService.get()).setAppInstallationDate(Long.valueOf(Calendar.getInstance().getTimeInMillis()));
                create.cancel();
            }
        });
        create.setOnShowListener(new OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                AppRatingDialogFragment.this.analyticsService.reportEvent(Events.REVIEW_APP_RATE_IT);
            }
        });
        return create;
    }
}
