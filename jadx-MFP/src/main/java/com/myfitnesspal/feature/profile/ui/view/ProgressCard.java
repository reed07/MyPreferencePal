package com.myfitnesspal.feature.profile.ui.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.profile.service.ProfileAggregatorService.OfflineData;
import com.myfitnesspal.feature.progress.ui.activity.ProgressActivity;
import com.myfitnesspal.shared.model.unitconv.LocalizedWeight;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.util.UnitsUtils.Weight;
import com.uacf.core.util.ViewUtils;

public class ProgressCard extends MeCardBase {
    private static final double WEIGHT_EQUALITY_FUZZ = 0.075d;
    private TextView currentWeight;
    private TextView goalWeight;
    private ProgressGraphView progressGraph;
    private TextView startingWeight;
    private TextView weightChange;

    public String getAnalyticsType() {
        return "progress";
    }

    /* access modifiers changed from: protected */
    public int getButtonTextId() {
        return R.string.me_card_button_add_weight;
    }

    /* access modifiers changed from: protected */
    public int getContentLayoutId() {
        return R.layout.me_card_progress;
    }

    /* access modifiers changed from: protected */
    public int getLeftBadgeLayoutId() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public int getRightBadgeLayoutId() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public int getTitleTextId() {
        return R.string.me_card_title_progress;
    }

    public ProgressCard(@NonNull Context context) {
        super(context);
    }

    public ProgressCard(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ProgressCard(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i) {
        super(context, attributeSet, i);
    }

    public void redraw(UserWeightService userWeightService, OfflineData offlineData) {
        double d;
        if (offlineData != null) {
            ViewUtils.setVisible(this.contentContainer);
            Context context = getContext();
            Weight userCurrentWeightUnit = userWeightService.getUserCurrentWeightUnit();
            this.startingWeight.setText(LocalizedWeight.getDisplayStringWithoutUnit(context, offlineData.getStartingWeight(), userCurrentWeightUnit));
            this.goalWeight.setText(LocalizedWeight.getDisplayStringWithoutUnit(context, offlineData.getGoalWeight(), userCurrentWeightUnit));
            String abbreviatedUnit = LocalizedWeight.getAbbreviatedUnit(context, userCurrentWeightUnit);
            boolean z = Math.abs(offlineData.getGoalWeight().toPounds() - offlineData.getStartingWeight().toPounds()) <= WEIGHT_EQUALITY_FUZZ;
            int i = R.string.me_card_progress_weight_lost;
            if (z) {
                double pounds = offlineData.getCurrentWeight().toPounds();
                double pounds2 = offlineData.getStartingWeight().toPounds();
                if (userWeightService.getGoalPerWeekWeight() >= BitmapDescriptorFactory.HUE_RED) {
                    d = Math.max(0.0d, pounds2 - pounds);
                } else {
                    d = Math.max(0.0d, pounds - pounds2);
                    i = R.string.me_card_progress_weight_gained;
                }
            } else {
                if (offlineData.getGoalWeight().isGreaterThan(offlineData.getStartingWeight())) {
                    i = R.string.me_card_progress_weight_gained;
                }
                d = offlineData.getWeightDelta().toPounds();
            }
            this.weightChange.setText(context.getString(i, new Object[]{LocalizedWeight.getDisplayStringWithoutUnit(context, LocalizedWeight.fromPounds(d), userCurrentWeightUnit), abbreviatedUnit}));
            this.currentWeight.setText(context.getString(R.string.me_card_progress_current_weight, new Object[]{LocalizedWeight.getDisplayString(context, offlineData.getCurrentWeight(), userCurrentWeightUnit)}));
            updateProgressGraph(userWeightService, offlineData);
            return;
        }
        ViewUtils.setInvisible(this.contentContainer);
    }

    /* access modifiers changed from: protected */
    public void onInflated() {
        this.startingWeight = (TextView) findViewById(R.id.startingWeight);
        this.goalWeight = (TextView) findViewById(R.id.goalWeight);
        this.currentWeight = (TextView) findViewById(R.id.currentWeight);
        this.weightChange = (TextView) findViewById(R.id.weightChange);
        this.progressGraph = (ProgressGraphView) findViewById(R.id.progressGraph);
        setOnContentViewClickListener(new OnClickListener() {
            public void onClick(View view) {
                ProgressCard.this.getNavigationHelper().withIntent(ProgressActivity.newStartIntent(ProgressCard.this.getContext(), null)).startActivity();
            }
        });
    }

    private void updateProgressGraph(UserWeightService userWeightService, OfflineData offlineData) {
        double pounds = offlineData.getGoalWeight().toPounds();
        double pounds2 = offlineData.getStartingWeight().toPounds();
        double pounds3 = offlineData.getCurrentWeight().toPounds();
        double abs = Math.abs(pounds - pounds2);
        double pounds4 = offlineData.getWeightDelta().toPounds();
        if (abs <= WEIGHT_EQUALITY_FUZZ) {
            float f = 1.0f;
            if (Math.abs(pounds3 - pounds) <= WEIGHT_EQUALITY_FUZZ) {
                this.progressGraph.setProgress(1.0f);
            } else if (userWeightService.getGoalPerWeekWeight() >= BitmapDescriptorFactory.HUE_RED) {
                ProgressGraphView progressGraphView = this.progressGraph;
                if (pounds3 > pounds) {
                    f = BitmapDescriptorFactory.HUE_RED;
                }
                progressGraphView.setProgress(f);
            } else {
                ProgressGraphView progressGraphView2 = this.progressGraph;
                if (pounds3 < pounds) {
                    f = BitmapDescriptorFactory.HUE_RED;
                }
                progressGraphView2.setProgress(f);
            }
        } else if (pounds4 <= 0.0d || abs <= 0.0d) {
            this.progressGraph.setProgress(BitmapDescriptorFactory.HUE_RED);
        } else {
            this.progressGraph.setProgress((float) (pounds4 / abs));
        }
    }
}
