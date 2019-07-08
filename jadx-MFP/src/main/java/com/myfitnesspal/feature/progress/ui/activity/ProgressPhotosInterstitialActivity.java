package com.myfitnesspal.feature.progress.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.progress.constants.ImportDevice;
import com.myfitnesspal.feature.progress.constants.ProgressEntryPoint;
import com.myfitnesspal.feature.progress.service.ProgressAnalytics;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import dagger.Lazy;
import javax.inject.Inject;

public class ProgressPhotosInterstitialActivity extends MfpActivity {
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    @BindView(2131363212)
    View okButton;
    @Inject
    Lazy<ProgressAnalytics> progressAnalytics;

    public static Intent newStartIntent(Context context) {
        return new Intent(context, ProgressPhotosInterstitialActivity.class);
    }

    public boolean backPressed() {
        navigateToWeightPickerAndFinish();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        if (bundle == null) {
            ((ProgressAnalytics) this.progressAnalytics.get()).reportProgressPhotosIntroInterstitialView();
        }
        setContentView((int) R.layout.progress_photos_interstitial_activity);
        this.okButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ((ProgressAnalytics) ProgressPhotosInterstitialActivity.this.progressAnalytics.get()).reportProgressPhotosIntroInterstitialPositive();
                ProgressPhotosInterstitialActivity.this.navigateToWeightPickerAndFinish();
            }
        });
        ((LocalSettingsService) this.localSettingsService.get()).setProgressPhotosIntroDisplayed(true);
    }

    /* access modifiers changed from: private */
    public void navigateToWeightPickerAndFinish() {
        getNavigationHelper().finishActivityAfterNavigation().withNoAnimations().withIntent(AddWeightActivity.newStartIntent(this, ProgressEntryPoint.Interstitial, ImportDevice.None)).startActivity();
    }
}
