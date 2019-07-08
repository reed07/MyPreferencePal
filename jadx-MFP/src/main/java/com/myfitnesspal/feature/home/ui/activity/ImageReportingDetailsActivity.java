package com.myfitnesspal.feature.home.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.home.service.ImageReportingService;
import com.myfitnesspal.feature.home.task.ReportImageTask;
import com.myfitnesspal.feature.home.task.ReportImageTask.CompletedEvent;
import com.myfitnesspal.feature.progress.service.ProgressAnalytics;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.v2.MfpV2ApiError;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import javax.inject.Inject;

public class ImageReportingDetailsActivity extends MfpActivity {
    private static final String ANALYTICS_DONT_LIKE = "dont_like";
    private static final String ANALYTICS_HATE_VIOLENCE = "hate";
    private static final String ANALYTICS_NOT_FOOD = "not_food";
    private static final String ANALYTICS_SEXUALLY_EXPLICIT = "sexual";
    private static final String ANALYTICS_SPAM_ADS = "spam";
    private static final String ERROR_ALREADY_REPORTED_MESSAGE = "Flag Already Exist";
    private static final int ERROR_ALREADY_REPORTED_STATUS_CODE = 422;
    private static final String REASON_DONT_LIKE = "I don't like this";
    private static final String REASON_HATE_VIOLENCE = "Hate speech or violent imagery";
    private static final String REASON_NOT_FOOD = "Not food";
    private static final String REASON_SEXUALLY_EXPLICIT = "Sexually explicit";
    private static final String REASON_SPAM_ADS = "Spam or advertising";
    @BindView(2131362025)
    Button btnReportImage;
    /* access modifiers changed from: private */
    public FlaggingTypes currentFlagging;
    /* access modifiers changed from: private */
    public String imageId;
    @Inject
    Lazy<ImageReportingService> imageReportingService;
    private Intent navigateTo;
    private String newsfeedId;
    @Inject
    Lazy<ProgressAnalytics> progressAnalytics;
    private int reasonId;
    private String reporteeUserId;
    private String reporterUserId;
    @BindView(2131363953)
    TextView tvDontAllow;
    @BindView(2131363971)
    TextView tvReasonType;
    @BindView(2131363972)
    TextView tvReasons;

    private enum FlaggingTypes {
        SEXUALLY_EXPLICIT(ImageReportingDetailsActivity.ANALYTICS_SEXUALLY_EXPLICIT, ImageReportingDetailsActivity.REASON_SEXUALLY_EXPLICIT, R.string.report_sexually_explicit, R.array.sexually_explicit_reasons_array),
        HATE_VIOLENCE(ImageReportingDetailsActivity.ANALYTICS_HATE_VIOLENCE, ImageReportingDetailsActivity.REASON_HATE_VIOLENCE, R.string.report_hate_violence, R.array.hate_violence_reasons_array),
        SPAM_ADS(ImageReportingDetailsActivity.ANALYTICS_SPAM_ADS, ImageReportingDetailsActivity.REASON_SPAM_ADS, R.string.report_spam_ads, R.array.spam_ads_reasons_array),
        DONT_LIKE(ImageReportingDetailsActivity.ANALYTICS_DONT_LIKE, ImageReportingDetailsActivity.REASON_DONT_LIKE, R.string.report_dont_like, -1),
        NOT_FOOD(ImageReportingDetailsActivity.ANALYTICS_NOT_FOOD, ImageReportingDetailsActivity.REASON_NOT_FOOD, R.string.report_not_food, -1);
        
        private String analyticsValue;
        private int reasonArrayResource;
        private String reasonStringForReporting;
        private int reasonTitleResource;

        private FlaggingTypes(String str, String str2, int i, int i2) {
            this.analyticsValue = str;
            this.reasonStringForReporting = str2;
            this.reasonTitleResource = i;
            this.reasonArrayResource = i2;
        }

        public String getAnalyticsValue() {
            return this.analyticsValue;
        }

        public String getReasonStringForReporting() {
            return this.reasonStringForReporting;
        }

        public int getReasonTitleResource() {
            return this.reasonTitleResource;
        }

        public int getReasonArrayResource() {
            return this.reasonArrayResource;
        }
    }

    public static Intent newStartIntent(Context context, int i, String str, String str2, String str3, Intent intent) {
        return new Intent(context, ImageReportingDetailsActivity.class).putExtra("image_id", str).putExtra(Extras.REASON_ID, i).putExtra(Extras.NEWSFEED_ID, str2).putExtra(Extras.REPORTEE_ID, str3).putExtra(Extras.NAVIGATE_TO, intent);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.image_reporting_details_activity);
        component().inject(this);
        ButterKnife.bind((Activity) this);
        Bundle extras = getIntent().getExtras();
        this.imageId = BundleUtils.getString(extras, "image_id");
        this.reasonId = BundleUtils.getInt(extras, Extras.REASON_ID);
        this.newsfeedId = BundleUtils.getString(extras, Extras.NEWSFEED_ID);
        this.reporteeUserId = BundleUtils.getString(extras, Extras.REPORTEE_ID);
        this.navigateTo = (Intent) BundleUtils.getParcelable(extras, Extras.NAVIGATE_TO, null, Intent.class.getClassLoader());
        this.reporterUserId = getSession().getUser().getUserId();
        this.currentFlagging = getCurrentFlagging();
        setupUI();
        setupListener();
    }

    private void setupUI() {
        this.tvReasonType.setText(getString(this.currentFlagging.getReasonTitleResource()));
        int reasonArrayResource = this.currentFlagging.getReasonArrayResource();
        if (reasonArrayResource == -1) {
            ViewUtils.setGone(this.tvDontAllow);
            this.tvReasons.setText(getString(Strings.isEmpty(this.newsfeedId) ? R.string.dont_like_generic_thank_you : R.string.dont_like_1));
            return;
        }
        String[] stringArray = getResources().getStringArray(reasonArrayResource);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stringArray.length; i++) {
            sb.append(stringArray[i]);
            if (i != stringArray.length - 1) {
                sb.append("\n");
            }
        }
        this.tvReasons.setText(sb.toString());
    }

    private void setupListener() {
        this.btnReportImage.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ImageReportingDetailsActivity.this.setBusy(true);
                new ReportImageTask(ImageReportingDetailsActivity.this.imageReportingService, true, ImageReportingDetailsActivity.this.currentFlagging.getReasonStringForReporting(), ImageReportingDetailsActivity.this.imageId).run(ImageReportingDetailsActivity.this.getRunner());
            }
        });
    }

    private void onImageReported(boolean z) {
        if (z) {
            reportImageReportedEvent();
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean("image_reported", true);
        if (this.navigateTo != null) {
            getNavigationHelper().withExtras(bundle).withIntent(this.navigateTo).startActivity();
        }
        setResult(-1, new Intent().putExtras(bundle));
        finish();
    }

    @Subscribe
    public void onReportImageTaskEvent(CompletedEvent completedEvent) {
        setBusy(false);
        if (completedEvent.getRunnerId() == getRunner().getId() && completedEvent.getFailure() == null) {
            onImageReported(true);
        } else {
            if (completedEvent.getFailure() != null && ((ApiException) completedEvent.getFailure()).getStatusCode() == 422) {
                MfpV2ApiError apiError = ((ApiException) completedEvent.getFailure()).getApiError();
                if (apiError != null && ERROR_ALREADY_REPORTED_MESSAGE.equals(apiError.getErrorDescription())) {
                    onImageReported(false);
                    return;
                }
            }
            postEvent(new AlertEvent(getString(R.string.generic_error_msg)));
        }
    }

    private void reportImageReportedEvent() {
        ((ProgressAnalytics) this.progressAnalytics.get()).reportImageReportedEvent(this.currentFlagging.getAnalyticsValue(), this.imageId, this.newsfeedId, this.reporterUserId, this.reporteeUserId);
    }

    private FlaggingTypes getCurrentFlagging() {
        switch (this.reasonId) {
            case R.id.tv_flagging_dont_like /*2131363955*/:
                return FlaggingTypes.DONT_LIKE;
            case R.id.tv_flagging_hate_violence /*2131363956*/:
                return FlaggingTypes.HATE_VIOLENCE;
            case R.id.tv_flagging_not_food /*2131363957*/:
                return FlaggingTypes.NOT_FOOD;
            case R.id.tv_flagging_sexually_explicit /*2131363958*/:
                return FlaggingTypes.SEXUALLY_EXPLICIT;
            case R.id.tv_flagging_spam_ads /*2131363959*/:
                return FlaggingTypes.SPAM_ADS;
            default:
                return FlaggingTypes.DONT_LIKE;
        }
    }
}
