package com.myfitnesspal.feature.progress.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.progress.constants.GalleryDataMode;
import com.myfitnesspal.feature.progress.constants.GalleryViewMode;
import com.myfitnesspal.feature.progress.service.ProgressAnalytics;
import com.myfitnesspal.feature.progress.service.ProgressCongratsService;
import com.myfitnesspal.feature.progress.service.ProgressCongratsService.GoalType;
import com.myfitnesspal.feature.progress.service.ProgressCongratsService.Message;
import com.myfitnesspal.feature.progress.service.ProgressCongratsService.MessageType;
import com.myfitnesspal.feature.progress.task.GenerateArtifactImageTask;
import com.myfitnesspal.feature.progress.task.GenerateArtifactImageTask.CompletedEvent;
import com.myfitnesspal.feature.progress.ui.activity.ProgressPhotosGalleryActivity.ToolbarCta;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.model.unitconv.LocalizedWeight;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.UnitsUtils.Weight;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.io.File;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

public class ProgressCongratsActivity extends MfpActivity {
    private static final String ERROR_TAG = "asset_image_failed";
    private static final String SHARE_FILENAME = "mfp-congrats-photos-share-artifact-%d.png";
    /* access modifiers changed from: private */
    public int analyticsMaxValue;
    /* access modifiers changed from: private */
    public MessageType analyticsMessageType;
    /* access modifiers changed from: private */
    public int analyticsValueToReport;
    @BindView(2131361955)
    TextView bodyHeader;
    @BindView(2131361956)
    TextView bodyText;
    @Inject
    Lazy<ProgressCongratsService> congratsService;
    @BindView(2131362729)
    View headerView;
    @BindView(2131363163)
    View notNowButton;
    @Inject
    Lazy<ProgressAnalytics> progressAnalytics;
    private String shareFilename;
    @BindView(2131363638)
    View sharePhotoButton;
    @Inject
    Lazy<UserWeightService> userWeightService;
    @BindView(2131364126)
    TextView valueSubtext;
    @BindView(2131364127)
    TextView valueText;

    public static Intent newStartIntent(Context context) {
        return new Intent(context, ProgressCongratsActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.progress_congrats_activity);
        component().inject(this);
        initEventHandlers();
        if (parseMessagesAndBindUi() && bundle == null) {
            ((ProgressAnalytics) this.progressAnalytics.get()).reportShareProgressInterstitialView(this.analyticsMessageType, this.analyticsValueToReport, this.analyticsMaxValue);
        }
    }

    public boolean backPressed() {
        markMessagesAndFinish();
        return true;
    }

    /* access modifiers changed from: private */
    public void markMessagesAndFinish() {
        for (Message markMessageConsumed : ((ProgressCongratsService) this.congratsService.get()).getPendingMessages()) {
            ((ProgressCongratsService) this.congratsService.get()).markMessageConsumed(markMessageConsumed);
        }
        finish();
    }

    private void initEventHandlers() {
        this.sharePhotoButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ProgressCongratsActivity.this.handleShareClickBasedOnRollout();
            }
        });
        this.notNowButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ProgressCongratsActivity.this.markMessagesAndFinish();
                ((ProgressAnalytics) ProgressCongratsActivity.this.progressAnalytics.get()).reportShareProgressInterstitialNegative(ProgressCongratsActivity.this.analyticsMessageType, ProgressCongratsActivity.this.analyticsValueToReport, ProgressCongratsActivity.this.analyticsMaxValue);
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleShareClickBasedOnRollout() {
        if (ConfigUtils.isProgressShareCongratsOn(getConfigService())) {
            generateCongratsArtifact();
            return;
        }
        getNavigationHelper().withIntent(ProgressPhotosGalleryActivity.newStartIntent(this, GalleryViewMode.Split, GalleryDataMode.Import, ToolbarCta.NextText)).startActivity(RequestCodes.PROGRESS_PHOTOS_GALLERY);
        reportEventAndFinish();
    }

    private void generateCongratsArtifact() {
        checkDeleteShareFile();
        String format = String.format(SHARE_FILENAME, new Object[]{Long.valueOf(System.currentTimeMillis())});
        this.shareFilename = GenerateArtifactImageTask.getOutputFilePath(this, format);
        new GenerateArtifactImageTask(format, this.headerView).run(getRunner());
    }

    @Subscribe
    public void onGenerateArtifactTaskCompleted(CompletedEvent completedEvent) {
        setBusy(false);
        if (completedEvent.successful()) {
            Uri fromFile = Uri.fromFile(new File((String) completedEvent.getResult()));
            getNavigationHelper().withIntent(ProgressPhotosGalleryActivity.newStartIntent(this, -1, fromFile, String.format("%1s %2s", new Object[]{this.valueText.getText().toString(), this.valueSubtext.getText().toString()}), GalleryViewMode.Single, GalleryDataMode.Import, ToolbarCta.NextText)).startActivity(RequestCodes.PROGRESS_PHOTOS_GALLERY);
            reportEventAndFinish();
            return;
        }
        showUnableToGenerateArtifactError();
    }

    private void reportEventAndFinish() {
        markMessagesAndFinish();
        ((ProgressAnalytics) this.progressAnalytics.get()).reportShareProgressInterstitialPositive(this.analyticsMessageType, this.analyticsValueToReport, this.analyticsMaxValue);
    }

    private void showUnableToGenerateArtifactError() {
        if (getSupportFragmentManager().findFragmentByTag(ERROR_TAG) == null) {
            showDialogFragment(((AlertDialogFragment) ((AlertDialogFragment) AlertDialogFragment.newInstance().setTitle(R.string.progress_photos)).setMessage((int) R.string.progress_photos_artifact_image_error)).setPositiveText(R.string.ok, null), ERROR_TAG);
        }
    }

    private void checkDeleteShareFile() {
        if (Strings.notEmpty(this.shareFilename)) {
            File file = new File(this.shareFilename);
            if (file.exists()) {
                file.delete();
            }
            this.shareFilename = null;
        }
    }

    private boolean parseMessagesAndBindUi() {
        List pendingMessages = ((ProgressCongratsService) this.congratsService.get()).getPendingMessages();
        if (CollectionUtils.isEmpty((Collection<?>) pendingMessages)) {
            Ln.e("started congrats fragment with no messages! finishing...", new Object[0]);
            finish();
            return false;
        }
        bindToUi((Message) pendingMessages.get(0));
        return true;
    }

    private void bindToUi(Message message) {
        if (message.getType() == MessageType.WeightPercentage) {
            bindPercentage((int) message.getValue());
        } else if (message.getType() == MessageType.WeightAbsolute) {
            bindAbsolute();
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("unknown message type ");
            sb.append(message.getType());
            throw new IllegalArgumentException(sb.toString());
        }
    }

    private Weight normalizeWeightUnit(Weight weight) {
        return weight == Weight.STONES_POUNDS ? Weight.STONES : weight;
    }

    private void bindAbsolute() {
        GoalType fromWeeklyChange = GoalType.fromWeeklyChange(((UserWeightService) this.userWeightService.get()).getGoalPerWeekWeight());
        Weight normalizeWeightUnit = normalizeWeightUnit(((UserWeightService) this.userWeightService.get()).getUserCurrentWeightUnit());
        int valueIncrement = (int) ((ProgressCongratsService) this.congratsService.get()).getValueIncrement(normalizeWeightUnit);
        int round = (int) Math.round(getTotalWeightLost(normalizeWeightUnit));
        String abbreviatedUnit = LocalizedWeight.getAbbreviatedUnit(this, normalizeWeightUnit);
        int i = round - (round % valueIncrement);
        int i2 = valueIncrement * 3;
        if (i >= i2) {
            this.bodyHeader.setText(R.string.progress_photos_congrats_body_header_value_inc3);
        } else if (i >= valueIncrement * 2) {
            this.bodyHeader.setText(R.string.progress_photos_congrats_body_header_value_inc2);
        } else {
            this.bodyHeader.setText(R.string.progress_photos_congrats_body_header_value_inc1);
        }
        this.bodyText.setText(R.string.progress_photos_congrats_body_text_value_inc3);
        int i3 = fromWeeklyChange == GoalType.Gain ? R.string.progress_photos_congrats_subheader_gain : R.string.progress_photos_congrats_subheader_loss;
        this.valueText.setText(String.valueOf(round));
        this.valueSubtext.setText(getString(i3, new Object[]{abbreviatedUnit}));
        setAnalyticsValues(MessageType.WeightAbsolute, i, i2);
    }

    private double getTotalWeightLost(Weight weight) {
        return LocalizedWeight.fromPounds((double) Math.round(Math.abs(((UserWeightService) this.userWeightService.get()).getCurrentWeightInPounds() - ((UserWeightService) this.userWeightService.get()).getStartingWeightInPounds()))).getValue(weight);
    }

    private void bindPercentage(int i) {
        int i2 = i - (i % 25);
        if (i2 >= 100) {
            this.bodyHeader.setText(R.string.progress_photos_congrats_body_header_pct_inc3);
            this.bodyText.setText(R.string.progress_photos_congrats_body_text_pct_inc3);
        } else if (i2 == 75) {
            this.bodyHeader.setText(R.string.progress_photos_congrats_body_header_pct_inc2);
            this.bodyText.setText(R.string.progress_photos_congrats_body_text_pct_inc2);
        } else {
            this.bodyHeader.setText(R.string.progress_photos_congrats_body_header_pct_inc1);
            this.bodyText.setText(R.string.progress_photos_congrats_body_text_pct_inc1);
        }
        this.valueText.setText(getString(R.string.progress_photos_congrats_header_percent, new Object[]{Integer.valueOf(i)}));
        this.valueSubtext.setText(R.string.progress_photos_congrats_subheader_percent);
        setAnalyticsValues(MessageType.WeightPercentage, i2, 100);
    }

    private void setAnalyticsValues(MessageType messageType, int i, int i2) {
        this.analyticsMessageType = messageType;
        this.analyticsValueToReport = i;
        this.analyticsMaxValue = i2;
    }
}
