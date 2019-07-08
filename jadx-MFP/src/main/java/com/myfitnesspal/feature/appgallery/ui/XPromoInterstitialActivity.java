package com.myfitnesspal.feature.appgallery.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.appgallery.api.ExerciseTrackingAppRecommendation;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.uacf.core.util.ExtrasUtils;

public class XPromoInterstitialActivity extends MfpActivity {
    public static Intent newStartIntent(Context context, ExerciseTrackingAppRecommendation exerciseTrackingAppRecommendation) {
        return new Intent(context, XPromoInterstitialActivity.class).putExtra(Extras.PARTNER_APP, exerciseTrackingAppRecommendation);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.fragment_container);
        component().inject(this);
        if (bundle == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, XPromoInterstitialFragment.newInstance((ExerciseTrackingAppRecommendation) ExtrasUtils.getParcelable(getIntent(), Extras.PARTNER_APP, ExerciseTrackingAppRecommendation.class.getClassLoader())), XPromoInterstitialFragment.class.getName()).commit();
        }
    }
}
