package com.myfitnesspal.feature.home.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.ViewUtils;

public class ImageReportingActivity extends MfpActivity {
    public static final int VARIANT_MEAL_PHOTOS = 2;
    public static final int VARIANT_PROGRESS_PHOTOS = 1;
    /* access modifiers changed from: private */
    public String imageId;
    private String mealId;
    private String mealVersion;
    /* access modifiers changed from: private */
    public Intent navigateToOnReported;
    /* access modifiers changed from: private */
    public String newsfeedId;
    private OnClickListener onClickListener;
    /* access modifiers changed from: private */
    public String reporteeUserId;
    @BindView(2131363955)
    View tvFlaggingDontLike;
    @BindView(2131363956)
    View tvFlaggingHateViolence;
    @BindView(2131363957)
    View tvFlaggingNotFood;
    @BindView(2131363958)
    View tvFlaggingSexuallyExplicit;
    @BindView(2131363959)
    View tvFlaggingSpamAds;

    public static Intent newStartIntent(Context context, String str, String str2, String str3, int i, Intent intent) {
        return new Intent(context, ImageReportingActivity.class).putExtra("image_id", str).putExtra(Extras.NEWSFEED_ID, str2).putExtra(Extras.REPORTEE_ID, str3).putExtra(Extras.VARIANT_ID, i).putExtra(Extras.NAVIGATE_TO, intent);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.image_reporting_activity);
        component().inject(this);
        ButterKnife.bind((Activity) this);
        Bundle extras = getIntent().getExtras();
        this.imageId = BundleUtils.getString(extras, "image_id");
        this.newsfeedId = BundleUtils.getString(extras, Extras.NEWSFEED_ID);
        this.reporteeUserId = BundleUtils.getString(extras, Extras.REPORTEE_ID);
        this.navigateToOnReported = (Intent) BundleUtils.getParcelable(extras, Extras.NAVIGATE_TO, null, Intent.class.getClassLoader());
        ViewUtils.setGone(BundleUtils.getInt(extras, Extras.VARIANT_ID, 1) == 2 ? this.tvFlaggingDontLike : this.tvFlaggingNotFood);
        setupListeners();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 190 && i2 == -1) {
            Intent intent2 = null;
            if (intent != null) {
                intent2 = new Intent().putExtras(intent.getExtras());
            }
            setResult(-1, intent2);
            finish();
        }
        super.onActivityResult(i, i2, intent);
    }

    private void setupListeners() {
        this.onClickListener = new OnClickListener() {
            public void onClick(View view) {
                ImageReportingActivity.this.getNavigationHelper().withIntent(ImageReportingDetailsActivity.newStartIntent(ImageReportingActivity.this, view.getId(), ImageReportingActivity.this.imageId, ImageReportingActivity.this.newsfeedId, ImageReportingActivity.this.reporteeUserId, ImageReportingActivity.this.navigateToOnReported)).startActivity(190);
            }
        };
        this.tvFlaggingSexuallyExplicit.setOnClickListener(this.onClickListener);
        this.tvFlaggingHateViolence.setOnClickListener(this.onClickListener);
        this.tvFlaggingSpamAds.setOnClickListener(this.onClickListener);
        this.tvFlaggingDontLike.setOnClickListener(this.onClickListener);
        this.tvFlaggingNotFood.setOnClickListener(this.onClickListener);
    }
}
